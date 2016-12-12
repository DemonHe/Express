package express.presentation.mainUI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class MyOtherBlueLabel extends JLabel{
	//blue 37 114 177
	public MyOtherBlueLabel(String content){
		init(content);
	}
	
	public void whenPressed(){
		this.setBackground(new Color(27,110,174));
	}
	
	public void init(String content){
		Font font=new Font("苹方 粗体", Font.PLAIN, 17);
		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.setOpaque(true);
		this.setForeground(Color.white);
		this.setFont(font);
		this.setText(content);
		this.setMyColor();
	}

	public void setMyColor(){
		//blue
		this.setBackground(new Color(32,134,191));
	}
	
	
	
}
