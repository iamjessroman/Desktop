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
import javaxt.utils.string;
import weka.classifiers.Classifier;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Debug;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.core.converters.ConverterUtils;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Normalize;

public class Test {

    public static final String MODEL = "./data/modelo.model";
    public static final String STRUCTURE = "./data/test.arff";
    private String cadena;

    public Test(String cadena) {
        this.cadena = cadena;

    }

    public double predecir() {
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

            return pred;
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
    }
}
