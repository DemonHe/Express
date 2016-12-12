import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Fanxing {

	public static void main(String[] args) {
	
		ArrayList<Integer> lis=new ArrayList<Integer>();
		int n=4;
		while(n-->0){
			lis.add(n);
		}
		
		Iterator u=lis.iterator();
		while(u.hasNext()){
			System.out.println(u.next());
			
		}

	}
	
	


}
