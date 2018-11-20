package Test;

import static desktop.ARFFfile.dataRaw;
import javaxt.io.Image;
import javaxt.utils.string;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jessi
 */
public class Test {

    public static String getPred(Image image, int i) {
        int libre = 0;
        int ocupado = 0;

        String estado = " ";

        for (int j = 0; j < image.getHeight(); j++) {
            for (int k = 0; k < image.getWidth(); k++) {
                int number = 112;
                if (image.getColor(k, j).getRed() > number) {
                    libre++;
                } else {
                    ocupado++;

                }
            }
        }
        if (libre > ocupado) {
             estado = "libre";
        } else {
            estado = "ocuapdo";
        }

        return null;

    }
}
