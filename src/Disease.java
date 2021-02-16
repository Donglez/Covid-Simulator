public class Disease 
{
    private String name;
    public double R0; //to be multiplied by infection factors
    public double deathChance[]; //to be multiplied by death factors
    public double incubationTime;
    public double epsilon;
    public double infectiousTime;
    public double psi;
    
    public Disease(String name, double R0, double dc[], double incT, double infT)
    {
        this.name = name;
        this.R0 = R0;
        this.deathChance = dc;
        this.incubationTime = incT;
        this.epsilon = 1/incT;
        this.infectiousTime = infT;
        this.psi = 1/infT;
    }
    
}
