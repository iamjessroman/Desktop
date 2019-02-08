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
import javaxt.io.Image;

public class ClassExecutors {

    List<Image> imgs;
    String [] states;
    int temp = 0;

    public void RUN(int n, List<Image> images, String [] types) {
        List<Runnable> tasks = new ArrayList<>();
        imgs = images;     
        states=types;
        //Test
//        System.out.println(images.size());
//        System.out.println(types.length);
        
        
        System.out.println("Inside : " + Thread.currentThread().getName());
        System.out.println("Creating Executor Service with a thread pool of Size" + n);
        ExecutorService executorService = Executors.newFixedThreadPool(n);
        Runnable task = null;
        for (int i = 0; i < images.size(); i++) {
            task = () -> {
                System.out.println("Executing Task inside : " + Thread.currentThread().getName());
                try {
                    this.image();
                    temp++;
                } catch (Exception ex) {
                    Logger.getLogger(ClassExecutors.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ClassExecutors.class.getName()).log(Level.SEVERE, null, ex);
                }

            };

            tasks.add(task);
        }

        tasks.stream().forEach(executorService::submit);

        executorService.shutdown();
    }

    public void image() throws Exception {
        Binary b = new Binary();
  
        b.transform(imgs.get(temp), temp);
        out.println(Arrays.toString(imgs.get(temp).getInputFormats()));
    }
}
