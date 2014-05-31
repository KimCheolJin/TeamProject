package match.graphic;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import match.data.MTeam;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TeamInfo extends JPanel implements ActionListener {

	JButton[] player = new JButton[11];
	MTeam team;
	JLabel[] strategy = new JLabel[3];
	
	public TeamInfo(MTeam t) {
		setLayout(null);
		setBackground(new Color(200, 200, 200));
		team = t;
		for (int i = 0; i < player.length; i++) {
			player[i] = new JButton(team.player[i].name);
			player[i].setBounds(20 + i / 11 * 100, 100 + i % 11 * 25, 160, 25);
			add(player[i]);
		}
		
		JPanel strategyPanel = new JPanel();
		strategyPanel.setBounds(20,20,160,60);
		strategyPanel.setBackground(new Color(255,255,255));
		strategyPanel.setLayout(null);
		for(int i = 0; i < 3; i++){
			strategy[i] = new JLabel();
			strategy[i].setBounds(30, 20 * i, 160, 20);
			strategyPanel.add(strategy[i]);
		}
		add(strategyPanel);
		draw();
	}

	public void draw() {
		for (int i = 0; i < 11; i++) {
			if (i == 0)
				if (team.player[i].sp < 1)
					player[i].setBackground(new Color(50, 50, 50));
				else
					player[i].setBackground(new Color(100, 100, 100));
			else if (i < team.defNum + 1)
				if (team.player[i].sp < 1)
					player[i].setBackground(new Color(50, 50, 150));
				else
					player[i].setBackground(new Color(100, 100, 200));
			else if (i < team.defNum + team.midNum + 1)
				if (team.player[i].sp < 1)
					player[i].setBackground(new Color(50, 150, 50));
				else
					player[i].setBackground(new Color(100, 200, 100));
			else
				if (team.player[i].sp < 1)
					player[i].setBackground(new Color(150, 50, 50));
				else
					player[i].setBackground(new Color(200, 100, 100));
			player[i].setText(team.player[i].name);
		}
		strategy[0].setText("공격   성향    :    " + team.strategyT);
		strategy[1].setText("공격   전술    :    " + team.strategyA);
		strategy[2].setText("수비   전술    :    " + team.strategyD);
	}

	public void actionPerformed(ActionEvent e) {

	}

}
