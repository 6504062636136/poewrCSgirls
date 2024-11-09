package com.mycompany.game2;

import javax.swing.JFrame;

public class Display extends JFrame {
    public Display() {
        super("Game2");
        this.setSize(1000, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(300, 100);
        this.getContentPane().setLayout(null);

        Game gamePanel = new Game();
        this.getContentPane().add(gamePanel);
        gamePanel.requestFocusInWindow(); // Request focus here as well
        this.setVisible(true);
    }

    public static void main(String[] arg) {
        Display display = new Display();
    }
}
