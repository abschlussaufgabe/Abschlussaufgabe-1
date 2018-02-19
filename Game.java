package edu.kit.informatik;

import java.util.Arrays;
import java.util.LinkedList;

public class Game {
	private LinkedList<Player> players = new LinkedList<Player>();
	private static String[][] board;

	/**
	 * Constructor.
	 * @param n board size
	 */
	public Game(int n) {
		board = new String[n][n];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				board[i][j] = "**";
			}
		}
	}

	/**
	 * Simulates one turn in the torus game mode
	 * 
	 * @param row1 the first stone's row number
	 * @param col1 the first stone's column number
	 * @param row2 the second stones's row number
	 * @param col2 the second column's column number
	 * @param player the current player 
	 * @return true if the current player wins the game in this turn else false
	 * @throws IllegalArgumentException
	 */
	public boolean playTurnTorus(int row1, int col1, int row2, int col2, Player player)
			throws IllegalArgumentException {
		if (row1 >= board.length) {
			row1 = row1 % board.length;
		}
		if (row1 < 0) {
			row1 = -row1 % board.length;
		}
		if (row2 >= board.length) {
			row2 = row2 % board.length;
		}
		if (row2 < 0) {
			row2 = -row2 % board.length;
		}
		if (col1 >= board.length) {
			col1 = col1 % board.length;
		}
		if (col1 < 0) {
			col1 = -col1 % board.length;
		}
		if (col2 >= board.length) {
			col2 = col2 % board.length;
		}
		if (col2 < 0) {
			col2 = -col2 % board.length;
		}
		if (!board[row1][col1].equals("**") || !board[row2][col2].equals("**")) {
			throw new IllegalArgumentException();
		} else {
			board[row1][col1] = player.getName();
			board[row2][col2] = player.getName();
		}
		return winner(player) || winTorusRow(player) || winTorusColumn(player) || winTorusLeftDiagonal(player)
				|| winTorusRightDiagonal(player);
	}

	/**
	 * Simulates one turn in the standard game mode
	 * 
	 * @param row1 the first stone's row number
	 * @param col1 the first stone's column number
	 * @param row2 the second stone's row number
	 * @param col2 the second stone's column number
	 * @param player the current player
	 * @return true if the current player wins the game during this turn else false
	 * @throws IllegalArgumentException
	 */
	public boolean playTurnStandard(int row1, int col1, int row2, int col2, Player player)
			throws IllegalArgumentException {

		if (!board[row1][col1].equals("**") || !board[row2][col2].equals("**")) {
			throw new IllegalArgumentException();
		} else {
			board[row1][col1] = player.getName();
			board[row2][col2] = player.getName();
		}
		return winner(player);
	}

	private static boolean checkRow(int r0, int c0, int dr, int dc, int len, String name) {
		for (int k = 0; k != len; k++) {
			int r = r0 + k * dr;
			int c = c0 + k * dc;
			if (r < 0 || c < 0 || (r >= board.length) || (c >= board[r].length) || !board[r][c].equals(name)) {
				return false;
			}
		}
		return true;
	}

	private boolean winner(Player currentPlayer) {
		String x = currentPlayer.getName();
		for (int r = 0; r != board.length; r++) {
			for (int c = 0; c != board[r].length; c++) {
				if (checkRow(r, c, 0, 1, 6, x)) {
					return true;
				}
				if (checkRow(r, c, 1, 0, 6, x)) {
					return true;
				}
				if (checkRow(r, c, 1, 1, 6, x)) {
					return true;
				}
				if (checkRow(r, c, 1, -1, 6, x)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Prints the game board
	 */
	public void printGrid() {
		for (String[] row : board) {
			Terminal.printLine(Arrays.toString(row).replace("[", "").replace("]", "").replace(",", ""));
		}
	}

	/**
	 * Prints a specific row out of the game board
	 * 
	 * @param rowNumber the number of the row to print
	 */
	public void printRow(int rowNumber) {
		String[] row = board[rowNumber];
		if (!row[rowNumber].equals("P1") && !row[rowNumber].equals("P2") && !row[rowNumber].equals("P3")
				&& !row[rowNumber].equals("P4")) {
			row[rowNumber] = "**";
		}
		Terminal.printLine(Arrays.toString(row).replace("[", "").replace("]", "").replace(",", ""));
	}

	/**
	 * Prints a specific column out of the game board
	 * 
	 * @param columnNumber the number of the column to print
	 */
	public void printColumn(int columnNumber) {
		String column = "";
		for (int i = 0; i < board.length; i++) {
			column += board[i][columnNumber] + " ";
		}
		Terminal.printLine(column);
	}

	/**
	 * Prints a single slot out of the game board
	 * 
	 * @param row the row number of the slot to print
	 * @param col the column number of the slot to print
	 */
	public void printSlot(int row, int col) {
		Terminal.printLine(board[row][col]);
	}

	/**
	 * Instantiates the players that are going to participate in the game
	 * 
	 * @param numberOfPlayers the number of players to instantiate
	 */
	public void instantiatePlayers(int numberOfPlayers) {
		Player playerOne = new Player("P1");
		Player playerTwo = new Player("P2");
		Player playerThree = new Player("P3");
		Player playerFour = new Player("P4");
		switch (numberOfPlayers) {
		case 1: {
			players.add(playerOne);
			break;
		}
		case 2: {
			players.add(playerOne);
			players.add(playerTwo);
			break;
		}
		case 3: {
			players.add(playerOne);
			players.add(playerTwo);
			players.add(playerThree);
			break;
		}
		case 4: {
			players.add(playerOne);
			players.add(playerTwo);
			players.add(playerThree);
			players.add(playerFour);
			break;
		}

		}

	}

	/**
	 * @return the list of the players playing the game
	 */
	public LinkedList<Player> getPlayers() {
		return players;
	}

	/**
	 * Specifies the current player 
	 * 
	 * @param turn the current turn
	 * @param numberOfPlayers the number of the players in the game
	 * @return the current player 
	 */
	public Player determineCurrentPlayer(int turn, int numberOfPlayers) {
		if (turn == numberOfPlayers + 1) {
			turn = 1;
		}
		return players.get(turn - 1);

	}

	private boolean winTorusRow(Player player) {
		boolean won = false;
		String nameConcat = player.getName() + player.getName() + player.getName() + player.getName() + player.getName()
				+ player.getName();
		for (int i = 0; i < board.length; i++) {
			if ((board[i][0] + board[i][17] + board[i][16] + board[i][15] + board[i][14] + board[i][13])
					.equals(nameConcat)) {
				won = true;
			} else if ((board[i][0] + board[i][1] + board[i][2] + board[i][3] + board[i][4] + board[i][17])
					.equals(nameConcat)) {
				won = true;
			} else if ((board[i][0] + board[i][1] + board[i][17] + board[i][16] + board[i][15] + board[i][14])
					.equals(nameConcat)) {
				won = true;
			} else if ((board[i][0] + board[i][1] + board[i][2] + board[i][3] + board[i][16] + board[i][17])
					.equals(nameConcat)) {
				won = true;
			} else if ((board[i][0] + board[i][1] + board[i][2] + board[i][17] + board[i][16] + board[i][15])
					.equals(nameConcat)) {
				won = true;
			}
		}
		return won;
	}

	private boolean winTorusColumn(Player player) {
		boolean won = false;
		String nameConcat = player.getName() + player.getName() + player.getName() + player.getName() + player.getName()
				+ player.getName();
		for (int i = 0; i < board.length; i++) {
			if ((board[0][i] + board[17][i] + board[16][i] + board[15][i] + board[14][i] + board[13][i])
					.equals(nameConcat)) {
				won = true;
			} else if ((board[0][i] + board[1][i] + board[2][i] + board[3][i] + board[4][i] + board[17][i])
					.equals(nameConcat)) {
				won = true;
			} else if ((board[0][i] + board[1][i] + board[17][i] + board[16][i] + board[15][i] + board[14][i])
					.equals(nameConcat)) {
				won = true;
			} else if ((board[0][i] + board[1][i] + board[2][i] + board[3][i] + board[16][i] + board[17][i])
					.equals(nameConcat)) {
				won = true;
			} else if ((board[0][i] + board[1][i] + board[2][i] + board[17][i] + board[16][i] + board[15][i])
					.equals(nameConcat)) {
				won = true;
			}
		}
		return won;
	}

	private boolean winTorusLeftDiagonal(Player player) {
		boolean won = false;
		int n = board.length;
		String nameConcat = player.getName() + player.getName() + player.getName() + player.getName() + player.getName()
				+ player.getName();
		if ((board[0][0] + board[n - 1][n - 1] + board[n - 2][n - 2] + board[n - 3][n - 3] + board[n - 4][n - 4]
				+ board[n - 5][n - 5]).equals(nameConcat)) {
			won = true;
		} else if ((board[0][0] + board[1][1] + board[2][2] + board[3][3] + board[4][4] + board[n - 1][n - 1])
				.equals(nameConcat)) {
			won = true;
		} else if ((board[0][0] + board[1][1] + board[n - 1][n - 1] + board[n - 2][n - 2] + board[n - 3][n - 3]
				+ board[n - 4][n - 4]).equals(nameConcat)) {
			won = true;
		} else if ((board[0][0] + board[1][1] + board[2][2] + board[3][3] + board[n - 1][n - 1] + board[n - 2][n - 2])
				.equals(nameConcat)) {
			won = true;
		}

		else if ((board[0][0] + board[1][1] + board[2][2] + board[n - 1][n - 1] + board[n - 2][n - 2]
				+ board[n - 3][n - 3]).equals(nameConcat)) {
			won = true;
		}

		return won;
	}

	private boolean winTorusRightDiagonal(Player player) {
		boolean won = false;
		int n = board.length;
		String nameConcat = player.getName() + player.getName() + player.getName() + player.getName() + player.getName()
				+ player.getName();
		if ((board[n - 1][n - 1] + board[n - 1][0] + board[n - 2][1] + board[n - 3][2] + board[n - 4][3]
				+ board[n - 5][4]).equals(nameConcat)) {
			won = true;
		} else if ((board[n - 1][n - 1] + board[n - 2][n - 2] + board[n - 3][n - 3] + board[n - 4][n - 4]
				+ board[n - 5][n - 5] + board[n - 1][0]).equals(nameConcat)) {
			won = true;
		} else if ((board[n - 1][n - 1] + board[n - 2][n - 2] + board[n - 1][0] + board[n - 2][1] + board[n - 3][2]
				+ board[n - 4][3]).equals(nameConcat)) {
			won = true;
		} else if ((board[n - 1][n - 1] + board[n - 2][n - 2] + board[n - 3][n - 3] + board[n - 4][n - 4]
				+ board[n - 1][0] + board[n - 2][1]).equals(nameConcat)) {
			won = true;
		} else if ((board[n - 1][n - 1] + board[n - 2][n - 2] + board[n - 3][n - 3] + board[n - 1][0] + board[n - 2][1]
				+ board[n - 3][2]).equals(nameConcat)) {
			won = true;
		}
		return won;
	}

	/**
	 * Checks if the game ends in a draw
	 * 
	 * @param currentTurn the current turn
	 * @param won true if the game is won else false
	 * @param gameOver true if the game is over else false
	 * @return true if the game ended in a draw else false
	 */
	public boolean checkDraw(int currentTurn, boolean won, boolean gameOver) {
		if (currentTurn == 324 && board.length == 18 && won == false
				|| currentTurn == 400 && board.length == 20 && won == false) {
			Terminal.printLine("draw");
			gameOver = true;
		}

		return gameOver;
	}

}