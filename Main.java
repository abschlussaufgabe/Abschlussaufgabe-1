package edu.kit.informatik;

public class Main {
	static ExceptionChecker exceptionChecker = new ExceptionChecker();
	static int currentTurn = 1;
	static boolean running = true;
	static boolean gameOver = false;
	static boolean won = false;

	/**
	 * This is the program entry method main
	 * 
	 * @param args array of strings of the given command line arguments
	 */
	public static void main(String[] args) {
		String[] commands = args[0].split("\\s");
		if (commands.length < 0 || commands.length > 3) {
			Terminal.printError("wrong number of commands");
		}
		String mode = commands[0];
		int boardSize = Integer.parseInt(commands[1]);
		int numberOfPlayers = Integer.parseInt(commands[2]);
		Game game = new Game(boardSize);
		while (running) {
			String[] input = Terminal.readLine().split(" ", 2);
			String[] params = null;
			if (input.length > 1) {
				params = input[1].split(";");
			}
			switch (input[0]) {
			case "place": {
				if (gameOver) {
					Terminal.printError("game already over");
					break;
				}
				try {
					if (!exceptionChecker.checkPlaceProperNumberOfArguments(params.length)) {
						break;
					}
					game.instantiatePlayers(numberOfPlayers);
					Player currentPlayer = game.determineCurrentPlayer(currentTurn, numberOfPlayers);
					if (mode.equals("torus")) {
						won = game.playTurnTorus(Integer.parseInt(params[0]), Integer.parseInt(params[1]),
								Integer.parseInt(params[2]), Integer.parseInt(params[3]), currentPlayer);
					} else {
						won = game.playTurnStandard(Integer.parseInt(params[0]), Integer.parseInt(params[1]),
								Integer.parseInt(params[2]), Integer.parseInt(params[3]), currentPlayer);
					}
					if (won) {
						Terminal.printLine(currentPlayer.getName() + " " + "wins");
						gameOver = true;
					} else {
						Terminal.printLine("OK");
						currentTurn++;
					}
					gameOver = game.checkDraw(currentTurn, won, gameOver);
				} catch (IllegalArgumentException iae) {
					Terminal.printError("slot already full");

				} catch (Exception e) {
					Terminal.printError("unexisting slot");
				}
				break;
			}
			case "print": {
				if (!exceptionChecker.checkPrintWithoutArguments(input.length)) {
					break;
				}
				game.printGrid();
				break;
			}
			case "rowprint": {
				try {
					if (!exceptionChecker.checkPrintRowProperArguments(params.length)) {
						break;
					}
					game.printRow(Integer.parseInt(params[0]));
				} catch (Exception e) {
					Terminal.printError("unexisting row");
				}
				break;
			}
			case "colprint": {
				try {
					if (!exceptionChecker.checkPrintColProperArguments(params.length)) {
						break;
					}
					game.printColumn(Integer.parseInt(params[0]));
				} catch (Exception e) {
					Terminal.printError("unexisting column");
				}
				break;
			}
			case "state": {
				try {
					if (!exceptionChecker.checkStateProperNumberOfArguments(params.length)) {
						break;
					}
					game.printSlot(Integer.parseInt(params[0]), Integer.parseInt(params[1]));

				} catch (Exception e) {
					Terminal.printError("unexisting slot");
				}
				break;
			}
			case "quit": {
				if (!exceptionChecker.checkQuitWithoutArguments(input.length)) {
					break;
				}
				running = false;
				break;
			}
			case "reset": {
				game = new Game(18);
				currentTurn = 1;
				Terminal.printLine("OK");
				break;
			}
			default:
				Terminal.printError("unknown command");
			}
		}
	}
}