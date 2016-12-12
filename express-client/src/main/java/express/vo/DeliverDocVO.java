package express.vo;

public class DeliverDocVO extends DocumentVO{
	private String title="派件单";
	private String arriveDate;
	private String orderID;
	private String deliverManID;//快递员工号
	
	
	
	//consturctor
	public DeliverDocVO(String arriveDate,String orderID,String deliverManID){
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
	
	//add title
		public String getTitle(){
			return title;
		}

}
