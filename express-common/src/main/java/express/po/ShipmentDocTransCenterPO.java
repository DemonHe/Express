package express.po;


import java.util.ArrayList;


public class ShipmentDocTransCenterPO extends DocumentPO{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4511353721522706530L;
	private String date;
	private String transID;//汽运编号 中转中心编号+日期+0000000七位数字
	private String arrivalplace; //到达地（本地中转中心或者其它营业厅）
	private String vanID;
	private String checkMan;
	private String transMan;
	private ArrayList<String> orderID;
	private double money;
	private String shipmentID;
	private String startPlace;
	
	public ShipmentDocTransCenterPO(String date,String transID,
			String arrivalplace,String vanID,String checkman,String transman,
			ArrayList<String> order ,double money,String shipment,String startPlace){
		this.date=date;
		this.transID=transID;
		this.arrivalplace=arrivalplace;
		this.vanID=vanID;
		this.checkMan=checkman;
		this.transMan=transman;
		this.orderID=order;
		this.money=money;
		this.shipmentID=shipment;
		this.startPlace=startPlace;
	}
	
	public String getStartPlace(){
		return startPlace;
	}
	
	
	public String getDate(){
		return date;
	}
	public String getTransId(){
		return transID;
	}
	
	
	public String getArrivalPlace(){
		return arrivalplace;
	}
	
	public String getVanID(){
		return vanID;
	}
	
	public String getCheckMan(){
		return checkMan;
	}
	
	public String getTransMan(){
		return transMan;
	}
	
	public ArrayList<String> getAllOrder(){
		return orderID;
	}
	
	public double getMoney(){
		return money;
	}
	
	
	public String getShipmentID(){
		return shipmentID;
	}
	//set methods
	public void setMoney(double money){
		this.money=money;
	}

}