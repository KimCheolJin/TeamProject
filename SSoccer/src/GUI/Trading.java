package GUI;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class Trading extends JPanel {

	JList jlist = new JList();
	JTextField status[] = new JTextField[8];
	
	public Trading(){
		setLayout(null);
		setSize(540, 380);
		
		JScrollPane jsp = new JScrollPane(jlist);
		jsp.setBounds(20, 20, 500, 240);
		add(jsp);		
		
		setSearch();
		
		JButton search = new JButton("�˻�");
		search.setBounds(440, 285, 80, 30);
		add(search);
		
		JButton scout = new JButton("����");
		scout.setBounds(440, 325, 80, 30);
		add(scout);
	}
	
	private void setSearch(){
		JLabel jlabel[] = new JLabel[8];
		for(int i = 0; i < 8; i++) {
			jlabel[i] = new JLabel();
			jlabel[i].setBounds(40 + i / 4 *200, 280 + i % 4 * 20, 100, 20);
			add(jlabel[i]);
			status[i] = new JTextField();
			status[i].setBounds(120 + i / 4 * 200, 280 + i % 4 * 20, 100, 20);
			add(status[i]);
		}
		jlabel[0].setText("��");
		jlabel[1].setText("�н�");
		jlabel[2].setText("�帮��");
		jlabel[3].setText("���ǵ�");
		jlabel[4].setText("��Ű��");
		jlabel[5].setText("����");
		jlabel[6].setText("��Ŭ");
		jlabel[7].setText("ü��");
	}



}
