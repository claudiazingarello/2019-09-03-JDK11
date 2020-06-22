package it.polito.tdp.food.model;

public class Adiacenza {
	private String portion_name1;
	private String portion_name2;
	private int peso;
	public Adiacenza(String portion_name1, String portion_name2, int peso) {
		super();
		this.portion_name1 = portion_name1;
		this.portion_name2 = portion_name2;
		this.peso = peso;
	}
	public String getPortion_name1() {
		return portion_name1;
	}
	public void setPortion_name1(String portion_name1) {
		this.portion_name1 = portion_name1;
	}
	public String getPortion_name2() {
		return portion_name2;
	}
	public void setPortion_name2(String portion_name2) {
		this.portion_name2 = portion_name2;
	}
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	
	
}
