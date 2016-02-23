package homework2;

import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCompression {
	public static void main(String[] args) {
		String a = "aabcccccaaa";
		StringBuilder out = new StringBuilder();
		Pattern p = Pattern.compile("((\\w)\\2*)");
		Matcher m = p.matcher(a);

		while (m.find()) {
			out.append(m.group(2));
			out.append(m.group(1).length());
		}
		String compressed = out.toString();
		if (compressed.length() < a.length()) {
			System.out.print(compressed);
		} else {
			System.out.print(compressed);
		}
	}
}
