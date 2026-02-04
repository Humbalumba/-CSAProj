import images.*;
import java.util.*;

public class MenuDriver {
    private Scanner scanner;
    private APImage image;
    private int input;

    public MenuDriver() {
        scanner = new Scanner(System.in);
        image = new APImage("test\\src\\images\\smokey.jpg");
    }

    public void displayMenu() {
        System.out.println("Image Processing Menu:");
        System.out.println("1. Convert to Grayscale");
        System.out.println("2. Convert to Black and White");
        System.out.println("3. Rotate Image");
        System.out.println("4. Exit");
        System.out.print("Choose an option: ");
    }

    public void runMenu(){
        while (input != 4) {
            displayMenu();
            input = scanner.nextInt();
            if (input == 1){
                    Filters.grayscale(image);
                    image.draw();
                } else if (input == 2){
                    Filters.blackAndWhite(image);
                    image.draw();
                } else if (input == 3){
                    System.out.print("Enter rotation angle (90, -90, 180): ");
                    int angle = scanner.nextInt();
                    while (angle != 90 && angle != -90 && angle != 180) {
                        System.out.println("Invalid angle. Please use 90, -90, or 180.");
                        System.out.print("Enter rotation angle (90, -90, 180): ");
                        angle = scanner.nextInt();
                    }
                    image = Filters.rotate(image, angle);
                    image.draw();

                } else {
                    System.out.println("Invalid option. Please try again.");
            }
        }                    
        System.out.println("Exiting...");
    }
}
