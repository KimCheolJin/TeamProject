package GUI;



import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import client.FriendGUI;
import data.Load;
import data.TestLoad;

public class MainMenu extends JFrame {
	
	protected Load data;

	protected Image img;
	protected SelectMatch sm;
	public JTabbedPane tp;
	protected SetStrategy ss;
	protected Training tr;
	
	protected StoreNew sn;
	protected StoreOld so;
	protected StoreUp  su;
	public FriendGUI fg;
	
	public MainMenu(String id){
		
		//���۽� �ε� �ѹ� �Ͼ
		//data = new Load(id);
		data = new TestLoad(id);
		
		setBounds(300,300,550, 435);
		setTitle("Soccer Soccer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		setCompo(id);
		makeTab();
		
		setVisible(true);
	}
	
	protected void setCompo(String id){		
		tp = new JTabbedPane();
		sm = new SelectMatch(data, this);
		ss = new SetStrategy(data.getTeam(),id);
		tr = new Training(data.getTeam(), id);
		sn = new StoreNew(data);
		so = new StoreOld(data);
		su = new StoreUp(data);
	}
	
	protected void makeTab(){
		tp.add("��� ����", sm);
		tp.add("���� ����", ss);
		tp.add("���� �Ʒ�", tr);
		tp.add("�ű� ����", sn);
		tp.add("���� ����", so);
		tp.add("���� ���", su);
		add(tp);
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MainMenu("TEAM");
		//new MainMenu(makeTeam("���̸�"));
	}
	

}
