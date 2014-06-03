package GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import data.Player;
import data.Team;
import data.TemporaryTeam;

public class Release extends JPanel {
	
	
	private JButton player[] = new JButton[22];
	private Team team;
	private TemporaryTeam tTeam;

	private JPanel playerPanel;
	private JPanel namePanel;
	private JLabel name;
	private JPanel statusPanel[] = new JPanel[8];
	private JLabel status[] = new JLabel[8];
	private JTextField price = new JTextField();
	
	private Player p;
	
	public Release(Team team) {
		setLayout(null);
		setSize(540, 380);

		this.team = team;
		tTeam = new TemporaryTeam(team);
		setBtn();
		setOkCancel();
		setPlayerPanel();
	}

	public void setBtn() {
		BtnListener btnListener = new BtnListener();
		
		for (int i = 0; i < player.length; i++) {
			player[i] = new JButton(tTeam.player[i].name);
			player[i].setBounds(10 + i / 11 * 160, 15 + i % 11 * 25, 150, 25);
			player[i].addActionListener(btnListener);
			add(player[i]);
		}

		for (int i = 0; i < 22; i++) {
			if (i == 0)
				player[i].setBackground(new Color(100, 100, 100));
			else if (i < tTeam.defNum + 1)
				player[i].setBackground(new Color(100, 100, 200));
			else if (i < tTeam.defNum + tTeam.midNum + 1)
				player[i].setBackground(new Color(100, 200, 100));
			else if (i < 11)
				player[i].setBackground(new Color(200, 100, 100));
			else
				player[i].setBackground(new Color(200, 200, 200));
		}
	}

	public void setOkCancel() {
		JButton ok = new JButton("µî·Ï");
		ok.setBounds(430, 300, 90, 50);
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		add(ok);
	}

	public void setPlayerPanel() {
		playerPanel = new JPanel();
		playerPanel.setBounds(330, 15, 200, 275);
		playerPanel.setLayout(null);
		playerPanel.setVisible(false);
		add(playerPanel);

		namePanel = new JPanel();
		namePanel.setBounds(0, 20, 200, 40);
		name = new JLabel();
		namePanel.add(name);
		playerPanel.add(namePanel);

		for (int i = 0; i < 8; i++) {
			status[i] = new JLabel();
			statusPanel[i] = new JPanel();
			statusPanel[i].setBounds(i % 2 * 100, 60 + i / 2 * 40, 100, 40);
			statusPanel[i].add(status[i]);
			playerPanel.add(statusPanel[i]);
		}
		
		JLabel priceLabel = new JLabel("°¡°Ý :");
		priceLabel.setBounds(40, 235, 40, 40);
		playerPanel.add(priceLabel);
		
		price.setBounds(80, 240, 100, 30);
		playerPanel.add(price);
	}

	public void setPlayerInfo() {

		name.setText(p.name);
		status[0].setText("½¸ : " + p.shoot);
		status[1].setText("°ñÅ°ÆÛ : " + p.gk);
		status[2].setText("ÆÐ½º : " + p.pass);
		status[3].setText("Â÷´Ü : " + p.steal);
		status[4].setText("µå¸®ºí : " + p.dribble);
		status[5].setText("ÅÂÅ¬ : " + p.tackle);
		status[6].setText("¹ÎÃ¸ : " + p.speed);
		status[7].setText("Ã¼·Â : " + p.stamina);
		playerPanel.setVisible(true);
	}

	private class BtnListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			for (int i = 0; i < player.length; i++) {
				if (e.getSource() == player[i])
					if(tTeam.player[i].name != null)
						p = tTeam.player[i];
			}
			setPlayerInfo();
		}
	}
	
	
	
	
}
