
import java.util.ArrayList;

public class Model 
{
    public double n; //population
    public double[] S;
    public double[] E;
    public double[] I;
    public double[] R;
    public double[] D;
    private int tempInf = 0;
    private int tempInfDep = 0;
    private Disease disease;
    public final int MaxDays = 1000;
    //factors below
    private double mu = 0;
    private double sigma = 0;
    private double alpha;
    private double gamma;
    private double psi;
    private double epsilon;
    private double lambda;
    private String countryName;
    private int numInf = 0;
    private int numFat = 0;
    private boolean lockDown = false;
    private boolean awareness = false;
    private int chaosCnt = 0;
    private int chaosStart = 200;
    
    
    public Model()
    {
        
    }
    
    public Model(double N, Disease disease, double distribution[], String countryName)
    {
        this.n = N;
        this.disease = disease;
        alpha = 0;
        this.countryName = countryName;
        for (int cnt = 0; cnt < distribution.length; cnt++)
        {
            //System.out.println("Dist " + distribution[cnt]);
            //System.out.println("Death chance " + disease.deathChance[cnt]);
            alpha += (distribution[cnt]/100)*(disease.deathChance[cnt]/100);
        }
        S = new double[MaxDays];
        E = new double[MaxDays];
        I = new double[MaxDays];
        R = new double[MaxDays];
        D = new double[MaxDays];
        psi = disease.psi;
        epsilon = disease.epsilon;
        S[0] = n;
        E[0] = 0;
        I[0] = 0;
        R[0] = 0;
        D[0] = 0;
        for (int i = 1; i < MaxDays; i++)
        {
            S[i] = n;
            E[i] = 1/n;
            I[i] = 0;
            R[i] = 0;
            D[i] = 0;
        }
        
    }
    
    public double getInfectious(int t)
    {
        return I[t];
    }
    
    public double getDeceased(int t)
    {
        return D[t];
    }
    
    public void infect(int t)
    {
        S[t] = S[t] - 1;
        I[t] = I[t] + 1;
    }
    

    
    public void receiveSusceptible(int t)
    {
        n = n + 1;
        S[t] = S[t] + 1;
    }
    
    public void receiveExposed(int t)
    {
        n = n + 1;
        E[t] = E[t] + 1;
    }
    
    public void removeSusceptible(int t)
    {
        n = n - 1;
        S[t] = S[t] - 1;
    }
    
    public void removeExposed(int t)
    {
        n = n - 1;
        E[t] = E[t] - 1;
    }
    
    public void scheduleInfectiousReception()
    {
        tempInf += 1;
    }
    
    public void scheduleInfectiousDeparture()
    {
        tempInfDep += 1;
    }
    
    public void receiveInfectious(int t)
    {
        n = n + tempInf;
        I[t] = I[t] + tempInf;
        tempInf = 0;
    }
    
    public void removeInfectious(int t)
    {
        n = n - tempInfDep;
        I[t] = I[t] - tempInfDep;
        tempInfDep = 0;
    }
    
    public int numberOfInfected()
    {
        return numInf;
    }
    
    public int numberOfFatalities()
    {
        return numFat;
    }
    
    public void chaos()
    {
        mu = 0;
        sigma = 0;
    }
    
    public void toggleLockDown()
    {
        lockDown = !lockDown;
        awareness = true;
    }
    
    
    public void update(int t)
    {
        if (lockDown)
        {
            mu = 1;
            sigma = 1;
            chaosCnt++;
        }
        else if (chaosCnt >= chaosStart)
        {
            mu = 0.5;
            sigma = 0;
        }
        else if (awareness)
        {
            mu = 0.5;
            sigma = 1;
            chaosCnt++;
        }
        else
        {
            mu = 0;
            sigma = 0;
        }
        gamma = disease.R0*psi*(1 - 0.85*mu)*(1 - 0.82*sigma);
        lambda = alpha*psi;
        S[t+1] = S[t] - gamma*(S[t]/n)*I[t];
        E[t+1] = E[t] + gamma*(S[t]/n)*I[t] - epsilon*E[t];
        I[t+1] = I[t] + epsilon*E[t] - lambda*I[t] - psi*I[t];
        R[t+1] = R[t] + psi * I[t];
        D[t+1] = D[t] + lambda*I[t];
        if (I[t+1] < 0.1)
        {
            I[t+1] = 0;
        }
        numInf = (int)(I[t+1]);
        numFat = (int)(D[t+1]);
        
        //if (countryName.equals("China")) {System.out.println(countryName + " currently has " + I[t] + " infectious individuals at t=" + t);}  

    }
    
    
    
}
