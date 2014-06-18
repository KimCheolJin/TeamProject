package client;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import match.main.Client;
import match.main.Server;
import DBCENTER.DBdata;
import GUI.MainMenu;
import GUI.SetStrategy;
import GUI.StoreNew;
import GUI.StoreOld;
import GUI.StoreUp;
import GUI.Training;

public class ClientK extends MainMenu implements Runnable {

	int port = 1112;
	String IP = "127.0.0.1";
	
	Socket socket;
	public BufferedWriter bw1;
	public BufferedReader br1;
	public BufferedWriter bw2;
	public BufferedReader br2;

	private String kindOfMatch;
	public String friendID;
	public String myID;
	
	DBdata dbd = new DBdata();
	
	
	public ClientK(String id) {
		super(id);
		myID = id;
		setting();
		fg = new FriendGUI(data, this);
		tp.add("친구 목록", fg);
		//startMatch();
		new Thread(this).start();
	}
	
	protected void setCompo(String id){		
		tp = new JTabbedPane();
		sm = new SelectNetworkMatch(data, this);
		ss = new SetStrategy(data.getTeam(), id);
		tr = new Training(data.getTeam(), id);
		sn = new StoreNew(data);
		so = new StoreOld(data);
		su = new StoreUp(data);
	}
	
	private void setting() {
		try {
			socket = new Socket(IP, port);
			bw1 = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			br1 = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			socket = new Socket(IP, port);
			bw2 = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			br2 = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			bw1.write(data.getUser().ID);
			bw1.newLine();
			bw1.flush();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void run(){
		try {
			kindOfMatch = br2.readLine();
			String matchIP = br2.readLine();
			if(kindOfMatch.equals("friend")){
				friendID = br2.readLine();
				new Client(data.getTeam(), matchIP, this);	
			}
			else {
				if (matchIP.equals("0"))
					new Server(data.getTeam(), this);
				else
					new Client(data.getTeam(), matchIP, this);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		setVisible(false);
	}
	
	public void pause() {
		tp.setVisible(false);
	}

	public void restartClient(int score1, int score2) {
		setVisible(true);
		tp.setVisible(true);
		try {
			bw2.write("reaccept");
			bw2.newLine();
			bw2.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(kindOfMatch == "friend") {
			
			dbd.putResult(myID, friendID, score1, score2);
			
		} else if(kindOfMatch == "special") {
			
			dbd.putResult(myID, score1, score2);
			
		} 
		kindOfMatch = null;
		new Thread(this).start();
	}

	public static void main(String args[]){
		String id = "asd";
		new ClientK(id);
	}

}
