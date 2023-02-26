package PicturePrep;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import static java.lang.Math.PI;

public class PicturePreparation {
    /**
     *  Defines the filename of the polarimage, that is created by imagePrep
     */
    public static final String NAME_OF_POLAR_IMAGE = "polarImage.jpeg";

    /**
     * Converts the given cartesian image into a polarimage, converts that into a grayscale-image and applies a gradient filter to it.
     * @param file the Image to be processed
     * @return  a 2 dimensional array of integers, containing the inverted brightness values of the polarimage
     * @throws IOException Throws a IOException if the given file can not be read.
     */
    public static int [][] imagePrep(File file) throws IOException {
        BufferedImage cartesianImage = ImageIO.read(file);
        int polarWidth = cartesianImage.getWidth();
        int polarHeight = cartesianImage.getHeight()/2;
        BufferedImage polarImage = new BufferedImage(polarWidth, polarHeight, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < cartesianImage.getHeight()/2; y++) {
            for (int x = 0; x < cartesianImage.getWidth(); x++) {
                double theta = 2 * PI * x / cartesianImage.getWidth();
                double r = y;

                int polarX = (int) (r * Math.cos(theta) + cartesianImage.getWidth() / 2);
                int polarY = (int) (r * Math.sin(theta) + cartesianImage.getHeight() / 2);
                if (polarX >= 0 && polarX < cartesianImage.getWidth() && polarY >= 0 && polarY < cartesianImage.getHeight()) {
                    polarImage.setRGB(x, y, cartesianImage.getRGB(polarX, polarY));
                }
            }
        }
        // polarimage gets saved, to display it with the dijkstra path
        ImageIO.write(polarImage, "jpeg", new File(NAME_OF_POLAR_IMAGE));


        // The image gets converted into a grayscale-image, to get the brightness of the pixels
        BufferedImage grayImage = new BufferedImage(polarWidth, polarHeight, BufferedImage.TYPE_INT_RGB);
        int[][] pixel = new int[polarWidth][polarHeight];

        for (int x = 0; x < polarWidth; x++){
            for (int y = 0; y < polarHeight; y++){Color color = new Color(polarImage.getRGB(x,y));
                int z = (int) (0.3 * color.getRed() + 0.59 * color.getGreen() + 0.11 * color.getBlue());
                Color gray = new Color(z,z,z);
                grayImage.setRGB(x,y,gray.getRGB());
            }
        }
        ImageIO.write(polarImage, "jpeg", new File("graymap.jpeg"));

        // Die Farben des Bildes werden invertiert, damit man den hellsten Weg mithilfe des Dijkstra-Algorithmus finden kann.
        // The colors of the image get inverted, to enable a search for the brightest path with a dijkstra-algorithm
        BufferedImage invertedImage = new BufferedImage(polarWidth, polarHeight-2,BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < polarWidth; x++) {
            for (int y = 0; y < (polarHeight - 2); y++) {
                Color invertedColor = new Color(255-grayImage.getRGB(x,y));
                invertedImage.setRGB(x,y,invertedColor.getRGB());
                pixel[x][y] = invertedColor.getRGB();
            }
        }

        ImageIO.write(polarImage, "jpeg", new File("inverted_graymap.jpeg"));


        // gradient-filter is applied
        BufferedImage gradientImage = new BufferedImage(polarWidth, polarHeight - 2, polarImage.getType());
        for (int x = 0; x < polarWidth; x++){
            for (int y = 1; y < (polarHeight-1); y++){
                gradientImage.setRGB(x,y-1,pixel[x][y-1] * (-1) + pixel[x][y + 1]);
            }
        }
        ImageIO.write(polarImage, "jpeg", new File("gradient.jpeg"));

        // The size of the image is halved, to make the size adequate for presentation.
        int [][]smallerPicture = new int[polarWidth/2][polarHeight/2];
        BufferedImage smallerImage = new BufferedImage(polarWidth/2, polarHeight/2, polarImage.getType());
        for(int i = 0; i < polarWidth/2; ++i){
            for(int j = 0; j < polarHeight/2; ++j){
                smallerPicture[i][j] = (pixel[i*2][j*2] + pixel[i*2 + 1][j * 2] + pixel[i * 2][j * 2 + 1] + pixel[i * 2 + 1][j * 2 + 1])/4;
                smallerImage.setRGB(i, j, smallerPicture[i][j]);
                int p = smallerImage.getRGB(i,j);
                int red = (p>>16)&0xff;
                int green = (p>>8)&0xff;
                int blue = p&0xff;
                smallerPicture[i][j] = (red + green + blue)/3;
            }
        }
        ImageIO.write(polarImage, "jpeg", new File("smallerimage.jpeg"));

        return smallerPicture;
    }
}