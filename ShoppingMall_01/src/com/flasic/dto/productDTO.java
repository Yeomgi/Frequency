package com.flasic.dto;

import java.sql.Timestamp;

public class productDTO {
	private int num;
	private String name;
	private String kind;
	private String color;
	private String productsize;
	private int price1;	
	private int price2;	
	private int price3;	
	private String content;
	private String image;
	private String image2;
	private String image3;
	private String image4;
	private String image5;
	private int suply;
	private Timestamp productdate;
	private int hit;
	private String best;
	
	public productDTO() {
	}
	
	public productDTO(int num, String name, String kind, String color, String productsize, int price1, int price2,
			int price3, String content, String image, String image2, String image3, String image4, String image5,
			int suply, Timestamp productdate, int hit, String best) {
		super();
		this.num = num;
		this.name = name;
		this.kind = kind;
		this.color = color;
		this.productsize = productsize;
		this.price1 = price1;
		this.price2 = price2;
		this.price3 = price3;
		this.content = content;
		this.image = image;
		this.image2 = image2;
		this.image3 = image3;
		this.image4 = image4;
		this.image5 = image5;
		this.suply = suply;
		this.productdate = productdate;
		this.hit = hit;
		this.best = best;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
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

	public String getProductsize() {
		return productsize;
	}

	public void setProductsize(String productsize) {
		this.productsize = productsize;
	}

	public int getPrice1() {
		return price1;
	}

	public void setPrice1(int price1) {
		this.price1 = price1;
	}

	public int getPrice2() {
		return price2;
	}

	public void setPrice2(int price2) {
		this.price2 = price2;
	}

	public int getPrice3() {
		return price3;
	}

	public void setPrice3(int price3) {
		this.price3 = price3;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getImage2() {
		return image2;
	}

	public void setImage2(String image2) {
		this.image2 = image2;
	}

	public String getImage3() {
		return image3;
	}

	public void setImage3(String image3) {
		this.image3 = image3;
	}

	public String getImage4() {
		return image4;
	}

	public void setImage4(String image4) {
		this.image4 = image4;
	}

	public String getImage5() {
		return image5;
	}

	public void setImage5(String image5) {
		this.image5 = image5;
	}

	public int getSuply() {
		return suply;
	}

	public void setSuply(int suply) {
		this.suply = suply;
	}

	public Timestamp getProductdate() {
		return productdate;
	}

	public void setProductdate(Timestamp productdate) {
		this.productdate = productdate;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getBest() {
		return best;
	}

	public void setBest(String best) {
		this.best = best;
	}
	
	
}
