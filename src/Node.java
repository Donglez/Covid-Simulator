
import java.awt.Graphics;


public class Node
{
    public int val;
    public int x;
    public int y;
    public Country country;
    public Airport airport;
    
    public Node(int val, int x, int y)
    {
        this.val = val;
        this.x = x;
        this.y = y;
    }
    
    public Node(int val, double x, double y)
    {
        this.val = val;
    }
    
    public Node(int val)
    {
        this.val = val;
    }
    
    public String toString(){
        return "" + val;
    }
    
    public boolean equals(Node n)
    {
        return this.val == n.val;
    }
    
    public void setCountry(Country c)
    {
        this.country = c;
    }
    
    public void setAirport(Airport a)
    {
        this.airport = a;
    }
    
    public void draw(Graphics g)
    {
        g.fillOval(x-2, y-2, 4, 4);
    }
    
    public Rectangle2 r()
    {
        return new Rectangle2(x - 2, y-2, 4, 4);
    }
    
}
