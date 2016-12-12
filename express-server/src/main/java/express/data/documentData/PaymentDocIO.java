package express.data.documentData;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import express.data.IOHelper.IOHelper;
import express.dataService.documentDataService.PaymentDocDataService;
import express.po.PaymentDocPO;

public class PaymentDocIO extends UnicastRemoteObject implements PaymentDocDataService{

	String filename="SerializableData/PaymentDoc.ser";
	ArrayList<PaymentDocPO> paymentList;
	
	public PaymentDocIO() throws RemoteException{
		super();
		paymentList=new ArrayList<PaymentDocPO>();
		IOHelper io=new IOHelper();
		try {
			paymentList=(ArrayList<PaymentDocPO>)io.readFromDisk(filename);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean createPaymentDoc(PaymentDocPO payment) throws RemoteException{
		paymentList.add(payment);
		return true;
	}

	@Override
	public PaymentDocPO getPaymentDoc(String id) throws RemoteException{
		for(PaymentDocPO payment:paymentList){
			if(payment.getPaymentID().equals(id))
				return payment;
		}
		return null;
	}

	@Override
	public boolean changePaymentDoc(PaymentDocPO payment,String id) throws RemoteException {
		if(paymentList.size()>0){
			int index=0;
			for(PaymentDocPO po:paymentList){
				if(po.getPaymentID().equals(id)){
					paymentList.set(index, payment);
					return true;
				}
				index++;
			}
		}
		return false;
	}

	@Override
	public ArrayList<PaymentDocPO> getAllPaymentDoc() throws RemoteException {
		if(paymentList.size()>0)
			return paymentList;
		else
			return null;
	}

	@Override
	public boolean writeAllPaymentDoc() throws RemoteException {
		IOHelper io=new IOHelper();
		try {
			io.writeToDisk(filename, paymentList);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}


}
