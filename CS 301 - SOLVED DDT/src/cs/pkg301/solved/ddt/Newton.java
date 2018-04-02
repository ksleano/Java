/**
 * @author Pavan Aiyar
 * 
 *         File: Newton.java
 * 
 *         Description: loads from file a line containing x values, and a line
 *         containing f(x) values, constructs and formats corresponding divided
 *         difference table, interpolating polynomial, and simplified polynomial
 *         
 *         Note: ACCURATE TO 3 DECIMAL PLACES in respect to formatting. Change
 *         all instances of .3 and the round() method if you want a different accuracy
 *         but this will look terrible for larger (~50) node input.  
 */

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Newton {

    private double[][] table;
    private ArrayList<Double> coef;
    private String[][] f;
    private String filename;

    public Newton(String filename) {
        this.filename = filename;
        this.coef = new ArrayList<Double>();
        this.loadTable();
    }

    /**
     * Load table from file
     */
    public void loadTable() {
        FileInputStream fstream = null;
        try {
            fstream = new FileInputStream(this.filename);
        }
        catch (FileNotFoundException fn) {
            System.out.printf("File %s not found\n", this.filename);
        }
        DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        String[] x = null;
        String[] y = null;

        try {
            x = br.readLine().split("\\s+");
            y = br.readLine().split("\\s+");
        }
        catch (IOException ie) {
            System.out.printf("Error reading contents of %s\n", this.filename);
        }

        if (x.length != y.length) {
            System.out.println("Error: Number of nodes do not match.");
            System.exit(0);
        }

        this.table = new double[x.length][x.length + 1];

        for (int i = 0; i < x.length; ++i) {
            table[i][0] = Double.parseDouble(x[i]);
        }// x to first column

        for (int i = 0; i < y.length; ++i) {
            table[i][1] = Double.parseDouble(y[i]);
        }// y to second column
    }

    /**
     * Solve the divided difference table
     */
    public void solve() {
        int n = this.table[0].length; // column length

        for (int j = 2; j < n; ++j) {
            for (int i = 0; i < n - j; ++i) {
                this.table[i][j] = (this.table[i + 1][j - 1] - this.table[i][j - 1])
                        / (this.table[i + (j - 1)][0] - this.table[i][0]);
            }
        }

        for (int i = 1; i < table[0].length; ++i) {
            this.coef.add(table[0][i]);
        }
    }

    // 3 decimal place rounding
    private double round(double a) {
        return (double) Math.round(a * 1000) / 1000;
    }

    /**
     * Convert the table into an interpolating polynomial, and print
     */
    public void toPolynomial() {
        ArrayList<String> xToken = new ArrayList<String>();
        String p = "";
        for (int i = 0; i < this.table.length - 1; ++i) {
            double t = this.table[i][0];

            if (t < 0) {
                p = "+";
            }
            else if (t > 0) {
                p = "-";
            }
            if (round(t) == 0) {
                xToken.add("(x)");
            }
            else {
                xToken.add(String.format("(x%s%.3f)", p, t));
            }
        }

        String poly = String.format("%.3f", this.coef.get(0));

        for (int i = 1; i < xToken.size() + 1; ++i) {
            double t = this.coef.get(i);
            if (t != 0) {
                if (t > 0) {
                    p = "+";
                }
                else {
                    p = "-";
                }
                String varx = "";
                for (int j = 0; j < i; ++j) {
                    varx += xToken.get(j);
                }
                poly += String.format(" %s %.3f%s", p, Math.abs(t), varx);
            }
        }
        System.out.println(poly);
    }

    /**
     * Simplify the table by using the polynomial class, and print
     */
    public void toSimplified() {
        Polynomial p = new Polynomial();
        ArrayList<Double> t = new ArrayList<Double>();

        ArrayList<ArrayList<Double>> matrix = new ArrayList<ArrayList<Double>>();
        for (int i = 0; i < this.table[0].length - 1; ++i) {
            t.add(0.0);
        }
        t.add(0, this.coef.get(0));
        matrix.add(t);

        for (int i = 1; i < this.coef.size(); ++i) {
            t = new ArrayList<Double>();
            double c = this.coef.get(i);
            for (int j = 0; j < i; ++j) {
                t.add(this.table[j][0]);
            }
            matrix.add(p.expandPoly(c, t, this.table[0].length));
        }

        t = p.compress(matrix);

        System.out.println(toSimpString(t));
    }

    private String toSimpString(ArrayList<Double> c) {
        String poly = "";
        String power = "";
        for (int i = 0; i < c.size(); ++i) {
            Double f = c.get(i);
            power = String.format("x^%d", i);
            if (f != 0) {
                if (i == 0) {
                    poly += String.format(" %+.3f", f);
                }
                else {
                    poly += String.format(" %+.3f%s", f, power);
                }
            }
        }

        return poly;
    }

    /**
     * Format table in side-ways pyramid form
     */
    private void formatTable() {
        int h = 2 * this.table.length;
        int w = this.table[0].length;

        this.f = new String[h][w];

        for (int i = 0; i < this.f.length; ++i) {
            for (int j = 0; j < this.f[i].length; ++j) {
                this.f[i][j] = String.format("%8s", " ");
            }
        }

        // Transfer column 1
        int q = 0; // offset
        for (int i = 0; i < this.table.length; ++i) {
            this.f[q][0] = String.format("%8.3f", this.table[i][0]);
            q += 2;
        }
        // Transfer column 2
        q = 0; // offset
        for (int i = 0; i < this.table.length; ++i) {
            this.f[q][1] = String.format("%8.3f", this.table[i][1]);
            q += 2;
        }

        int n = this.table[0].length;
        for (int col = 2; col < n; ++col) {
            q = (col - 1);
            for (int row = 0; row < n - col; ++row) {
                f[q][col] = String.format("%8.3f", this.table[row][col]);
                q += 2;
            }
        }

    }

    // Print table in in side-ways pyramid form
    public void printFormatted() {
        formatTable();
        for (String[] s : f) {
            for (String b : s) {
                System.out.print(b + " ");
            }
            System.out.println();
        }
    }

    public double[][] getTable() {
        return this.table;
    }

    public ArrayList<Double> getCoef() {
        return this.coef;
    }

    // DEBUG METHOD TO PRINT COEFFICIENTS
    public void printCoef() {
        for (Double d : this.coef) {
            System.out.printf("%8.3f ", d);
        }
    }

    // DEBUG METHOD TO PRINT ENTIRE TABLE
    public void printTable() {
        for (int row = 0; row < this.table.length; ++row) {
            for (int column = 0; column < this.table[row].length; ++column) {
                System.out.printf("%8.3f ", this.table[row][column]);
            }
            System.out.println("\n");
        }
    }

}
