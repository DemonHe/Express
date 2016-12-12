package express.vo;

import express.po.GoodsArrivalStatus;

public class ArrivalDocBusinessHallVO extends DocumentVO{
	private String title="营业厅到达单";
	private String arriveDate;
	/**
	 * 营业厅到达单分为两种   营业�?--->营业�?
	 * 						中转中心--->营业�?
	 * 
	 * 
	 * 营业厅到营业厅时不存在中转单  没有中转单ID   设为none  
	 * 
	 * 
	 * 中转单ID 在建立到达单的时候�?�过查找获得
	 * 
	 */
	
	private String transferDocID="none";//中转单ID 
	private String departure;//出发�?
	private GoodsArrivalStatus arrivalStatus;//枚举�?  货物到达状�?�（损坏、完整�?�丢失）
	private String orderID;
	
	
	public ArrivalDocBusinessHallVO(String arriveDate,String transferDocID,String departure,
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
	//add title
	public String getTitle(){
		return title;
	}
}
