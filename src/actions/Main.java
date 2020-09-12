package actions;

import game.GameClock;
import game.GameMaster;
import gui.Gui;

public class Main {
    public static boolean running = false;

    public static void main(String[] args) {
        Gui g = new Gui();
        GameMaster gm = new GameMaster();
        GameClock gc = new GameClock(g);

        // Bestimmt die lebenden Zellen
        gm.setup();
        // Setzt das Gui auf
        g.create();
        // Startet die Game Clock
        gc.start();
    }
}
