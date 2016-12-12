package express.dataService_Driver;

import java.rmi.RemoteException;

import express.dataService.documentDataService.PaymentDocDataService;
import express.po.PaymentItem;

public class PaymentDataService_Driver {

	public void drive(PaymentDocDataService paymentDataService){
		try {
			if(paymentDataService.createPaymentDoc(null))
				System.out.print("Create payment");
			paymentDataService.getPaymentDoc("1");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
