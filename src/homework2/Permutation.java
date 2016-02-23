package homework2;

import java.util.Arrays;

public class Permutation {
	public static void main(String[] args) {
		String a = "crate";
		char[] charsA = a.toCharArray();
		String b = "caret";
		char[] charsB = b.toCharArray();

		Arrays.sort(charsA);
		Arrays.sort(charsB);
		boolean permutation = true;

		if (!Arrays.equals(charsA, charsB)) {
			permutation = false;
		}

		if (permutation) {
			System.out.println(a + " and " + b + " are permutations");
		} else {
			System.out.println(a + " and " + b + " are not permutations");

		}
	}
}
