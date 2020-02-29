package game.utils;

public class Polynomial extends AMath {
	
	private double coefficientX4 = 0;
	private double coefficientX3 = 0;
	private double coefficientX2 = 0;
	private double coefficientX1 = 0;
	private double constant = 0;
	
	// Idea: Array with n double -> constructor ... 
	
	public Polynomial(double constant) {
		this.constant = constant;
	}
	
	public Polynomial(double coefficientX1, double constant) {
		this(constant);
		this.coefficientX1 = coefficientX1;		
	}
	
	public Polynomial(double coefficientX2, double coefficientX1, double constant) {
		this(coefficientX1 ,constant);
		this.coefficientX2 = coefficientX2;		
	}
	
	public Polynomial(double coefficientX3, double coefficientX2, double coefficientX1, double constant) {
		this(coefficientX2 ,coefficientX1 ,constant);
		this.coefficientX3 = coefficientX3;		
	}
	
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
	
	
	@Override
	public double getValue(int x) {
		return (coefficientX4 * Math.pow(x, 4.0)) + (coefficientX3 * Math.pow(x, 3.0)) + (coefficientX2 * Math.pow(x, 2.0)) + (coefficientX1 * Math.pow(x, 1.0)) + constant;
	}

	@Override
	public String toString() {
		return "Polynomial [coefficientX4=" + coefficientX4 + ", coefficientX3=" + coefficientX3 + ", coefficientX2="
				+ coefficientX2 + ", coefficientX1=" + coefficientX1 + ", constant=" + constant + "]";
	}
	
	

}
