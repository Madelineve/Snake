package snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import static java.time.Clock.system;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {

    private final int B_WIDTH = 1000;   // szerokość planszy
    private final int B_HEIGHT = 600;   // wysokość planszy
    private final int DOT_SIZE = 20;    // rozmiar kulek
    private final int ALL_DOTS = 1500;   // maksymalna długość węża (1000*600)/(20*20)
    private final int DELAY = 140;  // szybkość gry

    // dwie tablice przechowujące współrzędne części węża
    private final int x[] = new int[ALL_DOTS];
    private final int y[] = new int[ALL_DOTS];

    private int dots; // aktualna długość węża
    private int apple_x; // współrzędna x jabłka
    private int apple_y; // współrzędna y jabłka
    private int points = -10; // liczba zdobytych punktów

    //kierunek węża, początkowo ustawiony na prawo
    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    private boolean inGame = true;

    private Timer timer;
    private Image ball; // obrazek ciała węża
    private Image apple; // obrazek jabłka
    private Image head; // obrazek głowy

    private Menu menu;

    public enum STATE {
        MENU,
        MAPA1,
        MAPA2
    };

    public static STATE State = STATE.MENU;

    public Board() {

        addKeyListener(new TAdapter());
        addMouseListener(new MouseInput());
        setFocusable(true);
        loadImages();
        initGame();
        menu = new Menu();

    }

    // wczytanie obrazków do gry
    private void loadImages() {

        ImageIcon d = new ImageIcon("dotRight.png");
        ball = d.getImage();

        ImageIcon a = new ImageIcon("apple.png");
        apple = a.getImage();

        ImageIcon h = new ImageIcon("headRight.png");
        head = h.getImage();

    }

    // stworzenie węża, umieszczenie pierwszego jabłka w losowym miejscu
    private void initGame() {

        dots = 3;

        for (int z = 0; z < dots; z++) {
            x[z] = 60 - z * 10;
            y[z] = 60;
        }

        locateApple();

        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (State == STATE.MAPA1 || State == STATE.MENU) {
            ImageIcon d = new ImageIcon("background.png");
            Image img = d.getImage();
            g.drawImage(img, 0, 0, null);
            doDrawing(g);
        } else if (State == STATE.MAPA2) {
            ImageIcon d = new ImageIcon("background1.png");
            Image img = d.getImage();
            g.drawImage(img, 0, 0, null);
            doDrawing(g);
        }

    }

    private void doDrawing(Graphics g) {
        if (State == STATE.MAPA1 || State == STATE.MAPA2) {
            if (inGame) {

                g.drawImage(apple, apple_x, apple_y, this);
                Font myFont = new Font("Serif", Font.BOLD, 20);
                g.setFont(myFont);
                g.drawString("WYNIK: " + points, 40, 40);
                for (int z = 0; z < dots; z++) {
                    if (z == 0) {
                        g.drawImage(head, x[z], y[z], this);
                    } else {
                        g.drawImage(ball, x[z], y[z], this);
                    }
                }

                Toolkit.getDefaultToolkit().sync();

            } else {
                gameOver(g);
              /*  State = STATE.MENU;
                menu.render(g);*/
            }
        } else if (State == STATE.MENU) {
            menu.render(g);

        }
    }

    private void gameOver(Graphics g) {
        Font myFont = new Font("Serif", Font.BOLD, 30);
        g.setFont(myFont);
        g.drawString("KONIEC GRY", B_WIDTH / 2 - 100, B_HEIGHT / 2);
        g.drawString("WYNIK: " + points, B_WIDTH / 2 - 78, B_HEIGHT / 2 + 25);
        
    }

    // sprawdzenie czy wąż zjadł jabłko
    private void checkApple() {

        if ((x[0] == apple_x) && (y[0] == apple_y)) {

            dots++;
            locateApple();

        }
    }

    // umieszczenie jabłka w losowym miejscu
    private void locateApple() {

        points += 10;

        Random in = new Random();
        int r = in.nextInt(47) + 1;   // losowa współrzędna x jabłka, 47=(szerokość / rozmiar kulki) - 3
        int q = in.nextInt(27) + 1;   // losowa współrzędna x jabłka, 27=(wysokość / rozmiar kulki) - 3 

        if (State == STATE.MAPA2) { //sprawdzenie czy wylosowane jabłko w mapie 1 nie leży na kamieniu
            if (r >= 13 && q >= 6 && r <= 15 && q <= 13) {
                r += 4;
            }
            if (r >= 13 && q >= 16 && r <= 15 && q <= 23) {
                r += 4;
            }
            if (r >= 33 && q >= 6 && r <= 35 && q <= 13) {
                r -= 4;
            }
            if (r >= 33 && q >= 16 && r <= 35 && q <= 23) {
                r -= 4;
            }
        }

        apple_x = ((r * DOT_SIZE));
        apple_y = ((q * DOT_SIZE));
    }

    private void move() {

        for (int z = dots; z > 0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }

        ImageIcon h;

        if (leftDirection) {
            x[0] -= DOT_SIZE;
            h = new ImageIcon("headLeft.png");
            head = h.getImage();
            h = new ImageIcon("dotLeft.png");
            ball = h.getImage();
        }

        if (rightDirection) {
            h = new ImageIcon("headRight.png");
            head = h.getImage();
            h = new ImageIcon("dotRight.png");
            ball = h.getImage();
            x[0] += DOT_SIZE;
        }

        if (upDirection) {
            h = new ImageIcon("headUp.png");
            head = h.getImage();
            h = new ImageIcon("dotUp.png");
            ball = h.getImage();
            y[0] -= DOT_SIZE;
        }

        if (downDirection) {
            h = new ImageIcon("headDown.png");
            head = h.getImage();
            h = new ImageIcon("dotDown.png");
            ball = h.getImage();
            y[0] += DOT_SIZE;
        }
    }

    private void checkCollision() {

        for (int z = dots; z > 0; z--) {

            if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
                inGame = false;
            }
        }

        if (y[0] >= B_HEIGHT - 50) {
            inGame = false;
        }

        if (y[0] < 20) {
            inGame = false;
        }

        if (x[0] >= B_WIDTH - 30) {
            inGame = false;
        }

        if (x[0] < 20) {
            inGame = false;
        }
        if (State == STATE.MAPA2) {
            if (x[0] >= 260 && y[0] >= 120 && x[0] <= 300 && y[0] <= 260) {
                inGame = false;
            }
            if (x[0] >= 260 && y[0] >= 320 && x[0] <= 300 && y[0] <= 460) {
                inGame = false;
            }
            if (x[0] >= 660 && y[0] >= 120 && x[0] <= 700 && y[0] <= 260) {
                inGame = false;
            }
            if (x[0] >= 660 && y[0] >= 320 && x[0] <= 700 && y[0] <= 460) {
                inGame = false;
            }
        }

        if (!inGame) {
            timer.stop();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        requestFocusInWindow();
        if (inGame) {

            checkApple();
            checkCollision();
            move();
        }

        repaint();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            if (State == STATE.MAPA1 || State == STATE.MAPA2) {
                int key = e.getKeyCode();

                if ((key == KeyEvent.VK_LEFT) && (!rightDirection)) {
                    leftDirection = true;
                    upDirection = false;
                    downDirection = false;
                }

                if ((key == KeyEvent.VK_RIGHT) && (!leftDirection)) {
                    rightDirection = true;
                    upDirection = false;
                    downDirection = false;
                }

                if ((key == KeyEvent.VK_UP) && (!downDirection)) {
                    upDirection = true;
                    rightDirection = false;
                    leftDirection = false;
                }

                if ((key == KeyEvent.VK_DOWN) && (!upDirection)) {
                    downDirection = true;
                    rightDirection = false;
                    leftDirection = false;
                }
            }
        }
    }

}
