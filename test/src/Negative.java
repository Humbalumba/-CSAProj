import java.util.Scanner;
import images.APImage;
import images.Pixel;

public class Negative {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);

        System.out.print("Enter an image file name: ");
        String fileName = reader.nextLine();
        APImage theOriginal = new APImage(fileName);
        theOriginal.draw();

        int width = theOriginal.getWidth();
        int height = theOriginal.getHeight();
        APImage theClone = new APImage(width, height);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Pixel pixelInOriginal = theOriginal.getPixel(x, y);
                Pixel pixelInClone = theClone.getPixel(x, y);

                int average = (pixelInOriginal.getRed() + 
                               pixelInOriginal.getGreen() + 
                               pixelInOriginal.getBlue()) / 3;

                int negativeValue = 255 - average;

                pixelInClone.setRed(negativeValue);
                pixelInClone.setGreen(negativeValue);
                pixelInClone.setBlue(negativeValue);
            }
        }
        
        theClone.draw();
    }
}