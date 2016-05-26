package homework4;

public class Horner {

	// use Horner's method to compute and return the polynomial evaluated at x
	public static double eval(double x, double[] array) {
		double result = 0;
		for (int i = array.length - 1; i >= 0; i--)
			result = array[i] + (x * result);
		System.out.println(result);
		return result;
	}

	public static void main(String[] args) {
		double[] array = { 3, -1, 5, 1 };

		eval(2, array);
	}
}