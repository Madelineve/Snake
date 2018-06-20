package snake;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class Snake {

    public static void main(String[] args) {
        
        JFrame frame= new JFrame("Snake");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new Panel());
        
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        frame.setLocation(screenSize.height / 2, screenSize.width / 14);
        frame.setPreferredSize(new Dimension(600,600));
        
        frame.pack();
        frame.setVisible(true);
    }

}
