import java.util.Scanner;
import java.util.Random;
import images.APImage;
import images.Pixel;

public class Posterize {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        Random gen = new Random();

        System.out.print("Enter an image file name: ");
        String fileName = reader.nextLine();
        APImage theOriginal = new APImage(fileName);
        theOriginal.draw();

        int r1 = gen.nextInt(256), g1 = gen.nextInt(256), b1 = gen.nextInt(256);
        int r2 = gen.nextInt(256), g2 = gen.nextInt(256), b2 = gen.nextInt(256);

        int width = theOriginal.getWidth();
        int height = theOriginal.getHeight();
        APImage thePosterized = new APImage(width, height);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Pixel oldPixel = theOriginal.getPixel(x, y);
                Pixel newPixel = thePosterized.getPixel(x, y);

                int average = (oldPixel.getRed() + oldPixel.getGreen() + oldPixel.getBlue()) / 3;

                if (average < 128) {
                    newPixel.setRed(r1);
                    newPixel.setGreen(g1);
                    newPixel.setBlue(b1);
                } else {
                    newPixel.setRed(r2);
                    newPixel.setGreen(g2);
                    newPixel.setBlue(b2);
                }
            }
        }
        thePosterized.draw();
    }
}