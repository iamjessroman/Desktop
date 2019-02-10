/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktop;

import weka.classifiers.Classifier;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.core.converters.ConverterUtils;

/**
 *
 * @author Joelo
 */
public class Clasificador {

    public static String MODEL = "";
    public static String STRUCTURE = "";
    private String cadena;

    public Clasificador(String cadena) {
        this.cadena = cadena;

    }

    public int predecir() {
        try {
            //carga el modelo
            Classifier cls = (Classifier) SerializationHelper.read(MODEL);

            //carga la estructura de los datos
            Instances dataSet = new ConverterUtils.DataSource(STRUCTURE).getDataSet();
            dataSet.setClassIndex(0);

            //crea una instancia vacía y le asigna la estructura
            Instance inst = new DenseInstance(dataSet.numAttributes());//Weka 3.8
            inst.setDataset(dataSet);

            //carga los datos de la instancia a ser clasificada
            //la forma de cargar los datos de la instancia dependerá de su problema
            String[] s = cadena.split(",");
            for (int i = 0; i < s.length; i++) {
                double d = Double.parseDouble(s[i]);
                inst.setValue(i, d);
            }

            //clasifica la instancia según el modelo
            double pred = cls.classifyInstance(inst);
            System.out.println(pred);
            //presenta la clasificación
            System.out.println("Predicción: " + inst.classAttribute().value((int) pred));

            return (int) pred;
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
    }
}
