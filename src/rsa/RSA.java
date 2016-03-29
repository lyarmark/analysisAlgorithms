package rsa;

public class RSA {
	public final int n;
	public final int e;
	private final int p;
	private final int q;
	private final int phiN;
	private final int m;

	public RSA() {
		this.p = 96973;
		this.q = 104701;
		this.e = 65537;
		this.n = p * q;
		this.phiN = (p - 1) * (q - 1); // phi(p) * phi(q)
		this.m = 3405;
	}

	public int getN() {
		return n;
	}

	public int getE() {
		return e;
	}

	public int getP() {
		return p;
	}

	public int getQ() {
		return q;
	}

	public int getM() {
		return m;
	}

	public int getPhiN() {
		return phiN;
	}

	public int[] extendedEuclid(int a, int b)
	/*
	 * This function will perform the Extended Euclidean algorithm to find the
	 * GCD of a and b. We assume here that a and b are non-negative (and not
	 * both zero). This function also will return numbers j and k such that d =
	 * j*a + k*b where d is the GCD of a and b.
	 */
	{
		int[] ans = new int[3];
		int q;

		if (b == 0) { /* If b = 0, then we're done... */
			ans[0] = a;
			ans[1] = 1;
			ans[2] = 0;
		} else { /* Otherwise, make a recursive function call */
			q = a / b;
			ans = extendedEuclid(b, a % b);
			int temp = ans[1] - ans[2] * q;
			ans[1] = ans[2];
			ans[2] = temp;
		}

		return ans;
	}

	public int[] euclidean(int a, int b) {
		if (b > a) {
			// reverse the order of inputs, run through this method, then
			// reverse outputs
			int[] coeffs = euclidean(b, a);
			int[] output = { coeffs[1], coeffs[0] };
			return output;
		}

		int q = a / b;
		// a = q*b + r --> r = a - q*b
		int r = a - q * b;

		// when there is no remainder, we have reached the gcd and are done
		if (r == 0) {
			int[] output = { 0, 1 };
			return output;
		}

		// call the next iteration down (b = qr + r_2)
		int[] next = euclidean(b, r);

		int[] output = { next[1], next[0] - q * next[1] };
		return output;
	}

	// recursive implementation
	public int gcd(int p, int q) {
		if (q == 0)
			return p;
		else
			return gcd(q, p % q);
	}

	public int fastExpo(int a, int b) {
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
