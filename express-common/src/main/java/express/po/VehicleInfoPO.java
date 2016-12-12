package express.po;

import java.io.Serializable;

public class VehicleInfoPO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9078943558557300245L;
	private String mark;//车辆代号
	private String license;//车牌号
	private String orgID;//机构名称
	private int useYear;//服役时间
	private boolean isUsing;//是否在使用
	
	public VehicleInfoPO(String mark,String license,String orgID,int useYear,boolean isUsing){
		this.mark=mark;
		this.license=license;
		this.orgID=orgID;
		this.useYear=useYear;
		this.isUsing=isUsing;
	}
	public VehicleInfoPO(){
		this.mark=null;
		this.license=null;
		this.orgID=null;
		this.useYear=0;
		this.isUsing=false;
	}
	public String getMark(){
		return mark;
	}
	public String getLicense(){
		return license;
	}
	public String getOrgID(){
		return orgID;
	}
	public int getUseYear(){
		return useYear;
	}
	public boolean checkIsUsing(){
		return isUsing;
	}
	public void setIsUsing(boolean b){
		isUsing=b;
	}
	public void setUseYear(int n){
		useYear=n;
	}
	public void setMark(String m){
		mark=m;
	}
	public void setLicense(String l){
		license=l;
	}
	public void setOrgID(String c){
		orgID=c;
	}
}
