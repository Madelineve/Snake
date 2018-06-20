package snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import javax.swing.ImageIcon;


public class Menu {
    
    public Rectangle map1 = new Rectangle(270, 250, 200, 120); 
    public Rectangle map2 = new Rectangle(510, 250, 200, 120); // stworzenie obiektów typu prostokąt
    
    public void render(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        
        Font font0 = new Font("arial",Font.BOLD,50); // stworzenie obiektów czcionek
        Font font1 = new Font("arial",Font.BOLD,25);
        g.setFont(font0);
        g.setColor(Color.pink);
        g.drawString("SNAKE", 500 - 100, 100);
        g.setFont(font1);
        g.drawString("Wybierz mapę :", 500 - 100, 200);
        
        ImageIcon min = new ImageIcon("miniatura.png");
        Image img = min.getImage();
        g2d.drawImage(img, 270, 250, null);
        
        ImageIcon min1 = new ImageIcon("miniatura1.png");
        Image img2 = min1.getImage();
        g2d.drawImage(img2, 510, 250, null);
         
        
        g2d.draw(map1);
        g2d.draw(map2);
    };
    
    

}
