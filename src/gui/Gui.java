package gui;

import actions.Main;
import draw.Draw;
import game.GameClock;
import game.GameMaster;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui {

    public JFrame jf;
    public static Draw d;
    GameMaster gm = new GameMaster();

    public void create() {
        // Fenster Titel
        jf = new JFrame("Game of Life");
        jf.setSize(708, 800);
        // Fenster schliessen wenn der "X" Knopf gedrückt wird
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Damit das Fenster in der Mitte des Bildschirms ist
        jf.setLocationRelativeTo(null);
        jf.setResizable(true);

        d = new Draw();
        d.setVisible(true);

        // JPanel für Buttons erstellen
        JPanel panel = new JPanel(new FlowLayout());
        // Buttons erstellen
        JButton slower5x = new JButton("5x Slower");
        JButton slower2x = new JButton("2x Slower");
        JButton changeState = new JButton("Start");
        JButton faster2x = new JButton("2x Faster");
        JButton faster5x = new JButton("5x Faster");
        // Buttons dem Panel hinzufügen
        panel.add(slower5x);
        panel.add(slower2x);
        panel.add(changeState);
        panel.add(faster2x);
        panel.add(faster5x);

        JPanel contain = new JPanel();
        contain.setLayout(new BorderLayout());
        contain.add(panel, BorderLayout.SOUTH);
        contain.add(d, BorderLayout.CENTER);
        jf.setContentPane(contain);
        jf.setVisible(true);

        // ActionListener für den Start/Pause Button
        changeState.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Das running boolean verändert sich nur wenn dieser Button gedrückt wird, weshalb wir
                // hier überprüfen ob die Variable True oder False ist und setzen sie dementsprechend auf
                // False oder True
                if (!Main.running) {
                    Main.running = true;
                    changeState.setText("Pause");
                }
                else {
                    Main.running = false;
                    changeState.setText("Start");
                }
            }
        });

        // Bei den nächsten Vier ActionListeners verändern wir die Variable speed wenn der Benutzer
        // eine der Vier "Speed Buttons" anklickt.
        slower5x.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (GameClock.speed == 0) {
                    GameClock.speed += 1;
                    faster2x.setEnabled(true);
                    faster5x.setEnabled(true);
                }
                else {
                    GameClock.speed *= 5;
                }
            }
        });
        slower2x.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (GameClock.speed == 0) {
                    GameClock.speed += 1;
                    faster2x.setEnabled(true);
                    faster5x.setEnabled(true);
                }
                else {
                    GameClock.speed *= 2;
                }
            }
        });
        faster2x.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameClock.speed /= 2;
                if (GameClock.speed == 0) {
                    faster2x.setEnabled(false);
                    faster5x.setEnabled(false);
                }
            }
        });
        faster5x.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameClock.speed /= 5;
                if (GameClock.speed == 0) {
                    faster2x.setEnabled(false);
                    faster5x.setEnabled(false);
                }
            }
        });
    }
}
