package client;

import java.awt.Image;
import java.io.IOException;
import java.net.InetAddress;

import match.main.Client;
import match.main.Server;
import data.Load;
import GUI.SelectMatch;

public class SelectNetworkMatch extends SelectMatch {

	private ClientK mainmenu;

	public SelectNetworkMatch(Image img, Load data, ClientK mainmenu) {
		super(img, data, mainmenu);
		this.mainmenu = mainmenu;
	}

	public void networkMatch(boolean special) {
		try {
			mainmenu.exitFriend();
			
			mainmenu.bw.write("");
			mainmenu.bw.newLine();
			mainmenu.bw.flush();
			
			mainmenu.bw.write(InetAddress.getLocalHost().getHostAddress());
			mainmenu.bw.newLine();
			mainmenu.bw.flush();

			String matchIP = mainmenu.br.readLine();
			matchIP = mainmenu.br.readLine();
			System.out.println(matchIP);
			if (matchIP.equals("0"))
				new Server(data.getTeam());
			else
				new Client(data.getTeam(), matchIP);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
