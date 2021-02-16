
import java.awt.*;

/**
 * A slider is a little button that is dragged across the screen
 * in a menu or other part of a user interface to set values according to their
 * position on the screen, and can therefore be used to set variable that have a 
 * large spectrum of possible values. 
 * <p>
 * For example, sliders can be used to change the volume of an audio player.
 * <p> 
 * Sliders will have their y value fixed, and will have maximum and minimum
 * x values that they can move up to.
 * @author dean
 */
public class Slider 
{
    private Image img;
    private int x;
    private int y;
    private int xMax;
    private int xMin;
    private Rectangle2 r2;
    private int tempx = x;
    private boolean moved = false;
    private boolean applied = true;
    
    /**
     * Blank Constructor
     */
    Slider()
    {
        
    }
    
    /**
     * 
     * @param i the slider's image.
     * @param X the initial horizontal position.
     * @param Y the fixed vertical position.
     * @param x1 the farthest left that the slider can move.
     * @param x2 the farthest right that a slider can move.
     */
    Slider(Image i, int X, int Y, int x1, int x2)
    {
        img = i;
        x = X;
        y = Y;
        xMin = x1;
        xMax = x2;
        tempx = x;
    }
    
    /**
     * Draws the sliders on the screen.
     * @param g the graphics of the paintComponent.
     */
    public void draw(Graphics g)
    {
        x = Math.max(xMin, x);
        x = Math.min(xMax, x);
        g.drawImage(img, x, y, null);
        r2 = new Rectangle2(x, y, img.getWidth(null), img.getHeight(null));
    }
    
    /**
     * sets the x value of the slider.
     * @param X a horizontal position.
     */
    public void setX(int X)
    {
        x = X;
    }
    /**
     * Sets the initial x value of the slider.
     * Saves a temporary x value that is used to compare further movements
     * of the slider.
     * @param X a horizontal position.
     */
    
    public void setInitialX(int X)
    {
        x = X;
        tempx = x;
    }
    
    /**
     * 
     * @return the horizontal position of the slider.
     */
    public int getX()
    {
        return x;
    }
    
    /**
     * 
     * @return the highest horizontal position that a slider can move to.
     */
    public int getMaxX()
    {
        return xMax;
    }
    
    /**
     * 
     * @return the lowest horizontal position that a slider can move to.
     */
    public int getMinX()
    {
        return xMin;
    }
    
    /**
     * Saves a temporary value of the x position.
     */
    public void saveTempX()
    {
        tempx = x;
    }
    
    /**
     * Get a value showing how far the slider has moved from its original
     * position to its current position.
     * @return the value of displacement moved.
     */
    public int getDisplacementMoved()
    {
        int disp = x - tempx;
        return disp;
    }
    
    /**
     * 
     * @return the image of the slider.
     */
    public Image getImage()
    {
        return img;
    }
    
    /**
     * @return a Rectangle2 with the same dimensions as the slider. 
     */
    public Rectangle2 getBox()
    {
        return r2;
    }
    
    /**
     * 
     * @return true if the slider has moved, else false.
     */
    public boolean hasMoved()
    {
        return moved;
    }
    
    /**
     * Sets the state of the slider's move boolean.
     * @param b true if the slider has moved, else false.
     */
    public void setMoved(boolean b)
    {
        moved = b;
    }
    
    /**
     * 
     * @return true if the slider's movements have been applied and saved,
     *  else false.
     */
    public boolean getApplied()
    {
        return applied;
    }
    
    /**
     * Sets the state of the slider's Apply boolean.
     * @param b true if the movements have been applied, else false.
     */
    public void setApplied(boolean b)
    {
        applied = b;
    }
}
