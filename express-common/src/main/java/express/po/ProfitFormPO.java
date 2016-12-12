package express.po;

import java.io.Serializable;

public class ProfitFormPO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8572762249925216779L;
	private String date;
	private double income;
	private double outcome;
	private double profit;
	
	public ProfitFormPO(String date,double income,double outcome,double profit){
		this.date=date;
		this.income=income;
		this.outcome=outcome;
		this.profit=profit;
	}
	public ProfitFormPO(){
		this.date=null;
		this.income=0;
		this.outcome=0;
		this.profit=0;
	}
	public String getTitle(){
		return date;
	}
	public double getIncome(){
		return income;
	}
	public double getOutCome(){
		return outcome;
	}
	public double getProfit(){
		return profit;
	}
	public void setTitle(String d){
		date=d;
	}
	public void setIncome(double n){
		income=n;
	}
	public void setOutcome(double n){
		outcome=n;
	}
	public void setProfit(double n){
		profit=n;
	}
}
