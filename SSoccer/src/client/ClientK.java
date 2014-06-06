package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

import match.main.Client;
import match.main.Server;
import data.Load;
import GUI.FriendGUI;
import GUI.MainMenu;

public class ClientK extends MainMenu implements Runnable {

	int port = 1111;
	String IP = "127.0.0.1";
	
	Socket so;
	public BufferedWriter bw;
	public BufferedReader br;
	
	String name;
	
	public ClientK(String id) {
		super(id);
		sm = new SelectNetworkMatch(img, data, this);
		setting(data);
		fg = new FriendGUI(data, bw, br);
		tp.add("친구 목록", fg);
		new Thread(this).start();
	}
	
	public void setting(Load data) {
		try {
			so = new Socket(IP, port);
			bw = new BufferedWriter(new OutputStreamWriter(so.getOutputStream()));
			br = new BufferedReader(new InputStreamReader(so.getInputStream()));

			bw.write(data.getUser().ID);
			bw.newLine();
			bw.flush();
		} catch (IOException e) {
			
		}
	}

	public void run() {
		/**
		try {
			while (true) {
				String matchIP = br.readLine();
				if(matchIP.equals(InetAddress.getLocalHost().getHostAddress()))
					new Server(data.getTeam());
				else
					new Client(data.getTeam(), matchIP);
			}
		} catch (IOException e) {

		}
		**/
	}
	
	public static void main(String args[]){
		String id = "";
		new ClientK(id);
	}

}
