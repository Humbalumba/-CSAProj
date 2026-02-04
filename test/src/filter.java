import java.util.*;
import images.*;

public class filter {
    
    public APImage blackAndWhite(APImage image) {
        for (Pixel p : image) {
            int red = p.getRed();
            int green = p.getGreen();
            int blue = p.getBlue();
            
            int average = (red + green + blue) / 3;
            
            if (average < 128) {
                p.setRed(0);
                p.setGreen(0);
                p.setBlue(0);
            } else {
                p.setRed(255);
                p.setGreen(255);
                p.setBlue(255);
            }
        }
        return image;
    }
    public APImage grayscale(APImage image) {
        for (Pixel p: image) {
            int red = p.getRed();
            int green = p.getGreen();
            int blue = p.getBlue();

            int average = (red + green + blue) / 3;

            p.setRed(average);
            p.setGreen(average);
            p.setBlue(average);
        }
        return image;
    }

    public APImage rotateLeft(APImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        Pixel[][] rotatedPixels = new Pixel[height][width];
        
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Pixel originalPixel = image.getPixel(x, y);
                rotatedPixels[y][width - 1 - x] = originalPixel;
            }
        }
        
        APImage rotatedImage = new APImage(height, width);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                rotatedImage.setPixel(y, x, rotatedPixels[y][x]);
            }
        }
        return rotatedImage;
    }
    public APImage rotateRight(APImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        Pixel[][] rotatedPixels = new Pixel[height][width];
        
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Pixel originalPixel = image.getPixel(x, y);
                rotatedPixels[height - 1 - y][x] = originalPixel;
            }
        }
        
        APImage rotatedImage = new APImage(height, width);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                rotatedImage.setPixel(y, x, rotatedPixels[y][x]);
            }
        }
        return rotatedImage;
    }
    public APImage rotate180(APImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        Pixel[][] rotatedPixels = new Pixel[height][width];
        
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Pixel originalPixel = image.getPixel(x, y);
                rotatedPixels[height - 1 - y][width - 1 - x] = originalPixel;
            }
        }
        
        APImage rotatedImage = new APImage(height, width);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                rotatedImage.setPixel(y, x, rotatedPixels[y][x]);
            }
        }
        return rotatedImage;
    }
    
}
