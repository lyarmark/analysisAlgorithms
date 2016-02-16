package homework1;

//http://introcs.cs.princeton.edu/java/23recursion/Euclid.java.html
public class EuclidGCD {
	public static void main(String[] args) {
		int a = 10;
		int b = 20;
		int gcd1 = gcd(a, b);
		int gcd2 = gcd2(a, b);
		System.out.println(gcd1 + " " + gcd2);
	}

	// recursive implementation
	public static int gcd(int p, int q) {
		if (q == 0)
			return p;
		else
			return gcd(q, p % q);
	}

	// non-recursive implementation
	public static int gcd2(int p, int q) {
		while (q != 0) {
			int temp = q;
			q = p % q;
			p = temp;
		}
		return p;
	}
}
