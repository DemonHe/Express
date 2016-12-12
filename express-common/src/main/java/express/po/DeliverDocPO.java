package express.po;

import java.io.Serializable;

public class DeliverDocPO  extends DocumentPO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8505147907076218773L;
	private String arriveDate;
	private String orderID;
	private String deliverManID;//快递员工号
	
	
	
	//consturctor
	public DeliverDocPO(String arriveDate,String orderID,String deliverManID){
		this.arriveDate=arriveDate;	
		this.orderID=orderID;
		this.deliverManID=deliverManID;
		
	}
	
	public String getArriveDate(){
		return arriveDate;
	}
	public String getOrderID(){
		return orderID;
	}
	public String getDeliverManID(){
		return deliverManID;
	}
	

	
}
