package server;



import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ServerK extends JFrame implements Runnable {

	private static final long serialVersionUID = 1L;
	public JTextArea jta = new JTextArea();
	public JScrollPane jsp = new JScrollPane(jta);

	ServerSocket ss = null;
	Socket so;
	BufferedWriter bw1[] = new BufferedWriter[64];
	BufferedReader br1[] = new BufferedReader[64];
	BufferedWriter bw2[] = new BufferedWriter[64];
	BufferedReader br2[] = new BufferedReader[64];
	
	int accept[] = new int[65]; // 0:비접속, 1:접속, 2:일반시합 찾는중, 3:랭크시합 찾는중, 4:시합중
	String ID[] = new String[64];
	
	String matchIP;
	int port = 1112;
	
	public ServerK(String title) {
		setTitle(title);
		setBounds(200, 200, 300, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		jta.setEditable(false);

		add(jsp, BorderLayout.CENTER);
		setVisible(true);

		try {
			ss = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		new Thread(this).start();
		while(true)
			accepting();
	}

	public void accepting(){
		try {
			int i = 0;
			for(i = 0; i < 64; i++){
				if(accept[i] == 0) break;
			}
			if(i < 64){
				so = ss.accept();
				new Thread(new FriendThread(so, i)).start();
				so = ss.accept();
				new Thread(new MatchThread(so, i)).start();
				accept[i] = 1;
			} else {
				Thread.sleep(1000);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	class FriendThread implements Runnable {
		
		private int i;
		
		FriendThread(Socket so, int i){
			this.i = i;
			try{
				br1[i] = new BufferedReader(new InputStreamReader(
						so.getInputStream()));
				bw1[i] = new BufferedWriter(new OutputStreamWriter(
						so.getOutputStream()));
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
		
		public void run(){	
			try {
				ID[i] = br1[i].readLine();
				int j;
				String line;
				while (true) {						
					line = br1[i].readLine();
					for(j = 0; j < 64; j++)
						if(line.equals(ID[j])) break;
					bw1[i].write(accept[j]);
					bw1[i].flush();
				}
			} catch (IOException e) {
				accept[i] = 0;
				ID[i] = null;
			}
		}
	}

	class MatchThread implements Runnable {

		private int i;

		public MatchThread(Socket so, int i) {
			this.i = i;
			try{
				br2[i] = new BufferedReader(new InputStreamReader(
						so.getInputStream()));
				bw2[i] = new BufferedWriter(new OutputStreamWriter(
						so.getOutputStream()));
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}

		public void run(){	
			while(true) {
				try {
					String line = br2[i].readLine();
					if(line.equals("practice")){
						matchIP = br2[i].readLine();
						accept[i] = 2;
					} else if(line.equals("AImode")){
						accept[i] = 4;
					} else if(line.equals("special")){
						matchIP = br2[i].readLine();
						accept[i] = 3;
					} else if(line.equals("friend")) {
						line = br2[i].readLine();	//ID를 전송받음.
						int j;
						
						for(j = 0; j < 64; j++)	//ID에 맞는 친구를 검색.
							if(line.equals(ID[j])) break;
						
						line = br2[i].readLine();	//IP를 전송받음
						bw2[j].write("friend");	//친구에게 IP 전송
						bw2[j].newLine();
						bw2[j].write(line);	//친구에게 IP 전송
						bw2[j].newLine();
						bw2[j].flush();
						accept[i] = 4;
				    } else if(line.equals("reaccept")) {
				    	accept[i] = 1;
				    }
				} catch (IOException e) {
					accept[i] = 0;
					ID[i] = null;
				}
			}
		}
	}

	public void run() {
		while(true) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			jta.setText("");
			int player1 = -1, player2 = -1, player3 = -1, player4 = -1;
			for(int i = 0; i < 64; i++){
				if(accept[i] != 0) {
					jta.append(accept[i] + " " + ID[i] + "\n");
					if(accept[i] == 2) {
						if(player1 == -1){
							player1 = i;
						} else player2 = i;
					} else if(accept[i] == 3){
						if(player3 == -1){
							player3 = i;
						} else player4 = i;
						
					}
				}
			}
			
			if(player1 != -1 && player2 != -1) {
				try {
					bw2[player1].write("practice");
					bw2[player1].newLine();
					bw2[player1].write("0");
					bw2[player1].newLine();
					bw2[player1].flush();
					accept[player1] = 4;
					
					bw2[player2].write("practice");
					bw2[player2].newLine();
					bw2[player2].write(matchIP);
					bw2[player2].newLine();
					bw2[player2].flush();
					accept[player2] = 4;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if(player3 != -1 && player4 != -1) {
				try {
					bw2[player3].write("special");
					bw2[player3].newLine();
					bw2[player3].write("0");
					bw2[player3].newLine();
					bw2[player3].flush();
					accept[player3] = 4;
					
					bw2[player4].write("special");
					bw2[player4].newLine();
					bw2[player4].write(matchIP);
					bw2[player4].newLine();
					bw2[player4].flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		new ServerK("MultiServer");
	}

}

