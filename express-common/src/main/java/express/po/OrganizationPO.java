package express.po;
import java.io.Serializable;


public class OrganizationPO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7958276314099597994L;
	private String city;
	private String name;
	private String address;
	private OrgProperty property;
	private String orgID;
	
	public OrganizationPO(String city,String name,String address,
			OrgProperty property,String orgID){
		this.city=city;
		this.name = name;
		this.address = address;
		this.property=property;
		this.orgID=orgID;
	}
	
	public OrganizationPO(){
		this.city=null;
		this.name = null;
		this.address = null;
		this.property=null;
		this.orgID= null;
	}
	
	public String getCity(){
		return city;
	}
	
	public String getName(){
		return name;
	}
	
	public String getAddress(){
		return address;
	}
	
	public OrgProperty getOrgProperty(){
		return property;
	}
	
	public String getOrgID(){
		return orgID;
	}
	
	public void setCity(String c){
		city=c;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setOrgProperty(OrgProperty p){
		property=p;
	}
	
	public void setAddress(String address){
		this.address = address;
	}
	
	public void setOrgID(String id){
		orgID=id;
	}
}
