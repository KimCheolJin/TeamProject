package match.graphic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

import match.data.MTeam;

public class Score extends JPanel {

	JLabel score;
	JLabel time;
	MTeam home;
	MTeam away;
	int t;
	
	public Score(MTeam home, MTeam away) {
		setLayout(new BorderLayout());
		this.home = home;
		this.away = away;
		setBackground(new Color(0,200,200));
		
		score = new JLabel(home.score + " : " + away.score);
		score.setFont(new Font(score.getFont().getFontName(), 0,150));
		JPanel scorePanel = new JPanel();
		scorePanel.add(score);
		add(scorePanel, BorderLayout.CENTER);
		
		time = new JLabel("00 : 00 : 0");
		JPanel timePanel = new JPanel();
		timePanel.add(time);
		add(timePanel, BorderLayout.SOUTH);
	}
	
	public void rescore(){
		score.setText(home.score + " : " + away.score);
		
		t += 2;
		String minute = "" + t / 600;
		if(t / 600 < 10) minute = "0" + minute;
		String second = "" +  t % 600 / 10;
		if(t % 600 / 10 < 10) second = "0" + second;
		time.setText(minute + " : " + second + " : " + (t % 10));
	}
	
}
