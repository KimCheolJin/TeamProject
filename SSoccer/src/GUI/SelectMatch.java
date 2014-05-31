package GUI;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JPanel;

public class SelectMatch extends JPanel {

	Image img;
	
	public SelectMatch(Image img) {
		this.img = img;
		setLayout(null);
		setSize(540, 380);
		
		JButton aimode = new JButton("AI 대전");
		aimode.setBounds(200, 50, 130, 50);
		add(aimode);
		JButton practice = new JButton("연습 경기");
		practice.setBounds(200, 150, 130, 50);
		add(practice);
		JButton specialMatch = new JButton("스페셜 메치");
		specialMatch.setBounds(200, 250, 130, 50);
		add(specialMatch);
		
		add(new bg());
	}
	
	public class bg extends JPanel {
		bg(){
			setBounds(0,0,540,380);
		}
		public void paint(Graphics g) {
			g.drawImage(img, 0, 0, 540, 380, this);
		}
	}
	
	public void addbtn() {


	}
	
}
