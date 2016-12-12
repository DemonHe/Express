package express.po;

import java.io.Serializable;
import java.util.ArrayList;

public class TransferDocPO extends DocumentPO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5098402620708166498L;
	private String date;
	private String transDocID;//
	private String flightNumber;
	private String begin;
	private String arrival;
	private String containerNumber;
	private String checkMan;
	private double money;
	private TransWay transWay;
	private ArrayList<String> orderlist;

	public TransferDocPO(String d, String transnumber, String f, String b, String ar, String c, String che, double m,
			TransWay transway, ArrayList<String> orderlist) {
		this.date = d;
		this.transDocID = transnumber;
		this.flightNumber = f;
		this.begin = b;
		this.arrival = ar;
		this.containerNumber = c;
		this.checkMan = che;
		this.money = m;
		this.transWay = transway;
		this.orderlist = orderlist;
	}

	public ArrayList<String> getOrderlist() {
		return orderlist;
	}

	public TransWay getTransWay() {
		return transWay;
	}

	public String getdate() {
		return date;
	}

	public void setdate(String date) {
		this.date = date;
	}

	public String getTransDocID() {
		return transDocID;
	}

	public void settranscenterNumber(String transcenterNumber) {
		this.transDocID = transcenterNumber;
	}

	public String getflightNumber() {
		return flightNumber;
	}

	public void setflightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getbegin() {
		return begin;
	}

	public void setbegin(String begin) {
		this.begin = begin;
	}

	public String getarrival() {
		return arrival;
	}

	public void setarrival(String arrival) {
		this.arrival = arrival;
	}

	public String getcheckMan() {
		return checkMan;
	}

	public void setcheckMan(String checkMan) {
		this.checkMan = checkMan;
	}

	public String getcontainerNumber() {
		return containerNumber;
	}

	public void setcontainerNumber(String containerNumber) {
		this.containerNumber = containerNumber;
	}


	public double getmoney() {
		return money;
	}

	public void setmoney(double money) {
		this.money = money;
	}
}