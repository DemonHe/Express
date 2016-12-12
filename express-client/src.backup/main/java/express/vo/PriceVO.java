package express.vo;

import express.po.DeliveryType;

public class PriceVO {
	private double price;  //每公斤每公里xxx�?
	private DeliveryType type;
	public PriceVO(double price,DeliveryType type){
		this.price = price;
		this.type=type;
	}
	public double getPrice(){
		return price;
	}
	
	public DeliveryType getDeliveryType(){
		return type;
	}
	
	public void setPrice(double price){
		this.price=price;
	}
}
