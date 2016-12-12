package express.businesslogicService.financialBLService;

import java.util.ArrayList;

import express.po.PaymentDocPO;
import express.vo.PaymentDocVO;

public interface PaymentBLService {

	public ArrayList<PaymentDocVO> createPaymentDoc();
	
	public boolean addPaymentDoc(PaymentDocVO vo);
	
	public PaymentDocVO getPaymentDoc(String id);
	
	public boolean changePaymentDoc(PaymentDocVO vo,String id);
	
	public boolean checkDateAvailable(String date);
	
	public boolean checkMoney(String m);
	
	public ArrayList<PaymentDocVO> getUnexamedPaymentDoc();
	
	public ArrayList<PaymentDocVO> getAllPaymentDoc();
	
	ArrayList<PaymentDocPO> getAllPaymentDocPO();
	
	public void endPaymentDoc();
}
