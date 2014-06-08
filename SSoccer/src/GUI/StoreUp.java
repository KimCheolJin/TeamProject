package GUI;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import login.Massage;
import data.Load;
import ETC.IntegerDocument;

public class StoreUp extends JPanel {
	
	IntegerDocument itdc = new IntegerDocument();
	
	int selectedIndex;
	int length;
	
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
	
	
	
	
	public StoreUp(final Load data){
		
		this.setSize(540,380);
		this.setLayout(null);
		
		//���� ������� ������� ������
		String[] ss = data.getTeam().printPlayer();
		length = data.getTeam().player.length; //�������� ������� ���� �ִ� 22
		
		//������Ϻκ�
		list = new JList<String>(ss);
		//list.setBounds(10, 10, 520, 150);
		sp = new JScrollPane(list);
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		sp.setBounds(10, 10, 520, 200);
		add(sp);
		
		this.selectedIndex = length+1; //�ʱⰪ ����
		
		//Jlist �̺�Ʈ�ڵ鷯�κ�
		list.addListSelectionListener(new ListSelectionListener(){

		public void valueChanged(ListSelectionEvent e) {
						
			  selectedIndex = list.getSelectedIndex();
				
			}
		});
		
		//�����ݺκ�
		hasMoney = new JLabel("������  :");
		hasMoney.setBounds(10, 220, 70, 30);
		add(hasMoney);
		
		Money = new JLabel();
		int haveMoney = data.getUser().getMoney();
		Money.setText(Integer.toString(haveMoney)); 
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
		
		
		
		getit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(selectedIndex>=0 && selectedIndex<=length){
					
					int pri = Integer.parseInt(inputMoney.getText());
					
					//�������Է¾ȵǹǷ� ��������
					//���������Ǹ�
					data.sellPlayer(selectedIndex,pri);
					Massage ms = new Massage("��ϿϷ�!!");
					
					String[] after = data.getTeam().printPlayer();
					list.setListData(after);
					list.repaint();
					
				}
				else{
					
					//�����޼��� �߻�
					Massage ms = new Massage("�����������ϼ���!!");
					
				}
					
					
			}
			
		});
		
		
		
		
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
