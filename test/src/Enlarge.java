import images.APImage;
import images.Pixel;
import java.util.Scanner;

public class Enlarge {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an image file name: ");
        String fileName = scanner.nextLine();
        System.out.print("Enter an enlargement factor: ");
        int factor = scanner.nextInt(); 
        APImage original = new APImage(fileName);
        original.draw();

        int newWidth = original.getWidth() * factor;
        int newHeight = original.getHeight() * factor;
        APImage large = new APImage(newWidth, newHeight);

        for (int y = 0; y < original.getHeight(); y++) {
            for (int x = 0; x < original.getWidth(); x++) {
                Pixel oldPixel = original.getPixel(x, y);

                for (int dy = 0; dy < factor; dy++) {
                    for (int dx = 0; dx < factor; dx++) {
                        Pixel newPixel = large.getPixel(x * factor + dx, y * factor + dy);
                        newPixel.setRed(oldPixel.getRed());
                        newPixel.setGreen(oldPixel.getGreen());
                        newPixel.setBlue(oldPixel.getBlue());
                    }
                }
            }
        }
        large.draw();
        scanner.close();
    }
}