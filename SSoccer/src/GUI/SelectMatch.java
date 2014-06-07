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

	private Image img;
	protected MainMenu mainmenu;
	protected Load data;
	protected JButton aimode = new JButton("AI 대전");
	protected JButton practice = new JButton("연습 경기");
	protected JButton specialMatch = new JButton("스페셜 메치");
	
	
	public SelectMatch(Image img, Load data, MainMenu mainmenu) {
		this.img = img;
		this.data = data;
		this.mainmenu = mainmenu;
		setLayout(null);
		setSize(540, 380);
		
		aimode.setBounds(200, 50, 130, 50);
		aimode.addActionListener(this);
		add(aimode);
		practice.setBounds(200, 150, 130, 50);
		practice.addActionListener(this);
		add(practice);
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
		mainmenu.dispose();
		if (e.getSource() == aimode){
			new AIMode(data.getTeam(),data.getTeam());
		} else if (e.getSource() == practice) {
			practicePerformed();
		} else if (e.getSource() == specialMatch) {
			specialPerformed();
		}
	}
	
	public void practicePerformed() { 
		System.out.println("Asd");
	}
	
	public void specialPerformed() { }

}
