package muzeum;

public class Tablica
{
    public int[][] tablica;
    
    Tablica(int x, int y)
    {
        tablica = new int[x][y];
    }
    public void add(int x, int y, int val)
    {
        tablica[x][y] = val;
    }
}