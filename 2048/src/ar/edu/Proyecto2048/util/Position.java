package ar.edu.Proyecto2048.util;

public class Position {
	
	private Integer x;
	private Integer y;
	
	public Position (Integer x, Integer y)
	{
		this.setX(x);
		this.setY(y);
	}

	public Integer getX() {
		return x;
	}

	private void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	private void setY(Integer y) {
		this.y = y;
	}

	public boolean equals(Object obj){
		if(obj == null) return Boolean.FALSE;
		if(!(obj instanceof Position)) return Boolean.FALSE;
		return this.getX() == ((Position)obj).getX() && this.getY() == ((Position)obj).getY() ? Boolean.TRUE : Boolean.FALSE;
	}
	
	public String toString() {
		return this.getX()+" "+this.getY();
	}

}