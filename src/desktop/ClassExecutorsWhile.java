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
import java.io.IOException;
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
import org.json.JSONException;

public class ClassExecutorsWhile {

    List<Image> imgs;
    public static int state;
    int i = 0;
    int temp = 0;
    List<Runnable> tasks = new ArrayList<>();

    public void RUN(String id, int n, String URL, String path) throws IOException, JSONException {

        GetParking.BASE_URI = URL + "app";
        GetParking gp = new GetParking();
        GetJson gj = new GetJson();

        Runnable task = null;
        System.out.println("Inside : " + Thread.currentThread().getName());
        System.out.println("Creating Executor Service with a thread pool of Size " + n);
        ExecutorService executorService = Executors.newFixedThreadPool(n);
        do {
            task = new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("Executing Task inside : " + Thread.currentThread().getName());
                        gj.get(gp.getParklot(id), path, n);
                        TimeUnit.SECONDS.sleep(1);
                    } catch (Exception ex) {
                        Logger.getLogger(ClassExecutorsWhile.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            };
            tasks.add(task);
        } while (state == 0);

        tasks.stream().forEach(executorService::submit);
        executorService.shutdown();
    }

}
