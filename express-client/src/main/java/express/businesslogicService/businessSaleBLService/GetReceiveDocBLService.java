package express.businesslogicService.businessSaleBLService;

import java.util.ArrayList;

import express.po.ReceiveDocPO;
import express.vo.ReceiveDocVO;
import express.vo.SumReceiveVO;

public interface GetReceiveDocBLService {

	public ArrayList<ReceiveDocVO> getAllReceiveDoc();
	
	public ArrayList<ReceiveDocPO> getAllReceiveDocPO();
	
	public SumReceiveVO getReceiveDocList(String date,String orgID);
	
	public boolean getSum(double sum,String bankAccountID);
	
	public void endManage();
}