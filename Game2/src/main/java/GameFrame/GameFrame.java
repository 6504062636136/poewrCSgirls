package GameFrame;

import com.mycompany.game2.Game;
import javax.swing.JFrame;
import java.awt.CardLayout;
import java.awt.image.BufferedImage;


public class GameFrame extends JFrame {
    private CardLayout cardLayout;
    private MenuPanel menuPanel;
    private Game gamePanel;

    public GameFrame() {
        cardLayout = new CardLayout();
        this.setLayout(cardLayout);
        this.setSize(1000, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize the panels
        menuPanel = new MenuPanel(this);
        gamePanel = new Game();

        // Add the panels to the frame
        this.add(menuPanel, "Menu");
        this.add(gamePanel, "Game");

        // Show the menu panel first
        cardLayout.show(this.getContentPane(), "Menu");
    }

    public void startGame() {
        cardLayout.show(this.getContentPane(), "Game");
        gamePanel.requestFocusInWindow();  // Ensure game panel has keyboard focus
    }

    public static void main(String[] args) {
        GameFrame frame = new GameFrame();
        frame.setVisible(true);
    }
}


/*
public class GameFrame extends JFrame {
    private CardLayout cardLayout;
    private MenuPanel menuPanel;
    private Game gamePanel;
    private BufferedImage sky;  // Variable to hold the selected background image

    public GameFrame() {
        cardLayout = new CardLayout();
        this.setLayout(cardLayout);
        this.setSize(1000, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        menuPanel = new MenuPanel(this);
        // Initialize gamePanel with null sky (we'll set this later)
        gamePanel = new Game(sky);

        this.add(menuPanel, "Menu");
        this.add(gamePanel, "Game");

        cardLayout.show(this.getContentPane(), "Menu");
    }

    public void startGameWithSky(BufferedImage selectedSky) {
    this.sky = selectedSky;  // กำหนดพื้นหลังที่เลือก
    gamePanel = new Game(sky);  // รีเฟรช GamePanel โดยใช้พื้นหลังใหม่
    this.add(gamePanel, "Game");
    cardLayout.show(this.getContentPane(), "Game");  // สลับไปยังหน้าจอเกม
    gamePanel.requestFocusInWindow();
}
    public static void main(String[] args) {
        GameFrame frame = new GameFrame();
        frame.setVisible(true);
    }

    void startGame() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
}*/
