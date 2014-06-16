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
			
			System.out.println("서버 기동, 클라이언트 접속을 기다립니다.");
			ServerSocket ss = new ServerSocket(1111);
			Socket so = ss.accept();
			System.out.println("클라이언트 접속 완료");
			
			BufferedOutputStream bos = new BufferedOutputStream(so.getOutputStream());
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			System.out.println("홈팀 정보 발신 시작");
			
			oos.writeObject(team);
			oos.flush();
			oos.reset();
			System.out.println("홈팀 정보 발신 완료");
			
			BufferedInputStream bis = new BufferedInputStream(so.getInputStream());
			ObjectInputStream ois = new ObjectInputStream(bis);
			System.out.println("어웨이팀 정보 수신 시작");
			MTeam away = new MTeam((Team) ois.readObject());
			System.out.println("어웨이팀 정보 수신 완료");
			
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
