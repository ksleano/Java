/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kslea_000
 */

class Processor implements Runnable{
    private int id;
    public Processor(int id){
        this.id = id;
    }

    public void run() {
        System.out.println("Starting: " + id);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
        }
        System.out.println("Completed: " + id);

    }
     
    
}
public class ThreadPool {
    public static void main(String[] pirates){
    ExecutorService executor = Executors.newFixedThreadPool(3);
    
    for(int i = 0; i < 7; i++){
        executor.submit(new Processor(i));
    }
    // no new task will be submitted
    executor.shutdown();
    
    System.out.println("All Tasks submitted");
    
    try {
        executor.awaitTermination(1, TimeUnit.DAYS);
    } catch (InterruptedException ex) {
        Logger.getLogger(ThreadPool.class.getName()).log(Level.SEVERE, null, ex);
    }
    System.out.println("All Tasks Completed");

    
    }
}
    

    

