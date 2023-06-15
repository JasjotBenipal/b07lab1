import java.io.File;

public class Driver {
	public static void main(String [] args) {
		File file = new File("./load.txt");
		try {
			Polynomial filePoly = new Polynomial(file);
			System.out.println("Coefficients:");
			for(double coeff: filePoly.coefficients) {
				System.out.println(coeff);
			}
			System.out.println("Exponents:");
			for(double exponent: filePoly.exponents) {
				System.out.println(exponent);
			}
			filePoly.saveToFile("save.txt");
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		Polynomial p = new Polynomial();
		System.out.println("xxxxxxxxxxxxxxxxxxx");
		// Test the add method
		double [] c1 = {6,-2,5,5};
		int [] e1 = {0,2,3,4};
		Polynomial p1 = new Polynomial(c1, e1);
		double [] c2 = {-5,-2,4,6,-9};
		int [] e2 = {4,2,7,5,6};
		Polynomial p2 = new Polynomial(c2, e2);
		Polynomial s = p2.add(p1);
		for(double coeff: s.coefficients) {
			System.out.println(coeff);
		}
		System.out.println("Expected: 6, -4, 5, 6, -9, 4");
		for(int expo: s.exponents) {
			System.out.println(expo);
		}
		System.out.println("Expected: 0,2,3,5,6,7");
		
		// Test the multiply method
		double [] j1 = {1,5,1};
		int [] k1 = {0,1,2};
		Polynomial m1 = new Polynomial(j1, k1);
		double [] j2 = {3,3,2};
		int [] k2 = {3,0,1};
		Polynomial m2 = new Polynomial(j2, k2);
		Polynomial r = m2.multiply(m1);
		for(double coeff: r.coefficients) {
			System.out.println(coeff);
		}
		System.out.println("Expected: 3, 17, 13, 5, 15, 3");
		for(int expo: r.exponents) {
			System.out.println(expo);
		}
		System.out.println("Expected: 0,1,2,3,4,5");
		/*System.out.println("s(0.1) = " + s.evaluate(0.1));
		if(s.hasRoot(1))
			System.out.println("1 is a root of s");
		else
			System.out.println("1 is not a root of s"); 
		*/
	}
}
