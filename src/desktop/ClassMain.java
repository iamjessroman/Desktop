/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktop;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jessi
 */
public class ClassMain {

    public String[] Read() throws FileNotFoundException {
        String[] config = null;
        String ruta = "./data/configuration.txt";
        File archivo = new File(ruta);
        FileReader fr = new FileReader(ruta);
        BufferedReader br = new BufferedReader(fr);
        
        try {
            if (archivo.exists()) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    config=linea.split(",");
                }
                
            } else {
                   config=null;
            }
            fr.close();
        } catch (IOException ex) {
            Logger.getLogger(MyApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
        return config;
    }
}
