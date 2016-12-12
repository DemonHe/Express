package express.businesslogicService.delivermanBLService;

import express.vo.ReceiveInfoVO;

public interface ReceiveInfoBLService {
		public boolean isOrderIDAvailable(String id);
		public boolean addReceiveInfo(ReceiveInfoVO vo);
		public void endReceiveInfo();
}
