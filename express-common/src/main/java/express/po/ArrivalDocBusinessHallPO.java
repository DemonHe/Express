package express.po;

import java.io.Serializable;

public class ArrivalDocBusinessHallPO extends DocumentPO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8434653442398652066L;
	private String arriveDate;
	/**
	 * 营业厅到达单分为两种   营业厅--->营业厅
	 * 						中转中心--->营业厅
	 * 
	 * 
	 * 营业厅到营业厅时不存在中转单  没有中转单ID   设为none  
	 * 
	 * 
	 * 中转单ID 在建立到达单的时候通过查找获得
	 * 
	 */
	
	private String transferDocID="none";//中转单ID 
	private String departure;//出发地
	private GoodsArrivalStatus arrivalStatus;//枚举类  货物到达状态（损坏、完整、丢失）
	private String orderID;
	
	
	public ArrivalDocBusinessHallPO(String arriveDate,String transferDocID,String departure,
			GoodsArrivalStatus arrivalStatus,String orderID){
		this.arriveDate=arriveDate;
		this.transferDocID=transferDocID;
		this.departure=departure;
		this.arrivalStatus=arrivalStatus;
		this.orderID=orderID;
	}
	
	public String getArriveTime(){
		return arriveDate;
	}
	
	public String getTransferDocID(){
		return transferDocID;
	}
	
	public String getDeparture(){
		return departure;
	}
	public String getOrderID(){
		return orderID;
	}
	
	public GoodsArrivalStatus getArrivalStatus(){
		return arrivalStatus;
	}
	
}
