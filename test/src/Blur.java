import images.APImage;
import images.Pixel;
import java.util.Scanner;

public class Blur {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        System.out.print("Enter an image file name: ");
        String fileName = reader.nextLine();

        APImage original = new APImage(fileName);
        original.draw();

        int width = original.getWidth();
        int height = original.getHeight();
        APImage blurred = new APImage(width, height);

        for (int y = 1; y < height - 1; y++) {
            for (int x = 1; x < width - 1; x++) {
                
                Pixel top = original.getPixel(x, y - 1);
                Pixel bottom = original.getPixel(x, y + 1);
                Pixel left = original.getPixel(x - 1, y);
                Pixel right = original.getPixel(x + 1, y);
                Pixel current = original.getPixel(x, y);

                int avgRed = (top.getRed() + bottom.getRed() + left.getRed() + right.getRed() + current.getRed()) / 5;
                int avgGreen = (top.getGreen() + bottom.getGreen() + left.getGreen() + right.getGreen() + current.getGreen()) / 5;
                int avgBlue = (top.getBlue() + bottom.getBlue() + left.getBlue() + right.getBlue() + current.getBlue()) / 5;

                Pixel newPixel = blurred.getPixel(x, y);
                newPixel.setRed(avgRed);
                newPixel.setGreen(avgGreen);
                newPixel.setBlue(avgBlue);
            }
        }
        blurred.draw();
        reader.close();
    }
}