package game;

import actions.Main;
import gui.Gui;

public class GameClock extends Thread {

    private Gui gui;

	public GameClock(Gui gui) {
    	this.gui = gui;
	}

	public static int speed = 300;

	// Methode run in Thread.java überschreiben
    @Override
    public void run() {
        while (true) {
            try {
                // 300 Millisekunden warten bevor die nächste Generation erstellt wird
                sleep(speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Wenn der Start Knopf gedrück wird, ändert sich die Variable running von False auf True und
            // die nextGen Methode wird aufgerufen
            if (Main.running) {
                GameMaster.nextGen();
                this.gui.jf.repaint();
            }
        }
    }
}
