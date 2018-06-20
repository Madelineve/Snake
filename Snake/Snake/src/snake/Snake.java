package snake;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class Snake extends JFrame {

    public static void main(String[] args) {
    
       JFrame frame = new JFrame("Snake");
       Board gra = new Board();
       
       
       frame.setSize(1000,600); // rozmiar okna
       frame.setLocation(100, 50);
       frame.setResizable(false); //zablokowanie powiekszenia
       frame.setVisible(true);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.add(gra);
    }
}
