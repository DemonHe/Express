package express.vo;

import java.io.Serializable;

public class DocumentVO {
	/**
	 * 
	 */
	private boolean state=false;  //默认单据为未审批
	public boolean getState(){
		return state;
	}
	
	public boolean setState(boolean state){
		this.state = state;
		return false;
	}
}
