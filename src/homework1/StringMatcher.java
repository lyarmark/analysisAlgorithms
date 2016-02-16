package homework1;

//https://en.wikipedia.org/wiki/Boyer%E2%80%93Moore_string_search_algorithm
public class StringMatcher {
	public static void main(String[] args) {
		String pattern = "ina";
		String text = "findaneedleinahaystack";
		char[] haystack = text.replaceAll("\\s+", "").toCharArray();
		char[] needle = pattern.replaceAll("\\s+", "").toCharArray();
		System.out.println(findSubString(haystack, needle));
	}

	// returns the start index of the substring
	private static int findSubString(char[] haystack, char[] needle) {
		if (needle.length == 0) {
			return -1;
		}

		int M = needle.length;
		int N = haystack.length;

		for (int i = 0; i <= N - M; i++) {
			int j;
			for (j = 0; j < M; j++) {
				if (haystack[i + j] != needle[j])
					break;
			}
			if (j == M)
				return i; // found at offset i
		}
		return N; // not found

	}
	/*
	 * private static int[] makeCharTable(char[] needle) { final int
	 * ALPHABET_SIZE = 256; int[] table = new int[ALPHABET_SIZE]; for (int i =
	 * 0; i < table.length; i++) { table[i] = needle.length; } for (int i = 0; i
	 * < needle.length - 1; i++) { table[needle[i]] = needle.length - 1 - i; } }
	 */

}
