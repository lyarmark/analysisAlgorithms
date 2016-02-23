package homework2;

import java.util.HashSet;

public class UniqueChars {
	public static void main(String[] args) {
		String s = "world";
		char[] chars = s.toCharArray();
		boolean unique = true;
		HashSet<Character> set = new HashSet<Character>();
		for (char c : chars) {
			if (set.contains(c)) {
				unique = false;
			} else {
				set.add(c);
			}
		}
		if (unique) {
			System.out.println(s + " has unique characters");
		} else {
			System.out.println(s + " does not have unique characters.");
		}
	}
}
