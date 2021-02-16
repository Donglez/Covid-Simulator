
public class Incrementer 
{
    private int cnt = 0;
    private int max = 0;
    private int min = 0;
    private int increment = 0;
    private boolean increasing = true;
    
    public Incrementer(int increment, int max, int min)
    {
        this.increment = increment;
        this.max = max;
        this.min = min;
    }
    
    public void increment()
    {
        cnt+=increment;
    }
    
    public void decrement()
    {
        cnt-=increment;
    }
    
    public int counter()
    {
        return cnt;
    }
    
    public void incrementAndDecrement()
    {
        if (increasing)
        {
            increment();
            increasing = (cnt < max);
            cnt = Math.min(max, cnt);
        }
        else
        {
            decrement();
            increasing = (cnt < min);
            cnt = Math.max(cnt, min);
        }
    }
    
    
    
}
