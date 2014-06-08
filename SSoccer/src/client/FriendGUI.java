package client;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
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

import login.Massage;
import match.main.Server;
import data.Load;
import friend.FriendsInfo;
import DBCENTER.DBdata;
import DBCENTER.DBLogin;

public class FriendGUI extends JPanel implements Runnable,
		ListSelectionListener {

	// DB이용
	DBdata dbd = new DBdata();
	DBLogin dblogin = new DBLogin();

	private Load data;

	private JList<String> offAirList;
	private JList<String> onAirList;
	private JList<String> onMatchList;

	private ArrayList<String> onAirID = new ArrayList<String>();
	private ArrayList<String> onMatchID = new ArrayList<String>();
	private ArrayList<String> offAirID = new ArrayList<String>();

	private JTextField inputName;
	private JTextArea show;

	private JButton getit;
	private JButton game;
	private JButton trade;

	private BufferedWriter bw;
	private BufferedReader br;
	FriendsInfo selectedFriend;

	public Thread friendThread;
	private ClientK clientK;
	
	public FriendGUI(Load data, ClientK clientK) {

		setSize(540, 380);
		setLayout(null);

		this.data = data;
		this.clientK = clientK;

		setList();
		setOther(data.getUser().getUserID());

		this.bw = clientK.bw1;
		this.br = clientK.br1;

		friendThread = new Thread(this);
		friendThread.start();
	}

	private void setList() {
		// 친구목록부분

		// data에서 유저의 친구목록 받아오면 보여줌
		// 접속한 친구 목록
		onAirList = new JList<String>();
		onAirList.addListSelectionListener(this);
		JScrollPane onAirS = new JScrollPane(onAirList);
		onAirS.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		onAirS.setBounds(10, 30, 200, 100);
		add(onAirS);
		JLabel onAirLabel = new JLabel("접속 중");
		onAirLabel.setBounds(85, 10, 60, 20);

		add(onAirLabel);
		// 시합중인 친구 목록
		onMatchList = new JList<String>();
		onMatchList.addListSelectionListener(this);
		JScrollPane onMatchS = new JScrollPane(onMatchList);
		onMatchS.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		onMatchS.setBounds(10, 150, 200, 100);
		add(onMatchS);
		JLabel onMatchLabel = new JLabel("시합 중");
		onMatchLabel.setBounds(85, 130, 60, 20);
		add(onMatchLabel);

		// 비접속인 친구 목록
		offAirList = new JList<String>();
		offAirList.addListSelectionListener(this);
		JScrollPane offAirS = new JScrollPane(offAirList);
		offAirS.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		offAirS.setBounds(10, 270, 200, 100);
		add(offAirS);
		JLabel offAirLabel = new JLabel("비 접속");
		offAirLabel.setBounds(85, 250, 60, 20);
		add(offAirLabel);
	}

	private void setOther(final String id) {
		// 친구등록부분

		JLabel j2 = new JLabel("친구등록");
		j2.setBounds(230, 10, 70, 30);
		add(j2);

		inputName = new JTextField();
		inputName.setBounds(230, 50, 150, 30);
		add(inputName);

		getit = new JButton("등록");
		getit.setBounds(390, 50, 70, 30);
		add(getit);
		getit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// 친구등록시 등록한 친구이름 받음
				String temp = inputName.getText();

				if (dblogin.serchSameID(temp) == 0) {

					// uuser테이블에 사용자가 없는경우 오류메세지 띄움
					Massage ms = new Massage("그런친구는없습니다!!");
				} else {

					// uuser테이블에 사용자 있음 친구등록 실행
					dbd.putFriends(id, temp);
					Massage ms = new Massage("친구등록완료!!");
				}

			}
		});
		// 친구정보부분
		JLabel j3 = new JLabel("친구정보");
		j3.setBounds(230, 90, 70, 30);
		add(j3);

		// 친구 정보 표시
		show = new JTextArea();
		show.setFont(new Font(show.getFont().getName(), 0, 17));
		show.setBounds(230, 130, 300, 180);
		show.setEditable(false);
		add(show);

		// 버튼 부분
		game = new JButton("게임하기");
		game.setBounds(230, 330, 140, 35);
		game.setVisible(false);
		game.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					clientK.bw2.write("friend");
					clientK.bw2.newLine();
					clientK.bw2.flush();

					clientK.bw2.write(selectedFriend.getFriendId());
					clientK.bw2.newLine();
					clientK.bw2.flush();
					
					clientK.bw2.write(InetAddress.getLocalHost().getHostAddress());
					clientK.bw2.newLine();
					clientK.bw2.flush();
					
					new Server(data.getTeam());
					
					clientK.dispose();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		add(game);

		trade = new JButton("거래하기");
		trade.setBounds(390, 330, 140, 35);
		trade.setVisible(false);
		add(trade);
	}

	public void run() {
		ArrayList<String> onAir = new ArrayList<String>();
		ArrayList<String> onMatch = new ArrayList<String>();
		ArrayList<String> offAir = new ArrayList<String>();
		int accept;
		try {
			do {
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

					accept = br.read();
					if (accept == 1) {
						onAir.add(data.getFriend().getFriends().get(i)
								.getFriendNick());
						onAirID.add(data.getFriend().getFriends().get(i)
								.getFriendId());
					} else if (accept == 2 || accept == 3) {
						onMatch.add(data.getFriend().getFriends().get(i)
								.getFriendNick());
						onMatchID.add(data.getFriend().getFriends().get(i)
								.getFriendId());
					} else if (accept == 0) {
						offAir.add(data.getFriend().getFriends().get(i)
								.getFriendNick());
						offAirID.add(data.getFriend().getFriends().get(i)
								.getFriendId());
					}
				}
				offAirList
						.setListData(offAir.toArray(new String[offAir.size()]));
				onAirList.setListData(onAir.toArray(new String[onAir.size()]));
				onMatchList.setListData(onMatch.toArray(new String[onMatch
						.size()]));

				Thread.sleep(500);
			} while (true);

		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
	}

	public void valueChanged(ListSelectionEvent e) {
		String ID = "";
		int index = -1;
		if (e.getSource() == onAirList) {
			index = onAirList.getSelectedIndex();
			if (index == -1)
				return;
			ID = onAirID.get(index);
			game.setVisible(true);
			trade.setVisible(true);
		} else if (e.getSource() == onMatchList) {
			index = onMatchList.getSelectedIndex();
			if (index == -1)
				return;
			ID = onMatchID.get(index);
			game.setVisible(false);
			trade.setVisible(false);
		} else {
			index = offAirList.getSelectedIndex();
			if (index == -1)
				return;
			ID = offAirID.get(index);
			game.setVisible(false);
			trade.setVisible(false);
		}

		for (int i = 0; i < data.getFriend().getFriends().size(); i++)
			if (data.getFriend().getFriends().get(i).getFriendId().equals(ID)) {
				selectedFriend = data.getFriend().getFriends().get(i);
				show.setText("");
				show.append("이름 : " + selectedFriend.getFriendNick() + "\n\n");
				show.append("ID : " + selectedFriend.getFriendId() + "\n");
				show.append(selectedFriend.getRecord() + "\n");
				show.append(selectedFriend.getScore() + "\n");
				show.append(selectedFriend.getScore1() + "\n");
				show.append(selectedFriend.getScore2() + "\n");
				break;
			}
	}

}
