import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class Hello {
	private static final String[] MOVES = {"Rock", "Paper", "Scissors"};
	private static final Random RANDOM = new Random();

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int numberOfRounds = readNumberOfRounds(scanner);
		List<String[]> results = new ArrayList<>();
		int wins = 0;
		int losses = 0;
		int draws = 0;

		for (int round = 1; round <= numberOfRounds; round++) {
			String playerMove = readPlayerMove(scanner, round);
			String computerMove = MOVES[RANDOM.nextInt(MOVES.length)];
			String result = playRound(playerMove, computerMove);
			results.add(new String[]{String.valueOf(round), playerMove, computerMove, result});

			System.out.printf("Round %d: You chose %s; computer chose %s. %s.%n",
					round, playerMove, computerMove, result);

			if (result.equals("Win")) {
				wins++;
			} else if (result.equals("Loss")) {
				losses++;
			} else {
				draws++;
			}
		}

		System.out.println("\nFinal Scoreboard");
		System.out.printf("%-8s | %-14s | %-16s | %-6s%n",
				"Round", "Player Move", "Computer Move", "Result");
		System.out.println("---------+----------------+------------------+--------");
		for (String[] row : results) {
			System.out.printf("%-8s | %-14s | %-16s | %-6s%n",
					row[0], row[1], row[2], row[3]);
		}

		double winPercentage = wins * 100.0 / numberOfRounds;
		System.out.printf("%nWins: %d  Losses: %d  Draws: %d%n", wins, losses, draws);
		System.out.printf("Player win percentage: %.2f%%%n", winPercentage);
	}

	public static String playRound(String playerMove, String computerMove) {
		String player = normalizeMove(playerMove);
		String computer = normalizeMove(computerMove);
		if (player == null || computer == null) {
			throw new IllegalArgumentException("Moves must be Rock, Paper, or Scissors.");
		}

		if (player.equals(computer)) {
			return "Draw";
		}
		if ((player.equals("Rock") && computer.equals("Scissors"))
				|| (player.equals("Paper") && computer.equals("Rock"))
				|| (player.equals("Scissors") && computer.equals("Paper"))) {
			return "Win";
		}
		return "Loss";
	}

	private static int readNumberOfRounds(Scanner scanner) {
		while (true) {
			System.out.print("Number of rounds (press Enter for 5): ");
			String input = scanner.nextLine().trim();
			if (input.isEmpty()) {
				return 5;
			}
			try {
				int rounds = Integer.parseInt(input);
				if (rounds > 0) {
					return rounds;
				}
			} catch (NumberFormatException ignored) {
				// Ask again for invalid input.
			}
			System.out.println("Enter a positive whole number.");
		}
	}

	private static String readPlayerMove(Scanner scanner, int round) {
		while (true) {
			System.out.printf("Round %d - enter Rock, Paper, or Scissors: ", round);
			String move = normalizeMove(scanner.nextLine());
			if (move != null) {
				return move;
			}
			System.out.println("Invalid move. Please try again.");
		}
	}

	private static String normalizeMove(String move) {
		if (move == null) {
			return null;
		}
		for (String validMove : MOVES) {
			if (validMove.equalsIgnoreCase(move.trim().toLowerCase(Locale.ROOT))) {
				return validMove;
			}
		}
		return null;
	}
}
