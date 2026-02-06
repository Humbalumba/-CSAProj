import java.util.Scanner;
import images.*;

public class Sepia {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
       
        APImage image = new APImage("test/src/images/smokey.jpg");
        image.draw();
        
        for (Pixel p : image) {
            int red = p.getRed();
            int green = p.getGreen();
            int blue = p.getBlue();
            

            int gray = (int)(red * 0.299 + green * 0.587 + blue * 0.114);
            
            if (gray < 63){
                red = (int)(gray * 1.1);
                blue = (int)(gray * 0.9);
            } else if (gray < 192){
                red = (int)(gray * 1.15);
                blue = (int)(gray * 0.85);
            } else{
                red = Math.min((int)(gray * 1.08), 255);
                blue = (int)(gray * 0.93);
            }
            
            p.setRed(red);
            p.setGreen(gray);
            p.setBlue(blue);
        }
        
        System.out.print("Press return to continue: ");
        scanner.nextLine();
        scanner.close();
        
        image.draw();
    }
} 
