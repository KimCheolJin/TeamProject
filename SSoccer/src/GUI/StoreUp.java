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
	
	JButton getit; //등록버튼
	JButton up;
	
	
	
	
	public StoreUp(final Load data){
		
		this.setSize(540,380);
		this.setLayout(null);
		
		//설정 사용자의 선수목록 보여줌
		String[] ss = data.getTeam().printPlayer();
		length = data.getTeam().player.length; //소유중인 선수목록 길이 최대 22
		
		//선수목록부분
		list = new JList<String>(ss);
		//list.setBounds(10, 10, 520, 150);
		sp = new JScrollPane(list);
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		sp.setBounds(10, 10, 520, 200);
		add(sp);
		
		this.selectedIndex = length+1; //초기값 설정
		
		//Jlist 이벤트핸들러부분
		list.addListSelectionListener(new ListSelectionListener(){

		public void valueChanged(ListSelectionEvent e) {
						
			  selectedIndex = list.getSelectedIndex();
				
			}
		});
		
		//소지금부분
		hasMoney = new JLabel("소지금  :");
		hasMoney.setBounds(10, 220, 70, 30);
		add(hasMoney);
		
		Money = new JLabel();
		int haveMoney = data.getUser().getMoney();
		Money.setText(Integer.toString(haveMoney)); 
		Money.setBounds(80, 220, 70, 30);
		Money.setHorizontalAlignment(JLabel.RIGHT);
		add(Money);
		
		mark = new JLabel("원");
		mark.setBounds(160, 220, 20, 30);
		add(mark);
		
		
		//등록버튼
		getit = new JButton("등록");
		getit.setBounds(460, 220, 70, 30);
		add(getit);
		
		
		
		getit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(selectedIndex>=0 && selectedIndex<=length){
					
					int pri = Integer.parseInt(inputMoney.getText());
					
					//음수값입력안되므로 걱정없음
					//정상적인판매
					data.sellPlayer(selectedIndex,pri);
					Massage ms = new Massage("등록완료!!");
					
					String[] after = data.getTeam().printPlayer();
					list.setListData(after);
					list.repaint();
					
				}
				else{
					
					//오류메세지 발생
					Massage ms = new Massage("선수를선택하세요!!");
					
				}
					
					
			}
			
		});
		
		
		
		
		//판매금액부분
		input = new JLabel("판매금액  :");
		input.setBounds(10, 255, 70, 30);
		add(input);
		
		
		inputMoney = new JTextField();
		inputMoney.setBounds(80, 260, 100, 20);
		inputMoney.setHorizontalAlignment(inputMoney.RIGHT);
		
		inputMoney.setDocument(itdc);
		add(inputMoney);
		
		mark2 = new JLabel("원");
		mark2.setBounds(190, 260, 20, 20);
		add(mark2);
		
	}
	
	 
		
}
