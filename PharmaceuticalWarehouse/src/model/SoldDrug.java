package model;

import java.io.Serializable;

public class SoldDrug implements Serializable{
	private int id;
	private String username;
	private int value;
	public SoldDrug(int id,  int value, String username) {
		this.id = id;
		this.username = username;
		this.value = value;
	}
	public int getID() {
		return id;
	}
	public String getUsername() {
		return username;
	}
	public int getValue() {
		return value;
	}
}
