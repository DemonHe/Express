package express.vo;

import java.util.ArrayList;

public class ReceiveDocVO extends DocumentVO{
	private String title="收款�?";
	private String receiveDate;
	private double receivePrice;
	private String deliverManID;
	private ArrayList<String> allOrderIDs;
	private String orgID;
	
	public ReceiveDocVO(String receiveDate,double receivePrice,String deliverManID,
			ArrayList<String> allOrderIDs,String orgID){
		this.receiveDate=receiveDate;
		this.receivePrice=receivePrice;
		this.deliverManID=deliverManID;
		this.allOrderIDs=allOrderIDs;
		this.orgID=orgID;
	}

	public String getReceiveDate(){
		return receiveDate;
	}
	
	public double getReceivePrice(){
		return receivePrice;
	}
	
	public String getDeliverManID(){
		return deliverManID;
	}
	
	public ArrayList<String> getAllOrderIDs(){
		return allOrderIDs;
	}
	
	public String getOrgID(){
		return orgID;
	}
	
	//add title
		public String getTitle(){
			return title;
		}
}
