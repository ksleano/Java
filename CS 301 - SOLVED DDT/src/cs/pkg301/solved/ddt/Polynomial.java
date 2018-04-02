/**
 * @author Pavan Aiyar
 * 
 *         File: Polynomial.java
 * 
 *         Description: Class defines methods to expand polynomials strictly in
 *         the form c(x - x0)(x-x1)...(x-xn) for the purpose of providing a
 *         simplified polynomial representation of a given interpolating
 *         polynomial
 */

import java.util.ArrayList;

public class Polynomial {

    /**
     * Expand polynomial provided in form c*(x-t0)...(x-tn) return list of
     * coefficients x^0 ... x^n power
     */
    public ArrayList<Double> expandPoly(double c, ArrayList<Double> t,
            int minSize) {

        ArrayList<ArrayList<Double>> matrix = new ArrayList<ArrayList<Double>>();

        // coefficient array
        ArrayList<Double> g = new ArrayList<Double>();
        for (int i = 0; i < t.size() + 1; ++i) {
            g.add(0.0);
        }
        g.add(0,c); // put coefficient into this array

        for (int i = 0; i < t.size(); ++i) {
            matrix.add(push(g));
            matrix.add(mult(g, -t.get(i)));
            g = compress(matrix);
            matrix.clear();
        }

        int n = g.size();
        for (int i = 0; i < minSize - n; ++i) {
            g.add(0.0); // pad with 0s
        }

        return g;
    }

    // multiply by x (shift right)
    private ArrayList<Double> push(ArrayList<Double> a) {
        ArrayList<Double> t = new ArrayList<Double>();
        t.add(0.0);
        for (int i = 0; i < a.size() - 1; ++i) {
            t.add(a.get(i));
        }
        return t;
    }

    // multiply a by constant x
    private ArrayList<Double> mult(ArrayList<Double> a, double x) {
        ArrayList<Double> t = new ArrayList<Double>();
        for (double d : a) {
            t.add(d * x);
        }
        return t;
    }

    // Add like terms in coefficient matrix
    public ArrayList<Double> compress(ArrayList<ArrayList<Double>> a) {

        ArrayList<Double> compressed = new ArrayList<Double>();
        for (int i = 0; i < a.get(0).size(); ++i) {
            double sum = 0.0;
            for (int j = 0; j < a.size(); ++j) {
                sum += a.get(j).get(i);
            }
            compressed.add(sum);
        }

        return compressed;
    }

}
