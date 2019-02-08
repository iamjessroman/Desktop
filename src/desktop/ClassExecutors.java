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
import java.io.File;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

public class ClassExecutors {

    List<ImageIcon> imgs;
    int temp = 0;

    public void RUN(int n, List<ImageIcon> images) {
        List<Runnable> tasks = new ArrayList<>();
        imgs = images;

        System.out.println("Inside : " + Thread.currentThread().getName());
        System.out.println("Creating Executor Service with a thread pool of Size" + n);
        ExecutorService executorService = Executors.newFixedThreadPool(n);
        Runnable task = null;
        for (int i = 0; i < images.size(); i++) {
            task = () -> {
                System.out.println("Executing Task inside : " + Thread.currentThread().getName());
                this.foo();
                temp++;
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

    public void foo() {
        out.println(imgs.get(temp).getDescription());
    }
}
