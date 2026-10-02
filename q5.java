import java.util.Scanner;

public class q5 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter customer name: ");
		String customerName = scanner.nextLine();

		String reversedName = reverseCustomerName(customerName);
		System.out.println("Original name: " + customerName);
		System.out.println("Reversed name: " + reversedName);
	}

	public static String reverseCustomerName(String customerName) {
		return new StringBuilder(customerName).reverse().toString();
	}
}

