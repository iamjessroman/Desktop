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

    public String Read(String ruta) throws FileNotFoundException {
        String config = null;

        File archivo = new File(ruta);
        FileReader fr = new FileReader(ruta);
        BufferedReader br = new BufferedReader(fr);

        try {
            if (archivo.exists()) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    config = linea;
                }

            } else {
                config = null;
            }
            fr.close();
        } catch (IOException ex) {
            Logger.getLogger(MyApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
        return config;
    }

    public String[] ReadArray(String ruta) throws FileNotFoundException {
        String[] filters = null;
        File archivo = new File(ruta);
        FileReader fr = new FileReader(ruta);
        BufferedReader br = new BufferedReader(fr);

        try {
            if (archivo.exists()) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    filters = linea.split(",");
                }

            } else {
                filters = null;
            }
            fr.close();
        } catch (IOException ex) {
            Logger.getLogger(MyApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
        return filters;
    }

    public void Write(String Text, String ruta) {
        File archivo = new File(ruta);
        BufferedWriter bw;
        try {
            if (archivo.exists()) {
                bw = new BufferedWriter(new FileWriter(archivo));
                bw.write(Text);

            } else {
                bw = new BufferedWriter(new FileWriter(archivo));
                bw.write(Text);
            }
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(MyApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void WriteMix(String Text, String ruta) {
        File archivo = new File(ruta);
        BufferedWriter bw;
        try {
            if (archivo.exists()) {
                bw = new BufferedWriter(new FileWriter(archivo, true));
                bw.write(Text);

            } else {
                bw = new BufferedWriter(new FileWriter(archivo));
                bw.write(Text);
            }
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(MyApplication.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String[] ReadMix(String ruta) throws FileNotFoundException {
        String config[] = new String[100];
        File archivo = new File(ruta);
        FileReader fr = new FileReader(ruta);
        BufferedReader br = new BufferedReader(fr);

        try {
            if (archivo.exists()) {
                String linea;
                int n = 0;
                while ((linea = br.readLine()) != null) {
                    config[n] = linea;
                    n++;
                }

            } else {
                config = null;
            }
            fr.close();
        } catch (IOException ex) {
            Logger.getLogger(MyApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
        return config;
    }
}
