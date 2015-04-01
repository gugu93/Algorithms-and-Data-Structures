import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) throws FileNotFoundException 
    {
        Scanner in = new Scanner(System.in);
      
        for (int ia = 0; ia <= 10; ia++) {           
            int iloscWierzcholkow = in.nextInt();
              Graph g = new Graph(iloscWierzcholkow);
              String sth = "";
              int stWierzcholka, krawedz;
        for(int i = 0; i < iloscWierzcholkow; i++)
        {
            stWierzcholka = in.nextInt();
            for(int j = 0; j < stWierzcholka; j++)
            {
                krawedz = in.nextInt();
                if(krawedz>iloscWierzcholkow){ sth +=Integer.toString(krawedz) + ", "; }
                else
                g.krawedz[i][krawedz - 1] = true;
            }
        }
        System.out.println();
                
        System.out.println(g.wyniki(sth));
        }       
}
}



//bielecki