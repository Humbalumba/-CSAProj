import java.util.Scanner;
import images.APImage;
import images.Pixel;

public class ColorFilter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter an image file name: ");
        String fileName = scanner.nextLine();
        APImage theOriginal = new APImage(fileName);
        theOriginal.draw();

        System.out.print("Enter red adjustment: ");
        int redAdj = scanner.nextInt();
        System.out.print("Enter green adjustment: ");
        int greenAdj = scanner.nextInt();
        System.out.print("Enter blue adjustment: ");
        int blueAdj = scanner.nextInt();

        int width = theOriginal.getWidth();
        int height = theOriginal.getHeight();
        APImage filtered = new APImage(width, height);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Pixel oldPixel = theOriginal.getPixel(x, y);
                Pixel newPixel = filtered.getPixel(x, y);

                // Apply adjustments and clamp values between 0-255
                int red = Math.max(0, Math.min(255, oldPixel.getRed() + redAdj));
                int green = Math.max(0, Math.min(255, oldPixel.getGreen() + greenAdj));
                int blue = Math.max(0, Math.min(255, oldPixel.getBlue() + blueAdj));

                newPixel.setRed(red);
                newPixel.setGreen(green);
                newPixel.setBlue(blue);
            }
        }
        filtered.draw();
    }
}