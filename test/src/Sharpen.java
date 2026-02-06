import java.util.Scanner;
import images.APImage;
import images.Pixel;

public class Sharpen {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter an image file name: ");
        String fileName = scanner.nextLine();
        
        System.out.print("Enter degree of sharpening (amount to darken): ");
        int degree = scanner.nextInt();
        
        System.out.print("Enter an integer threshold: ");
        int threshold = scanner.nextInt();

        APImage original = new APImage(fileName);
        original.draw();

        int width = original.getWidth();
        int height = original.getHeight();
        APImage sharpened = new APImage(width, height);

        for (int y = 0; y < height - 1; y++) {
            for (int x = 1; x < width; x++) {
                
                Pixel oldPixel = original.getPixel(x, y);
                Pixel leftPixel = original.getPixel(x - 1, y);
                Pixel bottomPixel = original.getPixel(x, y + 1);
                Pixel newPixel = sharpened.getPixel(x, y);

                int oldAve = (oldPixel.getRed() + oldPixel.getGreen() + oldPixel.getBlue()) / 3;
                int leftAve = (leftPixel.getRed() + leftPixel.getGreen() + leftPixel.getBlue()) / 3;
                int bottomAve = (bottomPixel.getRed() + bottomPixel.getGreen() + bottomPixel.getBlue()) / 3;

                int r = oldPixel.getRed();
                int g = oldPixel.getGreen();
                int b = oldPixel.getBlue();

                if (Math.abs(oldAve - leftAve) > threshold || 
                    Math.abs(oldAve - bottomAve) > threshold) {
                    
                    r = Math.max(0, r - degree);
                    g = Math.max(0, g - degree);
                    b = Math.max(0, b - degree);
                }

                newPixel.setRed(r);
                newPixel.setGreen(g);
                newPixel.setBlue(b);
            }
        }
        sharpened.draw();
    }
}