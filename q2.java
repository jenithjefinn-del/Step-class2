import java.util.Scanner;

public class q2 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter text: ");
		String text = scanner.nextLine();

		boolean iterativeResult = isPalindromeIterative(text);
		boolean recursiveResult = isPalindromeRecursive(text);
		boolean arrayReversalResult = isPalindromeArrayReversal(text);

		System.out.println("Iterative: " + iterativeResult);
		System.out.println("Recursive: " + recursiveResult);
		System.out.println("Array reversal: " + arrayReversalResult);
		System.out.println("All approaches agree: "
				+ (iterativeResult == recursiveResult
				&& recursiveResult == arrayReversalResult));
	}

	public static boolean isPalindromeIterative(String text) {
		String normalized = normalize(text);
		int left = 0;
		int right = normalized.length() - 1;
		while (left < right) {
			if (normalized.charAt(left) != normalized.charAt(right)) {
				return false;
			}
			left++;
			right--;
		}
		return true;
	}

	public static boolean isPalindromeRecursive(String text) {
		return isPalindromeRecursive(normalize(text), 0, normalize(text).length() - 1);
	}

	private static boolean isPalindromeRecursive(String text, int left, int right) {
		if (left >= right) {
			return true;
		}
		return text.charAt(left) == text.charAt(right)
				&& isPalindromeRecursive(text, left + 1, right - 1);
	}

	public static boolean isPalindromeArrayReversal(String text) {
		String normalized = normalize(text);
		char[] characters = normalized.toCharArray();
		for (int left = 0, right = characters.length - 1; left < right; left++, right--) {
			char temporary = characters[left];
			characters[left] = characters[right];
			characters[right] = temporary;
		}
		return normalized.equals(new String(characters));
	}

	private static String normalize(String text) {
		if (text == null) {
			throw new IllegalArgumentException("Text cannot be null.");
		}
		StringBuilder normalized = new StringBuilder();
		for (int index = 0; index < text.length(); index++) {
			char character = text.charAt(index);
			if (Character.isLetterOrDigit(character)) {
				normalized.append(Character.toLowerCase(character));
			}
		}
		return normalized.toString();
	}
}

