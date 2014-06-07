package DBCENTER;

import java.sql.Connection;
import java.sql.PreparedStatement;

import data.Player;

public class DBdata {
	
	//선수 구매시 DB(사용자_PLAYER)로 정보 보냄
	public void putNewPlayer(Player p, String id){
		
		Connection conn = DBconn.getConnection();

		if (conn == null) {
			System.out.println("데이터베이스 연결 실패!!");
			System.exit(0);
		} else {
			System.out.println("데이터베이스 연결 성공!!");

			try {
				
				String sql = "INSERT INTO "+id+"_PLAYER(pnum,pname,shoot,dribble,pass,"
						+ "stamina,tackle,steal,speed,gk,exp)"
						+ "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
				
				PreparedStatement psmt = conn.prepareStatement(sql);
				
				psmt.setInt(1, p.primaryNum);
				psmt.setString(2, p.name);
				psmt.setInt(3, p.shoot);
				psmt.setInt(4, p.dribble);
				psmt.setInt(5, p.pass);
				psmt.setInt(6, p.stamina);
				psmt.setInt(7, p.tackle);
				psmt.setInt(8, p.steal);
				psmt.setInt(9, p.speed);
				psmt.setInt(10, p.gk);
				psmt.setInt(11, p.exp);
				
				psmt.executeUpdate();
				
				psmt.close();
				
			}
			catch(Exception e){
				System.out.println(e.toString());
			}
		}
		DBconn.close();
	}
	
    public void putOldPlayer(Player p, String id){
		
		Connection conn = DBconn.getConnection();

		if (conn == null) {
			System.out.println("데이터베이스 연결 실패!!");
			System.exit(0);
		} else {
			System.out.println("데이터베이스 연결 성공!!");

			try {
				
				//old선수 구매시 new선수 구매와 마찬가지로 사용자_PLAYER로 정보보냄
				String sql = "INSERT INTO "+id+"_PLAYER(pnum,pname,shoot,dribble,pass,"
						+ "stamina,tackle,steal,speed,gk,exp)"
						+ "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
				
				PreparedStatement psmt = conn.prepareStatement(sql);
				
				psmt.setInt(1, p.primaryNum);
				psmt.setString(2, p.name);
				psmt.setInt(3, p.shoot);
				psmt.setInt(4, p.dribble);
				psmt.setInt(5, p.pass);
				psmt.setInt(6, p.stamina);
				psmt.setInt(7, p.tackle);
				psmt.setInt(8, p.steal);
				psmt.setInt(9, p.speed);
				psmt.setInt(10, p.gk);
				psmt.setInt(11, p.exp);
				
				psmt.executeUpdate();
				
				//MARKET_PLAYER에서 구매된 선수 삭제해줌
				sql = "DELETE FROM MARKET_PLAYER WHERE PNUM = ?";
				
				psmt = conn.prepareStatement(sql);
				
				psmt.setInt(1, p.primaryNum);
				
				psmt.executeUpdate();
				
				psmt.close();
				
			}
			catch(Exception e){
				System.out.println(e.toString());
			}
		}
		DBconn.close();
	}
    
    public void sellPlayer(Player p, int pri, String id){
		
		Connection conn = DBconn.getConnection();

		if (conn == null) {
			System.out.println("데이터베이스 연결 실패!!");
			System.exit(0);
		} else {
			System.out.println("데이터베이스 연결 성공!!");

			try {
				
				//MARKET_PLAYER에 판매 할 선수 정보 전달
				
				String sql = "INSERT INTO MARKET_PLAYER(pnum,pname,shoot,dribble,pass,stamina,tackle,"
						+ "steal,speed,gk,price,exp)"
						+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
				
				PreparedStatement psmt = conn.prepareStatement(sql);
				
				psmt.setInt(1, p.primaryNum);
				psmt.setString(2, p.name);
				psmt.setInt(3, p.shoot);
				psmt.setInt(4, p.dribble);
				psmt.setInt(5, p.pass);
				psmt.setInt(6, p.stamina);
				psmt.setInt(7, p.tackle);
				psmt.setInt(8, p.steal);
				psmt.setInt(9, p.speed);
				psmt.setInt(10, p.gk);
				psmt.setInt(11, pri);
				psmt.setInt(12, p.exp);
				
				psmt.executeUpdate();
				
				//사용자_PLAYER에서 판매한 선수 삭제
				
				sql = "DELETE FROM "+id+"_PLAYER WHERE PNUM=?";
				
				psmt = conn.prepareStatement(sql);
				
				psmt.setInt(1, p.primaryNum);
				
				psmt.executeUpdate();
				
				psmt.close();
				
			}
			catch(Exception e){
				System.out.println(e.toString());
			}
		}
		
		DBconn.close();
    }
				
			

}
