import java.util.*;
import images.*;

public class Filters {
    
    public static APImage blackAndWhite(APImage image) {
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
    public static APImage grayscale(APImage image) {
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

    public static APImage rotateLeft(APImage image) {
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
    public static APImage rotateRight(APImage image) {
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
    public static APImage rotate180(APImage image) {
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
    public static APImage rotate(APImage image, int angle) {
 
        if (angle == 90) {
            return Filters.rotateRight(image);
        } else if (angle == -90) {
            return Filters.rotateLeft(image);
        } else {
            return Filters.rotate180(image);
        }

    }

    public static APImage colorFilter(APImage image, int redAdj, int greenAdj, int blueAdj) {
        int width = image.getWidth();
        int height = image.getHeight();
        APImage filteredImage = new APImage(width, height);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Pixel oldPixel = image.getPixel(x, y);
                Pixel newPixel = filteredImage.getPixel(x, y);

                int red = Math.max(0, Math.min(255, oldPixel.getRed() + redAdj));
                int green = Math.max(0, Math.min(255, oldPixel.getGreen() + greenAdj));
                int blue = Math.max(0, Math.min(255, oldPixel.getBlue() + blueAdj));

                newPixel.setRed(red);
                newPixel.setGreen(green);
                newPixel.setBlue(blue);
            }
        }
        return filteredImage;
    }
    
}
