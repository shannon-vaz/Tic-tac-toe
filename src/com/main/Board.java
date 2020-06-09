package com.main;

public class Board {
	
	enum Player {
		X, O
	}
	
	private String [][] boardState;
	private Player currentPlayer;
	private Boolean winnerFound;
	private Boolean gameComplete;
	
	public Board() {
		this.currentPlayer = Player.X;
		this.winnerFound = false;
		this.gameComplete = false;
		this.setBoardState(new String[3][3]);
		int slotNumber = 1;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				this.getBoardState()[i][j] = String.valueOf(slotNumber++);
			}
		}
	}
	
	public void makeEntry(int slotNumber) {
		if (gameComplete == true) {
			throw new IllegalArgumentException("Game is completed: No empty slots available");
		}
		if (slotNumber < 1 || slotNumber > 9) {
			throw new IllegalArgumentException("Incorrect input: expected input to be in range 1 - 9");
		}
		slotNumber--;
		int row = slotNumber / 3;
		int column = slotNumber % 3;
		String entry = getBoardState()[row][column];
		if (entry == Player.X.toString() || entry == Player.O.toString()) {
			throw new IllegalArgumentException("Incorrect input: entry already created");
		}
		getBoardState()[row][column] = currentPlayer.toString();
		checkForWinner();
		if (winnerFound) {
			gameComplete = true;
		} else {
			checkIfBoardIsFull();
		}
		if (gameComplete == false) {
			currentPlayer = getCurrentPlayer() == "X"? Player.O : Player.X;
		}
	}
	
	private void checkIfBoardIsFull() {
		if (gameComplete == true) return;
		gameComplete = true;
		for (String row[] : getBoardState()) {
			for (String entry : row) {
				gameComplete = gameComplete && (entry == Player.X.toString() || entry == Player.O.toString());
				if (!gameComplete) return;
			}
		}
	}

	private void checkForWinner() {
		if (gameComplete) {
			return;
		}

		for (int winningCondition = 0; !winnerFound && winningCondition < 8; winningCondition++) {
			switch (winningCondition) {
			case 0: 
				if (getBoardState()[0][0] == getBoardState()[0][1] && getBoardState()[0][1] == getBoardState()[0][2]) winnerFound = true;
				break;
			case 1: 
				if (getBoardState()[1][0] == getBoardState()[1][1] && getBoardState()[1][1] == getBoardState()[1][2]) winnerFound = true;
				break;
			case 2: 
				if (getBoardState()[2][0] == getBoardState()[2][1] && getBoardState()[2][1] == getBoardState()[2][2]) winnerFound = true;
				break;
			case 3: 
				if (getBoardState()[0][0] == getBoardState()[1][0] && getBoardState()[1][0] == getBoardState()[2][0]) winnerFound = true;
				break;
			case 4: 
				if (getBoardState()[0][1] == getBoardState()[1][1] && getBoardState()[1][1] == getBoardState()[2][1]) winnerFound = true;
				break;
			case 5: 
				if (getBoardState()[0][2] == getBoardState()[1][2] && getBoardState()[1][2] == getBoardState()[2][2]) winnerFound = true;
				break;
			case 6: 
				if (getBoardState()[0][0] == getBoardState()[1][1] && getBoardState()[1][1] == getBoardState()[2][2]) winnerFound = true;
				break;
			case 7: 
				if (getBoardState()[0][2] == getBoardState()[1][1] && getBoardState()[1][1] == getBoardState()[2][0]) winnerFound = true;
				break;
			}
		}
	}
	
	public Boolean isGameCompleted() {
		return gameComplete;
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
	
	public void displayBoard() {
		for (String row[] : getBoardState()) {
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
}
