package express.po;
import java.io.Serializable;
import java.util.ArrayList;

public class PredictTimePO implements Serializable{//把同一出发地和到达地的historyPO放到PredictPO里
	/**
	 * 
	 */
	private static final long serialVersionUID = -568026301802158904L;
	private String setOutCity;
	private String arrivalCity;
	private ArrayList<HistoryTimePO> predictlist =new ArrayList<HistoryTimePO>();

	public ArrayList<HistoryTimePO> getList(){
		return predictlist;
	}
	
	public boolean addHistoryTime(HistoryTimePO po){
		predictlist.add(po);
		return true;
	}
	
	public String getSetOutCity(){
		return setOutCity;
	}
	public String getArrivalCity(){
		return arrivalCity;
	}
	
	public void setSetOutCity(String setout){
		this.setOutCity=setout;
	}
	
	public void setArrivalCity(String arrival){
		this.arrivalCity=arrival;
	}
	
}
