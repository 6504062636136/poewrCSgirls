
package charactor;

import javax.swing.JPanel;
import com.mycompany.game2.ImageLoader;
import java.awt.image.BufferedImage;
import javax.swing.Timer;

public class Item {
    public int speed;
    public int x, y, height = 60;
    public int width = 60;
    private Timer timeMove;

    public Item (int x, int y, int speed, JPanel page) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.move(page);
    }

    public void move(JPanel page) {
        timeMove = new Timer(speed, e -> {
            if (x <= 0) {
                x = (int) (1000 + (300 + Math.random() * 1000));
                y = (int) (Math.random() * 300 + 100);
            }
            x -= 30;
            page.repaint();
        });
        timeMove.start();
    }

    public void stop() {
        if (timeMove != null) timeMove.stop();
    }

    public BufferedImage getImage() {
        return ImageLoader.loadImage("com.png"); 
    }
    
}
