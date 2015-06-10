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
    private String snake = "images/head.png";

    private int dx;
    private int dy;
    private int x;
    private int y;
    private Image image;
    
    public Snake() {
        ImageIcon ii = new ImageIcon(this.getClass().getResource(snake));
        image = ii.getImage();
        x = 40;
        y = 60;
    }
    
    public void move(int _x, int _y) {
        x += _x;
        y += _y;
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

}
