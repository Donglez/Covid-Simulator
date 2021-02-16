
import java.awt.Graphics;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dean Wait
 */
public class SmallWorldNetwork 
{
    private Graph gr;
    
    public SmallWorldNetwork(ArrayList<Node> nodeList, double beta, int K, int N)
    {
        gr = new Graph();
        gr.fillGraphWithNodes(nodeList);
        gr.convertSmallWorld(beta, K, N);
    }
    
    public void draw(Graphics g)
    {
        gr.draw(g);
    }
    
    public void drawTraveled(Graphics g)
    {
        gr.drawTraveled(g);
    }
    
    public void drawConnected(Graphics g)
    {
        gr.drawConnected(g);
    }
    
    public ArrayList<Edge> getEdges()
    {
        return gr.getEdges();
    }
}
