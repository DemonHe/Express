package express.businessLogic.documentBL;

public class ShipmentDocCheck {
	public boolean isTransIDavailable(String transid){
		//营业厅编�?+20150921日期+00000编码 、五位数�?
		//营业厅编号（025城市编码+000鼓楼营业厅）
		
		
	
		//也可以用中转中心  
		if(transid.length()!=19){		
			return false;
		}
		for(int i=0;i<19;i++){
			char c=transid.charAt(i);
			if(c>='0'&&c<='9'){
				
			}
			else {
				return false;
			}
		}
		
		return true;	
	}
}
