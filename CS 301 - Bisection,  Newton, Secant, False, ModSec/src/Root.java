
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kslea_000
 */
public class Root {

    /**
     * @param args the command line arguments
     */
    double errorRange = 0.01;
    
    public static void main(String[] args) {
        System.out.println("\nBisection Method [0,4]\n f(x) = 2x^3 - 11.7x^2 + 17.7x -5");
        // Problem 1 with [0,4] as the range and true error given.
        Bisection b1 = new Bisection(1, 0, 4, 3.563160824862055);
        b1.solve();
        
        System.out.println("\nBisection Method [1,2]\n f(x) = 2x^3 - 11.7x^2 + 17.7x -5");
        // Problem 1 with [0,4] as the range and true error given.
        Bisection b2 = new Bisection(1, 1, 2,  1.9217409317757106);
        b2.solve();
        
        System.out.println("\nBisection Method [0,1]\n f(x) = 2x^3 - 11.7x^2 + 17.7x -5");
        // Problem 1 with [0,4] as the range and true error given.
        Bisection b3 = new Bisection(1, 0, 1, 0.36509824336223606);
        b3.solve();
        
        System.out.println("\nBisection Method [1,130]\n f(x) = x + 10 -xcosh(50/x)");
        // Problem 1 with [0,4] as the range and true error given.
        Bisection b5 = new Bisection(2, 1, 130, 126.63243603998922);
        b5.solve();
        System.out.println("\nNewton Raphson at [0.5]\n f(x) = 2x^3 - 11.7x^2 + 17.7x -5");
        // Problem 1 starting at [] and true error given.
        NewtonRaphson nr1 = new NewtonRaphson(1, .5, 0.36509824336223606);
        nr1.solve();
        
        System.out.println("\nNewton Raphson at [2.873]\n f(x) = 2x^3 - 11.7x^2 + 17.7x -5");
        // Problem 1 starting at [] and true error given.
        NewtonRaphson nr2 = new NewtonRaphson(1, 2.873, 1.9217409317757106);
        nr2.solve();
        
        System.out.println("\nNewton Raphson at [3]\n f(x) = 2x^3 - 11.7x^2 + 17.7x -5");
        // Problem 1 starting at [] and true error given.
        NewtonRaphson nr3 = new NewtonRaphson(1, 3, 3.563160824862055);
        nr3.solve();
        
        System.out.println("\nNewton Raphson at [20]\n f(x) = x + 10 -xcosh(50/x)");
        // Problem 1 starting at [] and true error given.
        NewtonRaphson nr4 = new NewtonRaphson(2, 20, 126.63243603998883);
        nr4.solve();
        
        System.out.println("\nSecant Method at [3, 4]\n f(x) = 2x^3 - 11.7x^2 + 17.7x -5");
        Secant s1 = new Secant(1, 3, 4, 3.563160824862055);
        s1.solve();
        
        System.out.println("\nSecant Method at [1, 2]\n f(x) = 2x^3 - 11.7x^2 + 17.7x -5");
        Secant s2 = new Secant(1, 1, 2, 1.9217410380830566);
        s2.solve();
        
        System.out.println("\nSecant Method at [0, 1]\n f(x) = 2x^3 - 11.7x^2 + 17.7x -5");
        Secant s3 = new Secant(1, 0, 1, 0.3650986484595684);
        s3.solve();
        
        System.out.println("\nSecant Method at [2, 130]\n f(x) = x + 10 -xcosh(50/x)");
        Secant s4 = new Secant(2, 2, 130, 126.63243603998883);
        s4.solve();  
        
        System.out.println("\nFalse Position at [0, 1]\n f(x) = 2x^3 - 11.7x^2 + 17.7x -5");
        FalsePosition fp2 = new FalsePosition(1, 0, 1, 0.3650986484595684);
        fp2.solve();
        
        System.out.println("\nFalse Position at [1, 2]\n f(x) = 2x^3 - 11.7x^2 + 17.7x -5");
        FalsePosition fp1 = new FalsePosition(1, 1, 2, 1.9217410380830566);
        fp1.solve();
      
        
        System.out.println("\nFalse Position at [3, 4]\n f(x) = 2x^3 - 11.7x^2 + 17.7x -5");
        FalsePosition fp3 = new FalsePosition(1, 3, 4, 3.563160824862055);
        fp3.solve();
        
        System.out.println("\nFalse Position at [120, 130]\n f(x) = x + 10 -xcosh(50/x)");
        FalsePosition fp4 = new FalsePosition(2, 120, 130, 126.63243603998883);
        fp4.solve(); 
        
        System.out.println("\nModified Secant Method at [3]\n f(x) = 2x^3 - 11.7x^2 + 17.7x -5");
        ModifiedSecant ms1 = new ModifiedSecant(1, 3, 3.563160824862055);
        ms1.solve();
        
        System.out.println("\nModified Secant Method at [1.5]\n f(x) = 2x^3 - 11.7x^2 + 17.7x -5");
        ModifiedSecant ms2 = new ModifiedSecant(1, 1.5, 1.9217410380830566);
        ms2.solve();
        
        System.out.println("\nModified Secant Method at [1]\n f(x) = 2x^3 - 11.7x^2 + 17.7x -5");
        ModifiedSecant ms3 = new ModifiedSecant(1, 1, 0.3650986484595684);
        ms3.solve();
        
        System.out.println("\nModified Secant Method at [1]\n f(x) = x + 10 -xcosh(50/x)");
        ModifiedSecant ms4 = new ModifiedSecant(2, 1, 126.63243603998883);
        ms4.solve();  
    }
    
    
    
}

class Bisection{
    private final int prob;
    private double a, b, trueRoot;
    private double[] c = new double[100];
    private final double errorRange = 1.11e-16;
    private String filename;
    
    private FileWriter fw = null;
    
    public Bisection(int i, double a, double b, double trueRoot){
        super();
        this.a = a;
        this.b = b;
        this.trueRoot = trueRoot;
        this.prob = i;
        this.filename = "bisection" + prob + "[" + a + "," + b + "].csv";
        //
        try {
            fw = new FileWriter(filename);
        } catch (IOException ex) {
            Logger.getLogger(Bisection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public double function(double x){
        if(prob ==1)
            return 2*Math.pow(x, 3) - 11.7*Math.pow(x, 2) + 17.7*x -5;
        else if (prob == 2)
            return x + 10 - x*Math.cosh(50/x);
        else
            return 0;
    }
    
    public void solve(){
        double Ea = 1;
        double trueError = 1; 
        double val;
        // itterate for a maximum of 100 times, break when root is found
        System.out.printf("%1s %-34s %-34s %s\n", "i", "C", "Approximate Error", "True Error");

        for(int i = 0; i < 100; i++){
            
            // calculate NEW midpoint
            c[i] = (b+a)/2;
            val = function(a)*function(c[i]);
            if ( val == 0){
                System.out.println("Root");
                break;
            }
            // positive
            else if(val > 0)
                a = c[i];
            // negative
            else
                b = c[i];
            
            
            if(i > 0){
                // calculate relative error
                Ea = Math.abs((c[i] - c[i-1])/c[i]);
                // calculate true error
                trueError = Math.abs((trueRoot-c[i])/trueRoot);
                
                // write output to file for easier graphing later
                try {
                    fw.append(i + "," + c[i] + "," + Ea + "," + trueError + "\n");
                } catch (IOException ex) {
                    Logger.getLogger(Bisection.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            System.out.printf("%d %-32.16f %-32.16f %-32.16f\n", i, c[i], Ea, trueError);
            
            // check if error to exit or not
            if( Ea < errorRange)
                break;
            
        }
        try {
            fw.flush();
            fw.close();

        } catch (IOException ex) {
            Logger.getLogger(Bisection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

class FalsePosition{
    private final int prob;
    private double a, b, trueRoot;
    private double[] c = new double[100];
    private final double errorRange = 1.11e-16;
    private String filename;
    
    private FileWriter fw = null;
    
    public FalsePosition(int i, double a, double b, double trueRoot){
        super();
        this.a = a;
        this.b = b;
        this.trueRoot = trueRoot;
        this.prob = i;
        this.filename = "falseposition" + prob + "[" + a + "," + b + "].csv";
        //
        try {
            fw = new FileWriter(filename);
        } catch (IOException ex) {
            Logger.getLogger(Bisection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public double function(double x){
        if(prob ==1)
            return 2*Math.pow(x, 3) - 11.7*Math.pow(x, 2) + 17.7*x -5;
        else if (prob == 2)
            return x + 10 - x*Math.cosh(50/x);
        else
            return 0;
    }
    
    public void solve(){
        double Ea = 1;
        double trueError, val, num, denom;
        // itterate for a maximum of 100 times, break when root is found
        System.out.printf("%1s %-34s %-34s %s\n", "i", "C", "Approximate Error", "True Error");

        for(int i = 0; i < 100; i++){
            
            // calculate NEW midpoint
            num = a*function(b) - b*function(a);
            denom = function(b) - function(a);
            c[i] = num/denom;
            val = function(a)*function(c[i]);
            if ( val == 0){
                System.out.println("Root");
                break;
            }
            // positive
            else if(val > 0)
                a = c[i];
            // negative
            else
                b = c[i];
            
            if(i > 0){
                // calculate relative error
                Ea = Math.abs((c[i] - c[i-1])/c[i]);
                // calculate true error
                trueError = Math.abs((trueRoot-c[i])/trueRoot);
                System.out.printf("%d %-32.16f %-32.16f %-32.16f\n", i, c[i], Ea, trueError);
                
                // write output to file for easier graphing later
                try {
                    fw.append(i + "," + c[i] + "," + Ea + "," + trueError + "\n");
                } catch (IOException ex) {
                    Logger.getLogger(Bisection.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                // check if error to exit or not
                if( Ea < errorRange)
                    break;
            }
            
        }
        try {
            fw.flush();
            fw.close();

        } catch (IOException ex) {
            Logger.getLogger(Bisection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

class NewtonRaphson{
    private double[] newX = new double[100];
    private final double errorRange = 1.11e-16;
    private double oldX;
    private int prob;
    private double trueRoot;
    private String filename;
    private FileWriter fw = null;
    
    public NewtonRaphson(int prob, double oldX, double trueRoot){
        this.oldX = oldX;
        this.trueRoot = trueRoot;
        this.prob = prob;
        
        this.filename = "newtonraphson" + prob + "[" + oldX + "].csv";
        //
        try {
            fw = new FileWriter(filename);
        } catch (IOException ex) {
            Logger.getLogger(Bisection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public double function(double x){
        if(prob ==1){
            //double d = ((x-2)*x+1)*x-3;
            double d = 2*Math.pow(x, 3) - 11.7*Math.pow(x, 2) + 17.7*x -5;
            
            return d;
        }
        else if (prob == 2)
            return x + 10 - x*Math.cosh(50/x);
        else
            return 0;
    }
    
    public double derivativeFunction(double x){
        if(prob ==1){
            //double d = (3*x-4)*x+1;
            double d = 6*Math.pow(x, 2) - 23.4*x + 17.7;
            return d;
        }
            
        else if (prob == 2)
            
            return (50*Math.sinh(50/x))/x - Math.cosh(50/x) + 1;
        else
            return 0;
    }
    
    public void solve(){
        double Ea = 1;
        double trueError;
        
        // place the oldX in the array for the loop
        newX[0] = oldX;
        System.out.printf("%1s %-34s %-34s %s\n", "i", "C", "Approximate Error", "True Error");
        for(int i = 0; i < 100; i ++){
            // calculate X1
            double d = function(newX[i])/derivativeFunction(newX[i]);
            newX[i+1]= newX[i] - d;
            
            // calculate errors
            Ea = Math.abs((newX[i+1] - newX[i])/newX[i+1]);
            trueError = Math.abs(trueRoot - newX[i])/trueRoot;
            
            System.out.printf("%d %-32.16f %-32.16f %-32.16f\n", i, newX[i], Ea, trueError);
            
            try {
                    fw.append(i + "," + newX[i] + "," + Ea + "," + trueError + "\n");
            } catch (IOException ex) {
                Logger.getLogger(Bisection.class.getName()).log(Level.SEVERE, null, ex);
            }
            // check bound
            if(Ea < errorRange)
                break;
        }
        
        // close the file
        try {
            fw.flush();
            fw.close();

        } catch (IOException ex) {
            Logger.getLogger(Bisection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

class Secant{
    private double[] xList = new double[100];
    private final double errorRange = 1.11e-16;
    private int prob;
    private double trueRoot;
    private String filename;
    private FileWriter fw = null;
    
    public Secant(int prob, double Xn, double Xn1, double trueRoot){
        this.trueRoot = trueRoot;
        this.prob = prob;
        xList[0] = Xn;
        xList[1] = Xn1;
        
        this.filename = "secant" + prob + "[" + Xn + ", "+ Xn1 +"].csv";
        try {
            fw = new FileWriter(filename);
        } catch (IOException ex) {
            Logger.getLogger(Bisection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public double function(double x){
        if(prob ==1){
            //double d = ((x-2)*x+1)*x-3;
            double d = 2*Math.pow(x, 3) - 11.7*Math.pow(x, 2) + 17.7*x -5;
            return d;
        }
        else if (prob == 2)
            return x + 10 - x*Math.cosh(50/x);
        else
            return 0;
    }
    
    public void solve(){
        double Ea = 1;
        double trueError, fxn, fxn1, num, denom;
        
        // place the oldX in the array for the loop
        System.out.printf("%1s %-34s %-34s %s\n", "i", "X", "Approximate Error", "True Error");
        for(int i = 2; i < 100; i++){
            // calculate Xn+1
            fxn = function(xList[i-1]);
            fxn1 = function(xList[i-2]);
            num = fxn*(xList[i-1] - xList[i-2]);
            denom = fxn - fxn1;
            xList[i] = xList[i-1] - num/denom;
            
            Ea = Math.abs((xList[i] - xList[i-1])/xList[i]);
            trueError = Math.abs((trueRoot - xList[i])/trueRoot);
            
            System.out.printf("%d %-32.16f %-32.16f %-32.16f\n", i, xList[i], Ea, trueError);
            
            // write output to file for easier graphing later
            try {
                fw.append(i + "," + xList[i] + "," + Ea + "," + trueError + "\n");
            } catch (IOException ex) {
                Logger.getLogger(Secant.class.getName()).log(Level.SEVERE, null, ex);
            }
               
            if(Ea < errorRange)
                break;
        }
        
        // close the file
        try {
            fw.flush();
            fw.close();

        } catch (IOException ex) {
            Logger.getLogger(Bisection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

class ModifiedSecant{
    private double[] xList = new double[100];
    private final double errorRange = 1.11e-16;
    private int prob;
    private double trueRoot;
    private double d = 0.01;
    private String filename;
    private FileWriter fw = null;
    
    public ModifiedSecant(int prob, double Xn, double trueRoot){
        this.trueRoot = trueRoot;
        this.prob = prob;
        xList[0] = Xn;
    
        
        this.filename = "modifiedsecant" + prob + "[" + Xn + "].csv";
        try {
            fw = new FileWriter(filename);
        } catch (IOException ex) {
            Logger.getLogger(Bisection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public double function(double x){
        if(prob ==1){
            //double d = ((x-2)*x+1)*x-3;
            double d = 2*Math.pow(x, 3) - 11.7*Math.pow(x, 2) + 17.7*x -5;
            return d;
        }
        else if (prob == 2)
            return x + 10 - x*Math.cosh(50/x);
        else
            return 0;
    }
    
    public void solve(){
        double Ea = 1;
        double trueError, fxi, fxid, num, denom;
        
        // place the oldX in the array for the loop
        System.out.printf("%1s %-34s %-34s %s\n", "i", "X", "Approximate Error", "True Error");
            for(int i = 1; i < 100; i++){
            // calculate Xi+1
            fxi = function(xList[i-1]);
            fxid = function(xList[i-1] + d*xList[i-1]);
            num = fxi*d*xList[i-1];
            denom = fxid - fxi;
            xList[i] = xList[i-1] - num/denom;
            
            Ea = Math.abs((xList[i] - xList[i-1])/xList[i]);
            trueError = Math.abs((trueRoot - xList[i])/trueRoot);
            
            System.out.printf("%d %-32.16f %-32.16f %-32.16f\n", i, xList[i], Ea, trueError);
            
            // write output to file for easier graphing later
            try {
                fw.append(i + "," + xList[i] + "," + Ea + "," + trueError + "\n");
            } catch (IOException ex) {
                Logger.getLogger(Secant.class.getName()).log(Level.SEVERE, null, ex);
            }
               
            if(Ea < errorRange)
                break;
        }
        
        // close the file
        try {
            fw.flush();
            fw.close();

        } catch (IOException ex) {
            Logger.getLogger(Bisection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

