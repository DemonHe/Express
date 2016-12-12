package express.presentation.mainUI;

import java.awt.Color;

public class MyOtherGreenLabel extends MyOtherBlueLabel{

	public MyOtherGreenLabel(String content) {
		super(content);
	}

	public void setMyColor(){
		//green
		this.setBackground(new Color(96,169,23));
	}
	
	public void whenPressed(){
		this.setBackground(new Color(18,128,35));
	}
}
