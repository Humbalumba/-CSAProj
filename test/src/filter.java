import java.util.*;
import images.*;

public class filter {
    
    public APImage blackAndWhite(APImage image) {
        for (Pixel p : image) {
            int red = p.getRed();
            int green = p.getGreen();
            int blue = p.getBlue();
            
            int average = (red + green + blue) / 3;
            
            if (average < 128) {
                p.setRed(0);
                p.setGreen(0);
                p.setBlue(0);
            } else {
                p.setRed(255);
                p.setGreen(255);
                p.setBlue(255);
            }
        }
        return image;
    }
    public APImage grayscale(APImage image) {
        for (Pixel p: image) {
            int red = p.getRed();
            int green = p.getGreen();
            int blue = p.getBlue();

            int average = (red + green + blue) / 3;

            p.setRed(average);
            p.setGreen(average);
            p.setBlue(average);
        }
        return image;
    }

    
}
