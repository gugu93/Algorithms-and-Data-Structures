package muzeum;

public class Sala {

    private boolean sprawdzona;
    private boolean blokada;
    private int cenaSali;
    private int najtanszaDroga;
    private String najtanszykierunek;
    private Sala N, S, W, E, NE, SE, SW, NW;

    Sala(int cenaSali) {
        this.cenaSali = cenaSali;
        this.blokada = false;
        this.sprawdzona = false;
        this.najtanszykierunek = "";
        this.najtanszaDroga = -1;
    }

   

    public void ustawPolaczenia(Sala prev, boolean newLine) {
        if (newLine) { //jeśli pierwsza hala w nowej lini
            if (prev == null) {
                setN(null);
                setW(null);
                setS(null);
                setE(null);
                setNE(null);
                setSW(null);
                setSE(null);
                setNW(null);
            } else {
                while (prev.W != null) {
                    prev = prev.W;
                }
                setN(prev);
                setW(null);
                setS(null);
                setE(null);
                setNE(prev.E);
                setNW(null);
                setSE(null);
                setSW (null);        
            }
        } else { //jeśli to nie jest pierwsza hala w nowej linii
            if (prev.N == null) {
                setN(null);
                setNW(null);
                setNE(null);
            } else {
                setN(prev.N.E);
                setNW(prev.N);
                setNE(prev.N.E.E);
            }
            setW(prev);
        }
    }

    public void setN(Sala x) {
        this.N = x;
        if (x != null) {
            x.setS(this);
        }
    }

    public void setS(Sala x) {
        this.S = x;
    }

    public void setW(Sala x) {
        this.W = x;
        if (x != null) {
            x.setE(this);
        }
    }

    public void setE(Sala x) {
        this.E = x;
    }

    public void setNE(Sala x) {
        this.NE = x;
        if (x != null) {
            x.setSW(this);
        }
    }

    public void setSE(Sala x) {
        this.SE = x;
            }

    public void setSW(Sala x) {
        this.SW = x;
            }

    public void setNW(Sala x) {
        this.NW = x;
        if (x != null) {
            x.setSE(this);
        }
    }

    public Sala goN() {
        return this.N;
    }

    public Sala goS() {
        return this.S;
    }

    public Sala goE() {
        return this.E;
    }

    public Sala goW() {
        return this.W;
    }

    public Sala goNE() {
        return this.NE;
    }

    public Sala goSE() {
        return this.SE;
    }

    public Sala goSW() {
        return this.SW;
    }

    public Sala goNW() {
        return this.NW;
    }
    
     public boolean wasChecked() {
        return this.sprawdzona;
    }

    public boolean isLocked() {
        return this.blokada;
    }

    public void ustawNajtanszaCene(int cheapestWay) {
        this.najtanszaDroga = cheapestWay;
    }

    public void ustawNajtanszyKierunek(String cheapestWay) {
        this.najtanszykierunek = cheapestWay;
    }

    public int currentWayPrice() {
        return this.najtanszaDroga;
    }

    public String currentWayDirection() {
        return this.najtanszykierunek;
    }

    public void CHECK() {
        this.sprawdzona = true;
    }

    public void LOCK() {
        this.blokada = true;
    }

    public void CLEAN() {
        this.blokada = false;
        this.sprawdzona = false;
        this.najtanszykierunek = "";
        this.najtanszaDroga = -1;
    }

    public int getPrice() {
        return this.cenaSali;
    }
    
}