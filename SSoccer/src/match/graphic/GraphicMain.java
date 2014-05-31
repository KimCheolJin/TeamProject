package match.graphic;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import match.data.MTeam;

public class GraphicMain extends JFrame {
	
	public MTeam home;
	public MTeam away;
	public TeamInfo homeInfo;
	public TeamInfo awayInfo;
	public Stadium stadium;
	public Score score;
	
	public GraphicMain(MTeam home, MTeam away){
		this.home = home;
		this.away = away;
		setTitle(home.name + " vs " + away.name);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 1126, 658);
		setLayout(null);
		
		homeInfo = new TeamInfo(home);
		homeInfo.setBounds(0,200,200,430);
		add(homeInfo);
		
		awayInfo = new TeamInfo(away);
		awayInfo.setBounds(920,200,200,430);
		add(awayInfo);
		
		setEmblem();
		
		score = new Score(home, away);
		score.setBounds(200,0,720,200);
		add(score);

		stadium = new Stadium(home, away);
		stadium.setBounds(200,200,720,430);
		add(stadium);
		
		setVisible(true);
	}

	public void setEmblem(){
		ImageIcon homeImg = new ImageIcon("res/img/emblem/"+home.name+".png");
		JLabel homeEmblem = new JLabel(homeImg);
		homeEmblem.setBounds(0,0,200,200);
		add(homeEmblem);
		
		ImageIcon awayImg = new ImageIcon("res/img/emblem/"+away.name+".png");
		JLabel awayEmblem = new JLabel(awayImg);
		awayEmblem.setBounds(920,0,200,200);
		add(awayEmblem);

	}
}
