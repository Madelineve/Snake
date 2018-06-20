package snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;

public final class Panel extends JPanel {

    private final ArrayList<Kwadraty> snakeLista;

    private final Timer timer;
    private final int DELAY = 33; //oponienie 30ms dla 30ftp
    public int licznik = 0;
    long current = System.currentTimeMillis();
    public int x0 = 300;
    public int y0 = 250;

    public Panel() {
        snakeLista = new ArrayList<>();
        SnakeListener listener = new SnakeListener();
        addKeyListener(listener);
        requestFocusInWindow();
        timer = new Timer(DELAY, listener);
        dodaj();
        setBackground(Color.BLACK);
        timer.start();
    }

    void dodaj() {
        snakeLista.add(new Kwadraty(x0, y0, 20));
        x0 += 20;
    }

    /**
     *
     * @param graf
     */
    @Override
    public void paintComponent(Graphics graf) {
        super.paintComponent(graf);

        for (Kwadraty kwardat : snakeLista) {
            graf.setColor(Color.yellow);
            graf.fillRect(kwardat.x, kwardat.y, kwardat.size, kwardat.size);

        }
        graf.setColor(Color.MAGENTA);
        String a = Integer.toString(licznik);
        graf.setFont(new Font("Serif", Font.BOLD, 50));
        graf.drawString(a, 50, 100);
    }

    private class SnakeListener implements ActionListener, KeyListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            requestFocusInWindow();
            for (Kwadraty kwadrat : snakeLista) {
                kwadrat.updateSpeed();
            }
            repaint();

        }

        @Override
        public void keyTyped(KeyEvent ke) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT)) {
                for (Kwadraty kwadrat : snakeLista) {

                    kwadrat.Update(3);

                }
            }

            if ((key == KeyEvent.VK_RIGHT)) {
                for (Kwadraty kwadrat : snakeLista) {

                    kwadrat.Update(1);

                }
            }

            if ((key == KeyEvent.VK_UP)) {
                for (Kwadraty kwadrat : snakeLista) {
                    kwadrat.Update(0);

                }
            }

            if ((key == KeyEvent.VK_DOWN)) {
                for (Kwadraty kwadrat : snakeLista) {
                    kwadrat.Update(2);

                }
            }

            repaint();
        }

        @Override
        public void keyReleased(KeyEvent ke) {
        }

    }

    class Kwadraty {

        public int x;
        public int y;
        public int size;
        public int xspeed;
        public int yspeed;

        public Kwadraty(int x, int y, int size) {
            this.x = x;
            this.y = y;
            this.size = size;
            xspeed = 5;
            yspeed = 0;
        }

        void Update(int i) {
            if (i == 0) {

                xspeed = 0;
                yspeed = -5;

            } else if (i == 1) {

                xspeed = 5;
                yspeed = 0;

            } else if (i == 2) {

                xspeed = 0;
                yspeed = 5;

            } else if (i == 3) {

                xspeed = -5;
                yspeed = 0;

            }

            //updateSpeed();
        }

        void updateSpeed() {
            if (x == 600) {
                x = 10;
            }
            if (y == 600) {
                y = 10;
            }
            if (x < 10) {
                x = 600;
            }
            if (y < 10) {
                y = 600;
            }

            x += xspeed;
            y += yspeed;
        }

    }

}
