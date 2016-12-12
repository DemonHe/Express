package express.businesslogicService.transcenterSaleBLService;

import express.vo.ArrivalDocTransCenterVO;

public interface TransCenterArrivalDocblService {
	public boolean addArrivalDoc(ArrivalDocTransCenterVO vo);
	public ArrivalDocTransCenterVO getArrivalDoc(String OrderID);
	public void endArrivalDoc();
}
