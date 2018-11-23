/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktop;

import java.awt.Color;
import javaxt.io.Image;

/**
 *
 * @author jessi
 */
public class Binary {

    public void transform(int i) throws Exception {
        Image image = new Image("data/image"+i+".png");
            image.getImage();
            System.out.println(image.getWidth());
        for (int j = 0; j < image.getHeight(); j++) {
            for (int k = 0; k < image.getWidth(); k++) {
                int red = image.getColor(k, j).getRed();
                int green = image.getColor(k, j).getGreen();
                int blue = image.getColor(k, j).getBlue();
                int avg = (red + green + blue) / 3;
                int p = image.getBufferedImage().getRGB(k, j);
                p = (avg << 24) | (avg << 16) | (avg << 8) | avg;
                image.getBufferedImage().setRGB(k, j, p);

            }
        }

        for (int j = 0; j < image.getHeight(); j++) {
            for (int k = 0; k < image.getWidth(); k++) {
                int red = image.getColor(k, j).getRed();
                int green = image.getColor(k, j).getGreen();
                int blue = image.getColor(k, j).getBlue();
                int number = 112;
                if (red > number && green > number && blue > number) {
                    image.setColor(k, j, Color.WHITE);
                } else {
                    image.setColor(k, j, Color.BLACK);

                }

            }

        }
        ARFFfile arff = new ARFFfile(image.getHeight(),image.getWidth());
        arff.pixels(image, i);

    }
}
