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
public class App {
    private int count = 0;
    
    public static void main(String[] args){
        App app = new App();
        app.doWork();
       
    }
    
    public synchronized void increment(){
        count++;
    }
    
    public void doWork(){
        Thread t1 = new Thread(new Runnable() {

            public void run() {
                for(int i =0; i< 10000; i++){
                    increment();
                }
            }
        });
        
        Thread t2 = new Thread(new Runnable() {

          public void run() {
              for(int i =0; i< 10000; i++){
                  count++;
              }
          }
        });
        
        t1.start();
        t2.start();
        
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Count is " + count);
        
    }
}
