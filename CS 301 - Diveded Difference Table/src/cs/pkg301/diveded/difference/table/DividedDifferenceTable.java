/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs.pkg301.diveded.difference.table;

import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kslea_000
 */
public class DividedDifferenceTable {
    static double[][] table = new double[50][51];
    static int actualSize = 0;
    
    public static void main(String[] args) {
        enterFile(table);
        solve();
        print();
        printPolynomial();
    }
    
    public static void enterFile(double[][] table){
        Scanner s = new Scanner(System.in);
        System.out.print("Enter file name: ");
        File file = new File(s.nextLine());
        boolean flag = true;
        
        try {
            Scanner inputFile = new Scanner(file);
            Scanner line;
            double val;
            int row; int col = 0;
            while(inputFile.hasNextLine()){
                row = 0;
                line = new Scanner(inputFile.nextLine());
                while(line.hasNext()){
                    // first pass would fill x0, x1...xn
                    // second f(x0), f(x1)... f(xn)
                    val = Double.parseDouble(line.next());
                    System.out.print(val +" ");
                    table[row][col] = val;
                    row++;
                    
                    // keep track of the number of calculations "we" need
                    if (flag)
                        actualSize++;
                }
                // stop actualsize collection
                flag = false;
                col++;
                
                // add in the col to the row
                System.out.println("");
            }
            inputFile.close();
        } catch (FileNotFoundException ex) {
        }
        System.out.println("");
    }
    
    public static void solve(){
        double num, denom;
        for(int col = 1; col < actualSize; col++){
            for(int row = 0; row < actualSize - col; row++){
                System.out.println("i = " + row + " col = " + col);
                // i = 0 col = 1
                System.out.println("num " + table[row+1][col] + " " + table[row][col]);
                num = table[row+1][col] - table[row][col];
                
                System.out.println("denom " + table[row+col][0] + " " + table[row][0]);
                denom = table[row+col][0] - table[row][0];
                
                System.out.println("table["+row+"]["+(col+1)+"] = " + num/denom);
                table[row][col+1] = num/denom;
                
            }
            System.out.println("");
        }
        
        System.out.println("");
    }
    
    public static void print(){
        
        System.out.printf("%-10s", "x");
        String s = "";
        // headers
        for(int i = 0; i < actualSize; i++){
            s = "f["+ i +"]";
            System.out.printf("%-10s", s);
        }
        System.out.println("");
        for(int row = 0; row < actualSize; row++){
            System.out.printf("%-10.2f", table[row][0]);
            for(int col = 1; col < actualSize-row+1; col++){
                System.out.printf("%-10.2f", table[row][col]);
            }
            System.out.println("");
        }
                
    }
    
    public static void printPolynomial(){
        String poly = "";
        String coef = "";
        
        coef = "" + table[0][1];
        for(int i = 0; i < actualSize-1; i++){
            // build the polynomial string
            // check if positive or negative 
            // coef
            if(table[0][i+2] > 0)
                coef = coef + " + " + String.format("%.2f",table[0][i+2]);
            else
                coef = coef + String.format("%.2f",table[0][i+2]);
            
            if (table[i][0] > 0)
                poly = poly + String.format("(x-%.2f)", table[i][0]);
            else if (table[i][0] < 0)
                poly =  poly + String.format("(x%.2f)", table[i][0]);
            else
                poly =  poly + "(x)";
            
            coef = coef + poly;
        } 
        System.out.println("Polynomial = " + coef);
    }
    
    public static void printSimplifiedPolynomial(){
        ArrayList<ArrayList<Double>> simplifiedTable = new ArrayList<ArrayList<Double>>();
        for (int i = 0; i < table.length; ++i) {
            
        }
    }
    
    
}
