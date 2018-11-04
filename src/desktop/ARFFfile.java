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
import java.util.ArrayList;

import weka.core.Attribute;
import weka.core.Debug.Random;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

public class ARFFfile {

    public static void main(String[] args) {
        ArrayList<Attribute> atts = new ArrayList<Attribute>(500);
        ArrayList<Integer> classVal = new ArrayList<Integer>();

        
        atts.add(new Attribute("label", (ArrayList<String>) null));
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 100; j++) {
                classVal.add(i*100);
                atts.add(new Attribute(i+"x"+j, Attribute.NUMERIC));
            }
        }

        Instances dataRaw = new Instances("TestInstances", atts, 0);
        System.out.println("Before adding any instance");
        System.out.println("--------------------------");
        System.out.println(dataRaw);
        System.out.println("--------------------------");
//        
//                double[] instanceValue1 = new double[dataRaw.numAttributes()];
//        instanceValue1[0] = dataRaw.attribute(0).addStringValue("This is a string!");
//        for (int i = 1; i <= 500; i++) {
//        instanceValue1[i] = i*100-2;
//        dataRaw.add(new DenseInstance(1.0, instanceValue1));
//        }

        double[] instanceValue1 = new double[dataRaw.numAttributes()];

        instanceValue1[0] = dataRaw.attribute(0).addStringValue("This is a string!");
        instanceValue1[1] = 0;

        dataRaw.add(new DenseInstance(1.0, instanceValue1));
        

        double[] instanceValue2 = new double[dataRaw.numAttributes()];

        instanceValue2[0] = dataRaw.attribute(0).addStringValue("This is second string!");
        instanceValue2[1] = 1;

        dataRaw.add(new DenseInstance(1.0, instanceValue2));

        System.out.println("After adding second instance");
        System.out.println("--------------------------");
        System.out.println(dataRaw);
        System.out.println("--------------------------");

    }

}
