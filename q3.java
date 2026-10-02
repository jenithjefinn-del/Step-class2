import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class q3 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int peopleCount = readPositiveInteger(scanner, "Number of people (press Enter for 10): ", 10);
		double[] heights = new double[peopleCount];
		double[] weights = new double[peopleCount];

		System.out.print("Generate random demo measurements? (y/n): ");
		boolean randomDemo = scanner.nextLine().trim().equalsIgnoreCase("y");
		if (randomDemo) {
			populateRandomMeasurements(heights, weights);
		} else {
			for (int person = 0; person < peopleCount; person++) {
				System.out.printf("Person %d height (m): ", person + 1);
				heights[person] = readPositiveDouble(scanner);
				System.out.printf("Person %d weight (kg): ", person + 1);
				weights[person] = readPositiveDouble(scanner);
			}
		}

		printWellnessReport(heights, weights);
	}

	public static String getBmiStatus(double bmi) {
		if (!Double.isFinite(bmi) || bmi < 0) {
			throw new IllegalArgumentException("BMI must be a finite, non-negative number.");
		}
		if (bmi < 18.5) {
			return "Underweight";
		}
		if (bmi < 25.0) {
			return "Normal";
		}
		if (bmi < 30.0) {
			return "Overweight";
		}
		return "Obese";
	}

	public static void printWellnessReport(double[] heights, double[] weights) {
		if (heights == null || weights == null || heights.length != weights.length) {
			throw new IllegalArgumentException("Height and weight arrays must be non-null and equal in length.");
		}

		System.out.printf("%-10s | %-12s | %-12s | %-8s | %-12s%n",
				"Person", "Height (m)", "Weight (kg)", "BMI", "Status");
		System.out.println("-----------+--------------+--------------+----------+--------------");
		for (int person = 0; person < heights.length; person++) {
			if (!Double.isFinite(heights[person]) || heights[person] <= 0
					|| !Double.isFinite(weights[person]) || weights[person] <= 0) {
				throw new IllegalArgumentException("Height and weight must be finite positive numbers.");
			}
			double bmi = weights[person] / (heights[person] * heights[person]);
			System.out.printf(Locale.US, "%-10d | %-12.2f | %-12.2f | %-8.2f | %-12s%n",
					person + 1, heights[person], weights[person], bmi, getBmiStatus(bmi));
		}
	}

	private static void populateRandomMeasurements(double[] heights, double[] weights) {
		Random random = new Random();
		for (int person = 0; person < heights.length; person++) {
			heights[person] = 1.50 + random.nextDouble() * 0.50;
			weights[person] = 45.0 + random.nextDouble() * 65.0;
		}
	}

	private static int readPositiveInteger(Scanner scanner, String prompt, int defaultValue) {
		while (true) {
			System.out.print(prompt);
			String input = scanner.nextLine().trim();
			if (input.isEmpty()) {
				return defaultValue;
			}
			try {
				int value = Integer.parseInt(input);
				if (value > 0) {
					return value;
				}
			} catch (NumberFormatException ignored) {
				// Ask again for invalid input.
			}
			System.out.println("Enter a positive whole number.");
		}
	}

	private static double readPositiveDouble(Scanner scanner) {
		while (true) {
			String input = scanner.nextLine().trim();
			try {
				double value = Double.parseDouble(input);
				if (Double.isFinite(value) && value > 0) {
					return value;
				}
			} catch (NumberFormatException ignored) {
				// Ask again for invalid input.
			}
			System.out.print("Enter a valid positive number: ");
		}
	}
}

