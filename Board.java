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

    Snake cabeca;
    Batata frita;

    private boolean isPlaying = false;
    private boolean gameOver = false;
    private Font font;
    public static String moverPara = "direita";

    public Board() {
        addKeyListener(new TAdapter());

        setFocusable(true);        
        setDoubleBuffered(true);
        setBackground(Color.WHITE);

        score = new Score();
        add(score);    

        cabeca = new Snake();
        frita =  new Batata();

        //Adicionar cobrinha na tela por partes (SNAKE)
        Snake aux = cabeca;
        while(aux.getProxima() != null){
            add(aux);
            aux = aux.getProxima();
        }
        //Adicionar Batata Frita na tela (BATATA)
        add(frita);

        timer = new Timer(5, this);
        timer.start();
    }

    public void paint(Graphics g) {
        super.paint(g);
        score.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;      
        
        //adicionando imagens da cobrinha na tela
        int i = 0;
        int tamanho = tamanhoCobrinha();
        Snake aux = cabeca;
        while(i < tamanho){
            g2d.drawImage(aux.getImage(),aux.getX(),aux.getY(),this);
            aux = aux.getProxima();
            i++;
        }
        
        //adicionando imagem da batata na tela
        g2d.drawImage(frita.getImage(),frita.getX(),frita.getY(),this);
        
        //verifica seu deu game over e avisa o usuário
        if(gameOver){
            g2d.drawString("G A M E   O V E R ", 270, 280);
            g2d.drawString("Aperte enter para tentar novamente ", 140, 310);     
        }


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
        if(!gameOver){
            int tamanho = tamanhoCobrinha();
            int i = 0;
            switch(moverPara){
                case "esquerda":
                Snake aux = cabeca;
                while(i < tamanho){
                    aux.mover(-1, 0);
                    aux = aux.getProxima();
                    i++;
                }
                break;
    
                case "direita":
                aux = cabeca;
                while(i < tamanho){
                    aux.mover(1, 0);
                    aux = aux.getProxima();
                    i++;
                }
                break;
    
                case "cima":
                aux = cabeca;
                while(i < tamanho){
                    aux.mover(0, -1);
                    aux = aux.getProxima();
                    i++;
                }
                break;
    
                case "baixo":
                aux = cabeca;
                while(i < tamanho){
                    aux.mover(0, 1);
                    aux = aux.getProxima();
                    i++;
                }
                break;
            }
    
            //Verifica se a cabeca chegou na mesma posicao da comida, para o caso de alimentar a cobrinha e pontuar no score         
            if(((cabeca.getX() <= frita.getX()+20) && (cabeca.getX() >= frita.getX())) &&
               ((cabeca.getY() <= frita.getY()+20) && (cabeca.getY() >= frita.getY()))){
                frita = new Batata();
                score.addScore(100);
                aCobrinhaComeu();
            }
            
            //Verifica se relou nas bordas da Frame, para o caso de GAME OVER
            if((cabeca.getX() == 0) || (cabeca.getX() == 800) || (cabeca.getY() == 0) || (cabeca.getY() == 600)){
                gameOver = true;
                System.out.println("game over");
            }
            
            repaint();  
        }
    }

    private class TAdapter extends KeyAdapter {
        public void keyPressed(KeyEvent e) {

            // Obtém o código da tecla
            int key =  e.getKeyCode();

            switch (key){
                case KeyEvent.VK_ENTER:
                if(gameOver){
                    gameOver = false;
                    score = new Score();
                    cabeca = new Snake();
                    frita = new Batata();
                    moverPara = "direita";
                }
                break;

                case KeyEvent.VK_LEFT:
                if(moverPara == "direita"){
                    break;
                }else{
                    moverPara = "esquerda";
                    break;
                }

                case KeyEvent.VK_RIGHT:
                if(moverPara == "esquerda"){
                    break;
                }else{
                    moverPara = "direita";
                    break;
                }

                case KeyEvent.VK_UP:
                if(moverPara == "baixo"){
                    break;
                }else{
                    moverPara = "cima";
                    break;
                }

                case KeyEvent.VK_DOWN:
                if(moverPara == "cima"){
                    break;
                }else{
                    moverPara = "baixo";
                    break;
                }

            }
        }
    }

    public int tamanhoCobrinha(){
        int qtdPosicoes;
        //se a lista for vazia retorna 0
        //se não, sabemos que pelo menos uma posição ela tem, partindo desse caso, podemos contar quantas posições tem
        if(isEmpty()){
            qtdPosicoes = 0;
            return qtdPosicoes;
        }else{
            qtdPosicoes = 1;
        }

        Snake aux = cabeca;
        //se o proximo nó do inicio for nulo, quer dizer que temos apenas uma posição, o inicio.
        if(aux.getProxima() == null){
            qtdPosicoes = 1;
        } else {
            do{
                aux = aux.getProxima();
                qtdPosicoes++;
            }while(aux.getProxima() != null);
        }
        return qtdPosicoes;
    }

    public boolean isEmpty(){
        if(cabeca == null){
            return true;
        }else{  
            return false;
        }
    }
    
    /*
     * Método executado quando a cobrinha come a batata
     * Aumenta a cobrinha
     * 
     */
    public void aCobrinhaComeu(){
        Snake aux = cabeca;
        while(aux.getProxima() != null){
            aux = aux.getProxima();
        }
        aux.setProxima( new Snake((aux.getX() + 28) , aux.getY()) );
    }
}