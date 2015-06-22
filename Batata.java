import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.*;
import javax.swing.*;
import javax.swing.ImageIcon;
import java.util.Random;
/**
 * Write a description of class Batata here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Batata extends JPanel
{
    private String batatinha = "images/fries.png";

    private int x;
    private int y;
    private Image image;
    
    public Batata() {
        ImageIcon ii = new ImageIcon(this.getClass().getResource(batatinha));
        image = ii.getImage();
        x = geradorX();
        y = geradorY();
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
    
    public int geradorX() {
        Random gerador = new Random();
        return 10+gerador.nextInt(750);
    }
    
    public int geradorY() {
        Random gerador = new Random();
        return 10+gerador.nextInt(550);
    }
}
