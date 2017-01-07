package ar.edu.Proyecto2048.model;

import ar.edu.Proyecto2048.util.Constants;


public class BoardModel {
	
	private TileModel[][] board;
	private Boolean winner;
	private Boolean loser;
	
	public BoardModel() {
		this.setBoard(new TileModel[Constants.BOARD_SIZE][Constants.BOARD_SIZE]);
		this.setWinner(Boolean.FALSE);
		this.setLoser(Boolean.FALSE);
	}

	public Boolean isLoser() {
		return loser;
	}

	public void setLoser(Boolean loser) {
		this.loser = loser;
	}

	public Boolean isWinner() {
		return winner;
	}

	public void setWinner(Boolean winner) {
		this.winner = winner;
	}

	public TileModel[][] getBoard() {
		return board;
	}

	public void setBoard(TileModel[][] board) {
		this.board = board;
	}
	
	public void printBoard(){
		for(Integer i=0; i<Constants.BOARD_SIZE;i++){
			for(Integer j=0; j<Constants.BOARD_SIZE;j++) {
				if(this.getBoard()[i][j] != null) System.out.print(i+"-"+j+" ");
				else System.out.print("x"+"-"+"x"+" ");
				
			}
			System.out.println("");			
		}
		System.out.println("");
	}
	
	public void printBoardwithValues(){
		for(Integer i=0; i<Constants.BOARD_SIZE;i++){
			for(Integer j=0; j<Constants.BOARD_SIZE;j++) {
				if(this.getBoard()[i][j] != null) System.out.print(this.getBoard()[i][j].getValue()+" ");
				else System.out.print("-"+" ");
				
			}
			System.out.println("");			
		}
		System.out.println("");
	}
	
	public Boolean isBoardFull() {
		for(Integer i=0; i<Constants.BOARD_SIZE;i++)
			for(Integer j=0; j<Constants.BOARD_SIZE;j++) 
				if(this.getBoard()[i][j] == null) return Boolean.FALSE;
		return Boolean.TRUE;
	}

}