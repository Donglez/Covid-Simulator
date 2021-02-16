
import java.awt.Graphics;

public class Airport
{
    private int id;
    private String name;
    private String city;
    public Country country;
    private int x;
    private int y;
    private Node node;
    
    
    public Airport(int id, String name, String city, Country country, int x, int y)
    {
        this.id = id;
        this.name = name;
        this.city = city;
        this.country = country;
        this.y = y*4;
        this.x = x*4;
        node = new Node(id, this.x, this.y);
    }
    
    
    
    public void draw(Graphics g)
    {
        node.draw(g);
    }
    
    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }
    
    public Node Node()
    {
        return node;
    }
    
    public String toString()
    {
        return "Airport: " + name + ", city: " + city + ", country: " + country;
    }

}
