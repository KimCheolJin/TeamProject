package GUI;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import match.data.MTeam;
import data.Player;
import data.Team;

public class MainMenu extends JFrame {

	private Image img;
	private SelectMatch sm;
	private SetStrategy ss;
	private Training tr;
	private Release rl;
	private Friend fr;
	
	public MainMenu(Team team){
		setBounds(300,300,550, 435);
		setTitle("Soccer Soccer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		Image img = new ImageIcon("res/menu/Background.png").getImage();
		sm = new SelectMatch(img);
		ss = new SetStrategy(team);
		tr = new Training(team);
		rl = new Release(team);
		fr = new Friend();
		
		makeTab();
		
		setVisible(true);
	}
	
	private void makeTab(){
		JTabbedPane tp = new JTabbedPane();
		tp.add("경기 선택", sm);
		tp.add("전술 선택", ss);
		tp.add("선수 훈련", tr);
		tp.add("신규 영입", new Trading());
		tp.add("이적 시장", new Trading());
		tp.add("이적 등록", rl);
		tp.add("친구 목록", fr);
		add(tp);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MainMenu(makeTeam("팀이름"));
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
			team.player[i].exp = 1000;
		}
		team.player[21].setNull();
		team.colorR = (int) (Math.random() * 256);
		team.colorG = (int) (Math.random() * 256);
		team.colorB = (int) (Math.random() * 256);
		return team;
	}
	
}
