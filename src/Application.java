
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;



public class Application extends JFrame
{

    private static boolean testing = false;
    
    public Application()
    {
       Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
       super.setTitle("Small world");
       super.setSize(screenSize.width, screenSize.height-50);
       WorldPanel wp = new WorldPanel();
       
       JButton update = new JButton("Update");    
       update.setBounds(1440,50,200, 40); 
       super.add(update);
       update.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               try {
                   wp.update();
               } catch (InterruptedException ex) {
                   Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
          });
       
       JButton togglePaths = new JButton("All paths");    
       togglePaths.setBounds(1440,50 + 40,200, 40);
       super.add(togglePaths);
       togglePaths.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               wp.togglePaths();
           }
          });
       
       JButton toggleNodes = new JButton("All Nodes");    
       toggleNodes.setBounds(1440, 50 + 40 + 40,200, 40); 
       super.add(toggleNodes);
       toggleNodes.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               wp.toggleNodes();
           }
          });
       
       JButton toggleCountries = new JButton("All Countries");    
       toggleCountries.setBounds(1440, 50 + 40 + 40 + 40,200, 40); 
       super.add(toggleCountries);
       toggleCountries.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               wp.toggleCountries();
           }
          });
       
       JButton toggleInfectedCountries = new JButton("Toggle Infected Countries");    
       toggleInfectedCountries.setBounds(1440, 50 + 40 + 40 + 40 + 40,200, 40); 
       super.add(toggleInfectedCountries);
       toggleInfectedCountries.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               wp.toggleInfectedCountries();
           }
          });
       
       JButton toggleTraveledPaths = new JButton("Paths Traveled");    
       toggleTraveledPaths.setBounds(1440, 50 + 40 + 40 + 40 + 40 + 40,200, 40); 
       super.add(toggleTraveledPaths);
       toggleTraveledPaths.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               wp.toggleTraveledPaths();
           }
          });
       
       JButton toggleFatality = new JButton("Toggle Fatality");    
       toggleFatality.setBounds(1440, 50 + 40 + 40 + 40 + 40 + 40 + 40,200, 40); 
       super.add(toggleFatality);
       toggleFatality.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               wp.toggleFatality();
           }
          });
       
       
       JButton toggleClosingBorders = new JButton("Close Borders");    
       toggleClosingBorders.setBounds(1440, 50 + 40 + 40 + 40 + 40 + 40 + 40 + 40,200, 40); 
       super.add(toggleClosingBorders);
       toggleClosingBorders.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               wp.toggleClosingBorders();
           }
          });
       
       JButton toggleConnectedPaths = new JButton("Show Connected Paths");    
       toggleConnectedPaths.setBounds(1440, 50 + 40 + 40 + 40 + 40 + 40 + 40 + 40 + 40,200, 40); 
       super.add(toggleConnectedPaths);
       toggleConnectedPaths.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               wp.toggleConnectedPaths();
           }
          });
       
       JButton writeToTextFiles = new JButton("Export Result Data");    
       writeToTextFiles.setBounds(1440, 50 + 40 + 40 + 40 + 40 + 40 + 40 + 40 + 40 + 40,200, 40); 
       super.add(writeToTextFiles);
       writeToTextFiles.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               try {
                   wp.writeToTextFiles();
               } catch (UnsupportedEncodingException ex) {
                   Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
               } catch (IOException ex) {
                   Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
          });
       
       
       
       
       
       
       
       
       
       
       
       
       
       super.add(wp);
       
       super.setResizable(false);
       super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       super.setVisible(true);
       
       
       
    }
    
    public static void main(String[] args)
    {

        new Application();
        

	
    }
    
}