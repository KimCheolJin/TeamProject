package client;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.InetAddress;

import data.Load;
import GUI.MainMenu;
import GUI.SelectMatch;

public class SelectNetworkMatch extends SelectMatch {

	BufferedWriter bw;
	BufferedReader br;
	
	public SelectNetworkMatch(Image img, Load data, ClientK mainmenu) {
		super(img, data, mainmenu);
		this.bw = mainmenu.bw;
		this.br = mainmenu.br;
	}

	
	public void practicePerformed() {
		/**
		try {
			bw.write(InetAddress.getLocalHost().getHostAddress());
			bw.newLine();
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		**/
	}
	
	public void specialPerformed() { }
	
	
	
}
