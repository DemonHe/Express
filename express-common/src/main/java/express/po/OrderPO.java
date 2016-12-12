package express.po;

import java.io.Serializable;

public class OrderPO  extends DocumentPO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7657789011273737410L;
	private String senderName;//寄件人信息
	private String senderAddress;
	private String senderWorkPlace;
	private String senderCellPhoneNum;
	private String senderTelephoneNum;
	private String receiverName;//收件人信息
	private String receiverAddress;
	private String receiverWorkPlace;
	private String receiverCellPhoneNum;
	private String receiverTelephoneNum;
	private int numberOfGoods;//托运信息：原件数
	private double weight;//实际重量
	private double volume;//体积
	private String nameOfGoods;//内件品名
	private DeliveryType type;//枚举类  快递类型
	private String orderID;//生成出来的 ID
	private PackageType packagetype;//包装类型
	private double fee;
	private String predictTime;
	//添加 出发城市 和到达城市
	private String startCity;
	private String endCity;
	
	
	//自动获得运费和预计时间后 调用set方法
		public void setFee(double fee){
			this.fee=fee;
		}
		public void setPredictTime(String predicttime){
			this.predictTime=predicttime;
		}
		
		public double getFee(){
			return fee;
		}
		public String getPredictTime(){
			return predictTime;
		}
		
	
	
	
	
	//ID 要自己set
	public void setOrderID(String orderID){
		this.orderID=orderID;
	}
	
	
	
		//不写构造方法了，参数太多，改用set
	public void setSenderInfo(String senderName,String senderAddress,
			String senderWorkPlace,String senderCellPhoneNum,
			String senderTelephoneNum ,String startCity){   //设置寄件人信息
		this.senderName=senderName;
		this.senderAddress=senderAddress;
		this.senderWorkPlace=senderWorkPlace;
		this.senderCellPhoneNum=senderCellPhoneNum;
		this.senderTelephoneNum=senderTelephoneNum;
		this.startCity=startCity;
	}
	public void setReceiverInfo(String receiverName,String receiverAddress,
			String receiverWorkPlace,String receiverCellPhoneNum,
			String receiverTelephoneNum,String endCity){   //设置收件人信息
		this.receiverName=receiverName;
		this.receiverAddress=receiverAddress;
		this.receiverWorkPlace=receiverWorkPlace;
		this.receiverCellPhoneNum=receiverCellPhoneNum;
		this.receiverTelephoneNum=receiverTelephoneNum;
		this.endCity=endCity;
	}
	
	//设置货物信息
	public void setGoodsInfo(int numberOfGoods,double weight,double volume,
							String nameOfGoods,DeliveryType type,PackageType pkgtype){
		this.numberOfGoods=numberOfGoods;
		this.weight=weight;
		this.volume=volume;
		this.nameOfGoods=nameOfGoods;
		this.type=type;
		this.packagetype=pkgtype;
	}
	

		public String  getSenderAddress(){
			return senderAddress;
		}
		public String getSenderName (){
			return senderName;
		}
		public String  getSenderWorkPlace(){
			return senderWorkPlace;
		}
		public String  getSenderCellPhoneNum(){
			return senderCellPhoneNum;
		}
		public String  getSenderTelephoneNum(){
			return senderTelephoneNum;
		}
		public String  getOrderID(){
			return orderID;
		}
		public String  getReceiverAddress(){
			return receiverAddress;
		}
		public String getReceiverName (){
			return receiverName;
		}
		public String  getReceiverWorkPlace(){
			return receiverWorkPlace;
		}
		public String  getReceiverCellPhoneNum(){
			return receiverCellPhoneNum;
		}
		public String  getReceiverTelephoneNum(){
			return receiverTelephoneNum;
		}
	
		public int getNumberOfGoods(){
			return numberOfGoods;
		}
		public double getWeight(){
			return weight;
		}
		public double getVolume(){
			return volume;
		}
		public String getNameOfGoods(){
			return nameOfGoods;
		}
		public DeliveryType getType(){
			return type;
		}
		
		//添加get 出发城市  到达城市
		public String getStartCity(){
			return startCity;
		}
		
		public String getEndCity(){
			return endCity;
		}
		
		public PackageType getPackageType(){
			return packagetype;
		}
		
}
