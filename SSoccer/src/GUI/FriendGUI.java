package GUI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import data.Load;

public class FriendGUI extends JPanel implements Runnable {

	Load data;
	
	JList<String> offAirList;
	JList<String> onAirList;
	JList<String> onMatchList;
	
	JScrollPane offAirS;
	JScrollPane onAirS;
	JScrollPane onMatchS;

	JLabel j1;
	JLabel j2;
	JLabel j3;

	JTextField inputName;
	JTextField show;

	JButton getit;
	JButton game;
	JButton trade;
	
	BufferedWriter bw;
	BufferedReader br;
	
	public FriendGUI(Load data, BufferedWriter bw, BufferedReader br) {

		this.setSize(540, 380);
		this.setLayout(null);

		this.data = data;
		
		setList();
		setOther();
		
		this.bw = bw;
		this.br = br;

		new Thread(this).start();
	}

	private void setList() {
		// ģ����Ϻκ�
		j1 = new JLabel("ģ�����");
		j1.setBounds(80, 10, 100, 30);
		add(j1);

		// data���� ������ ģ����� �޾ƿ��� ������
		// ������ ģ�� ���
		onAirList = new JList<String>();
		onAirS = new JScrollPane(onAirList);
		onAirS.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		onAirS.setBounds(10, 50, 200, 90);
		add(onAirS);
		// �������� ģ�� ���
		onMatchList = new JList<String>();
		onMatchS = new JScrollPane(onMatchList);
		onMatchS.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		onMatchS.setBounds(10, 155, 200, 90);
		add(onMatchS);
		// �������� ģ�� ���
		offAirList = new JList<String>();
		offAirS = new JScrollPane(offAirList);
		offAirS.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		offAirS.setBounds(10, 260, 200, 90);
		add(offAirS);
	}
	
	private void setOther() {
		// ģ����Ϻκ�
				j2 = new JLabel("ģ�����");
				j2.setBounds(230, 10, 70, 30);
				add(j2);

				inputName = new JTextField();
				inputName.setBounds(230, 50, 150, 30);
				add(inputName);

				getit = new JButton("���");
				getit.setBounds(390, 50, 70, 30);
				add(getit);

				// ģ�������κ�
				j3 = new JLabel("ģ������");
				j3.setBounds(230, 90, 70, 30);
				add(j3);

				// ����Ʈ ����Ŭ���� ���� �ٲ�� ����
				show = new JTextField("1��1�� 23��");
				show.setBounds(230, 130, 300, 200);
				show.setEditable(false);
				add(show);

				// ��ư �κ�
				game = new JButton("�����ϱ�");
				game.setBounds(270, 335, 100, 20);
				add(game);

				trade = new JButton("�ŷ��ϱ�");
				trade.setBounds(380, 335, 100, 20);
				add(trade);
	}
	
	public void run() {
		String[] temp;
		ArrayList<String> offAir;
		ArrayList<String> onAir;
		ArrayList<String> onMatch;
		while(true){
			try {
				Thread.sleep(1000);
				offAir = new ArrayList<String>();
				onAir = new ArrayList<String>();
				onMatch = new ArrayList<String>();
				for(int i = 0; i < data.getFriend().getFriendList().size(); i++){
					bw.write(data.getFriend().getFriendList().get(i));
					data.getFriend().getAcceptList().set(i, br.read());
					if(data.getFriend().getAcceptList().get(i) == 0)
						offAir.add(data.getFriend().getFriendNickList().get(i));
					else if(data.getFriend().getAcceptList().get(i) == 1)
						onAir.add(data.getFriend().getFriendNickList().get(i));
					else if(data.getFriend().getAcceptList().get(i) == 2)
						onMatch.add(data.getFriend().getFriendNickList().get(i));
				}
				temp = offAir.toArray(new String[offAir.size()]);
				offAirList.setListData(temp);
				temp = onAir.toArray(new String[onAir.size()]);
				onAirList.setListData(temp);
				temp = onMatch.toArray(new String[onMatch.size()]);
				onMatchList.setListData(temp);
			} catch (InterruptedException | IOException e) {
				e.printStackTrace();
			}
		}
	}

}
