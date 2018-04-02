/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs.pkg301.gauss.elimination;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GaussElimination {
    static float[][] matrix;
    static float[] coef;
    static float[] scaleVector;
    static float[] solution;
    static int size;
    static int step = 0;

    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        System.out.print("Enter 1 for filename input or 2 for manual labor: ");
        String choice = input.nextLine();
        
        switch(choice){
            case "1":
                fillArray();
                solveMatrix();
                break;
            case "2":
                //System.out.println("gulag time");
                System.out.print("Enter number of variables + coefficient: ");
                setMatrixSize(input.nextInt());
                System.out.print("Enter 1 for autoFill or 2 for manual input: ");
                fillOptions(input.nextInt());
                solveMatrix();
                break;
            default:
                System.out.println("Invalid Input");
                break;
                
        }
    }
    
    public static void fillOptions(int choice){
        switch(choice){
            case 1:
                autoFill();
                break;
            case 2:
                manualFill();
                break;
            default:
                System.out.println("Invalid option");
                break;
        }
    }
    
    public static void autoFill(){
        Random r = new Random();
        float num;
        
        float highest = 0;
        float temp;
        
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size+1; j++ ){
                    num = r.nextFloat()*100+1;
                    // ignore the last index to be placed in the coef array
                    if(j != size){
                        temp = Math.abs(num);
                        matrix[i][j] = num;
                        // keep track of the highest to fill the scale vector
                        if(temp > highest){
                           highest = temp;
                           scaleVector[i] = highest;
                        }
                    }
                    else
                        coef[i] = num;
               }
               // reset highest to 0
               highest = 0;
        }
        
    }
    public static void manualFill(){
        float highest = 0;
        float temp;
        String[] vars;
        Scanner input = new Scanner(System.in);
        
        for(int i = 0; i < size; i++){
            System.out.printf("Enter row %d: ", i);
            vars = input.nextLine().split(" ");
            for(int j = 0; j < vars.length; j++ ){
                    // ignore the last index to be placed in the coef array
                    if(j != vars.length-1){
                        temp = Math.abs(Float.parseFloat(vars[j]));
                        matrix[i][j] = Float.parseFloat(vars[j]);
                        // keep track of the highest to fill the scale vector
                        if(temp > highest){
                           highest = temp;
                           scaleVector[i] = highest;
                        }
                    }
                    else
                        coef[i] = Float.parseFloat(vars[j]);
               }
               // reset highest to 0
               highest = 0;
        }
    }
    
    public static void fillArray(){
        int line = 0;
        float highest = 0;
        float temp;
        String[] vars;
        Scanner s = new Scanner(System.in);
        
        System.out.print("Enter file name: ");
        File file = new File(s.nextLine());
        try {
            Scanner inputFile = new Scanner(file);
            // send probe to start probing size of linear eq for the matrix'
            Scanner probe = new Scanner(file);
            setMatrixSize(probe);
            
            while(inputFile.hasNext()){
               // get the whole line and split
               vars = inputFile.nextLine().split(" ");
               for(int i = 0; i < vars.length; i++ ){
                    // ignore the last index to be placed in the coef array
                    if(i != vars.length-1){
                        temp = Math.abs(Float.parseFloat(vars[i]));
                        matrix[line][i] = Float.parseFloat(vars[i]);
                        // keep track of the highest to fill the scale vector
                        if(temp > highest){
                           highest = temp;
                           scaleVector[line] = highest;
                        }
                    }
                    else
                        coef[line] = Float.parseFloat(vars[i]);
               }
               
               line++;
               // reset highest to 0
               highest = 0;
                        
            }
            inputFile.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GaussElimination.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void solveMatrix(){
        print();
        int temp;
        for(;step < matrix.length-1; step++){
            // get the scale
            temp = getScaleVectors();
            // swap highest to the top
            swap(step, temp);
            
            // redus
            reduce();
            
            print();
        }
        backsub();
        print();
      
        for(int i = 0; i < solution.length; i++){
            System.out.printf("   X[%d]=%5.2f%n", i, solution[i]);
        }
        
    }
    
    public static void reduce(){
        float pivotValue, num, dem, eq1, eq2;
        num = 0; pivotValue = 0;
        dem = matrix[step][step];
        
        
        for(int row = step+1; row < matrix.length; row++){
            num = matrix[row][step];
            pivotValue = num/dem;
            //System.out.printf("%10.2f/%.2f = %.2f %n", num, dem, pivotValue);
            for(int col = step; col < matrix.length; col++){
                eq1 = matrix[row][col];
                eq2 = matrix[step][col];
                
               // System.out.printf("%.2f - %.2f*%.2f%n", eq1, pivotValue, eq2);
                matrix[row][col] = eq1 - (pivotValue*eq2);
                //System.out.println("new val: "+ matrix[row][col]);
            }
            // now the coefficients
            eq1 = coef[row];
            eq2 = coef[step];
            coef[row] = eq1 - pivotValue*eq2;
            //System.out.println("new val coef: "+ coef[row]);

        }

        
    }
    
    public static void backsub(){
        for(int i = solution.length-1; i >= 0; i-- ){
            float sum = 0;
            for(int j = i ; j < solution.length; j++){
                sum = sum + matrix[i][j]*solution[j];
            }
            solution[i] = (coef[i]-sum) / matrix[i][i];
        }
            
    }
    
    public static void swap(int row1, int row2){
        float temp1, temp2;
        // matrix swap
        for(int i = row1; i < matrix.length; i++){
            temp1 = matrix[row1][i];
            matrix[row1][i] = matrix[row2][i];
            matrix[row2][i] = temp1;
        }
        
        // coef swap
        temp2 = coef[row1];
        coef[row1] = coef[row2];
        coef[row2] = temp2;
        
        // dont forget the scale vectors
        temp2 = scaleVector[row1];
        scaleVector[row1] = scaleVector[row2];
        scaleVector[row2] = temp2;
    }
  
    public static int getScaleVectors(){
        int maxRow = 0;
        float highest = 0;
        float ratio = 0;
        float[] numerators = new float[size];
        // get the row of the highest pivot
        for(int index = step; index <numerators.length; index++){
            // get the first column
            numerators[index] = Math.abs(matrix[index][step]);
            // calculate ratio
            ratio = numerators[index]/scaleVector[index];
            if(ratio > highest){
                maxRow = index;
                highest = ratio;
            }
        }
        //System.out.println("ratio: " + ratio + " at row " + maxRow);
        return maxRow;
    }
    
    public static void setMatrixSize(Scanner s){
        String[] temp = s.nextLine().split(" ");
        size = temp.length-1;
        matrix = new float[size][size];
        coef = new float[size];
        scaleVector = new float[size];
        solution = new float[size];
    }
    
    public static void setMatrixSize(int s){
        size = s-1;
        matrix = new float[size][size];
        coef = new float[size];
        scaleVector = new float[size];
        solution = new float[size];
    }
    
    public static void print(){
        // row
        for(int i= 0; i < matrix.length; i++){
            // col
            for(int j = 0; j < matrix.length; j++){
                System.out.printf("%-10.2f" ,matrix[i][j]);
            }
            System.out.printf(" %-10.2f %n", coef[i]);
        }
        System.out.println("");

    }
}
