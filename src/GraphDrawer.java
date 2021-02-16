
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class GraphDrawer extends JPanel
{
    private int size = 10;
    private int centreX;
    private int centreY;
    private int radius;
    private Graph g;
    private Incrementer cnt[];
    private boolean rave = false;
    
    public GraphDrawer(Graph g, int x, int y, int r)
    {
        this.g = g;
        centreX = x;
        centreY = y;
        radius = r;
        for(Edge e: g.getEdges())
        {
            int val = e.a.val;
            double angle = 2*Math.PI/this.g.numNodes();
            int X = centreX + (int)Math.round(radius*Math.sin(val * angle));
            int Y = centreY + (int)Math.round(radius*Math.cos(val * angle));
            e.a.x = X;
            e.a.y = Y;
            
            val = e.b.val;
            angle = 2*Math.PI/this.g.numNodes();
            X = centreX + (int)Math.round(radius*Math.sin(val * angle));
            Y = centreY + (int)Math.round(radius*Math.cos(val * angle));
            e.b.x = X;
            e.b.y = Y;
        }
        cnt = new Incrementer[4];
        int ma = 250;
        int mi = 5;
        cnt[0] = new Incrementer(1, ma, mi);
        cnt[1] = new Incrementer(2, ma, mi);
        cnt[2] = new Incrementer(3, ma, mi);
        cnt[3] = new Incrementer(4, ma, mi);
    }
    
    public void rave()
    {
        rave = true;
    }
    
    public void paintComponent(Graphics g)
    {
        double ratio = 255/(double)(this.g.numNodes());
        
        for(Edge e: this.g.getEdges())
        {
            int a = (int)(Math.round(e.a.val*ratio));
            int b = 255 - a;
            int c = (int)Math.round(e.b.val*ratio);
            int d = 255 - c;
            
            if (rave)
            {
                a = cnt[0].counter();
                b = cnt[1].counter();
                c = cnt[2].counter();
                d = cnt[3].counter();
            }
            
            
            g.setColor(new Color(a, b, b));
            g.fillOval(e.a.x - size/2, e.a.y - size/2, size, size);
            g.drawLine(e.a.x, e.a.y, e.b.x, e.b.y);
            g.setColor(new Color(c, d, d));
            g.fillOval(e.b.x - size/2, e.b.y - size/2, size, size);
        }
        for(int i = 0; i < 3; i++)
        {
            cnt[i].incrementAndDecrement();
        }
        
        
        //repaint();
        
    }
    
}
