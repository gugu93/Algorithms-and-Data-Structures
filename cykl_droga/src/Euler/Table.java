package Euler;

public class Table {

    private int n;
    int[][] tab;
    Stack stos, stos2;
    int nieparzysty;
    int iloscNieparzystychWezlow;

    public Table() {
    }

    public void set(int n) {
        this.n = n;
        tab = new int[n][n];
        nieparzysty = 0;
        iloscNieparzystychWezlow = 0;
        stos = new Stack();
        stos2 = new Stack();
    }

    public void dodaj(int x, int y, int z) {
        this.tab[x][y] = z;
    }

    public boolean czyParzysty() //zwraca true jeśli wszystkie wierzchołki mają przysty stopień
    {
        boolean bool = true;
        int a = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a += tab[i][j];
            }
            if (a % 2 != 0) {
                if (bool) {
                    System.out.print("Brak cyklu Eulera ");
                    System.out.print("d(" + ((char) (i + 65)) + ")");
                    nieparzysty = i;
                    iloscNieparzystychWezlow++;
                    bool = false;
                } else {
                    bool = false;
                    System.out.print(", d(" + ((char) (i + 65)) + ")");
                    iloscNieparzystychWezlow++;
                }
            }
            a = 0;
        }
        if (!bool) {
            System.out.println(" - nieparzyste");
        }
        return bool;
    }

    public boolean czySpojny() //zwraca true jeśli wszystkie wierzchołki są spójne
    {
        boolean bool = true;
        int a = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a += tab[j][i];
            }
            if (a == 0) {
                if (bool) {
                    System.out.print("Brak cyklu Eulera ");
                    //System.out.print("d("+((char)(i+65))+")");
                    bool = false;
                } else {
                    bool = false;
                    //System.out.print(", d("+((char)(i+65))+")");
                }
            }
            a = 0;
        }
        if (!bool) {
            System.out.println("Graf niespójny");
        }
        return bool;
    }

    public boolean czySymetryczny() //zwraca true jeśli graf w postaci macierzowej jest symetryczny względem przekątnej
    {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tab[i][j] != tab[j][i]) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean czySpojny2() {
        for (int i = 0; i < n; i++) {
            if (stos2.search(i) == false) {
                return false;
            }
        }
        return true;
    }

    public boolean czyCykl(int x) {
        stos.clear();
        stos2.clear();
        stos.add(new Node(x));
        czyCykl2(x, 0);


        if (czySpojny2()) {
            return true;
        }
        return false;
    }

    private void czyCykl2(int i, int j) {
        if (i > n) {
            return;
        }
        if (tab[i][j] != 0) {
            tab[i][j]--;
            tab[j][i]--;
            stos.add(new Node(j));
            czyCykl2(j, 0);
            return;
        }
        j++;
        if (j == n) {
            j = 0;
            Node tmp = stos.wez();

            if (tmp == null) {
                return;
            }
            stos2.add(new Node(tmp.wartosc));

            if (stos.root() != null) {
                czyCykl2(stos.root().wartosc, 0);
            }
            return;
        } else {
            czyCykl2(i, j);
            return;
        }
    }
}