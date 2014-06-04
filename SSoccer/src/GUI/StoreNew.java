package GUI;

import java.awt.*;

import javax.swing.*;

import data.Load;

//�űԿ��� �κ�
public class StoreNew extends JPanel {
	
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
	
	
	public StoreNew(Load data){
		
		this.setSize(540,380);
		this.setLayout(null);
		
		//�����
		String[] ss={"1","2","3","4","5","6","7","8","9","10"};
		
		//������Ϻκ�
		list = new JList<String>(ss);
		//list.setBounds(10, 10, 520, 150);
		sp = new JScrollPane(list);
		//ss ��� data.getStore().getnp(); 
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
		
		//���Թ�ư
		getit = new JButton("����");
		getit.setBounds(460, 220, 70, 30);
		add(getit);
		
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
