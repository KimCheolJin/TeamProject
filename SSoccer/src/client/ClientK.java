package client;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;

import match.main.Client;
import match.main.Server;
import data.Load;
import GUI.MainMenu;
import GUI.SelectMatch;
import GUI.SetStrategy;
import GUI.StoreNew;
import GUI.StoreOld;
import GUI.StoreUp;
import GUI.Training;

public class ClientK extends MainMenu {

	int port = 1112;
	String IP = "127.0.0.1";
	
	Socket socket;
	public BufferedWriter bw;
	public BufferedReader br;
	
	String name;
	
	public ClientK(String id) {
		super(id);
		setting(data);
		fg = new FriendGUI(data, bw, br);
		tp.add("친구 목록", fg);
	}
	
	protected void setCompo(){		
		Image img = new ImageIcon("res/menu/Background.png").getImage();
		tp = new JTabbedPane();
		sm = new SelectNetworkMatch(img, data, this);
		ss = new SetStrategy(data.getTeam());
		tr = new Training(data.getTeam());
		sn = new StoreNew(data);
		so = new StoreOld(data);
		su = new StoreUp(data);
	}
	
	public void setting(Load data) {
		try {
			socket = new Socket(IP, port);
			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			bw.write(data.getUser().ID);
			bw.newLine();
			bw.flush();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void main(String args[]){
		String id = "asd";
		new ClientK(id);
	}
	
	public void exitFriend() {
		tp.remove(fg);
		fg = null;
	}

}
