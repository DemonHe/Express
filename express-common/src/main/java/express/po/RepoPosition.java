package express.po;

import java.io.Serializable;

public class RepoPosition implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4538085650019167731L;
	private String orderID;
	private Area block;
	private int row;
	private int shelf;
	private int position;
	private boolean isUsed;
	
	public RepoPosition(String id,Area b,int r,int s,int p,boolean isUsed){
		this.orderID=id;
		this.block=b;
		this.row=r;
		this.shelf=s;
		this.position=p;
		this.isUsed=isUsed;
	}
	
	public RepoPosition(){
		this.orderID=null;
		this.block=null;
		this.row=0;
		this.shelf=0;
		this.position=0;
		this.isUsed=false;
	}
	
	public String getOrderID(){
		return orderID;
	}
	
	public Area getblock(){
		return block;
	}
	
	public int getrow(){
		return row;
	}
	
	public int getshelf(){
		return shelf;
	}

	public int getposition(){
		return position;
	}
	
	public boolean getIsUsed(){
		return isUsed;
	}
	//
	public void setIsUsed(boolean b){
		this.isUsed=b;
	}
	
	public void setOrderID(String id){
		orderID = id;
	}
	
	public void setblock(Area b){
		block = b;
	}
	
	public void setrow(int n){
		row = n;
	}
	
	public void setshelf(int n){
		shelf = n;
	}

	public void setposition(int n){
		position = n;
	}
}