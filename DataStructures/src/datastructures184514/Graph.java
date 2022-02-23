/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures184514;
import java.util.ArrayList;
import java.util.HashSet;
/**
 * Class representation of a graph following the GraphADT interface provided
 * @author Bayley
 * @version 07/05/19
 */
public class Graph implements GraphADT
{
    private ArrayList<Vertex> vertexList;
    private ArrayList<Edge> edgeList;
    private int numOfVertex, numOfEdge;
    /**
     * Constructs a new graph object. 
     * Constructor takes a ArrayList of vertices and a ArrayList of edges and 
     * initialises the graph. 
     * @param vertices a list of vertices
     * @param edges a list of edges
     */
    public Graph(ArrayList<Vertex> vertices, ArrayList<Edge> edges)
    {
        vertexList = vertices;
        edgeList = edges;
        
        numOfEdge = edgeList.size();
        numOfVertex = vertexList.size();
        
        
        buildIncidenceSequences();         
    }
    /*
     * Constructs a new graph object
     * Constructor takes no parameters, and initialises the graph, along with
     * incident edges.
     * As no edges or vertcies given the count of verticies and edges is set as 0
    */
    public Graph() {
        vertexList = new ArrayList<Vertex>();
        edgeList = new ArrayList<Edge>();
        
        numOfEdge = 0;
        numOfVertex = 0;
        
        buildIncidenceSequences();
    }
    /**
     * Insert a vertex into the graph.
     * @param v Vertex
     */
    public Vertex insertVertex(String n) 
    {
        Vertex v = new Vertex(n);
        vertexList.add(v);
        numOfVertex++;
        return v;
    }
    
    /**
     * Remove a vertex from the graph. 
     * Vertex name is returned, or null if the 
     * graph does not contain the vertex.
     * @param v Vertex
     * @return String
     */
    public String removeVertex(Vertex v)
    {
        String name = null;
        if ( vertexList.remove(v) )
            name = v.getName();
            ArrayList<Edge> iSeq = v.getIncidentEdges();
            for( Edge e : iSeq ) { // must also remove any edges incident to v!
                removeEdge(e);
            }
        numOfVertex--;
        return name;
    }
    
    /**
     * Build a new edge with end vertices u and v and weight w and insert into the graph.
     * NB: The vertices u and v must already be in the graph: I have ignored checking of error conditions 
     * for simplicity.
     * @param u Vertex
     * @param v Vertex
     * @param w the weight of the new edge
     */
    public Edge insertEdge(Vertex u, Vertex v, String n)
    {
        Edge e = new Edge(u,v,n);
        edgeList.add(e);
        incidenceSeqAdd(e);
        numOfEdge++;
        return e;
    }
    
    /**
     * Remove the edge e from the graph. The name of the old edge is returned (or null if the edge is not
     * in the graph).
     * @param e the edge to be removed
     * @return int
     */
    public String removeEdge(Edge e)
    {
        String old =  null;
        if ( edgeList.remove(e) )  
        { 
            old = e.getName();
            incidenceSeqRemove(e); 
            numOfEdge--;
            
        }
        return old;  
    }
    
      /**
     * Find and return the vertex opposite v in edge e.
     * @param e an edge
     * @param w a vertex
     * @return a vertex 
     */
    public Vertex opposite(Edge e, Vertex w)
    {
        Vertex opposite = null;
        Vertex u = e.getVertexU();
        Vertex v = e.getVertexV();
        if (w == v) 
        {
            opposite = u;
        } 
        else if (w == u) 
        {
            opposite = v;
        }
        return opposite;
    }

    /**
     * Return a collection containing of all of the vertices in the graph.
     * @return ArrayList<Vertex>
     */
    public ArrayList<Vertex> vertices()
    {
        return vertexList;
    }

    /**
     * Return a collection of all of the edges in the graph.
     * @return ArrayList<Edge>
     */
    public ArrayList<Edge> edges()
    {
        return edgeList;
    }       

    /**
     * Checks whether two vertices are adjacent (i.e. joined by a single edge) or not.
     * @param v a vertex
     * @param w a vertex
     * @return boolean
     */
    public boolean areAdjacent(Vertex v, Vertex w)
    {
        return (v.valencyOfVertex() < w.valencyOfVertex()) ? v.adjacencyCheck(w) : w.adjacencyCheck(v);            
    }

    /**
     * Finds and returns the set of edges that are incident to a given vertex. 
     * @param v the vertex
     * @return ArrayList<Edge>
     */
    public ArrayList<Edge> incidentEdges(Vertex v)
    {   
        return vertexList.contains(v) ? v.getIncidentEdges() : null;
    }

    /**
     * Rename vertex v as n
     * @param v a vertex
     * @param n the new name
     */
    public String rename(Vertex v, String n)
    {
        String old = v.getName();
        v.setName(n);
        return old;
    }    

    /**
     * Rename edge e as n
     * @param e an edge
     * @param n the new name
     */
    public String rename(Edge e, String n)
    {
        String old = e.getName();
        e.setName(n);
        return old;
    }
    /*
     * Construct the edge incidence sequences for each vertex.
     * Creates a Incident list for each vertex, then adds the edges incident to 
     * vertex from the list iof edges.
     */
    private void buildIncidenceSequences()
    {
        for( Vertex v : vertexList ) {          
            v.createIncidenceSeq();
        }
        
        for( Edge e : edgeList ) {
            incidenceSeqAdd(e);
        }
    }
    
    /*
     * Adds edge e to incidence sequences of endpoints.
     * Used when adding edges to the incident edge list
     */
    private void incidenceSeqAdd(Edge e)
    {
        Vertex u = e.getVertexU();
        u.insertIncidentEdge(e);
        Vertex v = e.getVertexV();
        v.insertIncidentEdge(e);
    }
    
    /* 
     * Removes edge e from incidence sequences of vertex u and v 
     */
    private void incidenceSeqRemove(Edge e)
    {
        e.getVertexU().removeIncidentEdge(e);
        e.getVertexV().removeIncidentEdge(e);
    }

    /*
     * Perform a breadth-first search traversal of a graph that contains vertex v
     * Implementation of the pseudo provided in the lecture
     * It marks vertices and edges as visited once visited, traversed or cross edges
     * Each visited vertex is annotated with a reference to the vertex visited immediately prior to it in the traversal.
     * Helps with shortest path method later on.
     * 
     * @param v Staring vertex
     */
    public void bftraverse(Vertex v) 
    {
        ArrayList<HashSet<Vertex>> level = new ArrayList<HashSet<Vertex>>();
        level.add(0,new HashSet<Vertex>());
        level.get(0).add(v);
        v.setAsVisited();
        
        int index = 0;
        while ( level.get(index).size() != 0 ) 
        {
            level.add(index+1,new HashSet<Vertex>());
            for( Vertex u : level.get(index) ) 
            {  
                ArrayList<Edge> incidentEdges = incidentEdges(u);
                for( Edge edge : incidentEdges ) 
                {
                    if ( !edge.visitCheck() ) 
                    {
                        Vertex z = opposite(edge,u);
                        if ( !z.isVisited() ) 
                        {
                            edge.setAsVisited();
                            level.get(index+1).add(z);
                            z.setFollowed(edge);
                            z.setAsVisited(); 
                            
                        } 
                        else 
                        {
                            edge.setAsCrossEdge();
                        }
                    }
                }
            }
            index = index+1;
        }
    }
        /**
     * Return a list of all of the vertices reachable from the given vertex V. 
     * Uses BFS to see which nodes are visited, so therefore reachable
     * 
     * @param Vertex v
     * @return an array list of vertices of all the vertices reached
     */
    public ArrayList<Vertex> allReachable(Vertex v)
    {
        ArrayList<Vertex> verticiesReached = new ArrayList<Vertex>();
        reset();
        bftraverse(v);
        for ( Vertex u : vertexList ) 
        {
            if ( u.isVisited() ) 
            {
                verticiesReached.add(v);
            }
        }
        return  verticiesReached;
    }
    /**
     * Perform a breadth-first search traversal of the graph. 
     * It works by iterating through the graph vertices and carrying out a bfs traversal for each unvisited vertex.
     * This method has no parameters so starts on the first vertex of the vertex list
     */
    public void bftraverse()
    {
        reset();
        for( Vertex v : vertexList ) 
        { 
            if (!v.isVisited()) 
            {
                bftraverse(v);
            }
        }
    }   
    /**
     * Checks whether the whole graph is connected.
     * Performs BFS of a graph and checks that all vertices have been visited.
     * If there exists at least on unvisited vertex list the graph is not
     * connected would return false
     * 
     * @return true if the graph is connected, false if not connected
     */
    public boolean allConnected()
    {
        if (!vertexList.isEmpty() ) 
        {
            reset();
            bftraverse(vertexList.get(0));
            for ( Vertex u : vertexList ) 
            {
                if (!u.isVisited()) 
                {
                    return false;
                }
            }
        }
        return true;
    }
    /**
     * BFS used to find the most direct route between u and v vertices given. 
     * Reconstructs  the followed edge references, from the destination vertex v from u.
     * The path itself is represented as an ArrayList of Edge objects. 
     * Will return null if no path between u and v
     * 
     * @param u start vertex
     * @param v end vertex
     * @return an ArrayList of edges
     */
    public ArrayList<Edge> mostDirectRoute(Vertex u, Vertex v)
    {
        reset();
        bftraverse(u); 
        // no path between u and v is v is not visited a stated above
        if (!v.isVisited()) 
        { 
            return null;
        }
        
        ArrayList<Edge> route = new ArrayList<Edge>();
        Vertex o = v;
        Edge followed = null;
        while( (followed = o.getFollowed()) != null) 
        {
            route.add(followed);
            o = opposite(followed, o);
        }
        return route;  
    }

    /**
     * Reset the graph by 'unvisiting' (i.e. removing markings from) the vertices and edges. Called before 
     * fresh breadth-first traversal is carried out to ensure correct execution.
     */
    public void reset()
    {
        for(Vertex v : vertexList) {
            v.unvisitVertex();
        }
        for(Edge e : edgeList) {
            e.unvisitEdge();
        }
    } 
}
