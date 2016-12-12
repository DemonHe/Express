package express.vo;

public class DriverInfoVO {

	private String driverNumber;
	private String orgID;
	private String name;
	private String date;
	private String ID;
	private String cellPhone;
	private boolean sex;// men are true,women are false
	private int deadline;

	public DriverInfoVO(String Number1,String Number2,
			            String n,String d,
			            String id,String cell,boolean is,int ddl){
		 this.driverNumber=Number1;
		 this.orgID=Number2;
		 this.name=n;
		 this.date=d;
		 this.ID=id;
		 this.cellPhone=cell;
		 this.sex=is;
		 this.deadline=ddl;	
	}
	
	public DriverInfoVO(){
		this.driverNumber=null;
		this.orgID=null;
		this.name=null;
		this.date=null;
		this.ID=null;
		this.cellPhone=null;
		this.sex=true;
		this.deadline=0;	
	}
	
	public String getdriverNumber(){
		return driverNumber; 
	}
	
	public void setdriverNumber(String driverNumber){
		this.driverNumber=driverNumber; 
	}
	
	public String getbusinesshallNumber(){
		return orgID; 
	}
	
	public void setbusinesshallNumber(String businesshallNumber){
		this.orgID=businesshallNumber; 
	}
	
	public String getname(){
		return name; 
	}
	
	public void setname(String name){
		this.name=name; 
	}
	
	public String getdate(){
		return date; 
	}
	
	public void setdate(String date){
		this.date=date; 
	}
	
	public String getID(){
		return ID; 
	}
	
	public void setID(String ID){
		this.ID=ID; 
	}
	
	public String getcellPhone(){
		return cellPhone; 
	}
	public void setcellPhone(String cellPhone){
		this.cellPhone=cellPhone; 
	}
	
	public boolean getsex(){
		return sex; 
	}
	
	public void setsex(boolean  sex){
		this.sex=sex; 
	}
	
	public int getdeadline(){
		return deadline; 
	}
	public void setdeadline(int deadline){
		this.deadline=deadline; 
	}
	
}
