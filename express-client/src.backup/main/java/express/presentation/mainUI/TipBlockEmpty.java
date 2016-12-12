package express.presentation.mainUI;

import javax.swing.ImageIcon;

public class TipBlockEmpty extends TipBlock{

	public TipBlockEmpty(String contents) {
		super(contents);
		
	}

	
	public void setPicture(){
		this.setIcon(new ImageIcon("picture/ApplyEmpty.png"));
	}
}
