import java.util.*;
import images.*;

public class Filters {
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
    public static APImage blackAndWhite(APImage image) {
        return twoColorConvert(image, 0, 0, 0, 255, 255, 255);
    }
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
    public static APImage rotate(APImage image, int angle) {
 
        if (angle == 90) {
            return Filters.rotateRight(image);
        } else if (angle == -90) {
            return Filters.rotateLeft(image);
        } else {
            return Filters.rotate180(image);
        }

    }

    public static APImage colorFilter(APImage image, int redAdj, int greenAdj, int blueAdj) {
        int width = image.getWidth();
        int height = image.getHeight();
        APImage filteredImage = new APImage(width, height);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Pixel oldPixel = image.getPixel(x, y);
                Pixel newPixel = filteredImage.getPixel(x, y);

                int red = Math.max(0, Math.min(255, oldPixel.getRed() + redAdj));
                int green = Math.max(0, Math.min(255, oldPixel.getGreen() + greenAdj));
                int blue = Math.max(0, Math.min(255, oldPixel.getBlue() + blueAdj));

                newPixel.setRed(red);
                newPixel.setGreen(green);
                newPixel.setBlue(blue);
            }
        }
        return filteredImage;
    }
    
    private static int clamp(int v) {
        if (v < 0) return 0;
        if (v > 255) return 255;
        return v;
    }

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

    public static APImage posterize(APImage image) {
        Random random = new Random();
        int r1 = random.nextInt(256), g1 = random.nextInt(256), b1 = random.nextInt(256);
        int r2 = random.nextInt(256), g2 = random.nextInt(256), b2 = random.nextInt(256);
        twoColorConvert(image, r1, g1, b1, r2, g2, b2);
        return image;
    }

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
