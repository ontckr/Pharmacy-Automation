package model;


public class BoxSize {

	private String boxSize;
	private String name;

	public BoxSize(String name, String boxSize) {

		this.boxSize = boxSize;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getBoxSize() {
		return boxSize;
	}

}
