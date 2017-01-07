package ar.edu.Proyecto2048.model;

import ar.edu.Proyecto2048.util.Position;

public class TileModel {

	private Integer value;
	private Position position;
	
	public TileModel(Position posicion){
		this.setValue(2);
		this.setPosition(posicion);
	}

	public TileModel(Integer value, Position posicion){
		this.setValue(value);
		this.setPosition(posicion);
	}
	
	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}
	
	public void add(TileModel t) {
		this.setValue(t.getValue() + this.getValue());
	}
	
	public boolean equals(Object object) {
		if(object == null) return Boolean.FALSE;
		if(!(object instanceof TileModel)) return Boolean.FALSE;
		if(!this.getValue().equals(((TileModel)object).getValue())) return Boolean.FALSE;
		return Boolean.TRUE;
	}
	
	public Boolean is2048() {
		return this.getValue() == 2048 ? Boolean.TRUE : Boolean.FALSE;
	}
	
}