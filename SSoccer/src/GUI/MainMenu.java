package GUI;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import match.data.MTeam;
import data.Player;
import data.Team;
import data.Load;
import data.TestLoad;

public class MainMenu extends JFrame {
	
	protected Load data;

	protected Image img;
	protected SelectMatch sm;
	protected JTabbedPane tp;
	protected SetStrategy ss;
	protected Training tr;
	
	protected StoreNew sn;
	protected StoreOld so;
	protected StoreUp  su;
	public FriendGUI fg;
	
	public MainMenu(String id){
		
		//시작시 로드 한번 일어남
		//data = new Load(id);
		data = new TestLoad(id);
		
		setBounds(300,300,550, 435);
		setTitle("Soccer Soccer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		setCompo();
		makeTab();
		
		setVisible(true);
	}
	
	protected void setCompo(){		
		Image img = new ImageIcon("res/menu/Background.png").getImage();
		tp = new JTabbedPane();
		sm = new SelectMatch(img, data, this);
		ss = new SetStrategy(data.getTeam());
		tr = new Training(data.getTeam());
		sn = new StoreNew(data);
		so = new StoreOld(data);
		su = new StoreUp(data);
	}
	
	protected void makeTab(){
		tp.add("경기 선택", sm);
		tp.add("전술 선택", ss);
		tp.add("선수 훈련", tr);
		tp.add("신규 영입", sn);
		tp.add("이적 시장", so);
		tp.add("이적 등록", su);
		add(tp);
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MainMenu("TEAM");
		//new MainMenu(makeTeam("팀이름"));
	}
	

}
