/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktop;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.json.JSONArray;
import org.json.JSONException;
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
    public static String PATH = "";
    private String cadena;
    Conexion cx = new Conexion();

    public Clasificador(String cadena) {
        this.cadena = cadena;

    }

    public int predecir(String name, int id, String id_parking, String URL) {

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
//
            //clasifica la instancia según el modelo
            double pred = cls.classifyInstance(inst);
//            System.out.println(pred);
            //presenta la clasificación

            String state = "";
            if (pred < 1) {
                state = "Ocupado";
            } else {
                state = "Libre";
            }

            System.out.println("Predicción: " + name + " " + id + " " + id_parking + " " + state);

            getJson(URL, id, name, id_parking, (int) pred);

            return (int) pred;
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
    }

    public void getJson(String URL, int id, String name, String id_parking, int state) throws JSONException, FileNotFoundException, IOException {

        GetPath.BASE_URI = URL + "app";
        GetPath gp = new GetPath();
        String json = gp.getParklot(String.valueOf(id), name);
        JSONArray objects = new JSONArray(json);

        for (int i = 0; i < objects.length(); i++) {
            String ident = objects.getJSONObject(i).getString("idSpaceParking");
            if (ident.equals(id_parking)) {
                objects.getJSONObject(i).remove("statusParking");
//                System.out.println(objects.getJSONObject(i));
                objects.optJSONObject(i).put("statusParking", state);
//                System.out.println(objects.getJSONObject(i));
            }
        }
        
        
                ClassMain cm = new ClassMain();
                cm.Write(objects.toString(), PATH+"\\"+name+".json", false);
    }
}
