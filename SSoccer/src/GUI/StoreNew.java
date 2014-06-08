package GUI;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

import login.Massage;
import data.Load;


//�űԿ��� �κ�
public class StoreNew extends JPanel {
	
	int selectedIndex ; //���õ� list�� index �����ϴ� ����
	int length; //������ ������ ��� ����
	int numOfPlayer; //����������� Ȯ���ϴ� ����
	
	
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
		
		//����
		String[] ss=data.getStore().getnp(); 
		length = data.getStore().getnp().length;
		
		//������Ϻκ�
		list = new JList<String>(ss);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
		
		
		//���Թ�ư + ��ư�̺�Ʈ
		getit = new JButton("����");
		getit.setBounds(460, 220, 70, 30);
		add(getit);
		
		numOfPlayer = data.getTeam().getNumberOfPlayer();
		
		
		getit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(selectedIndex>=0 && selectedIndex<=length){
					
					if(numOfPlayer>22){
						
						//�������� ������ 22���� ������ ���Ÿ��ϰ� �����޼����߻�
						Massage ms = new Massage("���̻� ������ ������ �� �����ϴ�!!");
						
					}
					else{
						//�������� ���� 
						
						//�ݾ��� �����ϸ� �����޼��� ��
						int diff = data.getUser().getMoney()-data.getStore().getNewPlayerIndex(selectedIndex).getPrice();
						if(diff<0){
							Massage ms = new Massage("�ݾ��̺����մϴ�!!");
						}
						else{
							data.buyNewPlayer(selectedIndex);
							Massage ms = new Massage("���ԿϷ�!!");
							
						}
						
					}
					
				}
				else{
					
					//�����޼��� �߻�
					Massage ms = new Massage("�����������ϼ���!!");
					
				}
			}
			
		});
		
		
		//�ɷ�ġ�˻��κ�
		search = new JButton("�ɷ�ġ �˻�");
		search.setBounds(10, 255, 150, 20);
		add(search);
		
		//��
		s1 = new JLabel("Shoot");
		s1.setBounds(10, 290, 40, 25);
		add(s1);
	
		shoot = new JComboBox<String>(new String[] {"90�̻�","80�̻�","70�̻�","60�̻�","50�̻�"});
		shoot.setBounds(60, 290, 70, 25);
		add(shoot);
		
		//�帮��
		s2 = new JLabel("Dribble");
		s2.setBounds(10, 320, 50, 25);
		add(s2);
	
		dribble = new JComboBox<String>(new String[] {"90�̻�","80�̻�","70�̻�","60�̻�","50�̻�"});
		dribble.setBounds(60, 320, 70, 25);
		add(dribble);
		
		//�н�
		s3 = new JLabel("Pass");
		s3.setBounds(140, 290, 40, 25);
		add(s3);
	
		pass = new JComboBox<String>(new String[] {"90�̻�","80�̻�","70�̻�","60�̻�","50�̻�"});
		pass.setBounds(190, 290, 70, 25);
		add(pass);
		
		//���׹̳�
		s4 = new JLabel("Stamina");
		s4.setBounds(140, 320, 50, 25);
		add(s4);
	
		stamina = new JComboBox<String>(new String[] {"90�̻�","80�̻�","70�̻�","60�̻�","50�̻�"});
		stamina.setBounds(190, 320, 70, 25);
		add(stamina);
		
		//��Ŭ
		s5 = new JLabel("Tackle");
		s5.setBounds(270, 290, 40, 25);
		add(s5);
			
		tackle = new JComboBox<String>(new String[] {"90�̻�","80�̻�","70�̻�","60�̻�","50�̻�"});
		tackle.setBounds(320, 290, 70, 25);
		add(tackle);
				
		//��ƿ
		s6 = new JLabel("Steal");
		s6.setBounds(270, 320, 50, 25);
		add(s6);
			
		steal = new JComboBox<String>(new String[] {"90�̻�","80�̻�","70�̻�","60�̻�","50�̻�"});
		steal.setBounds(320, 320, 70, 25);
		add(steal);
		
		//���ǵ�
		s7 = new JLabel("Speed");
		s7.setBounds(410, 290, 40, 25);
		add(s7);
			
		speed = new JComboBox<String>(new String[] {"90�̻�","80�̻�","70�̻�","60�̻�","50�̻�"});
		speed.setBounds(460, 290, 70, 25);
		add(speed);
				
		//GK
		s8 = new JLabel("GK");
		s8.setBounds(410, 320, 50, 25);
		add(s8);
			
		gk = new JComboBox<String>(new String[] {"90�̻�","80�̻�","70�̻�","60�̻�","50�̻�"});
		gk.setBounds(460, 320, 70, 25);
		add(gk);
		
		
		
	}

}
