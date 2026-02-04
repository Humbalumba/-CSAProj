import java.util.Scanner;
import images.APImage;
import images.Pixel;

public class TestEdges {
    public static void main(String[] args) {
        Scanner intReader = new Scanner(System.in);
        System.out.print("Enter an integer threshold: ");
        int threshold = intReader.nextInt();

        Scanner stringReader = new Scanner(System.in);
        System.out.print("Enter an image file name: ");
        String fileName = stringReader.nextLine();

        APImage theOriginal = new APImage(fileName);
        theOriginal.draw();

        // Create a blank image to receive the edges
        int width = theOriginal.getWidth();
        int height = theOriginal.getHeight();
        APImage theSketch = new APImage(width, height);

        // Visit all pixels except for the left column and bottom row
        for (int y = 0; y < height - 1; y++) {
            for (int x = 1; x < width; x++) {
                
                Pixel oldPixel = theOriginal.getPixel(x, y);
                Pixel leftPixel = theOriginal.getPixel(x - 1, y);
                Pixel bottomPixel = theOriginal.getPixel(x, y + 1);

                int oldAve = (oldPixel.getRed() + oldPixel.getGreen() + oldPixel.getBlue()) / 3;
                int leftAve = (leftPixel.getRed() + leftPixel.getGreen() + leftPixel.getBlue()) / 3;
                int bottomAve = (bottomPixel.getRed() + bottomPixel.getGreen() + bottomPixel.getBlue()) / 3;

                Pixel newPixel = theSketch.getPixel(x, y);
                
                //White and black threshold
                if (Math.abs(oldAve - leftAve) <= threshold || 
                    Math.abs(oldAve - bottomAve) <= threshold) {
                    newPixel.setRed(255);
                    newPixel.setGreen(255);
                    newPixel.setBlue(255);
                }
            }
        }
        theSketch.draw();
    }
}