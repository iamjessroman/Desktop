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

public class ClassExecutors {

    List<Image> imgs;
    String[] states;
    int i = 0;
    int temp = 0;
    List<Runnable> tasks = new ArrayList<>();

    public void RUN(int n, List<Image> images, String[] types) {

        Runnable task = null;
        imgs = images;
        states = types;

        //Test
//        System.out.println(images.size());
//        System.out.println(types.length);
//
//        for (int i = 0; i < images.size(); i++) {
//            
//            imgs.get(i).saveAs("C:\\Users\\Jessica Roman\\Documents\\Tesis\\Roman\\Desktop\\data\\ParqueoA\\"+i+".jpg");
//        }
        System.out.println("Inside : " + Thread.currentThread().getName());
        System.out.println("Creating Executor Service with a thread pool of Size " + n);
        ExecutorService executorService = Executors.newFixedThreadPool(n);
        for (i = 0; i < images.size(); i++) {
            task = new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("Executing Task inside : " + Thread.currentThread().getName());
                        temp++;
                        image(temp);
                        TimeUnit.SECONDS.sleep(1);
                    } catch (Exception ex) {
                        Logger.getLogger(ClassExecutors.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            };
            tasks.add(task);
        }

        tasks.stream().forEach(executorService::submit);
        executorService.shutdown();
    }

    public void image(int j) throws Exception {
        Binary b = new Binary();
        b.transform(imgs.get(j - 1), j - 1, states[j - 1],imgs.size());
    }
}
