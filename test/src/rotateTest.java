import java.util.Scanner;
import images.*;

public class RotateTest {
    public APImage rotateLeft(APImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        Pixel[][] rotatedImage = new Pixel[height][width];
        
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Pixel originalPixel = image.getPixel(x, y);
                rotatedImage[y][width - 1 - x] = originalPixel;
            }
        }
        
        APImage rotatedImageAP = new APImage(height, width);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                rotatedImageAP.setPixel(y, x, rotatedImage[y][x]);
            }
        }
        return rotatedImageAP;
    }

    public APImage rotateRight(APImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        Pixel[][] rotatedImage = new Pixel[height][width];
        
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Pixel originalPixel = image.getPixel(x, y);
                rotatedImage[height - 1 - y][x] = originalPixel;
            }
        }
        
        APImage rotatedImageAP = new APImage(height, width);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                rotatedImageAP.setPixel(y, x, rotatedImage[y][x]);
            }
        }
        return rotatedImageAP;
    }

    public APImage rotate180(APImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        Pixel[][] rotatedImage = new Pixel[height][width];
        
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Pixel originalPixel = image.getPixel(x, y);
                rotatedImage[height - 1 - y][width - 1 - x] = originalPixel;
            }
        }
        
        APImage rotatedImageAP = new APImage(height, width);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                rotatedImageAP.setPixel(y, x, rotatedImage[y][x]);
            }
        }
        return rotatedImageAP;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        APImage image = new APImage("test\\src\\images\\smokey.jpg");
        image.draw();
        System.out.print("Type 90,180,-90  to continue: ");
        String input = scanner.next();
        scanner.close();

        RotateTest rotator = new RotateTest();
        APImage rotatedImage;
        if (input.equals("90")) {
            rotatedImage = rotator.rotateRight(image);
        } else if (input.equals("180")) {
            rotatedImage = rotator.rotate180(image);
        } else if (input.equals("-90")) {
            rotatedImage = rotator.rotateLeft(image);
        } else {
            rotatedImage = rotator.rotateLeft(image);
        }
        rotatedImage.draw();
    }
}
