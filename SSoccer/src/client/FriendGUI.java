package client;

import java.awt.Font;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import data.Load;
import friend.FriendsInfo;

public class FriendGUI extends JPanel implements Runnable,
		ListSelectionListener {

	Load data;

	JList<String> offAirList;
	JList<String> onAirList;
	JList<String> onMatchList;

	ArrayList<String> onAirID = new ArrayList<String>();
	ArrayList<String> onMatchID = new ArrayList<String>();
	ArrayList<String> offAirID = new ArrayList<String>();	
	
	JTextField inputName;
	JTextArea show;

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

		// data���� ������ ģ����� �޾ƿ��� ������
		// ������ ģ�� ���
		onAirList = new JList<String>();
		onAirList.addListSelectionListener(this);
		JScrollPane onAirS = new JScrollPane(onAirList);
		onAirS.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		onAirS.setBounds(10, 30, 200, 100);
		add(onAirS);
		JLabel onAirLabel = new JLabel("���� ��");
		onAirLabel.setBounds(85, 10, 60, 20);

		add(onAirLabel);
		// �������� ģ�� ���
		onMatchList = new JList<String>();
		onMatchList.addListSelectionListener(this);
		JScrollPane onMatchS = new JScrollPane(onMatchList);
		onMatchS.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		onMatchS.setBounds(10, 150, 200, 100);
		add(onMatchS);
		JLabel onMatchLabel = new JLabel("���� ��");
		onMatchLabel.setBounds(85, 130, 60, 20);
		add(onMatchLabel);

		// �������� ģ�� ���
		offAirList = new JList<String>();
		offAirList.addListSelectionListener(this);
		JScrollPane offAirS = new JScrollPane(offAirList);
		offAirS.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		offAirS.setBounds(10, 270, 200, 100);
		add(offAirS);
		JLabel offAirLabel = new JLabel("�� ����");
		offAirLabel.setBounds(85, 250, 60, 20);
		add(offAirLabel);
	}

	private void setOther() {
		// ģ����Ϻκ�

		JLabel j2 = new JLabel("ģ�����");
		j2.setBounds(230, 10, 70, 30);
		add(j2);

		inputName = new JTextField();
		inputName.setBounds(230, 50, 150, 30);
		add(inputName);

		getit = new JButton("���");
		getit.setBounds(390, 50, 70, 30);
		add(getit);

		// ģ�������κ�
		JLabel j3 = new JLabel("ģ������");
		j3.setBounds(230, 90, 70, 30);
		add(j3);

		// ģ�� ���� ǥ��
		show = new JTextArea();
		show.setFont(new Font(show.getFont().getName(), 0, 17));
		show.setBounds(230, 130, 300, 180);
		show.setEditable(false);
		add(show);

		// ��ư �κ�
		game = new JButton("�����ϱ�");
		game.setBounds(230, 330, 140, 35);
		game.setVisible(false);
		add(game);

		trade = new JButton("�ŷ��ϱ�");
		trade.setBounds(390, 330, 140, 35);
		trade.setVisible(false);
		add(trade);
	}

	public void run() {
		ArrayList<String> onAir = new ArrayList<String>();
		ArrayList<String> onMatch = new ArrayList<String>();
		ArrayList<String> offAir = new ArrayList<String>();
		
		while (true) {
			try {
				Thread.sleep(500);
				onAir.clear();
				onAirID.clear();
				onMatch.clear();
				onMatchID.clear();
				offAir.clear();
				offAirID.clear();
				for (int i = 0; i < data.getFriend().getFriends().size(); i++) {
					bw.write(data.getFriend().getFriends().get(i).getFriendId());
					bw.newLine();
					bw.flush();
					data.getFriend().getFriends().get(i).setAccept(br.read());
					if (data.getFriend().getFriends().get(i).getAccept() == 1) {
						onAir.add(data.getFriend().getFriends().get(i).getFriendNick());
						onAirID.add(data.getFriend().getFriends().get(i).getFriendId());
					} else if (data.getFriend().getFriends().get(i).getAccept() == 2) {
						onMatch.add(data.getFriend().getFriends().get(i).getFriendNick());
						onMatchID.add(data.getFriend().getFriends().get(i).getFriendId());						
					} else if (data.getFriend().getFriends().get(i).getAccept() == 0) {
						offAir.add(data.getFriend().getFriends().get(i).getFriendNick());
						offAirID.add(data.getFriend().getFriends().get(i).getFriendId());
					}
				}
				offAirList.setListData(offAir.toArray(new String[offAir.size()]));
				onAirList.setListData(onAir.toArray(new String[onAir.size()]));
				onMatchList.setListData(onMatch.toArray(new String[onMatch.size()]));
			} catch (InterruptedException | IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void valueChanged(ListSelectionEvent e) {
		String ID = "";
		int index = -1;
		if (e.getSource() == onAirList) {
			index = onAirList.getSelectedIndex();
			if(index == -1) return;
			ID = onAirID.get(index);
			game.setVisible(true);
			trade.setVisible(true);
		} else if (e.getSource() == onMatchList) {
			index = onAirList.getSelectedIndex();
			if(index == -1) return;
			ID = onMatchID.get(index);
			game.setVisible(false);
			trade.setVisible(false);
		} else {
			index = offAirList.getSelectedIndex();
			if(index == -1) return;
			ID = offAirID.get(index);
			game.setVisible(false);
			trade.setVisible(false);
		}

		for(int i = 0; i < data.getFriend().getFriends().size(); i++)
			if(data.getFriend().getFriends().get(i).getFriendId().equals(ID)) {
				FriendsInfo selectedFriend = data.getFriend().getFriends().get(i);
				show.setText("");
				show.append("�̸� : " + selectedFriend.getFriendNick() + "\n\n");
				show.append("ID : " + selectedFriend.getFriendId() + "\n");
				show.append(selectedFriend.getRecord() + "\n");
				show.append(selectedFriend.getScore() + "\n" );
				show.append(selectedFriend.getScore1() + "\n" );
				show.append(selectedFriend.getScore2() + "\n" );
				break;
			}
	}

}
