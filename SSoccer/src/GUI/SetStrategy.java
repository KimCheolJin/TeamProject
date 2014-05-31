package GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import data.Player;
import data.Team;
import match.data.MPlayer;
import match.data.MTeam;
import match.data.SendingStrategy;

public class SetStrategy extends JPanel {

	protected MTeam team;
	protected SendingStrategy sStrategy;
	protected JButton player[] = new JButton[22];
	JSlider sliderT;
	JSlider sliderA;
	JSlider sliderD;
	JComboBox Formation;
	
	public SetStrategy(final MTeam team){
		this.team = team;
		sStrategy = new SendingStrategy(team);
		setLayout(null);
		setSize(540, 380);
		setStrategyPanel(0, 55);
		setChangePanel(200, 0);
		setFomation(0, 15);
		draw();
		
		JButton cancel = new JButton("취소");
		cancel.setBounds(330, 300, 90, 50);
		cancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				sStrategy = new SendingStrategy(team);
				sliderT.setValue(sStrategy.strategyT);
				sliderA.setValue(sStrategy.strategyA);
				sliderD.setValue(sStrategy.strategyD);
				for(int i = 0; i < 22; i++){
					player[i].setText(sStrategy.player[i].name);
				}
				Formation.setSelectedIndex(sStrategy.strategyF);
			}
		});
		add(cancel);
	}
	
	public void setStrategyPanel(int x, int y) {
		JPanel T = new JPanel();
		JLabel labelT1 = new JLabel("전체 전략");
		JLabel labelT2 = new JLabel("수비 중시                      공격 중시");
		sliderT = new JSlider(JSlider.HORIZONTAL, 1, 9, sStrategy.strategyT);
		sliderT.setPaintTicks(true);
		sliderT.setMajorTickSpacing(1);
		sliderT.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e) {
				sStrategy.strategyT = sliderT.getValue();
			}
		});
		T.add(labelT1);
		T.add(labelT2);
		T.add(sliderT);
		T.setBounds(x, y, 200, 80);
		
		JPanel A = new JPanel();
		JLabel labelA1 = new JLabel("공격 전략");
		JLabel labelA2 = new JLabel("개인기 중시                    패스 중시");
		sliderA = new JSlider(JSlider.HORIZONTAL, 1, 9, sStrategy.strategyA);
		sliderA.setPaintTicks(true);
		sliderA.setMajorTickSpacing(1);
		sliderA.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e) {
				sStrategy.strategyA = sliderA.getValue();
			}
		});
		A.add(labelA1);
		A.add(labelA2);
		A.add(sliderA);
		A.setBounds(x, y + 80, 200, 80);
		
		
		JPanel D = new JPanel();
		JLabel labelD1 = new JLabel("수비 전략");
		JLabel labelD2 = new JLabel("압박 수비                      지역 수비");
		sliderD = new JSlider(JSlider.HORIZONTAL, 1, 9, sStrategy.strategyD);
		sliderD.setPaintTicks(true);
		sliderD.setMajorTickSpacing(1);
		sliderD.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e) {
				sStrategy.strategyD = sliderD.getValue();
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
			player[i] = new JButton(sStrategy.player[i].name);
			player[i].setBounds(x + 10 + i / 11 * 160, y + 15 + i % 11 * 25, 150, 25);
			player[i].addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					int i = 0;
					for (int j = 0; j < 22; j++) {
						if (sStrategy.player[i].name != null)
							i = j;
					}
					for (int j = 0; j < 22; j++) {
						if (e.getSource() == player[j]) {
							sStrategy.player[i] = sStrategy.player[j];
							sStrategy.player[j] = new MPlayer(new Player(null));
						}
					}
					for (i = 0; i < 22; i++) {
						player[i].setText(sStrategy.player[i].name);
					}
				}
				
			});
			add(player[i]);
		}
	}
	
	public void setFomation(int x, int y) {
		String fmt[] = {"2-3-5", "3-4-3", "4-3-3", "3-5-2", "4-4-2", "5-3-2", "6-3-1" };
		Formation = new JComboBox(fmt);
		Formation.setSelectedIndex(sStrategy.strategyF);
		Formation.setBounds(x + 10, y, 180, 25);
		Formation.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e) {
				String str = (String) e.getItem();
				sStrategy.defNum = str.charAt(0) - 48;
				sStrategy.midNum = str.charAt(2) - 48;
				sStrategy.atkNum = str.charAt(4) - 48;
				sStrategy.strategyF = Formation.getSelectedIndex();
				draw();
			}
		});
		add(Formation);
	}

	public void draw() {
		for (int i = 0; i < 22; i++) {
			if (i == 0)
				if (team.player[i].sp < 1)
					player[i].setBackground(new Color(50, 50, 50));
				else
					player[i].setBackground(new Color(100, 100, 100));
			else if (i < sStrategy.defNum + 1)
				if (team.player[i].sp < 1)
					player[i].setBackground(new Color(50, 50, 150));
				else
					player[i].setBackground(new Color(100, 100, 200));
			else if (i < sStrategy.defNum + sStrategy.midNum + 1)
				if (team.player[i].sp < 1)
					player[i].setBackground(new Color(50, 150, 50));
				else
					player[i].setBackground(new Color(100, 200, 100));
			else if (i < 11)
				if (team.player[i].sp < 1)
					player[i].setBackground(new Color(150, 50, 50));
				else
					player[i].setBackground(new Color(200, 100, 100));
		}

	}
	
	public static void main(String args[]){
		JFrame jf = new JFrame("StrategyEdit");
		jf.setSize(550,400);
		jf.setLayout(null);
		jf.add(new SetStrategy(new MTeam(makeTeam("맨유"))));
		jf.setVisible(true);
	}
	
	//임시 메소드. 팀과 그 팀의 구성원들을 생성.
	public static Team makeTeam(String tName){
		Team team = new Team(tName);
		for(int i = 0; i < 22; i++){
			String pname = team.name + " " + i + "번";
			team.player[i] = new Player(pname);
			team.player[i].shoot = 70;
			team.player[i].dribble = 70;
			team.player[i].pass = 70;
			team.player[i].stamina = 70;
			team.player[i].speed = 70;
			team.player[i].tackle = 70;
			team.player[i].steal = 70;
			team.player[i].gk = 70;
		}
		return team;
	}
}
