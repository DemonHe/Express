package express.rmi;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import express.dataService.bankAccountDataService.BankAccountDataService;
import express.dataService.documentDataService.BusinessSaleArrivalDocumentDataService;
import express.dataService.documentDataService.BusinessSaleDeliverDocumentDataService;
import express.dataService.documentDataService.BusinessSaleReceiveDocumentDataService;
import express.dataService.documentDataService.BusinessSaleShipmentDocDataService;
import express.dataService.documentDataService.BusinessSaleShipmentDocumentDataService;
import express.dataService.documentDataService.DeliverCreateOrderDataService;
import express.dataService.documentDataService.GoodsStatusDataService;
import express.dataService.documentDataService.InDocDataService;
import express.dataService.documentDataService.OutDocDataService;
import express.dataService.documentDataService.PaymentDocDataService;
import express.dataService.documentDataService.PredictTimeDataService;
import express.dataService.documentDataService.ReceiveInfoDataService;
import express.dataService.documentDataService.TransCenterArrivalDocumentDataService;
import express.dataService.documentDataService.TransCenterShipmentDocDataService;
import express.dataService.documentDataService.TransCenterTransferDocDataService;
import express.dataService.innerAccountDataService.InnerAccountDataService;
import express.dataService.ipandname.IPDataService;
import express.dataService.logDataService.LogDataService;
import express.dataService.organizationDataService.OrganizationDataService;
import express.dataService.repoDataService.RepoDataService;
import express.dataService.statisticsDataService.OperateFormDataService;
import express.dataService.statisticsDataService.ProfitFormDataService;
import express.dataService.strategyDataService.DistanceDataService;
import express.dataService.strategyDataService.PriceDataService;
import express.dataService.strategyDataService.SalaryDataService;
import express.dataService.userDataService.AdminUserDataService;
import express.dataService.userDataService.SignUserDataService;
import express.dataService.userDataService.UserDataService;
import express.dataService.vehicleAndDriverDataService.DriverDataService;
import express.dataService.vehicleAndDriverDataService.VehicleDataService;
import express.po.IPPO;

public class RMIClient {

	private static String file = "config-client/config.txt";
	private static String IP; // read from config file
	private static boolean inited = false;
	private static BankAccountDataService bankAccount;
	private static PaymentDocDataService paymentDoc;
	private static LogDataService log;
	private static DriverDataService driver;
	private static VehicleDataService vehicle;
	private static OrganizationDataService org;
	private static InnerAccountDataService innerAccount;
	private static ProfitFormDataService profitForm;
	private static OperateFormDataService operateForm;
	private static SalaryDataService salary;
	private static DistanceDataService distance;
	private static PriceDataService price;
	private static RepoDataService repoInfo;
	private static AdminUserDataService userAdmin;
	private static UserDataService userInfo;
	private static SignUserDataService userSign;
	private static BusinessSaleDeliverDocumentDataService deliverdoc;
	private static BusinessSaleArrivalDocumentDataService arrivaldoc;
	private static BusinessSaleReceiveDocumentDataService receivedoc;
	private static DeliverCreateOrderDataService order;
	private static GoodsStatusDataService search;
	private static TransCenterArrivalDocumentDataService transcenterarrivaldoc;
	private static TransCenterShipmentDocDataService shipmentdoc;
	private static PredictTimeDataService predicttime;
	private static ReceiveInfoDataService receiveinfo;
	private static TransCenterTransferDocDataService transferdoc;
	private static GoodsStatusDataService goodstatus;
	private static BusinessSaleShipmentDocDataService businessShipmentdoc;
	private static BusinessSaleArrivalDocumentDataService businessArrivalDoc;
	private static OutDocDataService outDoc;
	private static InDocDataService inDoc;
	private static IPDataService ipdata;
	
	public synchronized static void init() throws ClientException {
		
		
		if (inited) {
			return;
		}

		try {
			InputStreamReader read = new InputStreamReader(new FileInputStream(
					file));
			BufferedReader br = new BufferedReader(read);
			IP = br.readLine();
			br.close();
			initObjects();
			
			
			IPPO ip=new IPPO(AboutSystem.getMyIp(),AboutSystem.getMyName());
			System.out.println(AboutSystem.getMyIp());
			ipdata.addIP(ip);
			
			inited = true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ClientException(e);
		}
	}

	private static void initObjects() throws MalformedURLException,
			RemoteException, NotBoundException {
		String urlPrefix = "rmi://" + IP + ":1099/";
		
		bankAccount = (BankAccountDataService) Naming.lookup(urlPrefix
				+ "BankAccount-data");
		paymentDoc = (PaymentDocDataService) Naming.lookup(urlPrefix
				+ "PaymentDoc-data");
		log = (LogDataService) Naming.lookup(urlPrefix + "Log-data");
		driver = (DriverDataService) Naming.lookup(urlPrefix + "Driver-data");
		vehicle = (VehicleDataService) Naming
				.lookup(urlPrefix + "Vehicle-data");
		org = (OrganizationDataService) Naming.lookup(urlPrefix
				+ "Organization-data");
		innerAccount = (InnerAccountDataService) Naming.lookup(urlPrefix
				+ "InnerAccount-data");
		profitForm = (ProfitFormDataService) Naming.lookup(urlPrefix
				+ "ProfitForm-data");
		operateForm = (OperateFormDataService) Naming.lookup(urlPrefix
				+ "OperateForm-data");
		salary = (SalaryDataService) Naming.lookup(urlPrefix
				+ "SalaryStartegy-data");
		distance = (DistanceDataService) Naming.lookup(urlPrefix
				+ "DistanceStrategy-data");
		price = (PriceDataService) Naming.lookup(urlPrefix
				+ "PriceStrategy-data");
		repoInfo = (RepoDataService) Naming.lookup(urlPrefix + "RepoInfo-data");
		userAdmin = (AdminUserDataService) Naming.lookup(urlPrefix
				+ "UserAdmin-data");
		userInfo = (UserDataService) Naming.lookup(urlPrefix + "UserInfo-data");
		userSign = (SignUserDataService) Naming.lookup(urlPrefix
				+ "UserSign-data");
		deliverdoc = (BusinessSaleDeliverDocumentDataService) Naming
				.lookup(urlPrefix + "DeliverDoc-data");
		receivedoc = (BusinessSaleReceiveDocumentDataService) Naming
				.lookup((urlPrefix) + "ReceiveDoc-data");
		order = (DeliverCreateOrderDataService) Naming.lookup(urlPrefix
				+ "Order-data");
		receiveinfo = (ReceiveInfoDataService) Naming.lookup(urlPrefix
				+ "ReceiveInfo-data");
		transferdoc = (TransCenterTransferDocDataService) Naming
				.lookup(urlPrefix + "TransferDoc-data");
		businessArrivalDoc = (BusinessSaleArrivalDocumentDataService) Naming
				.lookup(urlPrefix + "BusinessHallArrivalDoc-data");
		outDoc = (OutDocDataService)  Naming.lookup(urlPrefix + "OutDoc-data");
		
		inDoc=(InDocDataService) Naming.lookup(urlPrefix+"InDoc-data");
		
		goodstatus=(GoodsStatusDataService)Naming.lookup(urlPrefix+"Status-data");
		
		predicttime=(PredictTimeDataService)Naming.lookup(urlPrefix+"Predicttime-data");
		
		transcenterarrivaldoc=(TransCenterArrivalDocumentDataService)Naming.lookup(urlPrefix+"TransArrivalDoc-data");
		
		businessShipmentdoc=(BusinessSaleShipmentDocDataService)Naming.lookup(urlPrefix+"BusinessShipment-data");
		
		shipmentdoc=(TransCenterShipmentDocDataService)Naming.lookup(urlPrefix+"TransCenterShipment-data");
		
		ipdata=(IPDataService)Naming.lookup(urlPrefix+"IP-data");
	}
	

	public static BankAccountDataService getBankAccountObject() {
		return bankAccount;
	}

	public static PaymentDocDataService getPaymentDocObject() {
		return paymentDoc;
	}

	public static LogDataService getLogObject() {
		return log;
	}

	public static DriverDataService getDriverObject() {
		return driver;
	}

	public static VehicleDataService getVehicleObject() {
		return vehicle;
	}

	public static OrganizationDataService getOrgObject() {
		return org;
	}

	public static InnerAccountDataService getInnerAccountObject() {
		return innerAccount;
	}

	public static ProfitFormDataService getProfitFormObject() {
		return profitForm;
	}

	public static OperateFormDataService getOperateFormObject() {
		return operateForm;
	}

	public static SalaryDataService getSalaryStrategy() {
		return salary;
	}

	public static DistanceDataService getDistanceStrategy() {
		return distance;
	}

	public static PriceDataService getPriceStrategy() {
		return price;
	}

	public static RepoDataService getRepoInfoObject() {
		return repoInfo;
	}

	public static AdminUserDataService getUserAdminObject() {
		return userAdmin;
	}

	public static UserDataService getUserInfoObject() {
		return userInfo;
	}

	public static SignUserDataService getUserSignObject() {
		return userSign;
	}

	public static BusinessSaleDeliverDocumentDataService getDeliverDocObject() {
		return deliverdoc;
	}

	public static BusinessSaleArrivalDocumentDataService getArrivalDocObject() {
		return arrivaldoc;
	}

	public static BusinessSaleReceiveDocumentDataService getReceiveDocObject() {
		return receivedoc;
	}

	public static DeliverCreateOrderDataService getOrderObject() {
		return order;
	}

	public static GoodsStatusDataService getSearchObject() {
		return search;
	}

	public static TransCenterArrivalDocumentDataService getTransCenterArrivalDocObject() {
		return transcenterarrivaldoc;
	}

	public static TransCenterShipmentDocDataService getShipmentDocObject() {
		return shipmentdoc;
	}

	public static PredictTimeDataService getPredictTimeObject() {
		return predicttime;
	}

	public static ReceiveInfoDataService getReceiveInfoObject() {
		return receiveinfo;
	}

	public static GoodsStatusDataService getGoodStatusObject() {
		return goodstatus;
	}

	public static BusinessSaleShipmentDocDataService getBusinessShipmentDocObject() {
		return businessShipmentdoc;
	}

	public static TransCenterTransferDocDataService getTransferDocObject() {
		return transferdoc;
	}

	public static BusinessSaleArrivalDocumentDataService getBusinessHallArrivalDocObject() {
		return businessArrivalDoc;
	}
	
	public static OutDocDataService getOutDocObject(){
		return outDoc;
	}

	public static InDocDataService getInDocObject(){
		return inDoc;
	}
	
	public static IPDataService getIPObject() {
		return ipdata;
	}
	
	
}
