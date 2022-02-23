
package datastructures184514;

/**
 * Class representation of an edge for a graph
 * 
 * @author Bayley
 * @version 07/05/2019
 */
public class Edge  
{
    private Vertex u, v;
    private String name;
    private String l = null;
    
    private final String V;
    private final String CROSSEDGE;

    /**
     * Construct a new edge with endpoints u and v and name name.
     * 
     * @param u vertex u
     * @param v vertex v
     * @param name name of vertex
     */
    public Edge(Vertex u, Vertex v, String name)
    {
        this.u = u;
        this.v = v;
        this.name = name;
        V = "visited";
        CROSSEDGE = "crossedge";
    }
    
    /**
     * Get vertex u.
     * 
     * @return Vertex
     */
    public Vertex getVertexU()
    {
        return u;
    }
    
    /**
     * Get vertex v.
     * 
     * @return Vertex
     */
    public Vertex getVertexV()
    {
        return v;
    } 
    /**
     * Get name of edge.
     * 
     * @return String
     */
    public String getName()
    {
        return name;
    }
    /**
     * Set name of edge.
     * 
     * @param name name of edge
     */
    public void setName(String name)
    {
        this.name = name;
    }
    
    /**
     * Set an edge as visited /traversed. 
     * Helps keep track of the edges that have been visited.
     * Used within graph traversal.
     */
    protected void setAsVisited()
    {
        l = V;
    }
    
    /**
     * Set an edge as a cross-edge. 
     * Used to keep track of visited nodes in graph traversals. 
     */
    protected void setAsCrossEdge()
    {
        l = CROSSEDGE;
    }
    
    /**
     * Checks if an edge has been visited or not. Used during graph traversals.
     * 
     * @return true or false
     */
    protected boolean visitCheck()
    {
        return l != null;
    }
    
    /**
     * Checks if an edge has been traversed. 
     * Helpful in graph traversal.
     * Returns true if edge has been traversed and false if it has not been.
     * @return true or false
     */
    protected boolean traverseCheck()
    {
        return V.equals(l);
    }
    
    /**
     * Checks if an edge has been set as a cross-edge. 
     * helpful in during graph traversal.
     * 
     * @ return true or false
     */
    protected boolean crossedgeCheck()
    {
        return CROSSEDGE.equals(l);
    }

    /**
     * Un-visits an edge. Reset the edge label to null which means unvisited. 
     * Used  to reset a graph, which is needed before a traversal.
     */
    protected void unvisitEdge()
    {
        l = null;
    }
    /**
     * Get the index of the edge in the the incidence sequence related to vertex v. 
     * If edge is not incident of v, will return -1
     * @param v - a vertex
     * @return int
     */
    public int getIndex(Vertex v)
    {
        return v.getIncidentEdges().indexOf(this);
    }
    
}
