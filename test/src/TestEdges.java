import java.util.Scanner;
import images.APImage;
import images.Pixel;

public class TestEdges {
    public static void main(String[] args) {
        Scanner intScanner = new Scanner(System.in);
        System.out.print("Enter an integer threshold: ");
        int threshold = intScanner.nextInt();

        Scanner stringScanner = new Scanner(System.in);
        System.out.print("Enter an image file name: ");
        String fileName = stringScanner.nextLine();

        intScanner.close();
        stringScanner.close();

        APImage original = new APImage(fileName);
        original.draw();

        int width = original.getWidth();
        int height = original.getHeight();
        APImage theSketch = new APImage(width, height);

        for (int y = 0; y < height - 1; y++) {
            for (int x = 1; x < width; x++) {
                
                Pixel oldPixel = original.getPixel(x, y);
                Pixel leftPixel = original.getPixel(x - 1, y);
                Pixel bottomPixel = original.getPixel(x, y + 1);
                int oldAve = (oldPixel.getRed() + oldPixel.getGreen() + oldPixel.getBlue()) / 3;
                int leftAve = (leftPixel.getRed() + leftPixel.getGreen() + leftPixel.getBlue()) / 3;
                int bottomAve = (bottomPixel.getRed() + bottomPixel.getGreen() + bottomPixel.getBlue()) / 3;

                Pixel newPixel = theSketch.getPixel(x, y);
                
                if (Math.abs(oldAve - leftAve) <= threshold || 
                    Math.abs(oldAve - bottomAve) <= threshold) {
                    newPixel.setRed(255);
                    newPixel.setGreen(255);
                    newPixel.setBlue(255);
                }
            }
        }
        theSketch.draw();
        intScanner.close();
    }
}