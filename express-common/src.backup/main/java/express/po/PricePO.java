package express.po;

import java.io.Serializable;

public class PricePO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3589588593305786681L;
	private double price;  //每公斤每公里xxx�?
	private DeliveryType type;
	
	public PricePO(double price,DeliveryType type){
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
