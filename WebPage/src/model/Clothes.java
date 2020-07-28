// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Clothes.java

package model;

import java.io.Serializable;

public class Clothes implements Serializable{

	private static final long serialVersionUID = 0xc4ad367a6279bdf8L;
	private String styleNo;
	private String cname;
	private String color;
	private Integer unitPrice;
	private String size;
	private String description;
	private String manufacturer;
	private String category;
	private long unitsInStock;
	private String fileName;
	private int quantity;

	public Clothes(){

	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getSize(){
		return size;
	}

	public void setSize(String size){
		this.size = size;
	}

	public String getStyleNo(){
		return styleNo;
	}

	public void setStyleNo(String styleNo){
		this.styleNo = styleNo;
	}

	public String getCname(){
		return cname;
	}

	public void setCname(String cname){
		this.cname = cname;
	}

	public String getColor(){
		return color;
	}

	public void setColor(String color){
		this.color = color;
	}

	public Integer getUnitPrice(){
		return unitPrice;
	}

	public void setUnitPrice(Integer unitPrice){
		this.unitPrice = unitPrice;
	}

	public String getDescription(){
		return description;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getManufacturer(){
		return manufacturer;
	}

	public void setManufacturer(String manufacturer){
		this.manufacturer = manufacturer;
	}

	public String getCategory(){
		return category;
	}

	public void setCategory(String category){
		this.category = category;
	}

	public long getUnitsInStock(){
		return unitsInStock;
	}

	public void setUnitsInStock(long unitsInStock){
		this.unitsInStock = unitsInStock;
	}

	public String getFileName(){
		return fileName;
	}

	public void setFileName(String fileName){
		this.fileName = fileName;
	}


}
