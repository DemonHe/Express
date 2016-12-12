package express.po;

import java.io.Serializable;

public class PaymentItem implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5680285073017944433L;
	private String name;
	private String date;
	private String account;
	private String entry;//条目
	private String comment;//备注
	private double sum;
	
	public PaymentItem(String name,String date,String account,
			String entry,String comment,double sum){
		this.name=name;
		this.date=date;
		this.account=account;
		this.entry=entry;
		this.comment=comment;
		this.sum=sum;
	}
	
	public PaymentItem(){
		this.name=null;
		this.date=null;
		this.account=null;
		this.entry=null;
		this.comment=null;
		this.sum=0;
	}
	
	public String getName(){
		return name;
	}
	public String getDate(){
		return date;
	}
	public String getAccount(){
		return account;
	}
	public double getSum(){
		return sum;
	}
	public String getEntry(){
		return entry;
	}
	public String getComment(){
		return comment;
	}
	public void setName(String name){
		this.name=name;
		
	}
	public void addDate(String date){
		this.date=date;
		
	}
	public void setAccount(String account){
		this.account=account;
		
	}
	public void setEntry(String entry){
		this.entry=entry;
		
	}
	public void setComment(String comment){
		this.comment=comment;
		
	}
	public double setSum(double sum){
		this.sum=sum;
		return this.sum;
	}
}
