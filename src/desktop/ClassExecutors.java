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
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.swing.JLabel;

public class ClassExecutors {

    public void RUN() throws FileNotFoundException{
        List<Runnable> tasks = new ArrayList<>();
        System.out.println("Inside : " + Thread.currentThread().getName());
        ClassMain cm = new ClassMain();
        String ruta = "./data/configuraciones.txt";
        String number[] = cm.ReadArray(ruta);
        System.out.println("Creating Executor Service with a thread pool of Size " + number[0]);
        ExecutorService executorService = Executors.newFixedThreadPool(Integer.valueOf(number[0]));

        for (int i = 0; i < Integer.valueOf(number[0]); i++) {
            Runnable task = () -> {
                System.out.println("Executing Task inside : " + Thread.currentThread().getName());
                try {
                    Images img = new Images();

                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException ex) {
                    throw new IllegalStateException(ex);
                }
            };
            tasks.add(task);
        }
        
        for (int i = 0; i < tasks.size(); i++) {
            executorService.submit(tasks.get(i));
        }

        executorService.shutdown();
    }
}
