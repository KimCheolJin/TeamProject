package match.main;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import client.ClientK;
import data.Team;
import match.data.MTeam;
import match.graphic.EditStrategy;
import match.graphic.GraphicMain;

public class Server {
	
	public Server(Team team, ClientK clientK){
		try {
			MTeam home = new MTeam(team);
			
			System.out.println("���� �⵿, Ŭ���̾�Ʈ ������ ��ٸ��ϴ�.");
			ServerSocket ss = new ServerSocket(1111);
			Socket so = ss.accept();
			System.out.println("Ŭ���̾�Ʈ ���� �Ϸ�");
			
			BufferedOutputStream bos = new BufferedOutputStream(so.getOutputStream());
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			System.out.println("Ȩ�� ���� �߽� ����");
			
			oos.writeObject(team);
			oos.flush();
			oos.reset();
			System.out.println("Ȩ�� ���� �߽� �Ϸ�");
			
			BufferedInputStream bis = new BufferedInputStream(so.getInputStream());
			ObjectInputStream ois = new ObjectInputStream(bis);
			System.out.println("������� ���� ���� ����");
			MTeam away = new MTeam((Team) ois.readObject());
			System.out.println("������� ���� ���� �Ϸ�");
			
			Socket so2 = ss.accept();
			BufferedOutputStream bosm = new BufferedOutputStream(so2.getOutputStream());
			ObjectOutputStream oosm = new ObjectOutputStream(bosm);
			oosm.flush();
			BufferedInputStream bism = new BufferedInputStream(so2.getInputStream());
			ObjectInputStream oism = new ObjectInputStream(bism);
			
			GraphicMain gm = new GraphicMain(home, away);
			new Thread(new ServerThread(gm, oos, clientK)).start();
			new Thread(new StrategyThread(oism, away, gm)).start();
			gm.editStrategy = new EditStrategy(home, gm.homeInfo, oosm);
			ss.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

}
