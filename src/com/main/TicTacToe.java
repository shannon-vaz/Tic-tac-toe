package com.main;

import java.util.Scanner;

public class TicTacToe {
	
	public static void main(String[] args) {
		Board board = new Board();
		displayWelcomeMessage();
		int slotNumber;
		while (board.isGameCompleted() == false) {
			System.out.println("Player turn: " + board.getCurrentPlayer());
			board.displayBoard();
			System.out.print("> ");
			slotNumber = (new Scanner(System.in)).nextInt();
			try {
				board.makeEntry(slotNumber);
			} catch (IllegalArgumentException ex) {
				System.out.println(ex.getMessage());
			}
		}
		
		System.out.println("\n\nGame Completed!");
		board.displayBoard();
		if (board.getWinner() != null) {
			System.out.println("Winner is player " + board.getWinner());
		} else {
			System.out.println("No winner found :(");
		}
	}
	
	public static void displayWelcomeMessage() {
		System.out.println("Welcome to Tic-Tac-Toe");
		System.out.println("This is a two player game: Player X and Player O");
		System.out.println("Each player plays in alternating turns");
		System.out.println("Player X goes first\n\n");
	}
}
