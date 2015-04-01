package muzeum;

public class Kopiec {

    private int size;
    private Sala tab[];

    public Kopiec(int size) {
        tab = new Sala[size];
        this.size = 0;
    }

    public Kopiec() {
        tab = new Sala[65536];
        this.size = 0;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }

    public Sala get() {
        Sala answ = tab[0];
        int j = 0;
        for (int i = 1; i < size; i++) {
            if (tab[i].currentWayPrice() < answ.currentWayPrice()) {
                answ = tab[i];
                j = i;
            }
        }
        for (int i = j; i < size - 1; i++) {
            tab[i] = tab[i + 1];
        }
        size--;
        return answ;
    }

    public void add(Sala a) {
        tab[size] = a;
        size++;
    }
}
