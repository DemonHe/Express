package express.data;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

import express.po.GoodTransStatusPO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SearchData {
	public GoodTransStatusPO search (String id){
		try{
			ObjectInputStream is =new ObjectInputStream(new FileInputStream("good1.ser"));
			GoodTransStatusPO state=(GoodTransStatusPO)is.readObject();
			return state;
		}catch(Exception e){
			e.printStackTrace();
			return null;
			
		}
	}
	
	
	
	
	
}

