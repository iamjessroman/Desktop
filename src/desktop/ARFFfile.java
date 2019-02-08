/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktop;

/**
 *
 * @author jessi
 */
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import weka.core.Attribute;
import weka.core.Debug.Random;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import javaxt.io.Image;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import Test.Test;
import static Test.Test.getPred;
import java.io.BufferedWriter;
import java.io.FileWriter;
import org.json.JSONArray;
import org.json.JSONObject;
import weka.core.converters.ArffSaver;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToNominal;

public class ARFFfile {

    public static ArrayList<Attribute> atts;
    public static Instances dataRaw;

    public ARFFfile(int Height, int Width) {
        atts = new ArrayList<Attribute>((Height * Width) + 1);
        atts.add(new Attribute("label", (ArrayList<String>) null));

        for (int i = 1; i <= Height; i++) {
            for (int j = 1; j <= Width; j++) {
                atts.add(new Attribute(i + "x" + j, Attribute.NUMERIC));
            }
        }

        atts.add(new Attribute("estado", (ArrayList<String>) null));
        dataRaw = new Instances("Parkings", atts, 0);
    }


    public static void pixels(Image image, int l) throws IOException, Exception {

        int p = 1;
        int libre = 0;
        int ocupado = 0;
        String cadena = " ";
        String[][] parkings = new String[8][2];

        double[] instanceValue1 = new double[dataRaw.numAttributes()];
        instanceValue1[0] = dataRaw.attribute(0).addStringValue(String.valueOf(l));
        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); j++) {
                int number = 175;
                if (image.getColor(j, i).getRed() > number) {
                    libre++;
                } else {
                    ocupado++;

                }
                instanceValue1[p] = image.getColor(j, i).getRed();
//                cadena=image.getColor(j, i).getRed()+",";
                p++;
            }
        }

        Conexion c = new Conexion();
        ClassMain cm = new ClassMain();
        String ruta = "./data/estados.txt";
        String est[]=cm.ReadArray(ruta);
        instanceValue1[(5001)] = dataRaw.attribute(5001).addStringValue(String.valueOf("libre"));
        c.datos();
        dataRaw.add(new DenseInstance(1.0, instanceValue1));
        String text = dataRaw.toString();
        System.out.println(text);
        cm.Write(text, "./data/test.arff",false);
//        ArffSaver saver = new ArffSaver();
//        saver.setInstances(dataRaw);
//        saver.setFile(new File("./data/test.arff"));
//        saver.writeBatch();
//        System.out.println(cadena);
//        getPred(image,l);
//

    }

}
