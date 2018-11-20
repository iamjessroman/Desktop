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
import javax.xml.bind.DatatypeConverter;
import javaxt.io.Image;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 *
 * @author jessi
 */
public class Images {

    public static JSONObject parking = new JSONObject();

    public static void getImage(JSONObject object) throws FileNotFoundException, IOException, JSONException {

        String url = object.getString("data-uri");
        String[] parts = url.split("data:image/jpeg;base64,");

        for (int j = 1; j < parts.length; j++) {
            //convert base64 string to binary data
            byte[] data = DatatypeConverter.parseBase64Binary(parts[j]);
            String path = "test_image" + j + ".png";
            File file = new File(path);
            try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
                outputStream.write(data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        getParkings(object);
    }

    public static void getParkings(JSONObject object) throws JSONException {
        JSONArray features = (JSONArray) object.get("objects");
        for (int i = 0; i < features.length(); i++) {
            JSONObject temp = features.getJSONObject(i);
            if (temp.getString("type").equals("rect")) {
                parking.put(String.valueOf(i), temp);
            }
        }
        process();
    }

    public static void process() throws JSONException {

        Hilos hilo1;
        Hilos hilo2;
        Hilos hilo3;
        Hilos hilo4;
        Hilos hilo5;
        Hilos hilo6;
        Hilos hilo7;
        Hilos hilo8;

        int number = parking.length();
        int numberthreads = 8;

        int cycles = number / numberthreads;
        int restante = number % numberthreads;

        int n = 1;
        
        ARFFfile arff = new ARFFfile(50,100);
        
        if (restante == 0) {
            for (int i = 0; i < cycles; i++) {
                
                 hilo1 = new Hilos(parking.getJSONObject(String.valueOf(1*n)),1);
                 hilo2 = new Hilos(parking.getJSONObject(String.valueOf(2*n)),2);
                 hilo3 = new Hilos(parking.getJSONObject(String.valueOf(3*n)),3);
                 hilo4 = new Hilos(parking.getJSONObject(String.valueOf(4*n)),4);
                 hilo5 = new Hilos(parking.getJSONObject(String.valueOf(5*n)),5);
                 hilo6 = new Hilos(parking.getJSONObject(String.valueOf(6*n)),6);
                 hilo7 = new Hilos(parking.getJSONObject(String.valueOf(7*n)),7);
                 hilo8 = new Hilos(parking.getJSONObject(String.valueOf(8*n)),8);
             
                 hilo1.start();
                 hilo2.start();
                 hilo3.start();
                 hilo4.start();
                 hilo5.start();
                 hilo6.start();
                 hilo7.start();
                 hilo8.start();
            }
        }

    }
}
