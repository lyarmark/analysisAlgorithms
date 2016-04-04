package rsa;

public class RSA {
	public final int n;
	public final int e;
	private final int p;
	private final int q;
	private final int phiN;
	private final int m;

	public RSA() {
		this.p = 61;
		this.q = 53;
		this.e = 17;
		this.n = p * q;
		this.phiN = (p - 1) * (q - 1); // phi(p) * phi(q)
		this.m = 65;
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

	public int[] extendedEuclid(int p, int q) {
		int[] ans = new int[3];
		int ax, yN;

		if (q == 0) {
			ans[0] = p;
			ans[1] = 1;
			ans[2] = 0;
			return ans;
		}

		ans = extendedEuclid(q, p % q);
		ax = ans[1];
		yN = ans[2];
		ans[1] = yN;
		int temp = p / q;
		temp = yN * temp;
		ans[2] = ax - temp;
		return ans;
	}

	// calculate multiplicative inverse of a%n using the extended euclidean GCD
	// algorithm
	public int inverse(int a, int N) {
		int[] ans = extendedEuclid(a, N);

		if (ans[1] == 0)
			return ans[1];
		else
			return ans[1] + (N);
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
