package express.dataService_Driver;

import java.util.ArrayList;

import express.dataService.documentDataService.ExamdocumentDataService;
import express.po.DocumentPO;

public class ExamdocumentDataService_Driver {
	public void drive(ExamdocumentDataService examdocumentdataservice){
		ArrayList<DocumentPO> dpo = examdocumentdataservice.getDocumentList();
		System.out.println("Get succeed.");
		DocumentPO dp = examdocumentdataservice.getDocument(0);System.out.println("Get succeed.");
		examdocumentdataservice.changeDocument(dp);
		examdocumentdataservice.changeDocument(dp);
	}
}
