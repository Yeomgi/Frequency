package com.flasic.dao;

public class productDAO {
	private String num;
	private String name;
	private String kind;
	private String color;
	private int supply;
	private String productsize;
	private int price;
	private String image;
	private int hit;
	public productDAO() {
	}
	public productDAO(String num, String name, String kind, String color, int supply, String productsize, int price,
			String image, int hit) {
		super();
		this.num = num;
		this.name = name;
		this.kind = kind;
		this.color = color;
		this.supply = supply;
		this.productsize = productsize;
		this.price = price;
		this.image = image;
		this.hit = hit;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getSupply() {
		return supply;
	}
	public void setSupply(int supply) {
		this.supply = supply;
	}
	public String getProductsize() {
		return productsize;
	}
	public void setProductsize(String productsize) {
		this.productsize = productsize;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	
	
}
