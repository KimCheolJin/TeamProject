package GUI;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

import login.Massage;
import data.Load;


//신규영입 부분
public class StoreNew extends JPanel {
	
	int selectedIndex ; //선택된 list의 index 저장하는 변수
	int length; //상점에 선수들 담긴 길이
	int numOfPlayer; //선수몇명인지 확인하는 변수
	
	
	JList<String> list;
	
	JScrollPane sp;
	
	JLabel hasMoney;
	JLabel Money;
	JLabel mark;
	JLabel s1;
	JLabel s2;
	JLabel s3;
	JLabel s4;
	JLabel s5;
	JLabel s6;
	JLabel s7;
	JLabel s8;
	
	JButton getit;
	JButton search;
	
	JComboBox<String> shoot;
	JComboBox<String> dribble;
	JComboBox<String> pass;
	JComboBox<String> stamina;
	JComboBox<String> tackle;
	JComboBox<String> steal;
	JComboBox<String> speed;
	JComboBox<String> gk;
	
	
	public StoreNew(final Load data){
	
		
		this.setSize(540,380);
		this.setLayout(null);
		
		//설정
		String[] ss=data.getStore().getnp(); 
		length = data.getStore().getnp().length;
		
		//선수목록부분
		list = new JList<String>(ss);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
		
		
		//영입버튼 + 버튼이벤트
		getit = new JButton("영입");
		getit.setBounds(460, 220, 70, 30);
		add(getit);
		
		numOfPlayer = data.getTeam().getNumberOfPlayer();
		
		
		getit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(selectedIndex>=0 && selectedIndex<=length){
					
					if(numOfPlayer>22){
						
						//소유중인 선수가 22명보다 많으면 구매못하게 오류메세지발생
						Massage ms = new Massage("더이상 선수를 영입할 수 없습니다!!");
						
					}
					else{
						//정상적인 구매 
						
						//금액이 부족하면 에러메세지 뜸
						int diff = data.getUser().getMoney()-data.getStore().getNewPlayerIndex(selectedIndex).getPrice();
						if(diff<0){
							Massage ms = new Massage("금액이부족합니다!!");
						}
						else{
							data.buyNewPlayer(selectedIndex);
							Massage ms = new Massage("영입완료!!");
							
						}
						
					}
					
				}
				else{
					
					//오류메세지 발생
					Massage ms = new Massage("선수를선택하세요!!");
					
				}
			}
			
		});
		
		
		//능력치검색부분
		search = new JButton("능력치 검색");
		search.setBounds(10, 255, 150, 20);
		add(search);
		
		//슛
		s1 = new JLabel("Shoot");
		s1.setBounds(10, 290, 40, 25);
		add(s1);
	
		shoot = new JComboBox<String>(new String[] {"90이상","80이상","70이상","60이상","50이상"});
		shoot.setBounds(60, 290, 70, 25);
		add(shoot);
		
		//드리블
		s2 = new JLabel("Dribble");
		s2.setBounds(10, 320, 50, 25);
		add(s2);
	
		dribble = new JComboBox<String>(new String[] {"90이상","80이상","70이상","60이상","50이상"});
		dribble.setBounds(60, 320, 70, 25);
		add(dribble);
		
		//패스
		s3 = new JLabel("Pass");
		s3.setBounds(140, 290, 40, 25);
		add(s3);
	
		pass = new JComboBox<String>(new String[] {"90이상","80이상","70이상","60이상","50이상"});
		pass.setBounds(190, 290, 70, 25);
		add(pass);
		
		//스테미너
		s4 = new JLabel("Stamina");
		s4.setBounds(140, 320, 50, 25);
		add(s4);
	
		stamina = new JComboBox<String>(new String[] {"90이상","80이상","70이상","60이상","50이상"});
		stamina.setBounds(190, 320, 70, 25);
		add(stamina);
		
		//태클
		s5 = new JLabel("Tackle");
		s5.setBounds(270, 290, 40, 25);
		add(s5);
			
		tackle = new JComboBox<String>(new String[] {"90이상","80이상","70이상","60이상","50이상"});
		tackle.setBounds(320, 290, 70, 25);
		add(tackle);
				
		//스틸
		s6 = new JLabel("Steal");
		s6.setBounds(270, 320, 50, 25);
		add(s6);
			
		steal = new JComboBox<String>(new String[] {"90이상","80이상","70이상","60이상","50이상"});
		steal.setBounds(320, 320, 70, 25);
		add(steal);
		
		//스피드
		s7 = new JLabel("Speed");
		s7.setBounds(410, 290, 40, 25);
		add(s7);
			
		speed = new JComboBox<String>(new String[] {"90이상","80이상","70이상","60이상","50이상"});
		speed.setBounds(460, 290, 70, 25);
		add(speed);
				
		//GK
		s8 = new JLabel("GK");
		s8.setBounds(410, 320, 50, 25);
		add(s8);
			
		gk = new JComboBox<String>(new String[] {"90이상","80이상","70이상","60이상","50이상"});
		gk.setBounds(460, 320, 70, 25);
		add(gk);
		
		
		
	}

}
