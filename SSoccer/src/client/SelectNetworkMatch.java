package client;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.InetAddress;

import match.main.Client;
import match.main.Server;
import data.Load;
import GUI.MainMenu;
import GUI.SelectMatch;

public class SelectNetworkMatch extends SelectMatch {

	private BufferedWriter bw;
	private BufferedReader br;
	private ClientK mainmenu;

	public SelectNetworkMatch(Image img, Load data, ClientK mainmenu) {
		super(img, data, mainmenu);
		this.mainmenu = mainmenu;
		this.bw = mainmenu.bw;
		this.br = mainmenu.br;
	}

	public void practicePerformed() {
		try {
			mainmenu.fg = null;
			
			bw.write("");
			bw.newLine();
			bw.flush();

			bw.write(InetAddress.getLocalHost().getHostAddress());
			bw.newLine();
			bw.flush();

			String matchIP = br.readLine();
			if (matchIP.equals(InetAddress.getLocalHost().getHostAddress()))
				new Server(data.getTeam());
			else
				new Client(data.getTeam(), matchIP);
			mainmenu.dispose();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void specialPerformed() {
	}

}
