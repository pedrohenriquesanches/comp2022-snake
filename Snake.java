import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.*;
import javax.swing.*;
import javax.swing.ImageIcon;
/**
 * Write a description of class Snake here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Snake extends JPanel
{
    private String cabeca = "images/headDir.png";
    private String corpo = "images/body.png";

    Snake proxima;
    private int x;
    private int y;
    private Image image;
    
    public Snake() {
        ImageIcon ii = new ImageIcon(this.getClass().getResource(cabeca));
        image = ii.getImage();
        x = 40;
        y = 60;
    }
    
    public Snake(int _x, int _y) {
        ImageIcon ii = new ImageIcon(this.getClass().getResource(corpo));
        image = ii.getImage();
        x = _x;
        y = _y;
    }
         
    public void mover(int _x, int _y){
        x += _x;
        y += _y;
    }
    
    public void moverPara(int _x, int _y){
        x = _x;
        y = _y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return image;
    }
    
    public void setImage(String head) {
        ImageIcon ii = new ImageIcon(this.getClass().getResource(head));
        image = ii.getImage();
    }
    
     public void setProxima(Snake _proxima){
        this.proxima = _proxima;
    }
    
    public Snake getProxima(){
        return this.proxima;
    }
}
