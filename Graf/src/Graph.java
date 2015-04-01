
import java.util.Stack;

public class Graph {

    int[] poprzednik; // tablica poprzednikow
    int[] low; // funkcja low
    int[] d; // wejscie
    char[] kolor; // kolory
    boolean[][] krawedz; // istniejące krawędzie
    Stack st; // stos
    String[] skladowe; // skladowe
    int ktora, ktoraSkladowa, liczbaWierzcholkow, czas;
    int[] punktyArtykulacji; // punkty atykulacji
    int[][] dwuspójne; // dwuspójne
    int[] mosty; // mosty
    int[] spójne; // spójne

    public Graph(int n) {
        liczbaWierzcholkow = n;
        spójne = new int[liczbaWierzcholkow];
        ktoraSkladowa = 0;
        poprzednik = new int[liczbaWierzcholkow];
        low = new int[liczbaWierzcholkow];
        d = new int[liczbaWierzcholkow];
        kolor = new char[liczbaWierzcholkow];
        krawedz = new boolean[liczbaWierzcholkow][liczbaWierzcholkow];
        skladowe = new String[n];
        mosty = new int[liczbaWierzcholkow];
        ktora = 0;
        punktyArtykulacji = new int[liczbaWierzcholkow];
        dwuspójne = new int[liczbaWierzcholkow][liczbaWierzcholkow];

        for (int i = 0; i < liczbaWierzcholkow; i++) {
            for (int j = 0; j < liczbaWierzcholkow; j++) {
                krawedz[i][j] = false;
                dwuspójne[i][j] = 0;
            }
            spójne[i] = -1;
            kolor[i] = 'w';
            skladowe[i] = "";
            punktyArtykulacji[i] = 0;
            mosty[i] = 0;
        }
    }

    boolean czySkierowany() {
        for (int i = 0; i < liczbaWierzcholkow; i++) {
            for (int j = 0; j < liczbaWierzcholkow; j++) {
                if ((krawedz[i][j] != krawedz[j][i]) || (i == j && krawedz[i][j] == true)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void DFS() {
        for (int i = 0; i < liczbaWierzcholkow; i++) {
            if (kolor[i] == 'w') {
                ++ktoraSkladowa;
            }

            kolor[i] = 'w';
            poprzednik[i] = 0;
            st = new Stack();
            czas = 0;
            DFS_Visit(i);
        }
    }

    public void DFS_Visit(int u) {
        if (spójne[u] == -1) {
            spójne[u] = ktoraSkladowa;
        }

        kolor[u] = 'r';
        low[u] = d[u] = ++czas;

        for (int i = 0; i < liczbaWierzcholkow; i++) {
            // badamy każdą krawędź połączoną z 'u'
            if (krawedz[u][i] == true) {
                int v = i;
                if (spójne[u] == -1) {
                    spójne[v] = ktoraSkladowa;
                }
                // jeśli dany wierzchołek nie jest poprzednikiem 'u'
                if (v != poprzednik[u]) {
                    if (kolor[v] == 'w') {
                        poprzednik[v] = u; // ustawiamy 'u' jako poprzednika 'v'
                        Pair para = new Pair(u, v);
                        st.push(para);
                        DFS_Visit(v);
                        // po powrocie z rekurencji...
                        if (low[v] >= d[u]) {// zdejmij dwuspójną lub most
                            String skladowa = "";
                            Pair yz;
                            do {
                                yz = (Pair) st.pop(); // ściagam most
                                skladowa = skladowa + "." + (yz.a + 1) + "." + (yz.b + 1);
                            } while (yz != para);
                            skladowe[ktora++] = skladowa;
                        }
                        low[u] = minimum(low[u], low[v]);
                        System.out.println(low[u]);
                    } else {
                        if (d[v] < d[u]) // (u,v) jest krawędzią wsteczną
                        {
                            low[u] = minimum(low[u], d[v]);
                            System.out.println(low[u]);
                        }
                    }
                }
            }
        }
    }

    int minimum(int a, int b) {
        return (a <= b) ? a : b;
    }

    String wyniki(String problem) {
        String wynik = "";
        if (problem != "") {
            wynik =  "Podano błędny wierzchołek: " + problem + "\n";
            return wynik;
        }
        if (czySkierowany()) {
            wynik += "Graf nie jest nieskierowany";
            return wynik;
        } else {
            DFS();
            for (int i1 = 0; i1 < liczbaWierzcholkow; i1++) {
                
            }
            for (int i = 0; i < liczbaWierzcholkow; i++) {
                if (!skladowe[i].equals("")) {
                    String numer = "";
                    for (int j = 0; j < skladowe[i].length(); j++) {
                        if (skladowe[i].charAt(j) == '.') {
                            if (!numer.equals("")) {
                                int wierzcholek = Integer.parseInt(numer);
                                dwuspójne[i][wierzcholek - 1] = i + 1;
                                numer = "";
                            }
                        } else {
                            numer = numer + skladowe[i].charAt(j);
                        }
                    }
                    if (!numer.equals("")) {
                        int wierzcholek = Integer.parseInt(numer);
                        dwuspójne[i][wierzcholek - 1] = i + 1;
                        numer = "";
                    }
                }
            }

            for (int j = 0; j < liczbaWierzcholkow; j++) {
                for (int i = 0; i < liczbaWierzcholkow; i++) {
                    if (dwuspójne[i][j] != 0) {
                        ++punktyArtykulacji[j]; // jeśli nalezy do dwuspójnej i kończy sie w j, to j będzie moglo byc potem punktem artykulacji
                    }
                    if (dwuspójne[j][i] != 0) {
                        ++mosty[j]; // mosty podobnie
                    }
                }
            }
            
            System.out.println("Spojne skladowe: ");
            for (int i = 1; i <= ktoraSkladowa; i++) {
                for (int j = 0; j < liczbaWierzcholkow; j++) {
                    if (spójne[j] == i) {
                        wynik += (j + 1) + " ";
                    }
                }
                wynik += "\n";
            }
            
          
            System.out.println(wynik);
            wynik = "";
            System.out.println("Dwuspojne skladowe: ");
            for (int i = liczbaWierzcholkow - 1; i >= 0; --i) {
                if (mosty[i] > 2) {// jeśli jest wiecej niz dwa mosty polaczone miedzy soba to jest to dwuspójna skladowa
                    int czy = 0;
                    for (int j = 0; j < liczbaWierzcholkow; j++) {
                        if (dwuspójne[i][j] != 0) {
                            if (czy < mosty[i] - 1) {
                                wynik += (j + 1) + " ";
                                ++czy;
                            } else {
                                wynik += (j + 1);
                            }
                        }
                    }
                    wynik += "\n";
                }
            }
             
            System.out.println(wynik);
            wynik = "";
            //System.out.println();

            System.out.println("Mosty: ");
            for (int i = 0; i < liczbaWierzcholkow; i++) {
                if (mosty[i] == 2) { // jesli rowno dwie to wtedy most
                    int czy = 0;
                    for (int j = 0; j < liczbaWierzcholkow; j++) {
                        if (dwuspójne[i][j] != 0) {
                            if (czy == 0) {
                                wynik += (j + 1) + " ";
                                ++czy;
                            } else {
                                wynik += (j + 1);
                            }
                        }
                    }
                    wynik += "\n";
                }
            }
            ;
            System.out.println(wynik);
            wynik = "";
              System.out.println();
              System.out.println("Punkty artykulacji: ");

            for (int i = 0; i < liczbaWierzcholkow; i++) {
                if (punktyArtykulacji[i] > 1) // jesli wiecej niz jedna krawedz dochodzi do wierzcholka i to i jest p. artykulacji
                {
                    wynik += (i + 1) + " ";
                }
            }
            wynik = wynik.substring(0, wynik.length() - 1);
            wynik += "\n";
            return wynik;
        }
    }
}
// LOW obliczamy zaczynając od liści drzewa przeszukiwania w głąb 
// (idąc aż do korzenia od liści) w następujący sposób:
// LOW = minimum(d[v], minimalne LOW wśród dzieci 'v', minimalne d[w])
// d[v] to czas odwiedzenia wierzchołka  podczas przeszukiwania grafu wgłąb
// d[w] to krawędź wtórna - 
// Dla każdego v z G: jeśli d[v]=low[v], to krawędź {p[v], v} jest mostem. 
// gdzie p[v] jest poprzednikiem wierzchołka 'v' w drzewie przeszukiwania wgłąb