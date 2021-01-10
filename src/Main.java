import javax.swing.*;

public class Main {

    public static void main(String[] args){
        JFrame f = new JFrame();
        BrickGame game = new BrickGame();
        f.setBounds(1,1,800,1000);
        f.setTitle("Brick Break Game");
        f.add(game);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
