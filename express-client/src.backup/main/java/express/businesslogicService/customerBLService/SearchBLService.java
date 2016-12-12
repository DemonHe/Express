package express.businesslogicService.customerBLService;

import express.vo.GoodTransStatusVO;

public interface SearchBLService {
		public GoodTransStatusVO getOrderIDStatus(String orderID);
}
