
import java.awt.Graphics;
import java.util.ArrayList;
public class Graph 
{
    private ArrayList<Edge> edges;
    private ArrayList<Node> nodes;
    private int N;
    public Graph()
    {
        edges = new ArrayList<>();
        nodes = new ArrayList<>();
    }
    
    public void populateSmallWorld(double beta, int K, int N)
    {
        //creating regular lattice
        this.N = N;
        for(int i = 0; i < N; i++)
        {
            //nodes.add(new Node(i));
            for(int j = i+1; j < N; j++)
            {
                if(Math.abs(i-j)%(N - K/2) <= K/2 && i!=j)
                {
                    
                    edges.add(new Edge(new Node(i), new Node(j)));
                }
            }
        }
        //rewiring according to beta
        for(int i = 0; i < N; i++)
        {
            for(int j = i + 1; j < i + K/2 + 1; j++)
            {
                if(Math.random() < beta)
                {
                    int k = (int)(Math.random()*(N+1));
                    replaceEdge(new Edge(new Node(i), new Node(j%N)), new Edge(new Node(i), new Node(k)));
                }
            }
        }
    }
    
    public void convertSmallWorld(double beta, int K, int N)
    {
        this.N = N;
        for(int i = 0; i < N; i++)
        {
            //nodes.add(new Node(i));
            for(int j = i+1; j < N; j++)
            {
                if(Math.abs(i-j)%(N - K/2) <= K/2 && i!=j)
                {
                    
                    edges.add(new Edge(nodes.get(i), nodes.get(j)));
                }
            }
        }
        //rewiring according to beta
        for(int i = 0; i < N; i++)
        {
            for(int j = i + 1; j < i + K/2 + 1; j++)
            {
                if(Math.random() < beta)
                {
                    int k = (int)(Math.random()*(N));
                    replaceEdge(new Edge(nodes.get(i), nodes.get(j%N)), new Edge(nodes.get(i), nodes.get(k)));
                }
            }
        }
    }
    
    public void addEdge(Node a, Node b){
        edges.add(new Edge(a, b));
    }
    
    public void addNode(Node n){
        nodes.add(n);
    }
    
    public void addEdge(Edge e){
        edges.add(e);
    }
    
    public void replaceEdge(Edge e1, Edge e2)// replaces e1 with another edge e2
    {
        for (Edge e: edges)
        {
            if (e1.equals(e))
            {
                e.a = e2.a;
                e.b = e2.b;
            }
        }
    }
    
    public void removeEdge(Edge e1)
    {
        for (Edge e: edges)
        {
            if (e1.equals(e))
            {
                edges.remove(e);
            }
        }
    }
    
    public void printEdges()
    {
        int cnt = 0;
        for(Edge e: edges)
        {
            System.out.println("" + e.a + " ---> " + e.b);
            cnt++;
        }
        System.out.println(cnt + " edges were created");
    }
    
    public ArrayList<Edge> getEdges()
    {
        return edges;
    }
    
    public int numNodes()
    {
        return N;
    }
    
    public void fillGraphWithNodes(ArrayList<Node> nodeList)
    {
        nodes = nodeList;
    }
    
    public void draw(Graphics g)
    {
        for (Edge e: edges)
        {
            g.drawLine(e.a.x, e.a.y, e.b.x, e.b.y);
        }
    }
    
    public void drawConnected(Graphics g)
    {
        for (Edge e: edges)
        {
            if (e.a.country.lockedDown || e.b.country.lockedDown)
            {
                continue;
            }
            g.drawLine(e.a.x, e.a.y, e.b.x, e.b.y);
        }
    }
    
    public void drawTraveled(Graphics g)
    {
        for (Edge e: edges)
        {
            if (e.traveled)
            {
                g.drawLine(e.a.x, e.a.y, e.b.x, e.b.y);
            }
        }
    }
    
}
