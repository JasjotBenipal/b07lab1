public class Polynomial{

	// Fields
	double [] coefficients;

	// test

	// No argument constructor
	public Polynomial(){
		this.coefficients = new double[0];
	}

	// 1 argument constructor
	public Polynomial(double[] polyInput){
		this.coefficients = polyInput;
	}

	// Add method
	public Polynomial add(Polynomial polyArg){
		int polyLength = polyArg.coefficients.length;

		if (this.coefficients.length > polyLength) {
			polyLength = this.coefficients.length;
		}

		double[] newPoly = new double[polyLength];

		for (int i = 0; i < this.coefficients.length; i++) {
			newPoly[i] = this.coefficients[i];
		}

		for (int i = 0; i < polyArg.coefficients.length; i++) {
			newPoly[i] += polyArg.coefficients[i];
		}
		
		return new Polynomial(newPoly);
	}

	// Evaluate Method
	public double evaluate(double x){
		double result = 0;
		for (int i = 0; i < this.coefficients.length; i++) {
			result = result + (Math.pow(x, i) * this.coefficients[i]);
		}
		return result;
	}

	// Root method
	public boolean hasRoot(double y){
		return evaluate(y) == 0;
	}
}
