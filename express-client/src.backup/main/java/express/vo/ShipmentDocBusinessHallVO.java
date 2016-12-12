package express.vo;

import java.util.ArrayList;


public class ShipmentDocBusinessHallVO extends DocumentVO{
	
	private String title="营业厅装车单";
	
	private String date;
	private String transID;//汽运编号 （营业厅编号+20150921日期+00000编码 、五位数字）
	private String businessHallNumber;  //本营业厅编号�?025城市编码+000鼓楼营业厅）
	private String arrivalplace; //到达地（本地中转中心或�?�其它营业厅�?
	private String vanID;
	private String checkMan;
	private String transMan;
	private ArrayList<String> orderID;
	private double money;
	private String shipmentID;
	private String startPlace;
	
	public ShipmentDocBusinessHallVO(String date,String transID,String bussinessHallnumber,
			String arrivalplace,String vanID,String checkman,String transman,
			ArrayList<String> order ,double money,String shipmentid,String startPlace){
		this.date=date;
		this.transID=transID;  //汽运编号
		this.businessHallNumber=bussinessHallnumber;
		this.arrivalplace=arrivalplace;
		this.vanID=vanID;
		this.checkMan=checkman;
		this.transMan=transman;
		this.orderID=order;
		this.money=money;
		this.shipmentID=shipmentid;
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
	
	public String getBusinessHallNum(){
		return businessHallNumber;
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

	//add title
		public String getTitle(){
			return title;
		}
	
}
