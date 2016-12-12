package express.dataService.documentDataService;

import java.util.ArrayList;

import express.po.DocumentPO;

public interface ExamdocumentDataService {
	
	public ArrayList<DocumentPO> getDocumentList();
	
	public DocumentPO getDocument(int index);
	
	public boolean changePassState(DocumentPO po);
	
	public boolean changeDocument(DocumentPO po);

}
