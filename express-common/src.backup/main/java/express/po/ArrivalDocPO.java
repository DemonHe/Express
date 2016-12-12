package express.po;

import java.io.Serializable;

public class ArrivalDocPO extends DocumentPO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5143144623117160100L;
	private String arriveDate;
	private String transCenterID;//中转中心ID
	private String transferDocID;//中转单ID
	private String departure;//出发�?
	private GoodsArrivalStatus arrivalStatus;//枚举�?  货物到达状�?�（损坏、完整�?�丢失）
	private String orderID;

	
	
	//constructor
	public ArrivalDocPO(String orderID,String arriveDate,String transCenterID,
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
