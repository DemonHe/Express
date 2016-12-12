//package express.data.stub;
//
//import java.rmi.RemoteException;
//import java.util.ArrayList;
//
//import express.dataService.documentDataService.PaymentDocDataService;
//import express.po.PaymentDocPO;
//import express.po.PaymentItem;
//
//public class PaymentDataService_stub implements PaymentDocDataService{
//
//	PaymentDocPO payment;
//	
//	public PaymentDataService_stub(PaymentDocPO payment){
//		this.payment=payment;
//	}
//	
//	public boolean createPaymentDoc(PaymentDocPO payment){
//		return true;
//	}
//	
//	public PaymentDocPO getPaymentDoc(String id){
//		System.out.println("Get payment");
//		return payment;
//	}
//
//	@Override
//	public boolean changePaymentDoc(PaymentDocPO payment,int index) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public ArrayList<PaymentDocPO> getAllPaymentDoc() throws RemoteException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public boolean writeAllPaymentDoc() throws RemoteException {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//}
