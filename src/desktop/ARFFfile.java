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
import java.io.BufferedWriter;
import java.io.FileWriter;
import javax.swing.JOptionPane;
import org.json.JSONArray;
import org.json.JSONObject;
import weka.core.converters.ArffSaver;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToNominal;

public class ARFFfile {

    public static int Height;
    public static int Width;
    public static ArrayList<Attribute> atts;
    public static Instances dataRaw;
    public static int timer;
    static JFrameCurrent jc = new JFrameCurrent();

    public ARFFfile() {
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

    public static void pixels(Image image, int l, String states) throws IOException, Exception {
        timer++;
        int p = 1;

        double[] instanceValue1 = new double[dataRaw.numAttributes()];
        instanceValue1[0] = dataRaw.attribute(0).addStringValue(String.valueOf(l));
        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); j++) {
                instanceValue1[p] = image.getColor(j, i).getRed();
                p++;
            }
        }

        instanceValue1[(2501)] = dataRaw.attribute(2501).addStringValue(states);
        dataRaw.add(new DenseInstance(1.0, instanceValue1));

        if (timer == 5) {
            JFrameCurrent.ARFF = dataRaw.toString();
            jc.setVisible(true);
        }

    }

}
