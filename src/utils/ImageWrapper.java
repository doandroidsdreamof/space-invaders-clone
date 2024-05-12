package utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageWrapper {

    public static BufferedImage loadImage(String path) {
        try {

            File file = new File(path);
            String absolutePath = file.getAbsolutePath();
            return ImageIO.read(new File(absolutePath));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading image");
            return null;
        }
    }
}
