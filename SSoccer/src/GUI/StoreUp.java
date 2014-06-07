package GUI;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import data.Load;
import ETC.IntegerDocument;

public class StoreUp extends JPanel {
	
	IntegerDocument itdc = new IntegerDocument();
	
    JList<String> list;
	
	JScrollPane sp;
	
	JLabel hasMoney;
	JLabel Money;
	JLabel mark;
	JLabel mark2;
	JLabel input;
	
	JTextField inputMoney;
	
	JButton getit; //��Ϲ�ư
	JButton up;
	
	
	
	
	public StoreUp(Load data){
		
		this.setSize(540,380);
		this.setLayout(null);
		
		//���� ������� ������� ������
		String[] ss= data.getTeam().printPlayer();
		
		//������Ϻκ�
		list = new JList<String>(ss);
		//list.setBounds(10, 10, 520, 150);
		sp = new JScrollPane(list);
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		sp.setBounds(10, 10, 520, 200);
		add(sp);
		
		//�����ݺκ�
		hasMoney = new JLabel("������  :");
		hasMoney.setBounds(10, 220, 70, 30);
		add(hasMoney);
		
		Money = new JLabel();
		Money.setText("258"); //�����ݾ�
		Money.setBounds(80, 220, 70, 30);
		Money.setHorizontalAlignment(JLabel.RIGHT);
		add(Money);
		
		mark = new JLabel("��");
		mark.setBounds(160, 220, 20, 30);
		add(mark);
		
		
		//��Ϲ�ư
		getit = new JButton("���");
		getit.setBounds(460, 220, 70, 30);
		add(getit);
		
		//�Ǹűݾ׺κ�
		input = new JLabel("�Ǹűݾ�  :");
		input.setBounds(10, 255, 70, 30);
		add(input);
		
		
		inputMoney = new JTextField();
		inputMoney.setBounds(80, 260, 100, 20);
		inputMoney.setHorizontalAlignment(inputMoney.RIGHT);
		
		inputMoney.setDocument(itdc);
		add(inputMoney);
		
		mark2 = new JLabel("��");
		mark2.setBounds(190, 260, 20, 20);
		add(mark2);
		
	}
	
	 
		
}
