package rsa;

import java.math.BigInteger;

public class RSA {
	public final BigInteger n;
	public final BigInteger e;
	private final BigInteger p;
	private final BigInteger q;
	private final BigInteger phiN;
	private final BigInteger m;

	public RSA() {
		this.p = new BigInteger("96973");
		this.q = new BigInteger("104701");
		this.e = new BigInteger("65537");
		this.n = p.multiply(q);
		// phi(p)* phi(q)
		this.phiN = (p.subtract(BigInteger.ONE)).multiply((q.subtract(BigInteger.ONE)));
		this.m = new BigInteger("3405");
	}

	public BigInteger getN() {
		return n;
	}

	public BigInteger getE() {
		return e;
	}

	public BigInteger getP() {
		return p;
	}

	public BigInteger getQ() {
		return q;
	}

	public BigInteger getM() {
		return m;
	}

	public BigInteger getPhiN() {
		return phiN;
	}

	public static BigInteger[] extendedEuclid(BigInteger a, BigInteger n) {
		BigInteger[] ans = new BigInteger[3];
		BigInteger ax, yN;

		if (n.equals(BigInteger.ZERO)) {
			ans[0] = a;
			ans[1] = BigInteger.ONE;
			ans[2] = BigInteger.ZERO;
			return ans;
		}

		ans = extendedEuclid(n, a.mod(n));
		ax = ans[1];
		yN = ans[2];
		ans[1] = yN;
		BigInteger temp = a.divide(n);
		temp = yN.multiply(temp);
		ans[2] = ax.subtract(temp);
		return ans;
	}

	// calculate multiplicative inverse of a%n using the extended euclidean GCD
	// algorithm
	public BigInteger inverse(BigInteger a, BigInteger n) {
		BigInteger[] ans = extendedEuclid(a, n);

		if (ans[1].compareTo(BigInteger.ZERO) == 1)
			return ans[1];
		else
			return ans[1].add(n);
	}

	public BigInteger fastExpo(BigInteger a, BigInteger b) {
		BigInteger result = BigInteger.ONE;

		while (b.compareTo(BigInteger.ZERO) == 1) {
			if (b.mod(new BigInteger("2")).compareTo(BigInteger.ONE) == 0) {
				result = result.multiply(a);
			}
			b = b.divide(new BigInteger("2"));
			a = a.multiply(a);
		}

		return result;
	}

	public BigInteger decrypt(BigInteger code, BigInteger d, BigInteger n) {
		BigInteger m = code.modPow(d, n);
		return m;
	}

	public BigInteger encrypt(BigInteger message, BigInteger e, BigInteger n) {
		// BigInteger c = fastExpo(message, e);
		BigInteger c = message.modPow(e, n);
		return c.mod(n);
	}

}
