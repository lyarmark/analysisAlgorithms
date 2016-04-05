package rsa;

import java.math.BigInteger;

public class RunRSA {

	public static void main(String[] args) {

		RSA rsa = new RSA();

		BigInteger p = rsa.getP();
		BigInteger q = rsa.getQ();
		BigInteger e = rsa.getE();
		BigInteger n = rsa.getN();
		BigInteger phiN = rsa.getPhiN();
		BigInteger m = rsa.getM();

		System.out.println("p: " + rsa.getP());
		System.out.println("q: " + rsa.getQ());
		System.out.println("n: " + rsa.getN());
		System.out.println("e: " + rsa.getE());
		System.out.println("phi(n): " + rsa.getPhiN());
		System.out.println("m: " + m);

		BigInteger d = rsa.inverse(e, phiN);
		System.out.println("d: " + d);

		BigInteger c = rsa.encrypt(m, e, n);
		System.out.println("c: " + c);

		BigInteger message = rsa.decrypt(c, d, n);
		System.out.println("message: " + message);
	}
}
