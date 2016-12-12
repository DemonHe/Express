package express.po;

import java.io.Serializable;
import java.util.ArrayList;

public class ReceiveDocPO  extends DocumentPO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3003580510116876072L;
	private String receiveDate;
	private double receivePrice;
	private String deliverManID;
	private ArrayList<String> allOrderIDs;
	private String orgID;

	public ReceiveDocPO(String receiveDate, double receivePrice,
			String deliverManID, ArrayList<String> allOrderIDs, String orgID) {
		this.receiveDate = receiveDate;
		this.receivePrice = receivePrice;
		this.deliverManID = deliverManID;
		this.allOrderIDs = allOrderIDs;
		this.orgID = orgID;
	}

	public String getReceiveDate() {
		return receiveDate;
	}

	public double getReceivePrice() {
		return receivePrice;
	}

	public String getDeliverManID() {
		return deliverManID;
	}

	public ArrayList<String> getAllOrderIDs() {
		return allOrderIDs;
	}

	public String getOrgID() {
		return orgID;
	}

}
