/*
Author - Sam Halligan*/
package rsa;

import java.math.*;
import java.util.*;
import java.io.*;
import javax.crypto.*;
import java.security.*;

class crypto {

	// Bit size of the probable primes
	final static int bits = 512;

	// Encryption exponent
	final static BigInteger e = new BigInteger("65537");

	public static void main(String[] args) throws IOException {
		BigInteger p, q, N, phiN, d;

		// Generate p, q, N and phi(n), and make sure phi(n) is relatively prime
		// to e
		while (true) {
			p = generateProbablePrime(bits);
			q = generateProbablePrime(bits);
			N = calculateProduct(p, q);
			phiN = phi(p, q);
			if (gcd(e, phiN).equals(BigInteger.ONE))
				break;
		}

		// generate the d decryption exponent
		d = inverse(e, phiN);

		// Read in the source code from a file named "code.txt"
		RandomAccessFile f = new RandomAccessFile("code.txt", "r");
		byte[] plaintext = new byte[(int) f.length()];
		f.read(plaintext);
		BigInteger message = new BigInteger(plaintext);

		// Now generate the 256 bit digest of the code using SHA-256 and apply
		// the decryption method
		byte[] hashedPlainText = generateHash(message);
		message = new BigInteger(1, hashedPlainText);
		BigInteger myCipher = encrypt(d, p, q, message);

		// Print all of the results
		System.out.println("N = " + N.toString(16));
		System.out.println("Code Digest = " + message.toString(16));
		System.out.println("Digital Signature = " + myCipher.toString(16));
		System.out.println("Unencrypted DS = " + decrypt(e, p, q, myCipher).toString(16));
	}

	// Generates a probable prime of the bitsize specified
	public static BigInteger generateProbablePrime(int bitSize) {
		return BigInteger.probablePrime(bitSize, new Random());
	}

	// Calculate N (p*q)
	public static BigInteger calculateProduct(BigInteger p, BigInteger q) {
		return p.multiply(q);
	}

	// Calculate phi(N)
	public static BigInteger phi(BigInteger p, BigInteger q) {
		BigInteger phiN = p.subtract(BigInteger.ONE);
		phiN = phiN.multiply(q.subtract(BigInteger.ONE));
		return phiN;
	}

	// Calculate GCD
	public static BigInteger gcd(BigInteger e, BigInteger n) {
		if (n.equals(BigInteger.ZERO))
			return e;
		return gcd(n, e.mod(n));
	}

	// calculate multiplicative inverse of a%n using the extended euclidean GCD
	// algorithm
	public static BigInteger inverse(BigInteger a, BigInteger N) {
		BigInteger[] ans = extendedEuclid(a, N);

		if (ans[1].compareTo(BigInteger.ZERO) == 1)
			return ans[1];
		else
			return ans[1].add(N);
	}

	// Calculate d = gcd(a,N) = ax+yN
	public static BigInteger[] extendedEuclid(BigInteger a, BigInteger N) {
		BigInteger[] ans = new BigInteger[3];
		BigInteger ax, yN;

		if (N.equals(BigInteger.ZERO)) {
			ans[0] = a;
			ans[1] = BigInteger.ONE;
			ans[2] = BigInteger.ZERO;
			return ans;
		}

		ans = extendedEuclid(N, a.mod(N));
		ax = ans[1];
		yN = ans[2];
		ans[1] = yN;
		BigInteger temp = a.divide(N);
		temp = yN.multiply(temp);
		ans[2] = ax.subtract(temp);
		return ans;
	}

	/*
	 * Chinese Remainder Theorem to calculate m^d(mod pq),where: m = message d =
	 * decryption exponent pq = factors of N
	 */
	public static BigInteger crt(BigInteger d, BigInteger p, BigInteger q, BigInteger m) {
		BigInteger dp, dq, qInverse, m1, m2, h;

		dp = d.mod(p.subtract(BigInteger.ONE));
		dq = d.mod(q.subtract(BigInteger.ONE));
		qInverse = inverse(q, p);

		m1 = m.modPow(dp, p);
		m2 = m.modPow(dq, q);
		h = qInverse.multiply(m1.subtract(m2)).mod(p);
		m = m2.add(h.multiply(q));

		return m;
	}

	// Encrypt using d
	public static BigInteger encrypt(BigInteger d, BigInteger p, BigInteger q, BigInteger m) {
		return crt(d, p, q, m);
	}

	// decrypt using e
	public static BigInteger decrypt(BigInteger e, BigInteger p, BigInteger q, BigInteger m) {
		return crt(e, p, q, m);
	}

	// use SHA-256 to generate a hash of the plaintext code
	public static byte[] generateHash(BigInteger p) {
		byte[] digest = new byte[0];

		try {
			MessageDigest m = MessageDigest.getInstance("SHA-256");
			digest = p.toByteArray();
			m.update(digest);
			digest = m.digest();
		} catch (Exception e) {
			System.out.println("Error in generateHash");
		}

		return digest;
	}
}
