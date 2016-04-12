package primes;

import java.math.BigInteger;
import java.util.concurrent.TimeUnit;

public class Primes {

	public static void main(String[] args) {
		BigInteger p = new BigInteger("31");
		BigInteger q = new BigInteger("63");
		// BigInteger p = new BigInteger("48112959837082048697");
		// BigInteger q = new BigInteger(" 513821217024129243948411056803");

		BigInteger n = p.multiply(q);
		long startTime = System.currentTimeMillis();
		PollardRho pr = new PollardRho();
		pr.factor(n);
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(totalTime),
				TimeUnit.MILLISECONDS.toMinutes(totalTime) % TimeUnit.HOURS.toMinutes(1),
				TimeUnit.MILLISECONDS.toSeconds(totalTime) % TimeUnit.MINUTES.toSeconds(1));
		System.out.println(hms);

	}
}
