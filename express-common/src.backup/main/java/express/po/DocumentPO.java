package express.po;

import java.io.Serializable;

public class DocumentPO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1487716908203274691L;
	private boolean state=false;  //默认单据为未审批
	public boolean getState(){
		return state;
	}
	
	public boolean setState(boolean state){
		this.state = state;
		return false;
	}
}
