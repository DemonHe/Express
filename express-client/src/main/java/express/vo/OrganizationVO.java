package express.vo;

import express.po.OrgProperty;

public class OrganizationVO {

	private String city;
	private String name;
	private String address;
	private OrgProperty property;
	private String orgID;
	
	public OrganizationVO(String city,String name,String address,
			OrgProperty property,String orgID){
		this.city=city;
		this.name = name;
		this.address = address;
		this.property=property;
		this.orgID=orgID;
	}
	
	public OrganizationVO(){
		this.city=null;
		this.name = null;
		this.address = null;
		this.property= null;
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

	//将机构类型转化为中文字符串
	public String transtype(OrgProperty orgtemp){
		String orgpro = "";
		if (orgtemp.equals(OrgProperty.TRANSCENTER))
			orgpro = "中转中心";
		if (orgtemp.equals(OrgProperty.SALE))
			orgpro = "营业厅";
		if (orgtemp.equals(OrgProperty.OTHER))
			orgpro = "总部";		
		return orgpro;
	}
	
	//将中文字符串的机构类型转化为OrgProperty
	public OrgProperty typetran(String orgtype){
		OrgProperty orgpro = null;
		if (orgtype.equals("中转中心"))
			orgpro = OrgProperty.TRANSCENTER;
		if (orgtype.equals("营业厅"))
			orgpro = OrgProperty.SALE;
		if (orgtype.equals("总部"))
			orgpro = OrgProperty.OTHER;
		return orgpro;
	}
}
