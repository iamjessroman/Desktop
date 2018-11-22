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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ClassExecutors {

    public static void main(String[] args) {
        System.out.println("Inside : " + Thread.currentThread().getName());

        System.out.println("Creating Executor Service with a thread pool of Size 8");
        ExecutorService executorService = Executors.newFixedThreadPool(8);

        Runnable task1 = () -> {
            System.out.println("Executing Task1 inside : " + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException ex) {
                throw new IllegalStateException(ex);
            }
        };

        Runnable task2 = () -> {
            System.out.println("Executing Task2 inside : " + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException ex) {
                throw new IllegalStateException(ex);
            }
        };

        Runnable task3 = () -> {
            System.out.println("Executing Task3 inside : " + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException ex) {
                throw new IllegalStateException(ex);
            }
        };

        Runnable task4 = () -> {
            System.out.println("Executing Task4 inside : " + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException ex) {
                throw new IllegalStateException(ex);
            }
        };

        Runnable task5 = () -> {
            System.out.println("Executing Task5 inside : " + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(6);
            } catch (InterruptedException ex) {
                throw new IllegalStateException(ex);
            }
        };

        Runnable task6 = () -> {
            System.out.println("Executing Task6 inside : " + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ex) {
                throw new IllegalStateException(ex);
            }
        };

        System.out.println("Submitting the tasks for execution...");
        executorService.submit(task1);
        executorService.submit(task2);
        executorService.submit(task3);
        executorService.submit(task4);
        executorService.submit(task5);
        executorService.submit(task6);
        

        executorService.shutdown();
    }
}
