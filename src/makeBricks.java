import java.awt.*;
import java.awt.Graphics2D;

public class makeBricks {
    public int map[][];
    int brickWidth, brickHeight;
    public makeBricks(int x, int y){
        map = new int[x][y];
        for (int i=0; i<map.length; i++){
            for (int j=0;j<map[0].length; j++){
                map[i][j] = 1;
            }
        }
        brickWidth = 700/y;
        brickHeight = 300/x;
    }
    public void draw(Graphics2D g){
        for (int i=0; i<map.length; i++){
            for (int j=0; j<map[0].length; j++){
                if (map[i][j] >0){
                    g.setColor(Color.white);
                    g.fillRect(j * brickWidth + 40, i * brickHeight + 30, brickWidth, brickHeight);

                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.black);
                    g.drawRect(j * brickWidth + 40, i * brickHeight + 30, brickWidth, brickHeight);
                 }
            }
        }
    }
    public void setBrickVal(int val, int x, int y){
        map[x][y] = val;
    }
}
