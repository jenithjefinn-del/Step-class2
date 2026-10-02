import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class q4 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter a word or sentence: ");
		String text = scanner.nextLine();

		char result = findFirstNonRepeatingChar(text);
		if (result == '\0') {
			System.out.println("No non-repeating character exists.");
		} else if (Character.isWhitespace(result)) {
			System.out.println("First non-repeating character: [whitespace]");
		} else {
			System.out.println("First non-repeating character: '" + result + "'");
		}
	}

	public static char findFirstNonRepeatingChar(String text) {
		if (text == null) {
			throw new IllegalArgumentException("Text cannot be null.");
		}

		Map<Character, Integer> frequencies = new HashMap<>();
		for (int index = 0; index < text.length(); index++) {
			char character = text.charAt(index);
			frequencies.put(character, frequencies.getOrDefault(character, 0) + 1);
		}

		for (int index = 0; index < text.length(); index++) {
			char character = text.charAt(index);
			if (frequencies.get(character) == 1) {
				return character;
			}
		}
		return '\0';
	}
}

