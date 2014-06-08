package client;

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
		// 친구목록부분

		// data에서 유저의 친구목록 받아오면 보여줌
		// 접속한 친구 목록
		onAirList = new JList<String>();
		onAirS = new JScrollPane(onAirList);
		onAirS.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		onAirS.setBounds(10, 30, 200, 100);
		add(onAirS);
		JLabel onAirLabel = new JLabel("접속 중");
		onAirLabel.setBounds(85, 10, 60, 20);
		add(onAirLabel);
		// 시합중인 친구 목록
		onMatchList = new JList<String>();
		onMatchS = new JScrollPane(onMatchList);
		onMatchS.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		onMatchS.setBounds(10, 150, 200, 100);
		add(onMatchS);
		JLabel onMatchLabel = new JLabel("시합 중");
		onMatchLabel.setBounds(85, 130, 60, 20);
		add(onMatchLabel);
		// 비접속인 친구 목록
		offAirList = new JList<String>();
		offAirS = new JScrollPane(offAirList);
		offAirS.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		offAirS.setBounds(10, 270, 200, 100);
		add(offAirS);
		JLabel offAirLabel = new JLabel("비 접속");
		offAirLabel.setBounds(85, 250, 60, 20);
		add(offAirLabel);
	}
	
	private void setOther() {
		// 친구등록부분
				j2 = new JLabel("친구등록");
				j2.setBounds(230, 10, 70, 30);
				add(j2);

				inputName = new JTextField();
				inputName.setBounds(230, 50, 150, 30);
				add(inputName);

				getit = new JButton("등록");
				getit.setBounds(390, 50, 70, 30);
				add(getit);

				// 친구정보부분
				j3 = new JLabel("친구정보");
				j3.setBounds(230, 90, 70, 30);
				add(j3);

				// 리스트 더블클릭시 내용 바뀌도록 구현
				show = new JTextField("1승1패 23점");
				show.setBounds(230, 130, 300, 200);
				show.setEditable(false);
				add(show);

				// 버튼 부분
				game = new JButton("게임하기");
				game.setBounds(230, 335, 140, 35);
				add(game);
				
				trade = new JButton("거래하기");
				trade.setBounds(390, 335, 140, 35);
				add(trade);
	}
	
	public void run() {
		ArrayList<String> offAir;
		ArrayList<String> onAir;
		ArrayList<String> onMatch;
		while(true){
			try {
				Thread.sleep(500);
				offAir = new ArrayList<String>();
				onAir = new ArrayList<String>();
				onMatch = new ArrayList<String>();
				for(int i = 0; i < data.getFriend().getFriends().size(); i++){
					bw.write(data.getFriend().getFriends().get(i).getFriendId());
					bw.newLine();
					bw.flush();
					data.getFriend().getFriends().get(i).setAccept(br.read());
					if(data.getFriend().getFriends().get(i).getAccept() == 0)
						offAir.add(data.getFriend().getFriends().get(i).getFriendNick());
					else if(data.getFriend().getFriends().get(i).getAccept() == 1)
						onAir.add(data.getFriend().getFriends().get(i).getFriendNick());
					else if(data.getFriend().getFriends().get(i).getAccept() == 2)
						onMatch.add(data.getFriend().getFriends().get(i).getFriendNick());
				}
				offAirList.setListData(offAir.toArray(new String[offAir.size()]));
				onAirList.setListData(onAir.toArray(new String[onAir.size()]));
				onMatchList.setListData(onMatch.toArray(new String[onMatch.size()]));
			} catch (InterruptedException | IOException e) {
				e.printStackTrace();
			}
		}
	}

}
