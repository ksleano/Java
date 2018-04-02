/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practice;

import java.util.concurrent.Semaphore;

/**
 *
 * @author kslea_000
 */
public class SemaphorePractice {
    public static void main(String[] args){
        Semaphore sem = new Semaphore(10);
        System.out.println(sem.availablePermits());
        sem.release();
        System.out.println(sem.availablePermits());
        sem.release();
        System.out.println(sem.availablePermits());
        
    }
}
