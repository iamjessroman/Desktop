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

    Conexion cx = new Conexion();

    public void transform(Image image, int i, String states) throws Exception {

        //Obtiene tamaño para las imagenes del ARFF
        String sql = "";
        String width = "";
        String height = "";
        String path_model = "";
        String path_arff = "";
        if (states == null) {
            sql = "SELECT * FROM `settings_arff` WHERE `id`=2";
            int n = 5;
            String[] temp = cx.select(sql, n, 2);

            String[] substring = temp[0].split(" columns ");
            width = substring[1];
            height = substring[2];
            String model=substring[3];
            String arff=substring[4];
            
            path_model = model.replace("/", "\\");
            path_arff = arff.replace("/", "\\");

        } else {
            sql = "SELECT * FROM `settings_arff` WHERE `id`=1";
            int n = 5;
            String[] temp = cx.select(sql, n, 2);

            String[] substring = temp[0].split(" columns ");
            width = substring[1];
            height = substring[2];
        }

        image.resize(Integer.valueOf(width), Integer.valueOf(height));

        System.out.println("num:" + i);
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

        //Test
//        image.saveAs("C:\\test\\"+i+".png");
        if (states == null) {
            String cadena = "";
            for (i = 0; i < image.getHeight(); i++) {
                for (int j = 0; j < image.getWidth(); j++) {
                    cadena += image.getColor(j, i).getRed() + ",";
                }
            }
            Clasificador.MODEL=path_model;
            Clasificador.STRUCTURE=path_arff;
            
            Clasificador c = new Clasificador(cadena);
            c.predecir();
            //System.out.println(cadena);

        } else {
            ARFFfile.pixels(image, i, states);
        }

    }
}
