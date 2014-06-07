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
	Socket so[] = new Socket[64];
	BufferedWriter bw[] = new BufferedWriter[64];
	BufferedReader br[] = new BufferedReader[64];
	int accept[] = new int[65]; // 0:厚立加, 1:立加, 2:矫钦吝
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
				so[i] = ss.accept();
				new Thread(new ServerThread(so[i], i)).start();
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
	
	class ServerThread implements Runnable {
		int i;
		
		ServerThread(Socket so, int i){
			this.i = i;
			try{
				bw[i] = new BufferedWriter(new OutputStreamWriter(
						so.getOutputStream()));
				br[i] = new BufferedReader(new InputStreamReader(
						so.getInputStream()));
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
		
		public void run(){	
			try {
				ID[i] = br[i].readLine();
				int j;
				while (true) {
					String line = br[i].readLine();
					if(line.equals("")){
						matchIP = br[i].readLine();
						accept[i] = 2;
					} else {
						for(j = 0; j < 64; j++){
							if(line.equals(ID[j])) break;
						}
						bw[i].write(accept[j]);
						bw[i].flush();
					}
				}
			} catch (IOException e) {
				accept[i] = 0;
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
			int player1 = -1, player2 = -1;
			for(int i = 0; i < 64; i++){
				if(accept[i] == 1) {
					jta.append(ID[i] + "\n");
				} else if(accept[i] == 2) {
					if(player1 == -1){
						player1 = i;
					} else player2 = i;
				}
			}
			
			if(player1 != -1 && player2 != -1) {
				try {
					bw[player1].newLine();
					bw[player1].write("0");
					bw[player1].newLine();
					bw[player1].flush();
					bw[player2].newLine();
					bw[player2].write(matchIP);
					bw[player2].newLine();
					bw[player2].flush();
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

