

package datastructures184514;
import java.util.ArrayList;
/**
 * Class Representation of a vertex of a graph
 *
 * @author Bayley
 * @version 07/05/19
 */
public class Vertex
{
    private String n,l;
    private Edge f;
    private ArrayList<Edge> inSeq;
    /**
     * Construct a new vertex
     * 
     * @param name the name of the vertex
     */
    public Vertex(String name)
    {
        n = name;
        //sets the visited and follow to false
        l=null;
        f=null;
        //creates the arrayList of the edges to represent the incident sequence connected to the vertex, sets to null
       createIncidenceSeq(); 
    }
    
    /**
     * Gets name of the vertex
     * 
     * @return name of the vertex
     */
    public String getName()
    {
        return n;
    }
    
    /**
     * Sets the name of the vertex
     * 
     * @param name the vertex name
     */
    public void setName(String name)
    {
        n = name;
    }
    /**
     * Creates the incidence sequence connected with the vertex
     * Sets the array List to an empty sequence
     * This is because no edges at the moment are incident to the vertex
     */
    protected void createIncidenceSeq()
    {
        inSeq = new ArrayList<Edge>();
    }
    /**
     * Inserts an edge to the incidence sequence array list.
     * @param e an edge
     */
    public void insertIncidentEdge(Edge e)
    {
        inSeq.add(e); 
    }
    /**
     * Removes an edge from the incidence sequence array list. 
     * will return true if the edge is removed 
     * will return false if the edge is not incident to the vertex.
     * @param e an edge
     * @return boolean true or false
     */
    public boolean removeIncidentEdge(Edge e)
    {  
        return inSeq.remove(e); 
    }  
    /**
     * Retrieves and returns an array list of edges 'incident' to the vertex
     * @return ArrayList<Edge>
     */
    public ArrayList<Edge> getIncidentEdges()
    {
        return inSeq;
    }
    /**
     * Returns the valency of the vertex i.e. the number of edges incident to the vertex
     * @return int
     */
    public int valencyOfVertex()
    {
        return inSeq.size();
    }
    
    /**
     * Mark a vertex as visited. 
     * Used for traversing to allow for keeping a note of visited vertexes
     */
    protected void setAsVisited()
    {
        l = "visited";
    }
    
    /**
     * Check whether a vertex has been visited.
     * Helpful when traversing a graph, as can check whether a vertex has been visited.
     * 
     * @return true / false
     */
    protected boolean isVisited() 
    {
        return l != null;
    }
     /** Get followed edge. 
     * 
     * @return Edge
     */
    
    protected Edge getFollowed()
    {
        return f;
    }   
    /**
     * Sets the followed edge. 
     * Used to record the edge that was traversed in order to reach the vertex
     * Used in my implementation of BFS traversal
     * 
     * @param f the edge traversed to reach vertex in a BFS traversal.
     */
    
    protected void setFollowed(Edge f)
    {
        this.f = f;
    }
    

    
    /**
     * Un-visits a vertex. 
     * Used to reset the vertex to unvisited
     * Helpful and used before carrying out graph traversal i.e. BFS traversal
     */
    protected void unvisitVertex()
    {
        l = null;
        f = null;
    }
    /**
     * Checks whether the vertex is adjacent to vertex z
     * @param z a vertex
     * @return boolean
     */
    public boolean adjacencyCheck(Vertex z) 
    {
        for ( Edge e : inSeq ) {
            if ( e.getVertexU() == z || e.getVertexV() == z) {
                return true;
            }
        }
        return false;
    }
}
