package com.main;

public class Board {
	
	private String [][] boardState;
	private Boolean boardFull;
	
	public Board() {
		this.boardFull = false;
		this.boardState = new String[3][3];
		int slotNumber = 1;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				this.boardState[i][j] = String.valueOf(slotNumber++);
			}
		}
	}
	
	public void displayBoard() {
		for (String row[] : boardState) {
			for (String entry : row) {
				System.out.print(entry + " ");
			}
			System.out.println();
		}
	}

	public String [][] getBoardState() {
		return boardState;
	}

	public void setBoardState(String [][] boardState) {
		this.boardState = boardState;
	}

	public Boolean getBoardFull() {
		return boardFull;
	}

	public void setBoardFull(Boolean boardFull) {
		this.boardFull = boardFull;
	}
	
}
