package express.presentation.userUI;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.button.ClassicButtonShaper;
import org.jvnet.substance.theme.SubstanceCremeTheme;
import org.jvnet.substance.watermark.SubstanceBubblesWatermark;

import express.businessLogic.IDKeeper;
import express.businessLogic.infoManageBL.Admin;
import express.businessLogic.infoManageBL.StaffForManager;
import express.businesslogicService.adminBLService.AdminBLService;
import express.businesslogicService.managerBLService.StaffManageBLService;
import express.po.UserRole;
import express.presentation.mainUI.MainUI;
import express.presentation.mainUI.MainUIService;
import express.presentation.managerUI.managerMenuUI;

public class SignInUI extends JFrame{
	
	private CardLayout card;
	private JPanel container;
	private MainUIService main;
	
	public SignInUI(String id){
		
		this.setLayout(null);
		container = new JPanel();
		container.setBounds(0,0,1000,700);
		card= new CardLayout();		
		container.setLayout(card);
		this.add(container);
		
		main = new MainUI(card,container);
		main.setframe(this);
		
		AdminBLService adb = new Admin();
		UserRole role = adb.getUser(id).getPosition();
		
		if(role.equals(UserRole.DeliverMan)){
			main.jumpTodeliverMenuUI(id);
		}else if(role.equals(UserRole.Admin)){
			main.jumpToadminStartUI(id);
		}else if(role.equals(UserRole.BusinessSale)){
			main.jumpTobusinessMenuUI(id);
		}else if(role.equals(UserRole.Financial)){
			IDKeeper.setHigh(false);
			main.jumpToFinanceMenuUI(id,false);
		}else if(role.equals(UserRole.Financial_highest)){
			IDKeeper.setHigh(true);
			main.jumpToFinanceMenuUI(id,true);
		}else if(role.equals(UserRole.Manager)){
			IDKeeper.setIsManager(true);
			main.jumpTomanagerMenuUI(id);
		}else if(role.equals(UserRole.TransCenterRepo)){
			main.jumpTotranscenterRepoMenuUI(id);
		}else if(role.equals(UserRole.TransCenterSale)){
			main.jumpTotransSaleMenuUI(id);
		}

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setUndecorated(true);
//		this.setLocation(screenSize.width/2-1200/2,screenSize.height/2-900/2);
		this.setSize(1000,700);
		 this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	 
}
