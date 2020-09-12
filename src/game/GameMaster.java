package game;

import actions.Main;
import draw.Draw;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class GameMaster {
    Draw d = new Draw();

    // Insegsamt gibt es 115 * 115 unterschiedliche Zellen auf dem Spielfeld
    public static final int cellCount = 115;
    // Hier verwenden wir eine boolean array, damit wir später den Status jeder Zelle überprüfen können.
    // Also ob sie am leben ist oder tot ist
    public static boolean[][] cells = new boolean[cellCount][cellCount];

    // Wir fangen mit 1000 Lebenden Zellen an
    int startCells = 1000;
    // Wir fangen mit der Generation 0 an.
    static int gen = 0;

    // In dieser Methode werden die anfangs Zellen zufällig bestimmt und erhalten nacher den Wert true,
    // da sie am leben sind.
    public void setup() {
        // Hier benutzen wird die r Methode von Zeile 105 und geben die Variable cellCount mit da sie die
        // anzahl Zellen in x Richtung und gleichzeitg auch in y Richtung ist.
        for (int i = 0; i < startCells; i++) {
            int x = r(0, cellCount);
            int y = r(0, cellCount);

            cells[x][y] = true;
        }
    }

    public static void nextGen() {
        gen++;
        System.out.println("Generation: " + gen);

        for (int x = 0; x < cellCount; x++) {
            for (int y = 0; y < cellCount; y++) {
                // Hier speichern wir die Anzahl Nachbaren für die akutelle Zelle ab.
                int n = neightbors(x, y);

                // Wiederbeleben
                // Wenn wir 3 lebende Nachbaren haben aber die Zelle tot ist, wird sie wiederbelebt.
                if (n == 3 && !cells[x][y]) {
                    cells[x][y] = true;
                }

                // Sterben - Einsamkeit
                // Wenn eine lebende Zelle weniger als 2 Nachbaren hat, stirbt sie.
                if (n < 2 && cells[x][y]) {
                    cells[x][y] = false;
                }

                // Leben {
                // Wenn eine Lebende Zelle 2 oder 3 Nachbaren hat, lebt sie weiter.
                if (n == 2 || n == 3 && cells[x][y]) {
                    // Nichts verändert sich an der Zelle
                }

                // Sterben - Überbevölkerung
                // Wenn eine Zelle mehr als 3 lebende Nachbaren hat, stirbt sie.
                if (n > 3 && cells[x][y]) {
                    cells[x][y] = false;
                }
            }
        }
    }

    // In dieser Methode überprüfen wir ob die Zellen Nachbaren haben.
    public static int neightbors(int x, int y) {
        int count = 0;

        // Diese Arrays verwenden wir als Hilfsvariablen, damit wir nicht so viel Code schreiben müssen.
        // Die Werte brauchen wir dann später im if statement. Wir fangen von der rechten Mitte aus an
        // und gehen dann im Uhrzeigersin um die ausgewählte Zelle.
        // | -1 | 0 | 1 |
        // | -1 | x | 1 |
        // | -1 | 0 | 1 |
        int[] xNeighbor = {1, 1, 0, -1, -1, -1, 0, 1};
        // Dasselbe müssen wir für y wiederholen aber mit anderen Werten, da wir 2 Variablen für unser
        // cells Array brauchen
        int[] yNeighbor = {0, 1, 1, 1, 0, -1, -1, -1};

        // Eine Zelle kann 8 Nachbaren haben, weshalb wir diese for Schalufe 8 mal durchlaufen lassen.
        for (int i = 0; i < 8; i++) {
            // Da wir in unseren Arrays negative Zahlen haben, werden wir eine Out Of Bounds Fehlermeldung
            // erhalten bei Zellen ganz am Spielfeldrand. Damit das Spiel nicht unterbrochen wird, setzen
            // wir hier ein try/catch statement auf, um den Fehler zu ignorieren.
            try {
                // Da unser cells Array ein boolean ist, können wir hier ganz einfach überprüfen ob die
                // Zelle einen oder mehrere Nachbaren hat. Wir nehmen die Momentane Zelle (x & y) und
                // addieren/subtrahieren eine Zahl (xNeighbor & yNeighbor), damit wir die nächstgelegene
                // Zelle überprüfen können. Wenn diese Zelle am leben ist, ist das Ergebnis True und count
                // wird um eine Zahl erhöt. Falls die Zelle tot ist, ist das Ergebnis False und nichts passiert.
                if(cells[x + xNeighbor[i]][y + yNeighbor[i]]) {
                    count++;
                }
            } catch (Exception e) {
                // Fehler ignorieren
            }
        }

        return count;
    }

    // Mit dieser Methode erhalten wir ein Random
    public static int r(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }
}
