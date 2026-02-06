import images.*;
import java.util.Scanner;

public class MenuDriver {
    private Scanner scanner;
    private APImage image;
    private int input;

    public MenuDriver() {
        scanner = new Scanner(System.in);
        System.out.print("Enter image file name (default: smokey.jpg): ");
        String imageName = scanner.nextLine();
        try{
            imageName = "test\\src\\images\\" + imageName + ".jpg";
            image = new APImage(imageName);
        } catch (Exception e){
            System.out.println("Invalid file name. Using default image.");
            imageName = "test\\src\\images\\smokey.jpg";
            image = new APImage(imageName);
        }

    }
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

    public void runMenu(){
        while (input != 11) {
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
                Filters.sepia(image);
                image.draw();
            } else if (input == 5) {
                Filters.blur(image);
                image.draw();
            } else if (input == 6) {
                Filters.sharpen(image);
                image.draw();
            } else if (input == 7) {
                Filters.negative(image);
                image.draw();
            } else if (input == 8) {
                Filters.posterize(image);
                image.draw();
            } else if (input == 9) {
                System.out.print("Enter scale factor to enlarge: ");
                double scaleFactor = scanner.nextDouble();
                image = Filters.enlarge(image, scaleFactor);
                image.draw();
                } else if (input == 10){
                    System.out.print("Enter red adjustment: ");
                    int redAdj = scanner.nextInt();
                    System.out.print("Enter green adjustment: ");
                    int greenAdj = scanner.nextInt();
                    System.out.print("Enter blue adjustment: ");
                    int blueAdj = scanner.nextInt();
                    image = Filters.colorFilter(image, redAdj, greenAdj, blueAdj);
                    image.draw();
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
