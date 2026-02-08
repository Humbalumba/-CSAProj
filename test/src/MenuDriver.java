import images.*;
import java.util.Scanner;

/**
 * Interactive menu driver for image processing operations.
 * Handles user input and applies selected filters to loaded images.
 */
public class MenuDriver {
    private Scanner scanner;
    private APImage image;
    private APImage original;
    private int input;

    /**
     * Constructs a MenuDriver and loads an image file.
     * Prompts user for image filename or uses default 'smokey.jpg' on error.
     * 
     * @precondition none
     * @postcondition image is loaded and ready for processing; scanner is initialized; input is 0
     */
    public MenuDriver() {
        scanner = new Scanner(System.in);
        System.out.print("Enter image file name (default: smokey.jpg): ");
        String imageName = scanner.nextLine();
        try{
            imageName = "test\\src\\images\\" + imageName + ".jpg";
            original = new APImage(imageName);
            image = original;
        } catch (Exception e){
            System.out.println("Invalid file name. Using default image.");
            imageName = "test\\src\\images\\smokey.jpg";
            original = new APImage(imageName);
            image = original;
        }
    }

    /**
     * Displays the filter options menu to the user.
     * 
     * @precondition none
     * @postcondition menu text is printed; user is prompted to select an option
     */
    public void displayMenu() {
        System.out.println("Image Processing Menu:");
        System.out.println("1. Convert to Grayscale");
        System.out.println("2. Convert to Black and White");
        System.out.println("3. Rotate Image");
        System.out.println("4. Sepia Image");
        System.out.println("5. Blur Image");
        System.out.println("6. Sharpen Image");
        System.out.println("7. Negative Image");
        System.out.println("8. Posterize Image");
        System.out.println("9. Enlarge Image");
        System.out.println("10. Apply Color Filter");
        System.out.println("11. Exit");
        System.out.print("Choose an option: ");
    }

    /**
     * Runs the menu loop for processing images.
     * Continuously prompts user for filter selection until exit option (11) is chosen.
     * 
     * @precondition image is loaded; scanner is initialized
     * @postcondition all requested filters are applied and displayed; image window closed; scanner is closed; program exits
     */
    public void runMenu(){
        while (input != 11) {
            displayMenu();
            input = scanner.nextInt();
                if (input == 1){
                    original.draw();
                    APImage filtered = original.clone();
                    Filters.grayscale(filtered);
                    filtered.draw();
                } else if (input == 2){
                    original.draw();
                    APImage filtered = original.clone();
                    Filters.blackAndWhite(filtered);
                    filtered.draw();
                } else if (input == 3){
                    System.out.print("Enter rotation angle (90, -90, 180): ");
                    int angle = scanner.nextInt();
                    while (angle != 90 && angle != -90 && angle != 180) {
                        System.out.println("Invalid angle. Please use 90, -90, or 180.");
                        System.out.print("Enter rotation angle (90, -90, 180): ");
                        angle = scanner.nextInt();
                    }
                    original.draw();
                    APImage rotated = Filters.rotate(original, angle);
                    rotated.draw();

            } else if (input == 4) {
                original.draw();
                APImage filtered = original.clone();
                Filters.sepia(filtered);
                filtered.draw();
            } else if (input == 5) {
                original.draw();
                APImage filtered = original.clone();
                Filters.blur(filtered);
                filtered.draw();
            } else if (input == 6) {
                original.draw();
                APImage filtered = original.clone();
                Filters.sharpen(filtered);
                filtered.draw();
            } else if (input == 7) {
                original.draw();
                APImage filtered = original.clone();
                Filters.negative(filtered);
                filtered.draw();
            } else if (input == 8) {
                original.draw();
                APImage filtered = original.clone();
                Filters.posterize(filtered);
                filtered.draw();
            } else if (input == 9) {
                System.out.print("Enter scale factor to enlarge: ");
                double scaleFactor = scanner.nextDouble();
                original.draw();
                APImage enlarged = Filters.enlarge(original, scaleFactor);
                enlarged.draw();
                } else if (input == 10){
                    System.out.print("Enter red adjustment: ");
                    int redAdj = scanner.nextInt();
                    System.out.print("Enter green adjustment: ");
                    int greenAdj = scanner.nextInt();
                    System.out.print("Enter blue adjustment: ");
                    int blueAdj = scanner.nextInt();
                    original.draw();
                    APImage colorFiltered = Filters.colorFilter(original, redAdj, greenAdj, blueAdj);
                    colorFiltered.draw();
                } else {
                    System.out.println("Invalid option. Please try again.");
            }
        }                    

        if (scanner != null) {
            scanner.close();
        }
        System.out.println("Exiting...");
    }
}
