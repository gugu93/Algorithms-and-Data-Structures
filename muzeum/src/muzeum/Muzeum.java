package muzeum;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Muzeum {


    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("dane.txt");
        Scanner in = new Scanner(file);
        //Wczytanie wymiarów muzeum
        int m = in.nextInt();
        int n = in.nextInt();
        Tablica tablica = new Tablica(m, n);

        int x, y;
        //Wczytanie sali do odwiedzenia
        x = in.nextInt();
        y = in.nextInt();

        // Wczytanie kosztów sal
        for (int j = 0; j < m; j++) {
            for (int i = 0; i < n; i++) {
                tablica.add(j, i, in.nextInt());
            }
        }
        //Ustawienie sąsiedztwa
        for (int j = 0; j < m; j++) {
            for (int i = 0; i < n; i++) {
                current = new Sala(tablica.tablica[j][i]);
                if (i != 0) {
                    current.ustawPolaczenia(prev, false);
                } else {
                    current.ustawPolaczenia(prev, true);
                }
                if (i == 0 && j == m - 1) {
                    start = current;
                }
                if (i == n - 1 && j == 0) {
                    end = current;
                }
                prev = current;

            }
        }
        
        for (int j = 0; j < m; j++) {
            for (int i = 0; i < n; i++) {
                currentH = new Sala(tablica.tablica[j][i]);
                if (i != 0) {
                    currentH.ustawPolaczenia(prevH, false);
                } else {
                    currentH.ustawPolaczenia(prevH, true);
                }
                if (i == 0 && j == m - 1) {
                    startH = currentH;
                }
                if (i == n - 1 && j == 0) {
                    endH = currentH;
                }
                prevH = currentH;
            }
        }
        //Stworzono kopię pomocniczą.
        Dijkstra pierwPrzejscie = new Dijkstra();
        pierwPrzejscie.hisPosition = start;

        pierwPrzejscie.Przejscie();

        if (x == 0 && y == 0) {
            System.out.println(end.currentWayDirection());
        } else {
            RESET(x, y);
            Dijkstra Guest = new Dijkstra();
            Guest.hisPosition = currentH;
            Guest.CzesciowePrzejscie(endH);          
            String way = (current.currentWayDirection() + endH.currentWayDirection()).substring
                    (0, (current.currentWayDirection() + endH.currentWayDirection()).length() - 2);
            System.out.println("Najktórsza droga przechodząca przez tą salę [" + x + "," + y + "] to: " + way);         
        }
    }

    public static void RESET(int x, int y) {
        current = start;
        currentH = startH;

        while (current.goN() != null) {
            current = current.goN();
            currentH = currentH.goN();
        }
        for (int i = 1; i < x; i++) {
            current = current.goS();
            currentH = currentH.goS();
        }
        for (int j = 1; j < y; j++) {
            current = current.goE();
            currentH = currentH.goE();
        }
    }
    
    
    
    public static Sala prev = null;
    public static Sala current = null;
    public static Sala start = null;
    public static Sala end = null;
    public static Sala prevH = null;
    public static Sala currentH = null;
    public static Sala startH = null;
    public static Sala endH = null;
}
