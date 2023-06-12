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
		/*
		Polynomial p = new Polynomial();
		System.out.println(p.evaluate(3));
		double [] c1 = {6,0,0,5};
		Polynomial p1 = new Polynomial(c1);
		double [] c2 = {0,-2,0,0,-9};
		Polynomial p2 = new Polynomial(c2);
		Polynomial s = p1.add(p2);
		System.out.println("s(0.1) = " + s.evaluate(0.1));
		if(s.hasRoot(1))
			System.out.println("1 is a root of s");
		else
			System.out.println("1 is not a root of s"); 
		*/
	}
}