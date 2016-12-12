package express.businessLogic.examDocumentBL;

import java.util.ArrayList;

import express.businessLogic.documentBL.ArrivalDocBusinessHall;
import express.businessLogic.documentBL.ArrivalDocTransCenter;
import express.businessLogic.documentBL.DeliverDoc;
import express.businessLogic.documentBL.InDoc;
import express.businessLogic.documentBL.Order;
import express.businessLogic.documentBL.OutDoc;
import express.businessLogic.documentBL.PaymentDoc;
import express.businessLogic.documentBL.ReceiveDoc;
import express.businessLogic.documentBL.ShipmentDocBusinessHall;
import express.businessLogic.documentBL.ShipmentDocTransCenter;
import express.businessLogic.documentBL.TransferDoc;
import express.businessLogic.syslogBL.SysLog;
import express.businesslogicService.managerBLService.ExamDocumentBLService;
import express.businesslogicService.managerBLService.SysLogBLService;
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

public class ExamDocument implements ExamDocumentBLService {

	@Override
	public ArrayList<OrderVO> getUEOrderlist() {
		Order order=new Order();
		return order.getUnExamedOrder();
	}

	@Override
	public ArrayList<ShipmentDocBusinessHallVO> getUEBusinessHallShipmentDoclist() {
		ShipmentDocBusinessHall shipment=new ShipmentDocBusinessHall();
		return shipment.getUnExamedBusinessShipmentDoc();
	}

	@Override
	public ArrayList<ArrivalDocTransCenterVO> getUETransCenterArrivalDoclist() {
		ArrivalDocTransCenter arrivaldoc=new ArrivalDocTransCenter();
		return arrivaldoc.getUnExamedTransArrivalDoc();
	}

	@Override
	public ArrayList<InDocVO> getUEInDoclist() {
		InDoc indoc=new InDoc();
		return indoc.getUnexamedInDoc();
	}

	@Override
	public ArrayList<TransferDocVO> getUETransferDoclist() {
		TransferDoc transfer=new TransferDoc();
		return transfer.getUnExamedTransferDoc();
	}

	@Override
	public ArrayList<OutDocVO> getUEOutDoclist() { 
		OutDoc outDoc=new OutDoc();
		return outDoc.getUnexamedOutDoc();
	}

	@Override
	public ArrayList<ShipmentDocTransCenterVO> getUETransCenterShipmentDoclist() {
		ShipmentDocTransCenter shipmentdoc=new ShipmentDocTransCenter();
		return shipmentdoc.getUnExamedShipmentDoc();
	}

	@Override
	public ArrayList<ArrivalDocBusinessHallVO> getUEBusinessHallArrivalDoclist() {
		ArrivalDocBusinessHall arrivaldoc=new ArrivalDocBusinessHall();
		return arrivaldoc.getUnExamedBusinessHallArrivalDoc();
	}

	
	
	
	@Override
	public ArrayList<DeliverDocVO> getUEDeliverDoclist() {
		DeliverDoc deliverDoc =new DeliverDoc();
		return deliverDoc.getUnexamedDeliverDoc();
	}

	@Override
	public ArrayList<ReceiveDocVO> getUEReceiveDoclist() {
		ReceiveDoc receivedoc=new ReceiveDoc();
		return receivedoc.getUnExamedReceiveDoc();
		
	}

	@Override
	public ArrayList<PaymentDocVO> getUEPaymentDoclist() {  
		PaymentDoc paymentdoc=new PaymentDoc();
		return paymentdoc.getUnexamedPaymentDoc();

	}

	@Override
	public boolean changeOrder(OrderVO vo) {
		Order order=new Order();
		return order.changeOrder(vo);
	}

	@Override
	public boolean changeBusinessHallShipmentDoc(ShipmentDocBusinessHallVO vo) {
		ShipmentDocBusinessHall shipment=new ShipmentDocBusinessHall();
		return shipment.changeShipmentDoc(vo);
	}

	@Override
	public boolean changeTransCenterArrivalDoc(ArrivalDocTransCenterVO vo) {
		ArrivalDocTransCenter arrivaldoc=new ArrivalDocTransCenter();
		return arrivaldoc.changeTransCenterArrivalDoc(vo);	
	}

	@Override
	public boolean changeInDoc(InDocVO vo) { //没给�?
		InDoc inDoc=new InDoc();
		return inDoc.changeInDoc(vo);

	}

	@Override
	public boolean changeTransferDoc(TransferDocVO vo) {
		TransferDoc transfer=new TransferDoc();
		return transfer.changeTransferDoc(vo);
	}

	@Override
	public boolean changeOutDoc(OutDocVO vo) { 
		OutDoc outDoc=new OutDoc();
		return outDoc.changeOutDoc(vo);
	}

	@Override
	public boolean changeTransCenterShipmentDoc(ShipmentDocTransCenterVO vo) {
		ShipmentDocTransCenter shipmentdoc=new ShipmentDocTransCenter();
		return shipmentdoc.changeTransCenterShipmentDoc(vo);	
	}

	@Override
	public boolean changeBusinessHallArrivalDoc(ArrivalDocBusinessHallVO vo) {
		 ArrivalDocBusinessHall arrivaldoc=new ArrivalDocBusinessHall();
		 return arrivaldoc.changeBusinessHallArrivalDoc(vo);	
	}

	@Override
	public boolean changeDeliverDoc(DeliverDocVO vo) {
		DeliverDoc deliverDoc=new DeliverDoc();
		return deliverDoc.changeDeliverDoc(vo);
	}

	@Override
	public boolean changeReceiveDoc(ReceiveDocVO vo) {
		ReceiveDoc receivedoc=new ReceiveDoc();
		return receivedoc.changeReceiveDoc(vo);	
	}

	@Override
	public boolean changePaymentDoc(PaymentDocVO vo) {  //
		PaymentDoc paymentDoc=new PaymentDoc();
		return paymentDoc.changePaymentDoc(vo, vo.getPaymentID());
	}

	@Override
	public void endJudge() {
		SysLogBLService syslog=new SysLog();
		syslog.addSysLog("审批单据");
	}

}
