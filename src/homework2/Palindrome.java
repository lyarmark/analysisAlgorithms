package homework2;

import java.util.Collection;
import java.util.HashMap;

public class Palindrome {
	public static void main(String[] args) {
		String a = "atcoca";
		char[] chars = a.toCharArray();
		boolean palindrome = true;

		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		// fill the HashMap
		for (char c : chars) {
			Integer count = map.get(c);
			if (count == null) {
				map.put(c, 1);
			} else {
				map.put(c, count + 1);
			}
		}

		Collection<Integer> counts = map.values();
		int total = 0;
		boolean firstOdd = true;
		for (Integer n : counts) {
			if ((n % 2) == 0) {
				Integer.sum(total, n);
			} else if (firstOdd) {
				Integer.sum(total, n);
				firstOdd = false;
			} else {
				palindrome = false;
			}
		}

		if (palindrome) {
			System.out.println(a + " can be a palindromes");
		} else {
			System.out.println(a + " cannot be a palindromes");
		}
	}
}
