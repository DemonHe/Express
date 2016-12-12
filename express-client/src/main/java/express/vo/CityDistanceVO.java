package express.vo;

public class CityDistanceVO {
	private String city1;
	private String city2;
	private double distance;

	
	public CityDistanceVO(String city1,String city2,double distance){
		this.city1 = city1;
		this.city2 = city2;
		this.distance = distance;
	}
	
	public String getCity1(){
		return city1;
	}
	
	public boolean setCity1(String city1){
		this.city1 = city1;
		return false;
	}
	
	public String getCity2(){
		return city2;
	}
	
	public boolean setCity2(String city2){
		this.city2 = city2;
		return false;
	}
	
	public double getDistance(){
		return distance;
	}
	
	public boolean setDistance(double distance){
		this.distance = distance;
		return false;
	}

}
