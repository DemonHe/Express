package express.businesslogicService.transcenterRepoBLService;

import java.util.ArrayList;

import express.po.InDocPO;
import express.vo.InDocVO;

public interface InDocblService {

	public boolean addInDoc(InDocVO vo);

	public InDocVO getInDoc(String orderID);
	
	public boolean changeInDoc(InDocVO vo);

	public boolean isOrderIDavailable(String id);
	
	public ArrayList<InDocPO> getAllInDocPO(String orgID);

	public void endInDoc();
}
