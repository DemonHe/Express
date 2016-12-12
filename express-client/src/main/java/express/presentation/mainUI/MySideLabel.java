package express.presentation.mainUI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;


public class MySideLabel extends JLabel{
	private boolean isClicked=false;
	
	
	
	public MySideLabel(String contents){
		//96.161.198
		//107.180.215
		init(contents);	
	}
	
	public void whenMouseOnIt(){
		if(!isClicked)
		this.setBackground(new Color(89, 205, 226));
	}
	
	public void whenClickHappend(){
		this.isClicked=true;
		this.setBackground(Color.white);
		this.setForeground(Color.black);	
	}
	
	
	public void init(String contents){
		this.setOpaque(true);
		this.setSize(150,50);
		this.setBackground(new Color(113,177,209));
		Font font=new Font("苹方 中等", Font.PLAIN, 17);
		this.setForeground(Color.white);
		this.setFont(font);
		this.setText(contents);	
		this.setHorizontalAlignment(SwingConstants.CENTER);
	}
	
	public void whenMouseleaveit(){
		if(!isClicked)
			this.setBackground(new Color(113,177,209));
	}
	
	public void restore(){
		this.isClicked=false;
		this.setBackground(new Color(113,177,209));
		this.setForeground(Color.white);
	}
	
}
