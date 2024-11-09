package GameFrame;

import com.mycompany.game2.ImageLoader;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class MenuPanel extends JPanel {
    private GameFrame parentFrame;
    private BufferedImage start;
    private BufferedImage pw;
    private BufferedImage sky;
    private BufferedImage floor;
    private List<BufferedImage> environmentImages = new ArrayList<>();
    private static final int GROUND_Y_POSITION = 400;

    public MenuPanel(GameFrame parentFrame) {
        this.parentFrame = parentFrame;
        this.setLayout(null);

        // Load images
        pw = ImageLoader.loadImage("pw.png");
        start = ImageLoader.loadImage("Start.png");
        sky = ImageLoader.loadImage("night.png"); // Default to night background
        floor = ImageLoader.loadImage("Floor.png");
        environmentImages.add(ImageLoader.loadImage("Tree.png")); // Add environment images like trees

        // Create Start button
        JButton startButton = new JButton(new ImageIcon("Start.png"));
        startButton.setBounds(450, 300, 100, 50); // Center the button
        startButton.setBorderPainted(false);
        startButton.setContentAreaFilled(false);
        startButton.setFocusPainted(false);
        startButton.addActionListener(e -> parentFrame.startGame());
        this.add(startButton);

        // Create Sky button to change background to Sky
        JButton skyButton = new JButton("Sky");
        skyButton.setBounds(350, 400, 100, 50); // Position the button
        skyButton.addActionListener(e -> setBackgroundImage("sky"));
        this.add(skyButton);

        // Create Night button to change background to Night
        JButton nightButton = new JButton("Night");
        nightButton.setBounds(470, 400, 100, 50); // Position the button
        nightButton.addActionListener(e -> setBackgroundImage("night"));
        this.add(nightButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Draw background and floor
        drawBackground(g2);
    }

    private void drawBackground(Graphics2D g2) {
        if (sky != null) {
            g2.drawImage(sky, 0, 0, 1000, 600, null); // Draw sky
        }
        if (floor != null) {
            g2.drawImage(floor, 0, GROUND_Y_POSITION, 2000, 220, null); // Draw floor
        }
        if (start != null) {
            g2.drawImage(start, 400, 300, 200, 100, null); // Draw start button
        }
        if (pw != null) {
            g2.drawImage(pw, 300, 50, 370, 200, null); // Draw pw image
        }

        // Draw environment images on the left and right sides
        int treeWidth = 200;
        int treeHeight = 200;
        int offsetX = 50;
        for (BufferedImage img : environmentImages) {
            if (img != null) {
                int imgY = GROUND_Y_POSITION - treeHeight + 15;
                g2.drawImage(img, offsetX - 90, imgY, treeWidth, treeHeight, null);
                g2.drawImage(img, this.getWidth() - treeWidth - offsetX + 90, imgY, treeWidth, treeHeight, null);
            }
        }
    }

    // Method to set the background image (change background)
    private void setBackgroundImage(String background) {
        if ("sky".equals(background)) {
            sky = ImageLoader.loadImage("y.png"); // Load sky background
        } else if ("night".equals(background)) {
            sky = ImageLoader.loadImage("night.png"); // Load night background
        }
        repaint(); // Repaint the panel to show the updated background
    }
}




/*
public class MenuPanel extends JPanel {
    private GameFrame parentFrame;
    private BufferedImage start;
    private BufferedImage pw;
    private BufferedImage sky;
    private BufferedImage floor;
    private List<BufferedImage> environmentImages = new ArrayList<>();
    private static final int GROUND_Y_POSITION = 400;

    public MenuPanel(GameFrame parentFrame) {
        this.parentFrame = parentFrame;
        this.setLayout(null);

        // Load images
        pw = ImageLoader.loadImage("pw.png");
        start = ImageLoader.loadImage("Start.png");
        sky = ImageLoader.loadImage("night.png"); // Default to night background
        floor = ImageLoader.loadImage("Floor.png");
        environmentImages.add(ImageLoader.loadImage("Tree.png")); // Add environment images like trees

        // Create Start button
        JButton startButton = new JButton(new ImageIcon("Start.png"));
        startButton.setBounds(450, 300, 100, 50); // Center the button
        startButton.setBorderPainted(false);
        startButton.setContentAreaFilled(false);
        startButton.setFocusPainted(false);
        startButton.addActionListener(e -> parentFrame.startGame());
        this.add(startButton);

        // Create Sky button to change background to Sky
        JButton skyButton = new JButton("Day");
        skyButton.setBounds(350, 500, 100, 50); // Position the button
        skyButton.addActionListener(e -> setBackgroundImage("sky"));
        this.add(skyButton);

        // Create Night button to change background to Night
        JButton nightButton = new JButton("Night");
        nightButton.setBounds(470, 500, 100, 50); // Position the button
        nightButton.addActionListener(e -> setBackgroundImage("night"));
        this.add(nightButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Draw background and floor
        drawBackground(g2);
    }

    private void drawBackground(Graphics2D g2) {
        if (sky != null) {
            g2.drawImage(sky, 0, 0, 1000, 600, null); // Draw sky
        }
        if (floor != null) {
            g2.drawImage(floor, 0, GROUND_Y_POSITION, 2000, 220, null); // Draw floor
        }
        if (start != null) {
            g2.drawImage(start, 400, 300, 200, 100, null); // Draw start button
        }
        if (pw != null) {
            g2.drawImage(pw, 300, 50, 370, 200, null); // Draw pw image
        }

        // Draw environment images on the left and right sides
        int treeWidth = 200;
        int treeHeight = 200;
        int offsetX = 50;
        for (BufferedImage img : environmentImages) {
            if (img != null) {
                int imgY = GROUND_Y_POSITION - treeHeight + 15;
                g2.drawImage(img, offsetX - 90, imgY, treeWidth, treeHeight, null);
                g2.drawImage(img, this.getWidth() - treeWidth - offsetX + 90, imgY, treeWidth, treeHeight, null);
            }
        }
    }

    // Method to set the background image (change background)
    private void setBackgroundImage(String background) {
        if ("sky".equals(background)) {
            sky = ImageLoader.loadImage("day.png"); // Load sky background
        } else if ("night".equals(background)) {
            sky = ImageLoader.loadImage("night.png"); // Load night background
        }
        repaint(); // Repaint the panel to show the updated background
    }
    public void startGame() {
        parentFrame.startGameWithSky(sky);
}
    public BufferedImage getSky() {
    return sky;
}
}*/
