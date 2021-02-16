
import java.awt.Color;
import java.awt.Graphics;

public class Country 
{
    private int id;
    private String name;
    private double population;
    public Model model;
    public boolean hasModel;
    public int x = -5;
    public int y = -5;
    private int size = 8;
    public boolean lockedDown = false;
    
    
    
    public Country(int id, String name, double population)
    {
        this.population = population;
        this.name = name;
        this.id = id;
        hasModel = false;
    }
    
    public void addModel(double[] distribution, Disease disease)
    {
        model = new Model(population, disease, distribution, name);
        hasModel = true;
    }
    
    public String toString()
    {
        return name + "\nPopulation: " + population;
    }
    
    public boolean equals(Country other)
    {
        return this.toString().equals(other.toString());
    }
    
    public Rectangle2 r()
    {
        return new Rectangle2(x - 6, y - 6, 12, 12);
    }
    
    public String getName()
    {
        return name;
    }
    
    public void draw(Graphics g)
    {
        if (x == -5 || y == -5)
        {
            System.out.println("Error, x and y have not been found");
        }
        else
        {
            Color temp = g.getColor();
            if (lockedDown)
            {
                g.setColor(new Color(0, 255, 100, 150));
            }
            
            size = 12;
            g.fillOval(x - (int)(size/2), y - (int)(size/2), size, size);
            g.setColor(temp);
        }
    }
    
    public void drawInfectionSeverity(Graphics g)
    {
        if (x == -5 || y == -5)
        {
            System.out.println("Error, x and y have not been found");
        }
        else
        {
            size = model.numberOfInfected();
            size = (int)(Math.sqrt(size)/(50000*0.005)) + 8;//changed model.n to 50 000
            g.fillOval(x - (int)(size/2), y - (int)(size/2), size, size);
        }
    }
    
    public void drawFatality(Graphics g)
    {
        if (x == -5 || y == -5)
        {
            System.out.println("Error, x and y have not been found");
        }
        else
        {
            size = model.numberOfFatalities();
            size = (int)(Math.sqrt(size)/(50000*0.001)) + 2;
            g.fillOval(x - (int)(size/2), y - (int)(size/2), size, size);
        }
    }
    
    public void toggleLockDown()
    {
        lockedDown = !lockedDown;
        model.toggleLockDown();
    }
    
    
    
    
}
