package GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import data.Player;
import data.Team;
import data.TemporaryTeam;
import match.data.MPlayer;

public class SetStrategy extends JPanel {

	protected Team team;
	protected TemporaryTeam tTeam;
	protected JButton player[] = new JButton[22];
	JSlider sliderT;
	JSlider sliderA;
	JSlider sliderD;
	JComboBox<String> Formation;
	
	public SetStrategy(Team team){
		this.team = team;
		tTeam = new TemporaryTeam(team);
		setLayout(null);
		setSize(540, 380);
		setStrategyPanel(0, 55);
		setChangePanel(200, 0);
		setFomation(0, 15);
		setOkCancel();
		draw();
	}
	
	public void setStrategyPanel(int x, int y) {
		JPanel T = new JPanel();
		JLabel labelT1 = new JLabel("전체 전략");
		JLabel labelT2 = new JLabel("수비 중시                      공격 중시");
		sliderT = new JSlider(JSlider.HORIZONTAL, 1, 9, tTeam.strategyT);
		sliderT.setPaintTicks(true);
		sliderT.setMajorTickSpacing(1);
		sliderT.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e) {
				tTeam.strategyT = sliderT.getValue();
			}
		});
		T.add(labelT1);
		T.add(labelT2);
		T.add(sliderT);
		T.setBounds(x, y, 200, 80);
		
		JPanel A = new JPanel();
		JLabel labelA1 = new JLabel("공격 전략");
		JLabel labelA2 = new JLabel("개인기 중시                    패스 중시");
		sliderA = new JSlider(JSlider.HORIZONTAL, 1, 9, tTeam.strategyA);
		sliderA.setPaintTicks(true);
		sliderA.setMajorTickSpacing(1);
		sliderA.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e) {
				tTeam.strategyA = sliderA.getValue();
			}
		});
		A.add(labelA1);
		A.add(labelA2);
		A.add(sliderA);
		A.setBounds(x, y + 80, 200, 80);
		
		
		JPanel D = new JPanel();
		JLabel labelD1 = new JLabel("수비 전략");
		JLabel labelD2 = new JLabel("압박 수비                      지역 수비");
		sliderD = new JSlider(JSlider.HORIZONTAL, 1, 9, tTeam.strategyD);
		sliderD.setPaintTicks(true);
		sliderD.setMajorTickSpacing(1);
		sliderD.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e) {
				tTeam.strategyD = sliderD.getValue();
			}
		});
		D.add(labelD1);
		D.add(labelD2);
		D.add(sliderD);
		D.setBounds(x, y + 160, 200, 80);

		add(T);
		add(A);
		add(D);
	}
	
	public void setChangePanel(int x, int y) {
		for (int i = 0; i < player.length; i++) {
			player[i] = new JButton(tTeam.player[i].name);
			player[i].setBounds(x + 10 + i / 11 * 160, y + 15 + i % 11 * 25, 150, 25);
			player[i].addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					int i = 0;
					for (int j = 0; j < 22; j++) {
						if (!tTeam.player[i].name.equals(""))
							i = j;
					}
					for (int j = 0; j < 22; j++) {
						if (e.getSource() == player[j]) {
							tTeam.player[i] = tTeam.player[j];
							tTeam.player[j] = new MPlayer(new Player());
						}
					}
					for (i = 0; i < 22; i++) {
						player[i].setText(tTeam.player[i].name);
					}
				}
				
			});
			add(player[i]);
		}
	}
	
	public void setFomation(int x, int y) {
		Formation = new JComboBox<String>(Team.fmt);
		Formation.setSelectedIndex(tTeam.strategyF);
		Formation.setBounds(x + 10, y, 180, 25);
		Formation.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e) {
				String str = (String) e.getItem();
				tTeam.defNum = str.charAt(0) - 48;
				tTeam.midNum = str.charAt(2) - 48;
				tTeam.atkNum = str.charAt(4) - 48;
				tTeam.strategyF = Formation.getSelectedIndex();
				draw();
			}
		});
		add(Formation);
	}

	public void setOkCancel() {
		JButton cancel = new JButton("취소");
		cancel.setBounds(330, 300, 90, 50);
		cancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				tTeam = new TemporaryTeam(team);
				sliderT.setValue(tTeam.strategyT);
				sliderA.setValue(tTeam.strategyA);
				sliderD.setValue(tTeam.strategyD);
				for(int i = 0; i < 22; i++){
					player[i].setText(tTeam.player[i].name);
				}
				Formation.setSelectedIndex(tTeam.strategyF);
			}
		});
		add(cancel);
		
		JButton ok = new JButton("적용");
		ok.setBounds(430, 300, 90, 50);
		ok.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				okPerformed();
			}
		});
		add(ok);
	}
	
	public void draw() {
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


	protected void okPerformed() {
		boolean eleven = true;
		
		for(int i = 0; i < 11; i++){
			if(tTeam.player[i].name.equals("")){
				eleven = false;
			}
		}
		
		if(eleven){
			tTeam.getData(team);
		}
		
	}	

}
