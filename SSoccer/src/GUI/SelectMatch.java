package GUI;


import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import match.main.AIMode;
import data.Load;
import data.AIdata;

public class SelectMatch extends JPanel implements ActionListener {

	private Image img;
	protected MainMenu mainmenu;
	protected Load data;
	protected JButton aimode = new JButton("AI 대전");
	protected JButton practice = new JButton("연습 경기");
	protected JButton specialMatch = new JButton("스페셜 메치");
	
	AIdata aid = new AIdata();
	
	
	public SelectMatch(Load data, MainMenu mainmenu) {
		this.data = data;
		this.mainmenu = mainmenu;
		setLayout(null);
		setSize(540, 380);
		
		specialMatch.setBounds(100, 50, 130, 50);
		specialMatch.addActionListener(this);
		add(specialMatch);
		practice.setBounds(100, 150, 130, 50);
		practice.addActionListener(this);
		add(practice);
		aimode.setBounds(100, 250, 130, 50);
		aimode.addActionListener(this);
		add(aimode);
		JTextField record = new JTextField();
		record.setBounds(310, 50, 130, 50);
		record.setEditable(false);
		record.setText(data.getUser().win + "/" + 
				data.getUser().lose + "  " + data.getUser().draw + "무");
		record.setFont(new Font(record.getFont().getName(), 0, 50));
		add(record);
		add(new bg());
	}
	
	public class bg extends JPanel {
		bg(){
			setBounds(0,0,540,380);
		}
		public void paint(Graphics g) {
			img = new ImageIcon("res/menu/Background.png").getImage();
			g.drawImage(img, 0, 0, 540, 380, this);
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == aimode){
			new AIMode(data.getTeam(),aid.getTeam(data));//data.getTeam(),data.getTeam()
			mainmenu.dispose();
		} else if (e.getSource() == practice) {
			networkMatch(false);
		} else if (e.getSource() == specialMatch) {
			networkMatch(true);
		}
	}
	
	public void networkMatch(boolean isReturn){}

}
