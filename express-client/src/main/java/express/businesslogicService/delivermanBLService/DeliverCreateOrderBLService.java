package express.businesslogicService.delivermanBLService;

import java.util.ArrayList;

import express.po.OrderPO;
import express.vo.ArrivalTimeVO;
import express.vo.OrderVO;

public interface DeliverCreateOrderBLService {
		public String addOrder(OrderVO vo);
		public boolean isCellPhoneAvailable(String cellphonenumber);
		public double getTotal(OrderVO vo);
		public OrderVO getOrder(String id);
		public ArrayList<OrderPO> getAllOrder();
		public ArrivalTimeVO getPredictArrivalTime(String startCity,String endCity);
		public void endOrder();
}
