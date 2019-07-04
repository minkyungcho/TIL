package com.vo;

import java.util.Date;

public class Product {
	private int id;
	private String name;
	private Double price;
	private Date regdate;
	private String imgname;
	
	public Product() {
	}
	
	public Product(int id, String name, Double price, Date regdate, String imgname) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.regdate = regdate;
		this.imgname = imgname;
	}

	public Product(int id, String name, Double price, String imgname) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.imgname = imgname;
	}

	public Product(int id, String name, Double price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}
	
	public Product(String name, Double price, String imgname) {
		this.name = name;
		this.price = price;
		this.imgname = imgname;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public String getImgname() {
		return imgname;
	}

	public void setImgname(String imgname) {
		this.imgname = imgname;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", regdate=" + regdate + ", imgname="
				+ imgname + "]";
	}
	
}
