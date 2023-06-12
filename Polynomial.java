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


	/* // Add method
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
	*/
	// Add method
	public Polynomial add(Polynomial polynomial) {
    	double[] currCoef = this.coefficients;
    	int[] currExpo = this.exponents;

    	double[] inputCoef = polynomial.coefficients;
    	int[] inputExpo = polynomial.exponents;

    	int currPolyLen = currCoef.length;
    	int inputPolyLen = inputCoef.length;
    	int addLen = Math.max(currPolyLen, inputPolyLen);

   	double[] resultCoefficients = new double[addLen];
    	int[] resultExponents = new int[addLen];

    	int i = 0, j = 0, k = 0;
    	while (i < currPolyLen && j < inputPolyLen) {
        	if (currExpo[i] < inputExpo[j]) {
            	resultCoefficients[k] = currCoef[i];
            	resultExponents[k] = currExpo[i];
            	i++;
        	} else if (currExpo[i] > inputExpo[j]) {
            	resultCoefficients[k] = inputCoef[j];
            	resultExponents[k] = inputExpo[j];
            	j++;
			} else {
				double sum = currCoef[i] + inputCoef[j];
				if (sum != 0) {
					resultCoefficients[k] = sum;
					resultExponents[k] = currExpo[i];
				} else {
					// Skip the term if the sum is zero
					k--;
				}
				i++;
				j++;
			}
			k++;
		}

		while (i < currPolyLen) {
			resultCoefficients[k] = currCoef[i];
			resultExponents[k] = currExpo[i];
			i++;
			k++;
		}

		while (j < inputPolyLen) {
			resultCoefficients[k] = inputCoef[j];
			resultExponents[k] = inputExpo[j];
			j++;
			k++;
		}

		return new Polynomial(resultCoefficients, resultExponents);
	}

	// Multiplication
	public Polynomial multiply(Polynomial polynomial) {
	    double[] currCoef = this.coefficients;
	    int[] currExpo = this.exponents;

	    double[] inputCoef = polynomial.coefficients;
	    int[] inputExpo = polynomial.exponents;

	    int currPolyLen = currCoef.length;
	    int inputPolyLen = inputCoef.length;
	    int resultLength = currPolyLen * inputPolyLen;

	    double[] resultCoefficients = new double[resultLength];
	    int[] resultExponents = new int[resultLength];

	    int index = 0;
	    for (int i = 0; i < currPolyLen; i++) {
		for (int j = 0; j < inputPolyLen; j++) {
		    double product = currCoef[i] * inputCoef[j];
		    int sumExponent = currExpo[i] + inputExpo[j];

		    boolean foundExponent = false;
		    for (int k = 0; k < index; k++) {
			if (resultExponents[k] == sumExponent) {
			    resultCoefficients[k] += product;
			    foundExponent = true;
			    break;
			}
		    }

		    if (!foundExponent) {
			resultCoefficients[index] = product;
			resultExponents[index] = sumExponent;
			index++;
		    }
		}
	    }

	    return new Polynomial(resultCoefficients, resultExponents);
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
