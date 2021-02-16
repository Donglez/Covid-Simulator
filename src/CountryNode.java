
public class CountryNode extends Node
{
    private Country country;

    public CountryNode(int id, int x, int y) 
    {
        super(id, x, y);
    }
    
    public CountryNode(int id, int x, int y, Country country) 
    {
        super(id, x, y);
        this.country = country;
    }
    
    
    
}
