package express.data.stub;

import java.util.ArrayList;

import express.dataService.documentDataService.ExamdocumentDataService;
import express.po.DocumentPO;

public class ExamdocumentDataService_stub implements ExamdocumentDataService{
	ArrayList<DocumentPO> dpo;
	
	public ExamdocumentDataService_stub(ArrayList<DocumentPO> d){
		dpo = d;
	}
	
	public ArrayList<DocumentPO> getDocumentList(){
		return dpo;
	}
	
	public DocumentPO getDocument(int index){
		return dpo.get(index);
	}
	
	public boolean changePassState(DocumentPO po){
		po.setState(true);
		System.out.println("Change succeed.");
		return true;
	}
	
	public boolean changeDocument(DocumentPO po){
		System.out.println("Change succeed.");
		return true;
	}
}
