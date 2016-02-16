package homework1;
//http://www.programminglogic.com/fast-exponentiation-algorithms/
public class FastExponentiation {
	public static void main(String[] args) {
		int result = expo(3,3);
		System.out.println(result);
	}

	static int expo(int a, int b) {
		int result = 1;

		while (b > 0) {
			if (b % 2 == 1) {
				result *= a;
			}
			b /= 2;
			a *= a;
		}

		return result;
	}
}
