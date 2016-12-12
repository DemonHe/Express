package express.vo;

public class RepoCacheVO {
	
	private int importSum;
	private int exportSum;
	private int sum;
	
	public RepoCacheVO(int i,int e,int s){
		this.importSum=i;
		this.exportSum=e;
		this.sum=s;
	}
	
	public RepoCacheVO(){
		this.importSum=0;
		this.exportSum=0;
		this.sum=0;
	}
	
	public int getImportSum(){
		return importSum;
	}
	
	public int getExportSum(){
		return exportSum;
	}
	
	public int getSum(){
		return sum;
	}
	//
	public void setImportSum(int n){
		importSum = n;
	}
	
	public void setExportSum(int n){
		exportSum = n;
	}
	
	public void setSum(int n){
		sum = n;
	}

}
