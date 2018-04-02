/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practice;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kslea_000
 */
public class BlockingQ {
    private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
    
    public static void main(String[] fdsa){
        Thread t1 = new Thread(new Runnable(){
            public void run(){
                producer();
            }
        });
        
        Thread t2 = new Thread(new Runnable(){
            public void run(){
                try {
                    consumer();
                } catch (InterruptedException ex) {
                    Logger.getLogger(BlockingQ.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        t1.start();
        t2.start();
        
//        try {
//            t1.join();
//            t2.join();
//        } catch (InterruptedException ex) {
//            Logger.getLogger(BlockingQ.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        
    }
    
    private static void producer(){
        Random random = new Random();
        
        while(true){
            try {
                queue.put(random.nextInt(100));
            } catch (InterruptedException ex) {
                Logger.getLogger(BlockingQ.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
    private static void consumer() throws InterruptedException{
        Random random = new Random();
        
        while(true){
           Thread.sleep(100);
           
           if(random.nextInt(10) == 0){
               int i = queue.take();
               System.out.println("Taken value: " + i + " queue size: " + queue.size());
           }
            
        }
    }
}
