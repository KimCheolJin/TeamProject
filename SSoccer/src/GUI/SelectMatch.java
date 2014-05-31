package GUI;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SelectMatch extends JPanel {

	public SelectMatch() {
		setLayout(null);
		setSize(542, 382);
		
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
			setBounds(0,0,545,372);
		}
		public void paint(Graphics g) {
			Image img = new ImageIcon("res/img/Soccer.jpg").getImage();
			g.drawImage(img, 0, 0, 545, 372, this);
		}
	}
	
	public void addbtn() {


	}

	public static void main(String args[]) {
		JFrame jf = new JFrame("StrategyEdit");
		jf.setSize(550, 400);
		SelectMatch sm = new SelectMatch();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setResizable(false);
		jf.add(sm);
		jf.setVisible(true);
	}
}
