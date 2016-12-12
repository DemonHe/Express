package express.vo;

import java.util.ArrayList;

public class SumReceiveVO {

	private ArrayList<ReceiveDocVO> receiveDoc;
	private double sum;
	
	public SumReceiveVO(ArrayList<ReceiveDocVO> receiveDoc,double sum){
		this.receiveDoc=receiveDoc;
		this.sum=sum;
	}
	
	public ArrayList<ReceiveDocVO> getReceiveDoc(){
		return receiveDoc;
	}
	
	public double getSum(){
		return sum;
	}
}
