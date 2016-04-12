package primes;

import java.math.BigInteger;
import java.util.concurrent.TimeUnit;

public class Primes {
	static final BigInteger TWO = new BigInteger("2");
	static final BigInteger THREE = new BigInteger("3");

	public static void main(String[] args) {

		// BigInteger p = new BigInteger("31");
		// BigInteger q = new BigInteger("63");
		BigInteger p = new BigInteger("48112959837082048697");
		BigInteger q = new BigInteger("513821217024129243948411056803");

		BigInteger n = p.multiply(q);
		long startTime = System.currentTimeMillis();
		PollardRho pr = new PollardRho();
		// pr.factor(n);
		factor(n);
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(totalTime),
				TimeUnit.MILLISECONDS.toMinutes(totalTime) % TimeUnit.HOURS.toMinutes(1),
				TimeUnit.MILLISECONDS.toSeconds(totalTime) % TimeUnit.MINUTES.toSeconds(1));
		System.out.println(hms);

	}

	public static void factor(BigInteger n) {
		for (BigInteger i = TWO; i.compareTo(n) < 0; i = i.add(BigInteger.ONE)) {
			if (n.mod(i).equals(BigInteger.ZERO)) {
				System.out.println(i);
			}
		}
	}
}
