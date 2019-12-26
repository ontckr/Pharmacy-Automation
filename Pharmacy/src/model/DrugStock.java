package model;

import java.io.Serializable;

public class DrugStock implements Serializable{

	private int stock;
	private String name;

	public DrugStock(int stock, String name) {
		this.stock = stock;
		this.name = name;
	}

	public int getStock() {
		return stock;
	}

	public String getName() {
		return name;
	}

}
