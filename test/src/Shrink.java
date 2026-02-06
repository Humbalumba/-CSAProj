import images.APImage;
import images.Pixel;
import java.util.Scanner;

public class Shrink {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an image file name: ");
        String fileName = scanner.nextLine();
        System.out.print("Enter a shrinkage factor: ");
        int factor = scanner.nextInt();
        APImage original = new APImage(fileName);
        original.draw();

        int newWidth = original.getWidth() / factor;
        int newHeight = original.getHeight() / factor;
        APImage small = new APImage(newWidth, newHeight);

        int newY = 0;
        for (int y = 0; y < original.getHeight() - factor + 1; y += factor) {
            int newX = 0;
            for (int x = 0; x < original.getWidth() - factor + 1; x += factor) {
                Pixel oldPixel = original.getPixel(x, y);
                Pixel newPixel = small.getPixel(newX, newY);

                newPixel.setRed(oldPixel.getRed());
                newPixel.setGreen(oldPixel.getGreen());
                newPixel.setBlue(oldPixel.getBlue());
                newX++;
            }
            newY++;
        }
        small.draw();
        scanner.close();
    }
}