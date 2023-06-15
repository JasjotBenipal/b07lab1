import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.FileNotFoundException;

public class Polynomial{
	// Fields
	double [] coefficients;
	int [] exponents;

	// No argument constructor
	public Polynomial(){
		this.coefficients = null;
		this.exponents = null;
	}

	// 1 argument constructor
	public Polynomial(double[] coefInput, int[] expoInput){
		this.coefficients = coefInput;
		this.exponents = expoInput;
	}

	// Save File Constructor
	public Polynomial(File file) throws Exception {
		Scanner myScanner = new Scanner(file);

		if (!myScanner.hasNextLine()) {
			this.coefficients = null;
			this.exponents = null;
		}
		else {
			String line = myScanner.nextLine();
			line = line.replace("-", "+-");

			// [5, 3x2, 7x8]
			String[] polyArray = line.split("\\+");
			this.coefficients = new double[polyArray.length];
			this.exponents = new int[polyArray.length];
			
			for(int i=0; i < polyArray.length; i++) {
				String [] subArray = polyArray[i].split("x");
				coefficients[i] = Double.parseDouble(subArray[0]);

				if (subArray.length > 1) {
					exponents[i] = Integer.parseInt(subArray[1]);
				}
				else {
					exponents[i] = 0;
				}
			}
		}
		myScanner.close();
	}

	// Save file method
	public void saveToFile(String myFile) throws Exception {
		String writeString = "";
		for(int i = 0; i < this.coefficients.length; i++) {
			writeString += (int)coefficients[i];
			if (exponents[i] != 0) {
				writeString+= "x" + exponents[i];
			}
			if ((i+1) < this.coefficients.length && this.coefficients[i+1] > 0){
				writeString += "+";
			}
			
		}

		if(writeString.endsWith("+")) {
			writeString = writeString.substring(0, writeString.length()-1);
		}
		FileWriter myWriter = new FileWriter(new File(myFile));
		myWriter.write(writeString);
		myWriter.close();
	}

	//Add method
	public Polynomial add(Polynomial polyArg) {
		double[] currCoef = this.coefficients;
		int[] currExpo = this.exponents;

		double[] inputCoef = polyArg.coefficients;
		int[] inputExpo = polyArg.exponents;

		int resultLength = currCoef.length + inputCoef.length;

		double[] resultCoefficients = new double[resultLength];
		int[] resultExponents = new int[resultLength];

		// Add coefficients for current polynomial
		for (int i = 0; i < currCoef.length; i++) {
			resultCoefficients[currExpo[i]] += currCoef[i];
			resultExponents[currExpo[i]] = currExpo[i];
		}

		// Add coefficients for input polynomial
		for (int i = 0; i < inputCoef.length; i++) {
			resultCoefficients[inputExpo[i]] += inputCoef[i];
			resultExponents[inputExpo[i]] = inputExpo[i];
		}

		// Determine the number of non-zero terms in the result
		int count = 0;
		for (int i = 0; i < resultLength; i++) {
			if (resultCoefficients[i] != 0) {
				count++;
			}
		}

		// Create arrays for non-zero coefficients and exponents
		double[] nonZeroCoefficients = new double[count];
		int[] nonZeroExponents = new int[count];

		// Populate the non-zero coefficient and exponent arrays
		int index = 0;
		for (int i = 0; i < resultLength; i++) {
			if (resultCoefficients[i] != 0) {
				nonZeroCoefficients[index] = resultCoefficients[i];
				nonZeroExponents[index] = resultExponents[i];
				index++;
			}
		}

		return new Polynomial(nonZeroCoefficients, nonZeroExponents);
	}


	// Multiplication
	public Polynomial multiply(Polynomial polyArg) {
		double[] currCoef = this.coefficients;
		int[] currExpo = this.exponents;

		double[] inputCoef = polyArg.coefficients;
		int[] inputExpo = polyArg.exponents;

		int maxExponent = currCoef.length * inputCoef.length;

		double[] resultCoefficients = new double[maxExponent + 1];
		int[] resultExponents = new int[maxExponent + 1];

		for (int i = 0; i < currCoef.length; i++) {
			for (int j = 0; j < inputCoef.length; j++) {
				int expoSum = currExpo[i] + inputExpo[j];
				resultCoefficients[expoSum] += currCoef[i] * inputCoef[j];
				resultExponents[expoSum] = expoSum;
			}
		}

		// Determine the number of non-zero terms in the result
		int count = 0;
		for (int i = 0; i <= maxExponent; i++) {
			if (resultCoefficients[i] != 0) {
				count++;
			}
		}

		// Create arrays for non-zero coefficients and exponents
		double[] nonZeroCoefficients = new double[count];
		int[] nonZeroExponents = new int[count];

		// Populate the non-zero coefficient and exponent arrays
		int index = 0;
		for (int i = 0; i <= maxExponent; i++) {
			if (resultCoefficients[i] != 0) {
				nonZeroCoefficients[index] = resultCoefficients[i];
				nonZeroExponents[index] = resultExponents[i];
				index++;
			}
		}

		return new Polynomial(nonZeroCoefficients, nonZeroExponents);
	}


	// Evaluate Method
	public double evaluate(double x){
		double result = 0;
		for (int i = 0; i < this.coefficients.length; i++) {
			result = result + (Math.pow(x, this.exponents[i]) * this.coefficients[i]);
		}
		return result;
	}

	// Root method
	public boolean hasRoot(double y){
		return evaluate(y) == 0;
	}

}
