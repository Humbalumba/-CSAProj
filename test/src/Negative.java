import java.util.Scanner;
import images.APImage;
import images.Pixel;

public class Negative {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter an image file name: ");
        String fileName = scanner.nextLine();
        APImage original = new APImage(fileName);
        original.draw();

        int width = original.getWidth();
        int height = original.getHeight();
        APImage clone = new APImage(width, height);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Pixel pixelInOriginal = original.getPixel(x, y);
                Pixel pixelInClone = clone.getPixel(x, y);

                int average = (pixelInOriginal.getRed() + 
                               pixelInOriginal.getGreen() + 
                               pixelInOriginal.getBlue()) / 3;

                int negativeValue = 255 - average;

                pixelInClone.setRed(negativeValue);
                pixelInClone.setGreen(negativeValue);
                pixelInClone.setBlue(negativeValue);
            }
        }
        
        clone.draw();
    }
}