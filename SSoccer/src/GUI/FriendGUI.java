package GUI;

import java.awt.*;

import javax.swing.*;

import data.Load;

public class FriendGUI extends JPanel{
	
	JList<String> list;
	
	JLabel j1;
	JLabel j2;
	JLabel j3;
	
	JScrollPane sp;
	
	JTextField inputName;
	JTextField show;
	
	JButton getit;
	JButton game;
	JButton trade;
	
	
	public FriendGUI(Load data){
		
		this.setSize(540,380);
		this.setLayout(null);
		
		//친구목록부분
		j1 = new JLabel("친구목록");
		j1.setBounds(80, 10, 100, 30);
		add(j1);
		
		//data에서 유저의 친구목록 받아오면 보여줌
		list = new JList<String>(new String[]{"1","2","3","4","5","6","7","8","9","10"});
		sp = new JScrollPane(list);
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		sp.setBounds(10, 50, 200, 300);
		add(sp);
		
		//친구등록부분
		j2 = new JLabel("친구등록");
		j2.setBounds(230, 10, 70, 30);
		add(j2);
		
		inputName = new JTextField();
		inputName.setBounds(230, 50, 150, 30);
		add(inputName);
		
		getit = new JButton("등록");
		getit.setBounds(390, 50, 70, 30);
		add(getit);
		
		//친구정보부분
		j3 = new JLabel("친구정보");
		j3.setBounds(230, 90, 70, 30);
		add(j3);
		
		//리스트 더블클릭시 내용 바뀌도록 구현
		show = new JTextField("1승1패 23점");
		show.setBounds(230, 130, 300, 200);
		show.setEditable(false);
		add(show);
		
		//버튼 부분
		game = new JButton("게임하기");
		game.setBounds(270, 335, 100, 20);
		add(game);
		
		trade = new JButton("거래하기");
		trade.setBounds(380, 335, 100, 20);
		add(trade);
		
		
	}

}
