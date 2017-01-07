package ar.edu.Proyecto2048.model;

import java.util.ArrayList;

import ar.edu.Proyecto2048.util.Direction;

public class ReplayModel {
	
	private ArrayList<Direction> movements;
	
	public ReplayModel() {
		this.setMovements(new ArrayList<Direction>());
	}

	public ArrayList<Direction> getMovements() {
		return movements;
	}

	private void setMovements(ArrayList<Direction> movements) {
		this.movements = movements;
	}

}
