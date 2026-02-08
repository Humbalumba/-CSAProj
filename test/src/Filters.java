import java.util.*;
import images.*;

/**
 * Image processing class with multiple filter methods
 */
public class Filters {
    
    /**
     * Converts an image to two colors based on a threshold value (128).
     * 
     * @param image the image to convert 
     * @param r1 red value for dark pixels (0-255)
     * @param g1 green value for dark pixels (0-255)
     * @param b1 blue value for dark pixels (0-255)
     * @param r2 red value for bright pixels (0-255)
     * @param g2 green value for bright pixels (0-255)
     * @param b2 blue value for bright pixels (0-255)
     * @return the modified image with two-color conversion applied
     * @precondition image is not null; color values are integers in range [0, 255]
     * @postcondition all pixels in image are set to either (r1,g1,b1) or (r2,g2,b2) based on average brightness threshold of 128
     */
    public static APImage twoColorConvert(APImage image, int r1, int g1, int b1, int r2, int g2, int b2) {
        int width = image.getWidth();
        int height = image.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Pixel p = image.getPixel(x, y);
                int red = p.getRed();
                int green = p.getGreen();
                int blue = p.getBlue();
                
                int average = (red + green + blue) / 3;
                
                if (average < 128) {
                    p.setRed(r1);
                    p.setGreen(g1);
                    p.setBlue(b1);
                } else {
                    p.setRed(r2);
                    p.setGreen(g2);
                    p.setBlue(b2);
                }
            }
        }
        return image;
    }
    
    /**
     * Converts an image to black and white.
     * 
     * @param image the image to convert 
     * @return the modified image in black and white
     * @precondition image is not null
     * @postcondition all pixels in image are either black (0,0,0) or white (255,255,255) based on brightness
     */
    public static APImage blackAndWhite(APImage image) {
        return twoColorConvert(image, 0, 0, 0, 255, 255, 255);
    }
    
    /**
     * Converts an image to grayscale.
     * 
     * @param image the image to convert
     * @return the modified image in grayscale
     * @precondition image is not null
     * @postcondition all pixels in image have equal R, G, B values equal to the average of their original RGB
     */
    public static APImage grayscale(APImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
            Pixel p = image.getPixel(x, y);
            int red = p.getRed();
            int green = p.getGreen();
            int blue = p.getBlue();

            int average = (red + green + blue) / 3;

            p.setRed(average);
            p.setGreen(average);
            p.setBlue(average);
            }
        }
        return image;
    }

    /**
     * Rotates an image 90 degrees counterclockwise.
     * 
     * @param image the image to rotate
     * @return a new rotated image with swapped dimensions
     * @precondition image is not null
     * @postcondition a new APImage is returned with dimensions (height, width) of original; original image is unmodified
     */
    public static APImage rotateLeft(APImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        Pixel[][] rotatedPixels = new Pixel[height][width];
        
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Pixel originalPixel = image.getPixel(x, y);
                rotatedPixels[y][width - 1 - x] = originalPixel;
            }
        }
        
        APImage rotatedImage = new APImage(height, width);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                rotatedImage.setPixel(y, x, rotatedPixels[y][x]);
            }
        }
        return rotatedImage;
    }
    
    /**
     * Rotates an image 90 degrees clockwise.
     * 
     * @param image the image to rotate
     * @return a new rotated image with swapped dimensions
     * @precondition image is not null
     * @postcondition a new APImage is returned with dimensions (height, width) of original; original image is unmodified
     */
    public static APImage rotateRight(APImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        Pixel[][] rotatedPixels = new Pixel[height][width];
        
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Pixel originalPixel = image.getPixel(x, y);
                rotatedPixels[height - 1 - y][x] = originalPixel;
            }
        }
        
        APImage rotatedImage = new APImage(height, width);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                rotatedImage.setPixel(y, x, rotatedPixels[y][x]);
            }
        }
        return rotatedImage;
    }
    
    /**
     * Rotates an image 180 degrees.
     * 
     * @param image the image to rotate 
     * @return a new rotated image with same dimensions
     * @precondition image is not null
     * @postcondition a new APImage is returned with same dimensions; pixels are flipped both horizontally and vertically; original image is unmodified
     */
    public static APImage rotate180(APImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        Pixel[][] rotatedPixels = new Pixel[height][width];
        
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Pixel originalPixel = image.getPixel(x, y);
                rotatedPixels[height - 1 - y][width - 1 - x] = originalPixel;
            }
        }
        
        APImage rotatedImage = new APImage(height, width);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                rotatedImage.setPixel(y, x, rotatedPixels[y][x]);
            }
        }
        return rotatedImage;
    }
    
    /**
     * Rotates an image by specified angle.
     * 
     * @param image the image to rotate
     * @param angle rotation angle in degrees (90, -90, or 180)
     * @return the rotated image
     * @precondition image is not null; angle is one of 90, -90, or 180
     * @postcondition image is rotated by the specified angle; returned image has appropriate dimensions for the rotation
     */
    public static APImage rotate(APImage image, int angle) {
 
        if (angle == 90) {
            return Filters.rotateRight(image);
        } else if (angle == -90) {
            return Filters.rotateLeft(image);
        } else {
            return Filters.rotate180(image);
        }

    }

    /**
     * Applies a sepia effect to an image.
     * 
     * @param image the image to process
     * @return the modified image with sepia effect
     * @precondition image is not null
     * @postcondition image has sepia applied; RGB values adjusted
     */
    public static APImage sepia(APImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Pixel p = image.getPixel(x, y);
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
        }
        return image;
    }
    /**
     * Clamps a value to the valid RGB range.
     * 
     * @param v the value to clamp
     * @return the clamped value in range [0, 255]
     * @precondition none
     * @postcondition return value is an integer in range [0, 255]; values less than 0 return 0, values greater than 255 return 255, otherwise returns original value
     */
    private static int clamp(int v) {
        if (v < 0) return 0;
        if (v > 255) return 255;
        return v;
    }

    /**
     * Applies a color filter by adjusting RGB values.
     * 
     * @param image the image to filter
     * @param redAdj adjustment to red channel (-255 to 255)
     * @param greenAdj adjustment to green channel (-255 to 255)
     * @param blueAdj adjustment to blue channel (-255 to 255)
     * @return a new image with color adjustments applied
     * @precondition image is not null; adjustment values are integers between -255 and 255
     * @postcondition a new APImage is returned with same dimensions as original; each pixel's RGB values are adjusted and clamped to [0, 255]; original image is unmodified
     */
    public static APImage colorFilter(APImage image, int redAdj, int greenAdj, int blueAdj) {
        int width = image.getWidth();
        int height = image.getHeight();
        APImage filteredImage = new APImage(width, height);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Pixel oldPixel = image.getPixel(x, y);
                Pixel newPixel = filteredImage.getPixel(x, y);

                int red = clamp(oldPixel.getRed() + redAdj);
                int green = clamp(oldPixel.getGreen() + greenAdj);
                int blue = clamp(oldPixel.getBlue() + blueAdj);

                newPixel.setRed(red);
                newPixel.setGreen(green);
                newPixel.setBlue(blue);
            }
        }
        return filteredImage;
    }
    

    /**
     * Applies a blur effect to an image
     * 
     * @param image the image to blur
     * @return the modified image with blur effect applied
     * @precondition image is not null
     * @postcondition each pixel in image is replaced with the average of itself and its 8 neighboring pixels; edge pixels use available neighbors only
     */
    public static APImage blur(APImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        int[][] r = new int[width][height];
        int[][] g = new int[width][height];
        int[][] b = new int[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Pixel p = image.getPixel(x, y);
                r[x][y] = p.getRed();
                g[x][y] = p.getGreen();
                b[x][y] = p.getBlue();
            }
        }

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int rSum = 0, gSum = 0, bSum = 0, count = 0;
                for (int dx = -1; dx <= 1; dx++) {
                    for (int dy = -1; dy <= 1; dy++) {
                        int nx = x + dx;
                        int ny = y + dy;
                        if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                            rSum += r[nx][ny];
                            gSum += g[nx][ny];
                            bSum += b[nx][ny];
                            count++;
                        }
                    }
                }
                Pixel p = image.getPixel(x, y);
                p.setRed(clamp(rSum / count));
                p.setGreen(clamp(gSum / count));
                p.setBlue(clamp(bSum / count));
            }
        }
        return image;
    }

    /**
     * Applies a sharpen effect to an image.
     * 
     * @param image the image to sharpen
     * @return the modified image with sharpening effect applied
     * @precondition image is not null
     * @postcondition image edges are enhanced; pixel values are clamped to [0, 255]; original dimensions unchanged
     */
    public static APImage sharpen(APImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        int[][] r = new int[width][height];
        int[][] g = new int[width][height];
        int[][] b = new int[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Pixel p = image.getPixel(x, y);
                r[x][y] = p.getRed();
                g[x][y] = p.getGreen();
                b[x][y] = p.getBlue();
            }
        }

        int[][] kernel = {{0, -1, 0}, {-1, 5, -1}, {0, -1, 0}};

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int rSum = 0, gSum = 0, bSum = 0;
                for (int kx = -1; kx <= 1; kx++) {
                    for (int ky = -1; ky <= 1; ky++) {
                        int nx = x + kx;
                        int ny = y + ky;
                        int kval = kernel[kx + 1][ky + 1];
                        if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                            rSum += kval * r[nx][ny];
                            gSum += kval * g[nx][ny];
                            bSum += kval * b[nx][ny];
                        }
                    }
                }
                Pixel p = image.getPixel(x, y);
                p.setRed(clamp(rSum));
                p.setGreen(clamp(gSum));
                p.setBlue(clamp(bSum));
            }
        }
        return image;
    }

    /**
     * Inverts all colors in an image (creates a negative).
     * 
     * @param image the image to invert 
     * @return the modified image with inverted colors
     * @precondition image is not null
     * @postcondition each RGB value in image is replaced with (255 - original value); dimensions unchanged
     */
    public static APImage negative(APImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
            Pixel p = image.getPixel(x, y);
            p.setRed(255 - p.getRed());
            p.setGreen(255 - p.getGreen());
            p.setBlue(255 - p.getBlue());
            }
        }
        return image;
    }

    /**
     * Converts image to two random colors (posterization effect).
     * 
     * @param image the image to posterize
     * @return the modified image with random color posterization
     * @precondition image is not null
     * @postcondition image has two randomly generated colors applied based on brightness threshold of 128; dimensions unchanged
     */
    public static APImage posterize(APImage image) {
        Random random = new Random();
        int r1 = random.nextInt(256), g1 = random.nextInt(256), b1 = random.nextInt(256);
        int r2 = random.nextInt(256), g2 = random.nextInt(256), b2 = random.nextInt(256);
        twoColorConvert(image, r1, g1, b1, r2, g2, b2);
        return image;
    }

    /**
     * Enlarges an image by a specified scale factor.
     * 
     * @param image the image to enlarge
     * @param scale scaling factor (positive value; >1 enlarges, <1 shrinks)
     * @return a new image scaled by the specified factor
     * @precondition image is not null; scale is positive (scale > 0)
     * @postcondition a new APImage is returned with dimensions scaled by factor; minimum dimensions are 1x1; original image is unmodified
     */
    public static APImage enlarge(APImage image, double scale) {
        if (scale <= 0) return image;
        int width = image.getWidth();
        int height = image.getHeight();
        int newW = Math.max(1, (int) Math.round(width * scale));
        int newH = Math.max(1, (int) Math.round(height * scale));
        APImage result = new APImage(newW, newH);
        for (int x = 0; x < newW; x++) {
            for (int y = 0; y < newH; y++) {
                int srcX = Math.min(width - 1, (int) (x / scale));
                int srcY = Math.min(height - 1, (int) (y / scale));
                Pixel src = image.getPixel(srcX, srcY);
                // copy color to target pixel
                Pixel dst = result.getPixel(x, y);
                dst.setRed(src.getRed());
                dst.setGreen(src.getGreen());
                dst.setBlue(src.getBlue());
            }
        }
        return result;
    }
    
}
