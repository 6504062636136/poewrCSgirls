// Game.java
package com.mycompany.game2;

import charactor.Environment;
import charactor.Wave;
import Event.Event;
import charactor.*;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

public class Game extends JPanel implements KeyListener {

    private static final int GAME_SPEED = 30;
    private static final int JUMP_HEIGHT = 100;
    private long lastPress = 0;
    private Nutcha nc = new Nutcha(50, 300, 100, 100);
    private Wave[] waveSet;
    private Environment[] envSet;
    private Item[] itemSet;
    private BufferedImage gameOverImage;
    private BufferedImage winImage; 
    private JButton restartButton;
    private int itemsCollected = 0;

    public Game() {
        this.setBounds(0, 0, 1000, 600);
        this.addKeyListener(this);
        this.setFocusable(true);
        this.setLayout(null);

        // Initialize waves and environment objects
        this.waveSet = initializeWaves(5);
        this.envSet = initializeEnvironment(3, Environment.TREE);
        this.itemSet = initializeItems(3);
        gameOverImage = ImageLoader.loadImage("over1.png");
        winImage = ImageLoader.loadImage("Win.png");

        restartButton = new JButton(new ImageIcon("restart.png"));
        restartButton.setBounds(400, 300, 200, 50); 
        restartButton.setOpaque(false); 
        restartButton.setContentAreaFilled(false); 
        restartButton.setBorderPainted(false); 
        restartButton.setVisible(false); 
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });
        this.add(restartButton);
    }
     private Item[] initializeItems(int size) {
        Item[] items = new Item[size];
        int far = 700;
        for (int i = 0; i < size; i++) {
            items[i] = new Item(50 + far, (int) (Math.random() * 300 + 100), GAME_SPEED, this);
            far += 1000;  // Adjust spacing as needed
        }
        return items;
    }

    @Override
    public void addNotify() {
        super.addNotify();
        this.requestFocusInWindow();
    }
private void drawItem(Item item, Graphics2D g2) {
    g2.drawImage(item.getImage(), item.x, item.y, item.width, item.height, null);
}
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;

        // Draw background and environment
        drawBackground(g2);

        // Draw Nutcha character
        int newSize = 100;
        g2.drawImage(nc.getImage(), nc.x, nc.y, newSize, newSize, null);
   
        // Draw Nutcha health
        drawNCHealth(g2);

        // Draw waves and check for collisions
        for (Wave wave : waveSet) {
            drawWave(wave, g2);
            if (Event.checkHit(nc, wave)) {
                g2.setStroke(new BasicStroke(10.0f));
                g2.setColor(Color.RED);
                g2.drawRect(0, 0, 985, 560);
                nc.health -= 10;
            }
        }
        for (Item item : itemSet) {
            drawItem(item, g2);
            if (Event.checkHit(nc, item)) {
                g2.setStroke(new BasicStroke(10.0f));
                g2.setColor(Color.green);
                g2.drawRect(0, 0, 985, 560);
                item.x = -50;
                itemsCollected++;
            }
        }
        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(20.0f)); // Set font size
        g2.drawString("Items Collected: " + itemsCollected, 800, 30);

        if (itemsCollected >= 5) {
            g2.drawImage(winImage, 300, -150, 300, 300, null); // Show win image
            for (Wave wave : waveSet) wave.stop();
            for (Item item : itemSet) item.stop();// Stop the waves
            restartButton.setVisible(true); // Show the restart button
            restartButton.revalidate(); // Make sure the button is visible
            restartButton.repaint();
        }
        
        
        if (nc.health <= -100) {
            g2.drawImage(gameOverImage, 0, -150, 1000, 600, null); // Adjust position and size as needed
            restartButton.setVisible(true); // Show the restart button
            restartButton.revalidate(); // Make sure the button is visible
            restartButton.repaint();
            for (Wave wave : waveSet)wave.stop();
            for (Item item : itemSet) item.stop(); 
            }
        }
        
        
        
   
    private static final int GROUND_Y_POSITION = 400;

    private void drawBackground(Graphics2D g2) {
        BufferedImage sky = ImageLoader.loadImage("day.png");
        BufferedImage floor = ImageLoader.loadImage("Floor.png");

        g2.drawImage(sky, 0, 0, 2000, 1000, null);
        g2.drawImage(floor, 0, GROUND_Y_POSITION, 2000, 220, null);

        for (Environment item : envSet) {
            g2.drawImage(item.getImage(), item.x, item.y, 250, 160, null);
        }
    }

    private void drawNCHealth(Graphics2D g2) {
        BufferedImage heartImage = ImageLoader.loadImage("heart.png");
        if (heartImage != null) {
            g2.drawImage(heartImage, 10, 20, 30, 30, null);
        }
        g2.setStroke(new BasicStroke(18.0f));
        g2.setColor(new Color(241, 98, 69));
        g2.drawLine(60, 30, 140 + nc.health, 30);
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(6.0f));
        g2.drawRect(50, 20, 200, 20);
    }

    private Wave[] initializeWaves(int size) {
        Wave[] waves = new Wave[size];
        int far = 700;
        for (int i = 0; i < size; i++) {
            waves[i] = new Wave(50 + far, nc.y, GAME_SPEED, this);
            far += 700;
        }
        return waves;
    }

    private Environment[] initializeEnvironment(int size, int eType) {
        Environment[] environments = new Environment[size];
        int far = 0;
        for (int i = 0; i < size; i++) {
            environments[i] = new Environment(50 + far, 20, this, eType, 10);
            far += 600;
        }
        return environments;
    }

    private void drawWave(Wave wave, Graphics2D g2) {
        g2.drawImage(wave.getImage(), wave.x, wave.y - wave.height, 40, wave.height + 10, null);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (System.currentTimeMillis() - lastPress > 600) {
            if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_SPACE) {
                nc.jump(this);
                this.repaint();
            }
            lastPress = System.currentTimeMillis();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    private void resetGame() {
        // Reset Nutcha's position and health
        nc = new Nutcha(50, 300, 50, 100);

        // Reset the waves and environment
        waveSet = initializeWaves(5);
        envSet = initializeEnvironment(3, Environment.TREE);
        
        itemsCollected = 0;

        // Reset game state and hide restart button
        restartButton.setVisible(false);

        // Redraw the screen
        this.repaint();
    }
}