package com.mycompany.game2;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageLoader {
    public static BufferedImage loadImage(String fileName) {
        try {
            return ImageIO.read(ImageLoader.class.getResource("/img/" + fileName));
        } catch (IOException e) {
            System.err.println("Error loading image: " + fileName);
            e.printStackTrace();
            return null;
        }
    }
}
