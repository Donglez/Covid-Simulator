
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;



public class TestModelApplication extends JFrame
{

    private static boolean testing = false;
    
    public TestModelApplication(Graph g, int x, int y, int r)
    {
       Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
       super.setTitle("Small world");
       super.setSize(screenSize.width, screenSize.height-50);
       super.add(new GraphDrawer(g,x,y,r));
       super.setResizable(false);
       super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       super.setVisible(true);
    }
    
    public static void main(String[] args)
    {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Graph g = new Graph();
        g.populateSmallWorld(0.2, 2, 10000);//anything more than 30000 for N takes a long ass time
        if (testing)
        {
            g.printEdges();
        }
        
        new TestModelApplication(g, screenSize.width/2, 3*screenSize.height/5, screenSize.height/2);
        

	
    }
    
}
