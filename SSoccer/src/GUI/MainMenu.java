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
	private SetStrategy ss;
	private Training tr;
	
	private StoreNew sn;
	private StoreOld so;
	private StoreUp  su;
	protected FriendGUI fg;
	
	public MainMenu(String id){
		
		//시작시 로드 한번 일어남
		data = new Load(id);
		//data = new TestLoad(id);
		
		setBounds(300,300,550, 435);
		setTitle("Soccer Soccer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		Image img = new ImageIcon("res/menu/Background.png").getImage();
		sm = new SelectMatch(img, data, this);
		ss = new SetStrategy(data.getTeam());
		tr = new Training(data.getTeam());
		
		sn = new StoreNew(data);
		so = new StoreOld(data);
		su = new StoreUp(data);
		
		makeTab();
		
		setVisible(true);
	}
	
	
	
	private void makeTab(){
		tp = new JTabbedPane();
		tp.add("경기 선택", sm);
		tp.add("전술 선택", ss);
		tp.add("선수 훈련", tr);
		tp.add("신규 영입", sn);
		tp.add("이적 시장", so);
		tp.add("이적 등록", su);
		add(tp);
	}
	
}
