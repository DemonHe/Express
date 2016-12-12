package express.po;

import java.io.Serializable;

public class SalaryPO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4155278183171900950L;
	private UserRole position;//职位
	private Strategy strategy;//薪水策略：提成�?�月薪�?�计�?
	private double value;//提成百分比，或月薪工资，或每次多少钱
	
	public SalaryPO(UserRole position,Strategy strategy,double value){
		this.position = position;
		this.strategy = strategy;
		this.value = value;
	}
	
	public SalaryPO(){
		this.position = null;
		this.strategy = null;
		this.value = 0;
	}
		
	public UserRole getPosition(){
		return position;
	}
	
	public boolean setPosition(UserRole pos){
		this.position = pos;
		return false;
	}
	
	public Strategy getStrategy(){
		return strategy;
	}
	
	public void setStrategy(Strategy strategy){
		this.strategy = strategy;
	}
	
	public double getValue(){
		return value;
	}
	
	public void setValue(double v){
		this.value = v;
	}
}
