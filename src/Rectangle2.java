import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.IOException;

/**
 * An extension upon the standard rectangle.
 * Has the extra ability to be drawn and filled.
 * @author dean
 */
public class Rectangle2 extends Rectangle
{
    int w;
    int h;
    Rectangle rectangle = new Rectangle();
    
    /**
     * Parameterized constructor for a Rectangle2
     * @param X horizontal position.
     * @param Y vertical position
     * @param W width
     * @param H height
     */
    public Rectangle2(int X, int Y, int W, int H)
    {
     x = X;
     y = Y;
     w = W;
     h = H;
     rectangle = new Rectangle(x,y,w,h);   
    }
    
    /**
     * Constructor that converts a Rectangle into a Rectangle2.
     * @param r a rectangle.
     */
    public Rectangle2(Rectangle r)
    {
     rectangle = r;
     x = r.x;
     y = r.y;
     w = r.width;
     h = r.height;
    }
    
    /**
     * Blank Constructor
     */
    public Rectangle2()
    {
        
    }
    
    /**
     * Draws the outline of the rectangle2 on the screen.
     * @param g the paintComponent's graphics.
     * @throws IOException 
     */
   public void draw(Graphics g) throws IOException
    {
      g.drawRect(x, y, w, h);
    }
    
   /**
    * Fills the body of the Rectangle2 on the screen.
    * @param g the paintComponent's graphics
    * @throws IOException 
    */
    public void fill(Graphics g) throws IOException
    {
      g.fillRect(x, y, w, h);
    }
    
    /**
     * Checks to see if a rectangle is intersecting a rectangle2.
     * @param r a rectangle.
     * @return true if the rectangle2 intersects a rectangle.
     */
    @Override
    public boolean intersects(Rectangle r)
    {
     boolean b = false;
     if (rectangle.intersects(r))
     {
       b = true;  
     }
     return b;
    }
    
    /**
     * Checks to see if a rectangle2 is intersecting another rectangle2.
     * @param r2 a rectangle2.
     * @return true if the rectangle2 intersects the other rectangle2, 
     * otherwise false.
     */
    public boolean intersects(Rectangle2 r2)
    {
      boolean b = false;
     if (rectangle.intersects(r2.rectangle))
     {
       b = true;  
     }
     return b;  
    }
            
    
    
}