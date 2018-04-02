/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs.pkg431.processtest;

import java.io.IOException;

public class ProcessTest {

    public static void main(String[] args) throws IOException {
        // build process and run "java"
        ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "java");
        processBuilder.start();
    }
    
}
