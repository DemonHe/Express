package express.businesslogicService.managerBLService;

import java.util.ArrayList;

import express.vo.ArrivalDocBusinessHallVO;
import express.vo.ArrivalDocTransCenterVO;
import express.vo.DeliverDocVO;
import express.vo.InDocVO;
import express.vo.OrderVO;
import express.vo.OutDocVO;
import express.vo.PaymentDocVO;
import express.vo.ReceiveDocVO;
import express.vo.ShipmentDocBusinessHallVO;
import express.vo.ShipmentDocTransCenterVO;
import express.vo.TransferDocVO;

public interface ExamDocumentBLService {
	//得到�?有单�?  UE==UnExamed
	public ArrayList<OrderVO> getUEOrderlist();
	public ArrayList<ShipmentDocBusinessHallVO> getUEBusinessHallShipmentDoclist();
	public ArrayList<ArrivalDocTransCenterVO> getUETransCenterArrivalDoclist();
	public ArrayList<InDocVO> getUEInDoclist();
	public ArrayList<TransferDocVO> getUETransferDoclist();
	public ArrayList<OutDocVO> getUEOutDoclist();
	public ArrayList<ShipmentDocTransCenterVO> getUETransCenterShipmentDoclist();
	public ArrayList<ArrivalDocBusinessHallVO> getUEBusinessHallArrivalDoclist();
	public ArrayList<DeliverDocVO> getUEDeliverDoclist();
	public ArrayList<ReceiveDocVO> getUEReceiveDoclist();
	public ArrayList<PaymentDocVO> getUEPaymentDoclist();
	
	
	//改变�? 写回逻辑�?  每次传一个对�?
	public boolean changeOrder(OrderVO vo);
	public boolean changeBusinessHallShipmentDoc(ShipmentDocBusinessHallVO vo);
	public boolean changeTransCenterArrivalDoc(ArrivalDocTransCenterVO vo);
	public boolean changeInDoc(InDocVO vo);
	public boolean changeTransferDoc(TransferDocVO vo);
	public boolean changeOutDoc(OutDocVO vo);
	public boolean changeTransCenterShipmentDoc(ShipmentDocTransCenterVO vo);
	public boolean changeBusinessHallArrivalDoc(ArrivalDocBusinessHallVO vo);
	public boolean changeDeliverDoc(DeliverDocVO vo);
	public boolean changeReceiveDoc(ReceiveDocVO vo);
	public boolean changePaymentDoc(PaymentDocVO vo);
	
	
	public void endJudge();
	
	
	
}
