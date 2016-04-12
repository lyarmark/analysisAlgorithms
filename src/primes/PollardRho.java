package primes;

import java.math.BigInteger;

public class PollardRho {
	private final static BigInteger ZERO = new BigInteger("0");
	private final static BigInteger ONE = new BigInteger("1");
	private final static BigInteger TWO = new BigInteger("2");

	private BigInteger f(BigInteger x1) {
		return x1.multiply(x1).add(ONE);
	}

	// divisors
	private BigInteger rho(BigInteger n) {
		BigInteger x1 = TWO, x2 = TWO, divisor;
		if (n.mod(TWO).equals(ZERO))
			return TWO;
		do {
			x1 = f(x1).mod(n);
			x2 = f(f(x2)).mod(n);
			BigInteger sub = new BigInteger(String.valueOf(Math.abs(x1.subtract(x2).intValue())));
			divisor = gcd(sub, n);
		} while (divisor.equals(ONE));
		return divisor;
	}

	// gcd
	public BigInteger gcd(BigInteger p, BigInteger q) {
		while (!q.equals(ZERO)) {
			BigInteger temp = q;
			q = p.mod(q);
			p = temp;
		}
		return p;
	}

	public boolean isPrime(BigInteger n) {
		for (BigInteger i = TWO; i.doubleValue() <= Math.sqrt(n.doubleValue()); i = i.add(BigInteger.ONE))
			if (n.mod(i).equals(BigInteger.ZERO))
				return false;
		return true;
	}

	public void factor(BigInteger n) {
		if (n.equals(BigInteger.ONE))
			return;

		// if n is prime
		// not necessary in this case ...
		if (isPrime(n)) {
			System.out.println(n);
			return;
		}
		BigInteger divisor = rho(n);
		factor(divisor);
		factor(n.divide(divisor));
	}
}
