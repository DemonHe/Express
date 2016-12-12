package express.po;

import java.io.Serializable;

public class OutDocPO extends DocumentPO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2093918775776937385L;
	private String orderid;
	private String date;
	private String arrival;
	private String transKind;
	private String transNumber;
	private String orgID;

	public OutDocPO(String de, String d, String a, String tK, String tN,
			String orgID) {
		this.orderid = de;
		this.date = d;
		this.arrival = a;
		this.transKind = tK;
		this.transNumber = tN;
		this.orgID = orgID;
	}

	public String getOrderID() {
		return orderid;
	}

	public void setdeliveryNumber(String deliveryNumber) {
		this.orderid = deliveryNumber;
	}

	public String getOrgID() {
		return orgID;
	}

	public void setOrgID(String id) {
		orgID = id;
	}

	public String getdate() {
		return date;
	}

	public void setdate(String date) {
		this.date = date;
	}

	public String getarrival() {
		return arrival;
	}

	public void setarrival(String arrival) {
		this.arrival = arrival;
	}

	public String gettransKind() {
		return transKind;
	}

	public void settransKind(String transKind) {
		this.transKind = transKind;
	}

	public String gettransNumber() {
		return transNumber;
	}

	public void settransNumber(String transNumber) {
		this.transNumber = transNumber;
	}
}
