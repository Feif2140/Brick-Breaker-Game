import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BrickGame extends JPanel implements KeyListener, ActionListener {
    private boolean gameover = false;
    private int score = 0;
    private int ballX, ballY, ballXdir, ballYdir, barX;
    private int total =21;
    Rectangle ball, bar;
    private makeBricks brick;

    public BrickGame(){
        brick = new makeBricks(3,7);

        ballX = 400;
        ballY = 500;
        ballXdir = 1;
        ballYdir = 2;
        barX = 350;
        ball = new Rectangle(ballX, ballY, 25, 25);
        bar = new Rectangle(barX, 900, 100, 20);
        Timer t = new Timer(10, this);
        t.start();

        setFocusable(true);
        addKeyListener(this);
    }


    public void paint(Graphics g){
        g.setColor(new Color(200,200,200));
        g.fillRect(0,0,800,1000);
        g.setColor(Color.blue);
        g.fillRect(barX, 900, 100, 20);
        g.setColor(Color.red);
        g.fillOval(ballX,ballY,25,25);


        brick.draw((Graphics2D)g);

        if (gameover){
            g.setColor(Color.BLACK);
            g.setFont(new Font("TimesRoman", Font.BOLD, 50));
            g.drawString("GAMEOVER", 250, 500);
        }
        g.setColor(Color.CYAN);
        g.setFont(new Font("TimesRoman", Font.BOLD, 20));
        g.drawString("Score:  " + score, 650,100);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (total<=0)
            gameover = true;

        if (!gameover) {
            A: for (int i=0; i<brick.map.length; i++){
                for (int j = 0; j<brick.map[0].length; j++){
                    if (brick.map[i][j] > 0) {
                        int brickX = j * brick.brickWidth + 40;
                        int brickY = i * brick.brickHeight + 30;
                        int brickWidth = brick.brickWidth;
                        int brickHeight = brick.brickHeight;

                        Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                        Rectangle ballRect = new Rectangle(ballX, ballY, 25, 25);

                        if (ballRect.intersects(rect)) {
                            brick.setBrickVal(0,i,j);
                            total--;
                            score +=5;

                            if (Math.abs(ballX - (brickX + brick.brickWidth)) <= 1 || Math.abs(ballX + 25 - brickX) <=1) ballXdir = -ballXdir;
                            else ballYdir = -ballYdir;
                            break A;
                        }
                    }
                    }
                }

            ballX += ballXdir;
            ballY += ballYdir;

            if (ballX <= 0 || ballX >= 760)
                ballXdir = -ballXdir;
            if (ballY <=0 || new Rectangle(barX, 900, 100, 20).intersects(new Rectangle(ballX, ballY, 25, 25)))
                ballYdir = -ballYdir;
            if (ballY >= 1000)
                gameover = true;
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()== KeyEvent.VK_LEFT) {
            if (barX >= 1)
                barX -= 7;
        }

        if (e.getKeyCode()== KeyEvent.VK_RIGHT)
            if (barX < 685)
            barX += 7;

            System.out.println("barX: " + barX);
            System.out.println("ballX: " + ballX);
            System.out.println("ballY: " + ballY);

        repaint();
    }
    @Override
    public void keyReleased(KeyEvent e) {

    }
}
