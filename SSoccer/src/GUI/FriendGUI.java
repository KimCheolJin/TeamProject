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
		
		//ģ����Ϻκ�
		j1 = new JLabel("ģ�����");
		j1.setBounds(80, 10, 100, 30);
		add(j1);
		
		//data���� ������ ģ����� �޾ƿ��� ������
		list = new JList<String>(new String[]{"1","2","3","4","5","6","7","8","9","10"});
		sp = new JScrollPane(list);
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		sp.setBounds(10, 50, 200, 300);
		add(sp);
		
		//ģ����Ϻκ�
		j2 = new JLabel("ģ�����");
		j2.setBounds(230, 10, 70, 30);
		add(j2);
		
		inputName = new JTextField();
		inputName.setBounds(230, 50, 150, 30);
		add(inputName);
		
		getit = new JButton("���");
		getit.setBounds(390, 50, 70, 30);
		add(getit);
		
		//ģ�������κ�
		j3 = new JLabel("ģ������");
		j3.setBounds(230, 90, 70, 30);
		add(j3);
		
		//����Ʈ ����Ŭ���� ���� �ٲ�� ����
		show = new JTextField("1��1�� 23��");
		show.setBounds(230, 130, 300, 200);
		show.setEditable(false);
		add(show);
		
		//��ư �κ�
		game = new JButton("�����ϱ�");
		game.setBounds(270, 335, 100, 20);
		add(game);
		
		trade = new JButton("�ŷ��ϱ�");
		trade.setBounds(380, 335, 100, 20);
		add(trade);
		
		
	}

}
