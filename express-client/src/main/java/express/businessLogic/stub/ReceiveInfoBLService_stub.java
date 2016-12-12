package express.businessLogic.stub;

import express.vo.ReceiveInfoVO;

public class ReceiveInfoBLService_stub {
	public boolean isOrderIDAvailable(String id){
		if(id.equals("0000000001")){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean addReceiveInfo(ReceiveInfoVO vo){
		System.out.println("ReceiveInfo added");
		return true;

	}
	public void endReceiveInfo(){
		System.out.println("ReceiveInfo ended");
	}
	
}
