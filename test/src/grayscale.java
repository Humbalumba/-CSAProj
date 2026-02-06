import java.util.Scanner;
import images.APImage;
import images.Pixel;

public class Grayscale {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        System.out.print("Enter an image file name: ");
        String fileName = reader.nextLine();
        APImage image = new APImage(fileName);
        image.draw();

        for (int i = 0; i < image.getWidth(); i++){

            for (int j = 0; j < image.getHeight(); j++){
            Pixel p = image.getPixel(i, j);
            int red = p.getRed();
            int green = p.getGreen();
            int blue = p.getBlue();
            int average = (red + green + blue) / 3;
            p.setRed(average);
            p.setGreen(average);
            p.setBlue(average);
        }
    }
     
        System.out.print("Press return to continue: ");
        reader.nextLine();
        reader.close();
        image.draw();
    }   
}
