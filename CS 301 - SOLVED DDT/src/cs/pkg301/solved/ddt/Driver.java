/**
 * @author Pavan Aiyar
 * 
 *         File: Driver.java
 * 
 *         Description: Driver class to demonstrate Newton Divided Difference
 *         program. 
 * 
 */
public class Driver {
    public static void main(String... args) {

        Newton n = new Newton("test.txt"); // pass input file
        n.solve();

        System.out.println("Divided Differences Table: ");
        n.printFormatted();

        System.out.println("Interpolating polynomial:");
        n.toPolynomial();

        System.out.println("\nSimplified polynomial:");
        n.toSimplified();

    }
}