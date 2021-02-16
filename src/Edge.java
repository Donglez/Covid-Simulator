public class Edge 
{
    public Node a;
    public Node b;
    public boolean traveled;
    
    public Edge(Node a, Node b)
    {
        this.a = a;
        this.b = b;
    }
    
    //returns true if one edge has the exact same nodes as another (direction does not matter)
    public boolean equals(Edge e)
    {
        return (this.a.equals(e.a) && this.b.equals(e.b)) || (this.a.equals(e.b) && this.b.equals(e.a));
    }
    
    public String toString()
    {
        return "(" + a + ", " + b + ")";
    }

}
