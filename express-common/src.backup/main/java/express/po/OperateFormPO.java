package express.po;

import java.io.Serializable;
import java.util.ArrayList;

public class OperateFormPO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4528353185474014511L;
	private String startDate;
	private String endDate;
	private ArrayList<ReceiveDocPO> receiveDoc;
	private ArrayList<PaymentItem> paymentDoc;

	public OperateFormPO(String startDate, String endDate,
			ArrayList<ReceiveDocPO> receiveDoc,
			ArrayList<PaymentItem> paymentDoc) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.receiveDoc = receiveDoc;
		this.paymentDoc = paymentDoc;
	}

	public OperateFormPO() {
		this.startDate = null;
		this.endDate = null;
		this.receiveDoc = null;
		this.paymentDoc = null;
	}

	public String getStartDate() {
		return startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public ArrayList<ReceiveDocPO> getReceiveDoc() {
		return receiveDoc;
	}

	public ArrayList<PaymentItem> getPaymentDoc() {
		return paymentDoc;
	}

	public void setStartDate(String d) {
		startDate = d;
	}

	public void setEndDate(String d) {
		endDate = d;
	}

	public void setReceiveDoc(ArrayList<ReceiveDocPO> r) {
		receiveDoc = r;
	}

	public void setPaymentDoc(ArrayList<PaymentItem> p) {
		paymentDoc = p;
	}
}
