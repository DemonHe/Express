package express.businessLogic.documentBL;

public class CheckOrder {
	public boolean isOrderIDAvailable(String id){
		if(id.length()==10){
			for(int i=0;i<9;i++){
				char c=id.charAt(i);
				if(c>='0'&&c<='9'){		
				}
				else{
					return false;
				}
			}
			return true;
		}
		else{
			return false;
		}
		
	}
}
