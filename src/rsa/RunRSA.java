package rsa;

public class RunRSA {

	public static void main(String[] args) {

		RSA rsa = new RSA();

		int p = rsa.getP();
		int q = rsa.getQ();
		int e = rsa.getE();
		int n = rsa.getN();
		int phiN = rsa.getPhiN();
		int m = rsa.getM();

		System.out.println("p: " + rsa.getP());
		System.out.println("q: " + rsa.getQ());
		System.out.println("n: " + rsa.getN());
		System.out.println("e: " + rsa.getE());
		System.out.println("phi(n): " + rsa.getPhiN());

		// EXTENDED EUCLID'S ALGORITHM
		int gcd = rsa.gcd(p, q);
		int d = (gcd);
		System.out.println("d: " + d);

		int c =  rsa.fastExpo(m, e) % n;
		System.out.println("c: " + c);

	}
}
