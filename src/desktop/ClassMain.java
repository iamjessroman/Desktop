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
import java.io.InputStreamReader;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author jessi
 */
public class ClassMain {

    public String[] getConexion() throws FileNotFoundException {
        String[] res = null;
        try {
            String ruta = "./config/conexion.txt";
            String[] conexion = ReadArray(ruta);

            if (conexion == null) {
                res = null;
                JOptionPane.showMessageDialog(null, "No se ha configurado la conexión a base de datos \n Ir a la Pestaña 'Conexión' y 'Guardar'", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                res = conexion;
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "No se ha configurado la conexión a base de datos \n Ir a la Pestaña 'Conexión' y 'Guardar'", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return res;
    }

    public boolean isEmpty(String w) {
        boolean r = w.length() < 1 ? true : false;
        return r;
    }

    public boolean isNumber(char ch) {
        return ch >= '0' && ch <= '9';
    }

    public boolean isValidSignal(char ch, String text) {
        if ((text == null || "".equals(text.trim())) && ch == '-') {
            return true;
        }

        return false;
    }

    public boolean validatePoint(char ch, String text) {
        if (ch != '.') {
            return false;
        }

        if (text == null || "".equals(text.trim())) {
            return false;
        } else if ("-".equals(text)) {
        }

        return true;
    }

    public String[] ReadArray(String ruta) throws FileNotFoundException {
        String[] arrays = null;
        File archivo = new File(ruta);
        FileReader fr = new FileReader(ruta);
        BufferedReader br = new BufferedReader(fr);

        try {
            if (archivo.exists()) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    arrays = linea.split(",");
                }

            } else {
                arrays = null;
            }
            fr.close();
        } catch (IOException ex) {
            
        }
        return arrays;
    }

    public void Write(String Text, String ruta, boolean b) {
        try {
            File archivo = new File(ruta);
            BufferedWriter bw;
            FileWriter fw;

            //Test
//            System.out.println(Text);
            if (archivo.exists()) {
                fw = new FileWriter(archivo.getAbsoluteFile(), b);
                bw = new BufferedWriter(fw);
                bw.write(Text + "\n");

            } else {
                archivo.createNewFile();
                fw = new FileWriter(archivo.getAbsoluteFile(), b);
                bw = new BufferedWriter(fw);
                bw.write(Text + "\n");
            }
            bw.close();
        } catch (IOException ex) {
            
        }

    }

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
            
        }
        return config;
    }

    public void WriteMix(String Text, String ruta) {

        try {
            File archivo = new File(ruta);
            BufferedWriter bw;
            FileWriter fw;

            if (archivo.exists()) {
                fw = new FileWriter(archivo.getAbsoluteFile(), true);
                bw = new BufferedWriter(fw);
                bw.write(Text + "\n");

            } else {
                archivo.createNewFile();
                fw = new FileWriter(archivo.getAbsoluteFile(), true);
                bw = new BufferedWriter(fw);
                bw.write(Text);
            }
            bw.close();

        } catch (IOException ex) {

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

        }
        return config;
    }

    public void WriteEstados(String Text, String ruta) {

        try {

            File archivo = new File(ruta);
            BufferedWriter bw;
            FileWriter fw;

            if (archivo.exists()) {
                archivo.delete();
                archivo.createNewFile();
                fw = new FileWriter(archivo.getAbsoluteFile(), true);
                bw = new BufferedWriter(fw);
                bw.write(Text + "\n");

            } else {
                archivo.createNewFile();
                fw = new FileWriter(archivo.getAbsoluteFile(), true);
                bw = new BufferedWriter(fw);
                bw.write(Text);
            }
            bw.close();

        } catch (IOException ex) {
        }

    }
}
