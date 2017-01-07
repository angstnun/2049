package ar.edu.Proyecto2048.model;

public class Seed {

	private long value;
	
	public Seed ()
	{
		this.setValue(System.currentTimeMillis());
	}

	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}
	
}