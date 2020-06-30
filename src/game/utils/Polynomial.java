package game.utils;


/** 
 * Represents a class that stores a simple Polynom like (f(x)=2x+15) and is able to calculate the y value with a given X
 * Used for: nearly all level based buildings / researches.
 * Example: Research XYZ gives at lvl 1 benefits of 10%, lvl 2 15%, lvl 3 17% etc. x= lvl, benefit = y axis
 * @author Martin
 *
 */
/**
 * @author Martin
 *
 */
public class Polynomial extends AMath {
	
	private double coefficientX4 = 0;
	private double coefficientX3 = 0;
	private double coefficientX2 = 0;
	private double coefficientX1 = 0;
	private double constant = 0;
	
	// Idea: Array with n double -> constructor ... 
	// Good idea but lazy Martin
	
	/**
	 * Poly with just a Constant
	 * @param constant
	 */
	public Polynomial(double constant) {
		this.constant = constant;
	}
	
	/** 
	 * Poly with coefficient * x ^ 1 + const
	 * @param coefficientX1
	 * @param constant
	 */
	public Polynomial(double coefficientX1, double constant) {
		this(constant);
		this.coefficientX1 = coefficientX1;		
	}
	
	/** 
	 * Poly with 2 Coefficient 
	 * @param coefficientX2
	 * @param coefficientX1
	 * @param constant
	 */
	public Polynomial(double coefficientX2, double coefficientX1, double constant) {
		this(coefficientX1 ,constant);
		this.coefficientX2 = coefficientX2;		
	}
	
	/**
	 * Poly with 3 Coefficient 
	 * @param coefficientX3
	 * @param coefficientX2
	 * @param coefficientX1
	 * @param constant
	 */
	public Polynomial(double coefficientX3, double coefficientX2, double coefficientX1, double constant) {
		this(coefficientX2 ,coefficientX1 ,constant);
		this.coefficientX3 = coefficientX3;		
	}
	
	
	/**
	 * Poly with 4 Coefficient 
	 * @param coefficientX4
	 * @param coefficientX3
	 * @param coefficientX2
	 * @param coefficientX1
	 * @param constant
	 */
	public Polynomial(double coefficientX4, double coefficientX3, double coefficientX2, double coefficientX1, double constant) {
		this(coefficientX3, coefficientX2 ,coefficientX1 ,constant);
		this.coefficientX4 = coefficientX4;		
	}	


	public static void main(String[] args) {
		Polynomial polynomial = new Polynomial(13.0);
		System.out.println("Testing(x=1) constant\t" + polynomial.toString() + "\n" + polynomial.getValue(1) + "\n");
		polynomial = new Polynomial(3, 0);
		System.out.println("Testing(x=1) 3x\t\t" + polynomial.toString() + "\n" + polynomial.getValue(1) + "\n");
		polynomial = new Polynomial(2, 0, 0);
		System.out.println("Testing(x=1) 2x^2\t" + polynomial.toString() + "\n" + polynomial.getValue(1) + "\n");
	}
	
	
	/**
	 * Returns with a given X your Y Value
	 * @param x int Your X Value´
	 * @return int Your Y Return Value
	 */
	@Override
	public double getValue(int x) {
		return (coefficientX4 * Math.pow(x, 4.0)) + (coefficientX3 * Math.pow(x, 3.0)) + (coefficientX2 * Math.pow(x, 2.0)) + (coefficientX1 * Math.pow(x, 1.0)) + constant;
	}

	@Override
	public String toString() {
		String output = "f(x)= ";
		if (coefficientX4 != 0) { output += coefficientX4 + "x^4";}
		if (coefficientX3 != 0) { output += " + " + coefficientX3 + "x^3";}
		if (coefficientX2 != 0) { output += " + " + coefficientX2 + "x^2";}
		if (coefficientX1 != 0) { output += " + " + coefficientX1 + "x";}
		if (constant != 0) { output += " + " + constant;}
		return output;
	}
	
	

}
