import java.util.Scanner;
import images.APImage;
import images.Pixel;

public class grayscale {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        APImage image = new APImage("test\\src\\images\\smokey.jpg");
        image.draw();

        for (Pixel p: image) {
            int red = p.getRed();
            int green = p.getGreen();
            int blue = p.getBlue();

            int average = (red + green + blue) / 3;

            p.setRed(average);
            p.setGreen(average);
            p.setBlue(average);
        }
     
        System.out.print("Press return to continue: ");
        reader.nextLine();
        
        image.draw();
    }   
}
