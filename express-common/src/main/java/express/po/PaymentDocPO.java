package express.po;

import java.io.Serializable;

public class PaymentDocPO extends DocumentPO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5672515769370646252L;
	private PaymentItem payment;
	private String paymentID;

	public PaymentDocPO(PaymentItem payment, String paymentID) {
		this.payment = payment;
		this.paymentID = paymentID;
	}

	public PaymentDocPO() {
		payment = null;
		paymentID = "0";
	}

	public PaymentItem getPaymentList() {
		return payment;
	}

	public String getPaymentID() {
		return paymentID;
	}

	public void setPaymentList(PaymentItem p) {
		payment = p;
	}

	public void setID(String id) {
		paymentID = id;
	}

}
