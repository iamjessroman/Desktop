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
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import static java.util.concurrent.TimeUnit.SECONDS;
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

    ScheduledExecutorService scheduler = null;
    public static ScheduledFuture<?> beeperHandle;

    public void RUN(String id, int n, String URL, String path) throws IOException, JSONException {

        GetParking.BASE_URI = URL + "app";
        GetParking gp = new GetParking();
        GetJson gj = new GetJson();

        Runnable task = null;
        System.out.println("Inside : " + Thread.currentThread().getName());
        System.out.println("Creating Executor Service with a thread pool of Size " + n);
        scheduler = Executors.newScheduledThreadPool(n);
        task = new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("Executing Task inside : " + Thread.currentThread().getName());
                    gj.get(gp.getParklot(id), path, n, Integer.valueOf(id), URL);
                    TimeUnit.SECONDS.sleep(1);
                } catch (Exception ex) {
                    Logger.getLogger(ClassExecutorsWhile.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };

        beeperHandle = scheduler.scheduleAtFixedRate(task, 1, 1, SECONDS);
        scheduler.schedule(new Runnable() {
            public void run() {
                beeperHandle.cancel(true);
            }
        }, state, SECONDS);

//
//        tasks.add(task);
//        tasks.stream().forEach(executorService::submit);
//        executorService.shutdown();
    }

    public void stop() {
        beeperHandle.cancel(false);

    }

}
