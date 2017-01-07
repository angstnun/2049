package ar.edu.Proyecto2048.controller;

import ar.edu.Proyecto2048.model.BoardModel;
import ar.edu.Proyecto2048.model.PlayerModel;
import ar.edu.Proyecto2048.model.ReplayModel;
import ar.edu.Proyecto2048.model.TileModel;
import ar.edu.Proyecto2048.util.Constants;
import ar.edu.Proyecto2048.util.Direction;
import ar.edu.Proyecto2048.util.PlayerState;
import ar.edu.Proyecto2048.util.Position;
import ar.edu.Proyecto2048.util.Randomizer;

public class GameplayController {
	
	public static BoardModel MAIN_BOARD;
	public static PlayerModel[] PLAYERS;
	public static PlayerModel currentPlayer;
	public static Boolean debug = Boolean.FALSE;
	
	private static void addPlayers(Integer nPlayers) {
		PLAYERS = new PlayerModel[nPlayers];
		for(Integer i=0;i<nPlayers;i++) PLAYERS[i] = (new PlayerModel(i));
	}

	/** Method for adding brand new tiles to board **/
	
	public static void spawnTile() {
		Integer row = Randomizer.nextRandomInt();
		Integer col = Randomizer.nextRandomInt();
		TileModel t = new TileModel(new Position(row, col));
		if(MAIN_BOARD.getBoard()[row][col] == null) MAIN_BOARD.getBoard()[row][col] = t;
		else spawnTile();		
	}
	
	public static void spawnTileAt(Integer row, Integer col) {
		TileModel t = new TileModel(new Position(row, col));
		if(MAIN_BOARD.getBoard()[row][col] == null) {
			MAIN_BOARD.getBoard()[row][col] = t;
		}
		else System.out.println("That position is already occupied");
	}
	
	private static void spawnTileAt(Integer value, Integer row, Integer col) {
		TileModel t = new TileModel(value, new Position(row, col));
		if(MAIN_BOARD.getBoard()[row][col] == null) {
			MAIN_BOARD.getBoard()[row][col] = t;
		}
		else System.out.println("That position is already occupied");
	}
	
	// Tile movement methods
	
	//**boardMover: Spiritual successor to old tileMover method (R.I.P)**//
	
	public static void boardMover(Direction d) {
		TileModel currentTile;
		Boolean added;	//Checks if a tile addition has occurred between currentTile and another tile.
		Boolean moved = Boolean.FALSE;	//Checks if any tiles have been moved.
		//Switches grid-reading direction depending on the direction selected.
		//Refactor with negative numbers
		switch(d) {
		case UP:
			for(Integer i=0; i<Constants.BOARD_SIZE; i++) {
				currentTile = null;
				added = Boolean.FALSE;
				for(Integer j=0; j<Constants.BOARD_SIZE; j++){
					if(MAIN_BOARD.getBoard()[j][i] != null && currentTile == null){
						currentTile = MAIN_BOARD.getBoard()[j][i];
						if(j > 0){
							MAIN_BOARD.getBoard()[j][i] = null;
							currentTile.setPosition(new Position(0,i));
							MAIN_BOARD.getBoard()[0][i] = currentTile;
							moved = Boolean.TRUE;
						}
					}
					else if(MAIN_BOARD.getBoard()[j][i] != null && currentTile != null){
						if(MAIN_BOARD.getBoard()[j][i].equals(currentTile)){
							//Only one addition per selected tile.
							if(added.equals(Boolean.TRUE)){
								MAIN_BOARD.getBoard()[j][i].setPosition(new Position(currentTile.getPosition().getX()+1,i));
								currentTile = MAIN_BOARD.getBoard()[j][i];
								MAIN_BOARD.getBoard()[j][i] = null;
								MAIN_BOARD.getBoard()[currentTile.getPosition().getX()][i] = currentTile;
								added = Boolean.FALSE;
								moved = Boolean.TRUE;
							}
							else{
								MAIN_BOARD.getBoard()[currentTile.getPosition().getX()][currentTile.getPosition().getY()].add(MAIN_BOARD.getBoard()[j][i]);
								if(MAIN_BOARD.getBoard()[currentTile.getPosition().getX()][currentTile.getPosition().getY()].is2048())
									MAIN_BOARD.setWinner(Boolean.TRUE);
								MAIN_BOARD.getBoard()[j][i] = null;
								currentPlayer.setScore(currentPlayer.getScore()+4);
								added = Boolean.TRUE;
								moved = Boolean.TRUE;
							}
						}
						else{
							if(MAIN_BOARD.getBoard()[MAIN_BOARD.getBoard()[j][i].getPosition().getX()-1][i] == null) moved = Boolean.TRUE;
							MAIN_BOARD.getBoard()[j][i].setPosition(new Position(currentTile.getPosition().getX()+1,i));
							currentTile = MAIN_BOARD.getBoard()[j][i];
							MAIN_BOARD.getBoard()[j][i] = null;
							MAIN_BOARD.getBoard()[currentTile.getPosition().getX()][i] = currentTile;
						}
					}
				}
			}
			break;
		case DOWN:
			for(Integer i=0;i<Constants.BOARD_SIZE;i++) {
				currentTile = null;
				added = Boolean.FALSE;
				for(Integer j=Constants.BOARD_SIZE-1;j>-1;j--){
					if(MAIN_BOARD.getBoard()[j][i] != null && currentTile == null){
						currentTile = MAIN_BOARD.getBoard()[j][i];
						if(j < Constants.BOARD_SIZE-1){
							MAIN_BOARD.getBoard()[j][i] = null;
							currentTile.setPosition(new Position(Constants.BOARD_SIZE-1,i));
							MAIN_BOARD.getBoard()[Constants.BOARD_SIZE-1][i] = currentTile;
							moved = Boolean.TRUE;
						}
					}
					else if(MAIN_BOARD.getBoard()[j][i] != null && currentTile != null){
						if(MAIN_BOARD.getBoard()[j][i].equals(currentTile)){
							//Only one addition per selected tile.
							if(added.equals(Boolean.TRUE)){
								MAIN_BOARD.getBoard()[j][i].setPosition(new Position(currentTile.getPosition().getX()-1,i));
								currentTile = MAIN_BOARD.getBoard()[j][i];
								MAIN_BOARD.getBoard()[j][i] = null;
								MAIN_BOARD.getBoard()[currentTile.getPosition().getX()][i] = currentTile;
								added = Boolean.FALSE;
								moved = Boolean.TRUE;
							}
							else{
								MAIN_BOARD.getBoard()[currentTile.getPosition().getX()][currentTile.getPosition().getY()].add(MAIN_BOARD.getBoard()[j][i]);
								if(MAIN_BOARD.getBoard()[currentTile.getPosition().getX()][currentTile.getPosition().getY()].is2048())
									MAIN_BOARD.setWinner(Boolean.TRUE);
								MAIN_BOARD.getBoard()[j][i] = null;
								currentPlayer.setScore(currentPlayer.getScore()+4);
								added = Boolean.TRUE;
								moved = Boolean.TRUE;
							}
						}
						else{
							if(MAIN_BOARD.getBoard()[MAIN_BOARD.getBoard()[j][i].getPosition().getX()+1][i] == null) moved = Boolean.TRUE;
							MAIN_BOARD.getBoard()[j][i].setPosition(new Position(currentTile.getPosition().getX()-1,i));
							currentTile = MAIN_BOARD.getBoard()[j][i];
							MAIN_BOARD.getBoard()[j][i] = null;
							MAIN_BOARD.getBoard()[currentTile.getPosition().getX()][i] = currentTile;
						}
					}
				}
			}
			break;
		case LEFT:
			for(Integer i=0;i<Constants.BOARD_SIZE;i++) {
				currentTile = null;
				added = Boolean.FALSE;
				for(Integer j=0; j<Constants.BOARD_SIZE; j++){
					if(MAIN_BOARD.getBoard()[i][j] != null && currentTile == null){
						currentTile = MAIN_BOARD.getBoard()[i][j];
						if(j > 0){
							MAIN_BOARD.getBoard()[i][j] = null;
							currentTile.setPosition(new Position(i,0));
							MAIN_BOARD.getBoard()[i][0] = currentTile;
							moved = Boolean.TRUE;
						}
					}
					else if(MAIN_BOARD.getBoard()[i][j] != null && currentTile != null){
						if(MAIN_BOARD.getBoard()[i][j].equals(currentTile)){
							//Only one addition per selected tile.
							if(added.equals(Boolean.TRUE)){
								MAIN_BOARD.getBoard()[i][j].setPosition(new Position(i,currentTile.getPosition().getY()+1));
								currentTile = MAIN_BOARD.getBoard()[i][j];
								MAIN_BOARD.getBoard()[i][j] = null;
								MAIN_BOARD.getBoard()[i][currentTile.getPosition().getY()] = currentTile;
								added = Boolean.FALSE;
								moved = Boolean.TRUE;
							}
							else{
								MAIN_BOARD.getBoard()[currentTile.getPosition().getX()][currentTile.getPosition().getY()].add(MAIN_BOARD.getBoard()[i][j]);
								if(MAIN_BOARD.getBoard()[currentTile.getPosition().getX()][currentTile.getPosition().getY()].is2048())
									MAIN_BOARD.setWinner(Boolean.TRUE);
								MAIN_BOARD.getBoard()[i][j] = null;
								currentPlayer.setScore(currentPlayer.getScore()+4);
								added = Boolean.TRUE;
								moved = Boolean.TRUE;
							}
						}
						else{
							if(MAIN_BOARD.getBoard()[i][MAIN_BOARD.getBoard()[i][j].getPosition().getY()-1] == null) 
								moved = Boolean.TRUE;
							MAIN_BOARD.getBoard()[i][j].setPosition(new Position(i,currentTile.getPosition().getY()+1));
							currentTile = MAIN_BOARD.getBoard()[i][j];
							MAIN_BOARD.getBoard()[i][j] = null;
							MAIN_BOARD.getBoard()[i][currentTile.getPosition().getY()] = currentTile;
						}
					}
				}
			}
			break;
		case RIGHT:
			for(Integer i=0;i<Constants.BOARD_SIZE;i++) {
				currentTile = null;
				added = Boolean.FALSE;
				for(Integer j=Constants.BOARD_SIZE-1;j>-1;j--){
					if(MAIN_BOARD.getBoard()[i][j] != null && currentTile == null){
						currentTile = MAIN_BOARD.getBoard()[i][j];
						if(j < Constants.BOARD_SIZE-1){
							MAIN_BOARD.getBoard()[i][j] = null;
							currentTile.setPosition(new Position(i,Constants.BOARD_SIZE-1));
							MAIN_BOARD.getBoard()[i][Constants.BOARD_SIZE-1] = currentTile;
							moved = Boolean.TRUE;
						}
					}
					else if(MAIN_BOARD.getBoard()[i][j] != null && currentTile != null){
						if(MAIN_BOARD.getBoard()[i][j].equals(currentTile)){
							//Only one addition per selected tile.
							if(added.equals(Boolean.TRUE)){
								MAIN_BOARD.getBoard()[i][j].setPosition(new Position(i,currentTile.getPosition().getY()-1));
								currentTile = MAIN_BOARD.getBoard()[i][j];
								MAIN_BOARD.getBoard()[i][j] = null;
								MAIN_BOARD.getBoard()[i][currentTile.getPosition().getY()] = currentTile;
								added = Boolean.FALSE;
								moved = Boolean.TRUE;
							}
							else{
								MAIN_BOARD.getBoard()[currentTile.getPosition().getX()][currentTile.getPosition().getY()].add(MAIN_BOARD.getBoard()[i][j]);
								if(MAIN_BOARD.getBoard()[currentTile.getPosition().getX()][currentTile.getPosition().getY()].is2048())
									MAIN_BOARD.setWinner(Boolean.TRUE);
								MAIN_BOARD.getBoard()[i][j] = null;
								currentPlayer.setScore(currentPlayer.getScore()+4);
								added = Boolean.TRUE;
								moved = Boolean.TRUE;
							}
						}
						else{
							if(MAIN_BOARD.getBoard()[i][MAIN_BOARD.getBoard()[i][j].getPosition().getY()+1] == null) moved = Boolean.TRUE;
							MAIN_BOARD.getBoard()[i][j].setPosition(new Position(i,currentTile.getPosition().getY()-1));
							currentTile = MAIN_BOARD.getBoard()[i][j];
							MAIN_BOARD.getBoard()[i][j] = null;
							MAIN_BOARD.getBoard()[i][currentTile.getPosition().getY()] = currentTile;
						}
					}
				}
			}
			break;
		default:
			System.out.println("Invalid direction");				
			break;
		}
		if(!debug){
			if(!MAIN_BOARD.isBoardFull()){
				if(moved){
					currentPlayer.setnMoves(currentPlayer.getnMoves()+1);
					spawnTile();
				}
			}
			else MAIN_BOARD.setLoser(Boolean.TRUE);			
		}
	}

	public static void play() {
		GameplayController.MAIN_BOARD = new BoardModel();
		if(!debug){
			Randomizer.initRandomizer();
			GameplayController.spawnTile();
			GameplayController.spawnTile();			
		}
		else{
			Randomizer.initRandomizer();
			GameplayController.spawnTileAt(1024, 0, 0);
			GameplayController.spawnTileAt(1024, 3, 3);
		}
		ReplayController.replayInit(new ReplayModel());
		GameplayController.addPlayers(Constants.PLAYERS_NUMBER);
		GameplayController.currentPlayer = PLAYERS[0];
	}
	
	public static void switchPlayer() {
		if(Constants.PLAYERS_NUMBER > 1){
			if(currentPlayer.getId() < Constants.PLAYERS_NUMBER-1){
				currentPlayer = PLAYERS[currentPlayer.getId()+1];
			}
			else currentPlayer = PLAYERS[0];			
		}
	}

	public static PlayerState checkGameplayState() {
		if(GameplayController.checkGameOver()) 
			return PlayerState.LOSER;
		else if(GameplayController.checkWinner())
			return PlayerState.WINNER;
		else{
			return PlayerState.PLAYING;
		}
	}

	private static Boolean checkGameOver() {
		if(MAIN_BOARD.isLoser()) return Boolean.TRUE;
		return Boolean.FALSE;
	}
	
	private static Boolean checkWinner() {
		if(MAIN_BOARD.isWinner()) return Boolean.TRUE;
		return Boolean.FALSE;
	}

	public static void replay() {
		GameplayController.MAIN_BOARD = new BoardModel();
		if(debug){
			GameplayController.spawnTileAt(1024, 0, 0);
			GameplayController.spawnTileAt(1024, 3, 3);			
		}
		else {
			Randomizer.initRandomizer(Randomizer.getSeed());
			GameplayController.spawnTile();
			GameplayController.spawnTile();
		}
		GameplayController.addPlayers(Constants.PLAYERS_NUMBER);
		GameplayController.currentPlayer = PLAYERS[0];
		ReplayController.start();
	}

}