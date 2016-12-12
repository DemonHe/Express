package express.presentation.mainUI;

import java.awt.Color;

public class MyOtherRedLabel extends MyOtherBlueLabel{

	public MyOtherRedLabel(String content) {
		super(content);
	}

	
	public void setMyColor(){
		//red
		this.setBackground(new Color(206,53,44));
	}
	
	
	public void whenPressed(){
		this.setBackground(new Color(154,22,22));
	}
	
}
