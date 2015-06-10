import java.awt.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.io.File;

public class Board extends JPanel implements ActionListener {

    private Timer timer;
    private Score score;
    Snake cobrinha;
    private boolean isPlaying = false;
    private Font font;
    public static String moverPara = "direita";
       
    public Board() {

        addKeyListener(new TAdapter());
        
        setFocusable(true);        
        setDoubleBuffered(true);
        setBackground(Color.WHITE);

        score = new Score();
        add(score);    
        
        cobrinha = new Snake();
        add(cobrinha);
        
        timer = new Timer(5, this);
        timer.start();
    }


    public void paint(Graphics g) {
        super.paint(g);
        
        score.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D)g;        
        g2d.drawImage(cobrinha.getImage(),cobrinha.getX(),cobrinha.getY(),this);
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
        
    }


    public void paintIntro(Graphics g) {
        if(isPlaying){
            isPlaying = false;
            Graphics2D g2d = (Graphics2D) g;
            //g2d.drawImage(cobrinha.getImage(),cobrinha.getX(),cobrinha.getY(),this);
            try{
                File file = new File("fonts/VT323-Regular.ttf");
                font = Font.createFont(Font.TRUETYPE_FONT, file);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(font);
                font = font.deriveFont(Font.PLAIN,40);
                g2d.setFont(font);
            }catch (Exception e){
                System.out.println(e.toString());
            }   
            g2d.drawString("S N A K E: " + this.score, 300, 300);            
        }
    }
    
    public void actionPerformed(ActionEvent e) {        
        switch(moverPara){
            case "esquerda":
                            cobrinha.move(-1, 0);
                            break;
            case "direita":
                            cobrinha.move(1, 0);
                            break;
            case "cima":
                            cobrinha.move(0, -1);
                            break;
            case "baixo":
                            cobrinha.move(0, 1);
                            break;
        }
        repaint();  
    }
    
    
    private class TAdapter extends KeyAdapter {

        public void keyPressed(KeyEvent e) {
            
            // Obtém o código da tecla
            int key =  e.getKeyCode();

            switch (key){
                case KeyEvent.VK_ENTER:
                    score.addScore(100);
                    break;
                    
                case KeyEvent.VK_LEFT:
                    moverPara = "esquerda";
                    //cobrinha.move(-1, 0);
                    break;
                    
                case KeyEvent.VK_RIGHT:
                    moverPara = "direita";
                    //cobrinha.move(1, 0);
                    break;
                    
                case KeyEvent.VK_UP:
                    moverPara = "cima";
                    //cobrinha.move(0, -1);
                    break;
                    
                case KeyEvent.VK_DOWN:
                    moverPara = "baixo";
                    //cobrinha.move(0, 1);
                    break;
            }
            
        }
    }
    
}