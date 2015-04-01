package Euler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {


        Scanner odczyt = new Scanner(System.in);
        Table tab = new Table();

        int n = odczyt.nextInt();
        tab.set(n);         //ilość elementów


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tab.dodaj(i, j, odczyt.nextInt());
            }

        }


        if (tab.czySpojny()) {
            if (tab.czySymetryczny()) {
                if (tab.czyParzysty()) {
                    tab.czyCykl(0);
                    if (tab.czySpojny2()) {
                        System.out.print("Cykl Eulera: ");
                        tab.stos2.write();
                    } else {
                        System.out.println("Graf nie jest spójny.");
                    }
                } else if (tab.iloscNieparzystychWezlow == 2) {
                    tab.czyCykl(tab.nieparzysty);
                    if (tab.czySpojny2()) {
                        System.out.print("Droga Eulera: ");
                        tab.stos2.write();
                    } else {
                        System.out.println("Graf nie jest spójny.");
                    }
                } else {
                    System.out.println("Brak drogi Eulera");
                }
            } else {
                System.out.println("Graf nie jest nieskierowany");
            }
        } else {
            System.out.println("Graf nie jest spójny.");
        }
    }
}
