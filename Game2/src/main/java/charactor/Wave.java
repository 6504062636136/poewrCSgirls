package charactor;

import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import javax.swing.Timer;
import com.mycompany.game2.ImageLoader;

public class Wave {
    public int speed;
    public int x, y, height = 40;
    private Timer timeMove;

    public Wave(int x, int y, int speed, JPanel page) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.move(page);
    }

    public void move(JPanel page) {
        timeMove = new Timer(speed, e -> {
            if (x <= 0) {
                x = (int) (1000 + (300 + Math.random() * 1000));
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
        return ImageLoader.loadImage("ff.png"); 
    }

    
}