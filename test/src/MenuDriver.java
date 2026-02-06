import images.*;
import java.util.*;

public class MenuDriver {
    private Scanner scanner;
    private APImage image;
    private int input;

    public MenuDriver() {
        scanner = new Scanner(System.in);
        image = new APImage("test\\src\\images\\smokey.jpg");
        System.out.print("Enter image file name (default: smokey.jpg): ");
        String imageName = scanner.nextLine();
        if (imageName.isEmpty()) {
            imageName = "test\src\images\smokey.jpg";
        } else {
            imageName = "test\src\images\" + imageName;
        }
        image = new APImage(imageName);

    public void displayMenu() {
        System.out.println("Image Processing Menu:");
        System.out.println("1. Convert to Grayscale");
        System.out.println("2. Convert to Black and White");
        System.out.println("3. Rotate Image");
        System.out.println("4. Exit");
        System.out.println("4. Blur Image");
        System.out.println("5. Sharpen Image");
        System.out.println("6. Negative Image");
        System.out.println("7. Posterize Image");
        System.out.println("8. Enlarge Image");
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

            } else if (input == 4) {
                Filters.blur(image);
                image.draw();
            } else if (input == 5) {
                Filters.sharpen(image);
                image.draw();
            } else if (input == 6) {
                Filters.negative(image);
                image.draw();
            } else if (input == 7) {
                // Filters.posterize(image);
                System.out.println("Posterize feature coming soon!");
                // image.draw();
            } else if (input == 8) {
                System.out.print("Enter scale factor to enlarge: ");
                double scaleFactor = scanner.nextDouble();
                image = Filters.enlarge(image, scaleFactor);
                image.draw();
                } else {
                    System.out.println("Invalid option. Please try again.");
            }
        }                    
        System.out.println("Exiting...");
    }
}
