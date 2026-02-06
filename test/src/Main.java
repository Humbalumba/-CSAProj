/**
 * Entry point for the Image Processing Program.
 */
public class Main {
    
    /**
     * Main method that starts the image processing application.
     * 
     * @param args command line arguments (not used)
     * @precondition none
     * @postcondition MenuDriver is initialized and runMenu() is called to start the interactive menu loop
     */
    public static void main(String[] args) {
        System.out.println("Welcome to the Image Processing Program");
        MenuDriver menu = new MenuDriver();
        menu.runMenu();
    }
}
