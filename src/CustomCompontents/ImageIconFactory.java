package CustomCompontents;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImageIconFactory {
    public ImageIconFactory(){

    }
    public static ImageIcon scaleAndCastImage(String imagePath, int imageWidth, int imageHeight) {
        BufferedImage originalImage;
        try {
            originalImage = ImageIO.read(new File(imagePath));
            int height = imageHeight;
            int width = imageWidth;
            Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            return scaledIcon;
        } catch (IOException e) {
            return null;
        }
        
    }
}
