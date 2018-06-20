package snake;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class MouseInput implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent me) {
       
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int myszX = e.getX();
        int myszY = e.getY(); // pobranie współrzędnych kursora
        
               
        if (myszX >= 270 && myszX <= 470){
            if(myszY >= 250 && myszY <=370){
                //nacisniecie mapy 1
               Board.State = Board.STATE.MAPA1;
               
            }
            
        }
        if (myszX >= 510 && myszX <= 710){
            if(myszY >= 250 && myszY <=370){
                //nacisniecie mapy 2
               Board.State = Board.STATE.MAPA2;
               
            }
            
        }
        
        
        
        
          }

    @Override
    public void mouseReleased(MouseEvent me) {
        
    }

    @Override
    public void mouseEntered(MouseEvent me) {
       
    }

    @Override
    public void mouseExited(MouseEvent me) {
      
    }
    
    

}
