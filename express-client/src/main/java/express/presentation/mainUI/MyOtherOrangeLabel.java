package express.presentation.mainUI;

import java.awt.Color;

public class MyOtherOrangeLabel extends MyOtherBlueLabel {

	public MyOtherOrangeLabel(String content) {
		super(content);
	}

	public void setMyColor() {
		// orange
		this.setBackground(new Color(250, 104, 0));
	}

	public void whenPressed() {
		this.setBackground(new Color(191, 90, 21));
	}

}
