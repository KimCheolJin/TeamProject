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
import GUI.MainMenu;
import GUI.SetStrategy;
import GUI.StoreNew;
import GUI.StoreOld;
import GUI.StoreUp;
import GUI.Training;

public class ClientK extends MainMenu {

	int port = 1112;
	String IP = "127.0.0.1";
	
	Socket socket;
	public BufferedWriter bw1;
	public BufferedReader br1;
	public BufferedWriter bw2;
	public BufferedReader br2;

	
	public ClientK(String id) {
		super(id);
		setting();
		fg = new FriendGUI(data, this);
		tp.add("친구 목록", fg);
		startMatch();
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
	
	private void startMatch(){
		try {
			String kindOfMatch = br2.readLine();
			String matchIP = br2.readLine();
			if(kindOfMatch.equals("friend")){
				new Client(data.getTeam(), matchIP, true);	
			}
			else {
				boolean isReturn = false;
				if(kindOfMatch.equals("special"))
					isReturn = true;
				System.out.println(isReturn);
				if (matchIP.equals("0"))
					new Server(data.getTeam(), isReturn);
				else
					new Client(data.getTeam(), matchIP, isReturn);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		dispose();
	}
	
	public void pause() {
		remove(tp);
	}

	public static void main(String args[]){
		String id = "asd";
		new ClientK(id);
	}

}
