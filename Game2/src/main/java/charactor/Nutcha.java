package charactor;

import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import javax.swing.Timer;
import com.mycompany.game2.ImageLoader;

public class Nutcha {
    public int x, y, NCSize, health;
    private int jumpHight = 200;
    

    public Nutcha(int x, int y, int NCSize, int health) {
        this.x = x;
        this.y = y;
        this.NCSize = NCSize;
        this.health = health;
    }

    public void jump(JPanel game) {
        this.y -= jumpHight;
        game.repaint();
        Timer timer = new Timer(450, e -> {
            y += jumpHight;
            game.repaint();
        });
        timer.setRepeats(false);
        timer.start();
    }

    public BufferedImage getImage() {
        return ImageLoader.loadImage("nc2.png");
    }
}