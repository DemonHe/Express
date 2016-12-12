package express.businesslogicService.transcenterRepoBLService;

import java.util.ArrayList;

import express.po.OutDocPO;
import express.vo.OutDocVO;

public interface OutDocblService {
	public boolean addOutDoc(OutDocVO vo);

	public OutDocVO getOutDoc(String orderID);

	public boolean isOrderIDavailable(String id);

	public void endOutDoc();

	public ArrayList<OutDocPO> getAllOutDocPO(String orgID);
}
