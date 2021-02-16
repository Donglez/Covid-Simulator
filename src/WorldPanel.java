import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.*;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WorldPanel extends JPanel implements MouseListener
{
    
    ArrayList<Airport> airports;
    ArrayList<Country> countries;
    SmallWorldNetwork swn;
    private int numPassengersPerPlane = 39;
    private boolean showAllPaths;
    private boolean showAllNodes;
    private boolean showAllCountries;
    private boolean showInfectedCountries;
    private boolean showTraveledPaths;
    private boolean showFatality;
    private boolean closingBorders = false;
    private boolean showConnectedPaths = false;
    private int t;
    private Disease covid;
    private JLabel dayLabel;
    boolean start = false;
    Image mapImage;
    Image background;
    public WorldPanel()
    {
        mapImage = Toolkit.getDefaultToolkit().getImage(("Data/accMapWhite.png"));
        background = Toolkit.getDefaultToolkit().getImage(("Data/Background.png"));
        t = 0;
        showAllPaths = false;
        showAllNodes = false;
        showInfectedCountries = false;
        showTraveledPaths = false;
        showFatality = false;
        airports = new ArrayList<>();
        countries = new ArrayList<>();
        
        
        dayLabel = new JLabel("t = " + t);
        dayLabel.setFont(new Font("Arial", Font.BOLD, 30));
        dayLabel.setLocation(720, 720);
        double d[] = new double[3];
        d[0] = 0;
        d[1] = 1.14;
        d[2] = 17.25;
        covid = new Disease("Covid-19", 9, d, 5.5, 7);
        importFileData();
        for (Country c: countries)
        {
            if (c.getName().equals("China"))
            {
                c.model.infect(0);
            }
        }
        findAndSetCountryCoords();
        addMouseListener(this);
        this.add(dayLabel);
    }
    
    public void importAirportData()
    {
        Scanner input = null;
        try {
            input = new Scanner(new File("Data/airports.txt"));
        } catch (Exception Ex) {
            System.out.println("Error opening file"); // use GUI prompt
        }
        String s;
        int cnt = 0;
        ArrayList<Node> nodes = new ArrayList<>();
        int removedCount = 0;
        while (input.hasNextLine())
        {
            s = input.nextLine();
            if (!s.toLowerCase().contains("airport") || s.toLowerCase().contains("island") || s.toLowerCase().contains("antarctica") )
            {
                removedCount++;
                continue;
            }
            s = s.replaceAll(", ", " ");
            s = s.replaceAll("\"", "");
            String vars[] = s.split(",");
            int id = cnt;//Integer.parseInt(vars[0]);
            double lat = Double.parseDouble(vars[6]);
            double longitude = Double.parseDouble(vars[7]);
            removedCount++;
            for (Country c: countries)
            {
                if (vars[3].equals(c.getName()))
                {
                    Airport a = new Airport(id, vars[1], vars[2], c, longToX(longitude), latToY(lat));
                    airports.add(a);
                    nodes.add(airports.get(cnt).Node());
                    nodes.get(cnt).setAirport(a);
                    nodes.get(cnt).setCountry(c);
                    cnt++;
                    removedCount--;
                    break;
                }
            }
            //airports.add(new Airport(id, vars[1], vars[2], vars[3], longToX(longitude), latToY(lat)));
            //nodes.add(airports.get(cnt).Node());
            //System.out.println(airports.get(cnt).getX() + ", " + airports.get(cnt).getY());
            //cnt++;
        }
        
        
        swn = new SmallWorldNetwork(nodes, 0.4, 2, cnt);
        System.out.println(cnt);
        System.out.println("Number of nodes ignored: " + removedCount);
    }
    
    public void importCountryData()
    {
        Scanner input = null;
        try {
            input = new Scanner(new File("Data/CountriesPopulation.txt"));
        } catch (Exception Ex) {
            System.out.println("Error opening file"); // use GUI prompt
        }
        String s = input.nextLine();
        int cnt = 0;
        while (input.hasNextLine())
        {
            s = input.nextLine();
            String vars[] = s.split(",");
            int id = cnt;//Integer.parseInt(vars[0]);
            countries.add(new Country(id, vars[0], Double.parseDouble(vars[1]) * 1000));
            //System.out.println(countries.get(cnt) + "\n");
            cnt++;
        }
        
        try {
            input = new Scanner(new File("Data/popDist.txt"));
        } catch (Exception Ex) {
            System.out.println("Error opening file"); // use GUI prompt
        }
        
        cnt = 0;
        while (input.hasNextLine())
        {
            s = input.nextLine();
            s = s.replaceAll("%", "");
            s = s.replaceAll(",", "#");
            //System.out.println(s);
            String vars[] = s.split("#");
            //System.out.println(vars[0] + ", " + vars[1] + ", " + vars[2] + ", " + vars[3]);
            for (Country c: countries)
            {
                if (vars[0].equals(c.getName()))
                {
                    
                    double dist[] = new double[3];
                    dist[0] = Double.parseDouble(vars[1]);
                    dist[1] = Double.parseDouble(vars[2]);
                    dist[2] = Double.parseDouble(vars[3]);
                    //System.out.println(c);
                    c.addModel(dist, covid);
                    cnt++;
                    
                }
            }
            //System.out.println(countries.get(cnt) + "\n");
            
            
            
        }
        System.out.println("Num models: " + cnt);
        System.out.println("Num countries: " + countries.size());
        for (Country c: countries)
        {
            if (!c.hasModel)
            {
                System.out.println(c);
            }
        }
    }
    
    public void importFileData()
    {
        importCountryData();
        importAirportData();
    }
    
    public void findAndSetCountryCoords()
    {
        for (Country c: countries)
        {
            int x = 0;
            int y = 0;
            int cnt = 0;
            for (Airport a: airports)
            {

                if (a.country.equals(c))
                {
                    //System.out.println(a.country.getName());
                    x = x + a.getX();
                    y = y + a.getY();
                    cnt++;
                }
                
            }
            x = (int)(x/cnt);
            y = (int)(y/cnt);
            c.x = x;
            c.y = y;
        }
    }
    
    public void paintComponent(Graphics g)
    {

        //this.setBackground(Color.red);

        g.drawImage(background, 0, 720, this);
        g.drawImage(background, 1640, 0, this);

        dayLabel.setLocation(720, 720);
        dayLabel.setText("t = " + t);
        g.drawImage(mapImage, 0, 0, 1440, 720, this);
        g.setColor(new Color(255, 0, 0, 200));
        if (showAllNodes)
        {
            g.setColor(new Color(255, 0, 0, 255));
            for (Airport a: airports)
            {
                a.draw(g);
            }
        }
        if (showAllPaths)
        {
            g.setColor(new Color(255, 0, 0, 100));
            swn.draw(g);
        }
        if (showAllCountries)
        {
            g.setColor(new Color(255, 0, 0, 250));
            for (Country c: countries)
            {
                c.draw(g);
            }
        }
        
        if (showInfectedCountries)
        {
            g.setColor(new Color(255, 0, 0, 180));
            for (Country c: countries)
            {
                if (c.model.getInfectious(t) >= 1)
                {
                    c.drawInfectionSeverity(g);
                }
            }
        }
        
        if (showFatality)
        {
            Color temp = g.getColor();
            g.setColor(new Color(20, 20, 20, 110));
            for (Country c: countries)
            {
                if (c.model.getDeceased(t) >= 1)
                {
                    c.drawFatality(g);
                }
            }
            g.setColor(temp);
        }
        
        if (showTraveledPaths)
        {
            g.setColor(new Color(255, 0, 100, 100));
            swn.drawTraveled(g);
        }
        
        if (showConnectedPaths)
        {
            g.setColor(new Color(255, 0, 0, 150));
            swn.drawConnected(g);
        }
        
        if (closingBorders)
        {
            Color temp = g.getColor();
            g.setColor(new Color(255, 255, 0, 200));
            for (Country c: countries)
            {
                c.draw(g);
            }
            g.setColor(temp);
        }
    }
    
    public void update() throws InterruptedException
    {
        //System.out.println("testing");
        for (Edge e : swn.getEdges())
        {
            if (e.a.country.lockedDown || e.b.country.lockedDown)
            {
                continue;
            }
            if (e.a.country.model.getInfectious(t) > 0 && !e.traveled)
            {
                double departing = 0;
                for (int i = 0; i < numPassengersPerPlane; i++)
                {
                    int n = (int)(Math.random()*e.a.country.model.n);
                    if (e.a.country.model.getInfectious(t) - departing > n)
                    {
                        e.a.country.model.scheduleInfectiousDeparture();
                        e.b.country.model.scheduleInfectiousReception();
                        e.traveled = true;
                        departing += 1;
                    }
                }
            }
            else if (e.b.country.model.getInfectious(t) > 0 && !e.traveled)
            {
                double departing = 0;
                for (int i = 0; i < numPassengersPerPlane; i++)
                {
                    int n = (int)(Math.random()*e.b.country.model.n);
                    if (e.b.country.model.getInfectious(t) - departing > n)
                    {
                        e.a.country.model.scheduleInfectiousReception();
                        e.b.country.model.scheduleInfectiousDeparture();
                        e.traveled = true;
                        departing += 1;
                    }
                }
            }
        }
        for (int i = 0; i < 10; i ++ )
        {
            
            for (Country c: countries)
            {
                c.model.receiveInfectious(t);
                c.model.removeInfectious(t);

                c.model.update(t);

            }
            t++;
        } 
        repaint();
    }
    
    public void togglePaths()
    {
        showAllPaths = !showAllPaths;
        repaint();
    }
    
    public void toggleClosingBorders()
    {
        closingBorders = !closingBorders;
        repaint();
    }
    
    public void toggleTraveledPaths()
    {
        showTraveledPaths = !showTraveledPaths;
        repaint();
    }
    
    public void toggleInfectedCountries()
    {
        showInfectedCountries = !showInfectedCountries;
        repaint();
    }
    
    public void toggleNodes() {
        showAllNodes = !showAllNodes;
        repaint();
    }
    
    public void toggleCountries() {
        showAllCountries = !showAllCountries;
        repaint();
    }
    
    public void toggleFatality() {
        showFatality = !showFatality;
        repaint();
    }
    
    public void toggleConnectedPaths()
    {
        showConnectedPaths = !showConnectedPaths;
        repaint();
    }
    
    public void writeToTextFiles() throws FileNotFoundException, UnsupportedEncodingException, IOException
    {
        Writer writer;
        
        for (Country c: countries)
        {
            if (c.hasModel)
            {
                writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Data/CountryData/" + c.getName() + ".txt"), "utf-8"));
                String s = "";
                for (int i = 0; i < t + 1; i++)
                {
                    s += i + "#";
                    s += c.model.S[i] + "#";
                    s += c.model.E[i] + "#";
                    s += c.model.I[i] + "#";
                    s += c.model.R[i] + "#";
                    s += c.model.D[i];
                    s+="\n";
                }
                writer.write(s);
                writer.close();
            }
        }
        
    }
    
    public static int latToY(double lat)
    {
        return (int)(-lat + 90);
    }
    
    public static int longToX(double lng)
    {
        return (int)(lng + 180);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
        Rectangle2 r2 = new Rectangle2(e.getX() - 2, e.getY() - 2, 4, 4);
        if (closingBorders)
        {
            for (Country c: countries)
            {
                if(c.r().intersects(r2))
                {
                    System.out.println("Initiating a full lockdown for: ");
                    System.out.println(c);
                    c.toggleLockDown();
                }
            }
            repaint();
        }
        
        if (!closingBorders)
        {
            for (Airport a: airports)
            {
                if(a.Node().r().intersects(r2))
                {
                    System.out.println("Initiating a full lockdown for: ");
                    System.out.println(a.toString());
                    break;
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
}
