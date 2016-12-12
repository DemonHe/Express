package express.vo;

import express.po.PaymentItem;

public class PaymentDocVO extends DocumentVO{

	private PaymentItem payment;
	private String paymentID;
	
	public PaymentDocVO(PaymentItem payment,String paymentID){
		this.payment=payment;
		this.paymentID=paymentID;
	}
	
	public PaymentDocVO(){
		payment=null;
		paymentID="0";
	}
	
	public PaymentItem getPaymentList(){
		return payment;
	}
	
	public String getPaymentID(){
		return paymentID;
	}
	
	public void setPaymentList(PaymentItem p){
		payment=p;
	}

	public void setID(String id){
		paymentID=id;
	}

}
