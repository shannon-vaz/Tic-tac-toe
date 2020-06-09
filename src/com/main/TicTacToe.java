package com.main;

import java.util.Scanner;

public class TicTacToe {

	private Board board;
	private Player currentPlayer;
	private Boolean winnerFound;
	
	public TicTacToe() {
		board = new Board();
		this.currentPlayer = Player.X;
		this.winnerFound = false;
	}

	public void play() {
		displayWelcomeMessage();
		int slotNumber;
		while (isGameCompleted() == false) {
			System.out.println("Player turn: " + currentPlayer);
			board.displayBoard();
			System.out.print("> ");
			slotNumber = (new Scanner(System.in)).nextInt();
			try {
				makeEntry(slotNumber);
			} catch (IllegalArgumentException ex) {
				System.out.println(ex.getMessage());
			}
		}
		
		System.out.println("\n\nGame Completed!");
		board.displayBoard();
		if (winnerFound) {
			System.out.println("Winner is player " + getWinner());
		} else {
			System.out.println("No winner found :(");
		}
	}

	public void makeEntry(int slotNumber) {
		if (slotNumber < 1 || slotNumber > 9) {
			throw new IllegalArgumentException("Incorrect input: expected slot number to be in range 1 - 9");
		}
		slotNumber--;
		int row = slotNumber / 3;
		int column = slotNumber % 3;
		String[][] boardState = board.getBoardState();
		String entry = boardState[row][column];
		if (entry == Player.X.toString() || entry == Player.O.toString()) {
			throw new IllegalArgumentException("Incorrect input: entry already created");
		}
		boardState[row][column] = currentPlayer.toString();
		board.setBoardState(boardState);
		checkForWinner();
		checkIfBoardIsFull();
		if (isGameCompleted() == false) {
			currentPlayer = getCurrentPlayer() == "X"? Player.O : Player.X;
		}
	}
	
	private void checkIfBoardIsFull() {
		if (board.getBoardFull() == true) return;
		String [][] boardState = board.getBoardState();
		Boolean boardFull = true;
		// check each entry in the board
		// if even one entry is empty, board is not full
		for (String row[] : boardState) {
			for (String entry : row) {
				boardFull = boardFull && (entry == Player.X.toString() || entry == Player.O.toString());
				if (!boardFull) return;
			}
		}
		board.setBoardFull(true);
	}

	private void checkForWinner() {
		if (winnerFound) return;
		String [][] boardState = board.getBoardState();
		// check each of the 8 winning conditions on the board
		for (int winningCondition = 0; !winnerFound && winningCondition < 8; winningCondition++) {
			switch (winningCondition) {
			// rows match
			case 0: 
				if (boardState[0][0] == boardState[0][1] && boardState[0][1] == boardState[0][2]) winnerFound = true;
				break;
			case 1: 
				if (boardState[1][0] == boardState[1][1] && boardState[1][1] == boardState[1][2]) winnerFound = true;
				break;
			case 2: 
				if (boardState[2][0] == boardState[2][1] && boardState[2][1] == boardState[2][2]) winnerFound = true;
				break;
			// columns match
			case 3: 
				if (boardState[0][0] == boardState[1][0] && boardState[1][0] == boardState[2][0]) winnerFound = true;
				break;
			case 4: 
				if (boardState[0][1] == boardState[1][1] && boardState[1][1] == boardState[2][1]) winnerFound = true;
				break;
			case 5: 
				if (boardState[0][2] == boardState[1][2] && boardState[1][2] == boardState[2][2]) winnerFound = true;
				break;
			// diagonals match
			case 6: 
				if (boardState[0][0] == boardState[1][1] && boardState[1][1] == boardState[2][2]) winnerFound = true;
				break;
			case 7: 
				if (boardState[0][2] == boardState[1][1] && boardState[1][1] == boardState[2][0]) winnerFound = true;
				break;
			}
		}
	}
	
	public Boolean isGameCompleted() {
		return board.getBoardFull() || winnerFound;
	}
	
	public String getWinner() {
		if (winnerFound) {
			return currentPlayer.toString();
		} else {
			return null;
		}
	}
	
	public String getCurrentPlayer() {
		return currentPlayer.toString();
	}
	
	public static void displayWelcomeMessage() {
		System.out.println("Welcome to Tic-Tac-Toe");
		System.out.println("This is a two player game: Player X and Player O");
		System.out.println("Each player plays in alternating turns");
		System.out.println("Player X goes first\n\n");
	}
}
