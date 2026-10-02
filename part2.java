import java.util.Scanner;

public class part2 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("How many seat numbers? ");
		int count = readCount(scanner);
		int[] seatNumbers = new int[count];

		for (int index = 0; index < count; index++) {
			System.out.printf("Seat number for student %d: ", index + 1);
			while (!scanner.hasNextInt()) {
				System.out.print("Enter a whole number: ");
				scanner.next();
			}
			seatNumbers[index] = scanner.nextInt();
		}

		checkDuplicateSeats(seatNumbers);
	}

	public static void checkDuplicateSeats(int[] seatNumbers) {
		if (seatNumbers == null) {
			System.out.println("Seat numbers were not provided.");
			return;
		}

		boolean duplicatesFound = false;
		System.out.print("Duplicate seat number(s): ");

		for (int index = 0; index < seatNumbers.length; index++) {
			boolean alreadyChecked = false;
			for (int previous = 0; previous < index; previous++) {
				if (seatNumbers[index] == seatNumbers[previous]) {
					alreadyChecked = true;
					break;
				}
			}
			if (alreadyChecked) {
				continue;
			}

			for (int next = index + 1; next < seatNumbers.length; next++) {
				if (seatNumbers[index] == seatNumbers[next]) {
					if (duplicatesFound) {
						System.out.print(", ");
					}
					System.out.print(seatNumbers[index]);
					duplicatesFound = true;
					break;
				}
			}
		}

		if (duplicatesFound) {
			System.out.println();
		} else {
			System.out.println("No duplicate seat numbers found.");
		}
	}

	private static int readCount(Scanner scanner) {
		while (!scanner.hasNextInt()) {
			System.out.print("Enter a non-negative whole number: ");
			scanner.next();
		}
		int count = scanner.nextInt();
		while (count < 0) {
			System.out.print("Enter a non-negative whole number: ");
			while (!scanner.hasNextInt()) {
				scanner.next();
			}
			count = scanner.nextInt();
		}
		return count;
	}
}
