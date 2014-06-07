package GUI;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import match.main.AIMode;
import data.Load;

public class SelectMatch extends JPanel implements ActionListener {

	Image img;
	protected Load data;
	MainMenu mainmenu;
	
	public SelectMatch(Image img, Load data, MainMenu mainmenu) {
		this.img = img;
		this.data = data;
		this.mainmenu = mainmenu;
		setLayout(null);
		setSize(540, 380);
		
		JButton aimode = new JButton("AI ����");
		aimode.setBounds(200, 50, 130, 50);
		aimode.addActionListener(this);
		add(aimode);
		JButton practice = new JButton("���� ���");
		practice.setBounds(200, 150, 130, 50);
		practice.addActionListener(this);
		add(practice);
		JButton specialMatch = new JButton("����� ��ġ");
		specialMatch.setBounds(200, 250, 130, 50);
		specialMatch.addActionListener(this);
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


	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "AI ����"){
			new AIMode(data.getTeam(),null);
		} else if (e.getActionCommand() == "���� ���") {
			practicePerformed();
		} else if (e.getActionCommand() == "����� ��ġ") {
			specialPerformed();
		}
		mainmenu.setVisible(false);
	}
	
	public void practicePerformed() { }
	
	public void specialPerformed() { }

}
