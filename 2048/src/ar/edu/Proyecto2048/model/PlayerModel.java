package ar.edu.Proyecto2048.model;

public class PlayerModel {
	
	private Integer id;
	private Integer score;
	private Integer nMoves;

	public PlayerModel(Integer id){
		this.setId(id);
		this.setScore(0);
		this.setnMoves(0);
	}

	public Integer getnMoves() {
		return nMoves;
	}

	public void setnMoves(Integer nMoves) {
		this.nMoves = nMoves;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
	
}