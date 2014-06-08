package GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import match.data.MPlayer;
import match.data.MTeam;
import data.Player;
import data.Team;
import data.TemporaryTeam;

import DBCENTER.DBdata;

public class Training extends JPanel {
	
	private JButton player[] = new JButton[22];
	private Team team;
	private TemporaryTeam tTeam;

	private JPanel playerPanel;
	private JPanel namePanel, expPanel;
	private JLabel name, exp;
	private JButton status[] = new JButton[8];

	private Player p;
	
	DBdata dbd = new DBdata();
	String tempid;
	
	public Training(Team team,String id) {
		setLayout(null);
		setSize(540, 380);

		this.team = team;
		this.tempid = id;
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
		JButton cancel = new JButton("취소");
		cancel.setBounds(330, 300, 90, 50);
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tTeam = new TemporaryTeam(team);
				for (int i = 0; i < 22; i++) {
					player[i].setText(tTeam.player[i].name);
				}
				playerPanel.setVisible(false);
			}
		});
		add(cancel);

		JButton ok = new JButton("적용");
		ok.setBounds(430, 300, 90, 50);
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tTeam.getData(team);
				dbd.UpdateUserPlayer(team, tempid);
				
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

		expPanel = new JPanel();
		expPanel.setBounds(0, 235, 200, 40);
		exp = new JLabel();
		expPanel.add(exp);
		playerPanel.add(expPanel);

		
		StatusListener statusListener = new StatusListener();
		for (int i = 0; i < 8; i++) {
			status[i] = new JButton();
			status[i].setBounds(i % 2 * 100, 60 + i / 2 * 40, 100, 40);
			status[i].addActionListener(statusListener);
			playerPanel.add(status[i]);
		}

	}

	public void setPlayerInfo() {

		name.setText(p.name);
		status[0].setText("슛 : " + p.shoot);
		status[1].setText("골키퍼 : " + p.gk);
		status[2].setText("패스 : " + p.pass);
		status[3].setText("차단 : " + p.steal);
		status[4].setText("드리블 : " + p.dribble);
		status[5].setText("태클 : " + p.tackle);
		status[6].setText("민첩 : " + p.speed);
		status[7].setText("체력 : " + p.stamina);
		exp.setText("exp : " + p.exp);
		playerPanel.setVisible(true);
	}

	//선수 버튼 클릭했을시의 반응
	private class BtnListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			for (int i = 0; i < player.length; i++) {
				if (e.getSource() == player[i])
					if(!tTeam.player[i].name.equals(""))
						p = tTeam.player[i];
			}
			setPlayerInfo();
		}
	}
	
	private class StatusListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(p.exp >= 100){
				if (e.getSource() == status[0]){
					p.shoot++;
				} else if (e.getSource() == status[1]){
					p.gk++;
				} else if (e.getSource() == status[2]){
					p.pass++;
				} else if (e.getSource() == status[3]){
					p.steal++;
				} else if (e.getSource() == status[4]){
					p.dribble++;
				} else if (e.getSource() == status[5]){
					p.tackle++;
				} else if (e.getSource() == status[6]){
					p.speed++;
				} else if (e.getSource() == status[7]){
					p.stamina++;
				}
				p.exp -= 100;
			}
			setPlayerInfo();
		}
	}
	
}
