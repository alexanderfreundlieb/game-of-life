package draw;

import game.GameMaster;

import javax.swing.*;
import java.awt.*;

public class Draw extends JPanel {

    // Methode paintComponent in jComponent.java überschreiben
    @Override
    public void paintComponent(Graphics g) {

        // Hintergrundfarbe setzen
    	g.setColor(Color.DARK_GRAY);
    	g.fillRect(0, 0, this.getWidth(), this.getHeight());

    	// Breite und Höhe für die Zellen berechnen damit es relativ zur Fenstergrösse ist
        double w = (double)this.getWidth() / GameMaster.cellCount;
        double h = (double)this.getHeight() / GameMaster.cellCount;

        // Hier geben wir den lebenden Zellen die Farbe orange. Da cells ein boolean ist, können
        // wir das ganz einfach überprüfen.
        for (int x = 0; x < GameMaster.cellCount; x++) {
            for (int y = 0; y < GameMaster.cellCount; y++) {
                if (GameMaster.cells[x][y]) {
                    g.setColor(Color.ORANGE);
                    // +1, damit sich die Felder ein kleinwenig überlappen
                    g.fillRect((int)(x * w), (int)(y * h), (int)w + 1, (int)h + 1);
                }
            }
        }
    }
}
