package express.vo;

import java.util.ArrayList;

public class ShipmentDocVO {
	private String date;
	private String transcenterNumber;
	private String arrival;
	private String vin;
	private String checkMan;
	private String transMan;
	private String shipmentID;
	private ArrayList<String> orderID;
	private double money;
	
	
	public ShipmentDocVO(String d,String transnumber,String ar,String v,String che,
			String transman,String shipmentID,ArrayList<String> orderID,double m){
		this.date=d;
		this.transcenterNumber=transnumber;
		this.arrival=ar;
		this.vin=v;
		this.checkMan=che;
		this.transMan=transman;
		this.shipmentID=shipmentID;
		this.money=m;
	}
	
	public String getdate(){
		return date;
	}
	public void setdate(String date){
		this.date=date;
	}
	
	public String gettranscenterNumber(){
		return transcenterNumber;
	}
	
	public void settranscenterNumber(String transcenterNumber){
		this.transcenterNumber=transcenterNumber;
	}
	
	public  String getarrival(){
		return arrival;
	}
	
	public  void setarrival(String arrival){
		this.arrival=arrival;
	}
	
	public  String getvin(){
		return vin;
	}
	
	public  void setvin(String vin){
		this.vin=vin;
	}
	
	public String getcheckMan(){
		return checkMan;
	}
	
	public void setcheckMan(String checkMan){
		this.checkMan=checkMan;
	}
	
	public String gettransMan(){
		return transMan;
	}
	
	public void settransMan(String transMan){
		this.transMan=transMan;
	}
	
	public String getshipmentID(){
		return shipmentID;
	}
	public void setshipmentID(String shipmentid){
		this.shipmentID=shipmentid;
	}
	
	public double getmoney(){
		return money;
	}
	
	public void  setmoney(double money){
		this.money=money;
	}
	
	public ArrayList<String> getOrderID(){
		return  orderID;
	}
	
	public void setOrderID(ArrayList<String> order){
		orderID=order;
	}
	
	
	
	
	
}
