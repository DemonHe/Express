package express.po;

import java.io.Serializable;

public class ArrivalDocTransCenterPO extends DocumentPO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5372014229082128409L;
	private String arriveDate;
	private String transCenterID;//中转中心ID
	
	/**
	 * 中转中心到达单分为两�?   营业�?--->中转中心
	 * 						中转中心--->中转中心
	 * 
	 * 营业厅到中转中心时不存在中转�?  没有中转单ID   设为�?1  
	 * 根据中转单ID 判断当前到达单类�?  以修改货运状�?
	 * 
	 */
	
	private String transferDocID="-1";//中转单ID   
	
	
	
	private String departure;//出发�?
	private GoodsArrivalStatus arrivalStatus;//枚举�?  货物到达状�?�（损坏、完整�?�丢失）
	private String orderID;
	
	//constructor
	public ArrivalDocTransCenterPO(String orderID,String arriveDate,String transCenterID,
						String transferDocID,String departure,
						GoodsArrivalStatus arrivalStatus){
		this.orderID=orderID;
		this.arriveDate=arriveDate;
		this.transCenterID=transCenterID;
		this.transferDocID=transferDocID;
		this.departure=departure;
		this.arrivalStatus=arrivalStatus;	
	}
	
	public String getOrderID(){
		return orderID;
	}
	
	public String getArriveDate(){
		return arriveDate;
	}
	public String getTransCenterID(){
		return transCenterID;
	}
	public String getTransferDocID(){
		return transferDocID;
	}
	public String getDeparture(){
		return departure;
	}
	public GoodsArrivalStatus getArrivalStatus(){
		return arrivalStatus;
	}
	
	
	
}
