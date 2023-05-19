public class Polynomial{

	// Fields
	double [] coefficients;

	// test

	// No argument constructor
	public Polynomial(){
		coefficients = new double[0];
	}

	// 1 argument constructor
	public Polynomial(double[] polyInput){
		coefficients = polyInput;
	}

	// Add method
	public Polynomial add(Polynomial polyArg){
		for (int i = 0; i < polyArg.coefficients.length; i++) {
			if (i >= coefficients.length){
				polyArg.coefficients[i] = polyArg.coefficients[i];					
			}
			else {
			polyArg.coefficients[i] = coefficients[i] + polyArg.coefficients[i];
			}
		}
		return polyArg;
	}

	// Evaluate Method
	public double evaluate(double x){
		double result = 0;
		for (int i = 0; i < coefficients.length; i++) {
			result = result + (Math.pow(x, i) * coefficients[i]);
		}
		return result;
	}

	// Root method
	public boolean hasRoot(double y){
		return evaluate(y) == 0;
	}
}








	
