/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practice;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kslea_000
 */

class Object{
    int num;
    boolean valueSet = false;
    public synchronized void set(int num){
        
        this.num = num;
    }
    
    public synchronized int get(){
        return num;
    }
    
}

class Producer implements Runnable{
    Object q;
    Thread t;
    boolean stopThread = false;
    
    public Producer(Object q){
        this.q = q;
        t = new Thread(this, "Producer");
    }
    
    // apparently this is safer than starting in constructor
    public void start(){
        t.start();
    }
    
    public void stop(){
        
    }
    @Override
    public synchronized void run(){
        while(!stopThread){
            int i = 0;
            while(true){
                q.set(i++);
                notify();
                System.out.println("Set = " + i);
                try {
                    t.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }   
}

class Consumer implements Runnable{
    Object q;
    Thread t;
    boolean stopThread = false;
    
    public Consumer(Object q){
        this.q = q;
        t = new Thread(this, "Consumer");
    }
    
    // apparently this is safer than starting in constructor
    public void start(){
        t.start();
    }
    
    public void stop(){
        stopThread = true;
    }
    @Override
    public synchronized void run(){
        while(true){
            System.out.println("get = " + q.get());
            notify();
            try {
                t.sleep(1000);

            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }   
}
public class ProducerConsumer {
        
    public static void main(String[] args) throws InterruptedException{
        Object obj = new Object();
        Producer p = new Producer(obj);
        Consumer c = new Consumer(obj);
        Thread set = new Thread(p);
        Thread get = new Thread(c);
        set.start();
        get.start();
        
        
       
        
        
        
    }
    
}
