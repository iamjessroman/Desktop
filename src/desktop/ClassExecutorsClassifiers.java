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
import java.awt.image.BufferedImage;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javaxt.io.Image;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ClassExecutorsClassifiers {

    List<Image> imgs;
    List<Runnable> tasks = new ArrayList<>();
    int temp = 0;
    int ident = 0;
    String nam = "";
    String http = "";

    public void RUN(JSONArray objects, int n, Image img, String name, int id, String URL) throws JSONException {

        Runnable task = null;
        nam = name;
        ident = id;
        http = URL;

        System.out.println("Inside : " + Thread.currentThread().getName());
        System.out.println("Creating Executor Service with a thread pool of Size " + n);
        ExecutorService executorService = Executors.newFixedThreadPool(n);

        for (int i = 1; i < objects.length(); i++) {

            int x = objects.getJSONObject(i).getInt("left");
            int y = objects.getJSONObject(i).getInt("top");
            int width = objects.getJSONObject(i).getInt("width");
            int height = objects.getJSONObject(i).getInt("height");
            double scaleX = objects.getJSONObject(i).getDouble("scaleX");
            double scaleY = objects.getJSONObject(i).getDouble("scaleY");
            JSONArray object = objects.getJSONObject(i).getJSONArray("objects");
            String text = object.getJSONObject(1).getString("text");

            Image parklot = img.copyRect(x, y, Math.round(width * (float) scaleX), Math.round(height * (float) scaleY));;

            task = new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("Executing Task inside : " + Thread.currentThread().getName());
                        temp++;
                        image(parklot, text);
                        TimeUnit.SECONDS.sleep(1);
                    } catch (Exception ex) {
                        Logger.getLogger(ClassExecutorsClassifiers.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            };
            tasks.add(task);
        }

        tasks.stream().forEach(executorService::submit);
        executorService.shutdown();
    }

    public void image(Image j, String id_parking) throws Exception {
        Binary b = new Binary();
        b.transform(j, temp, null, 0, nam, ident, id_parking, http);
    }
}
