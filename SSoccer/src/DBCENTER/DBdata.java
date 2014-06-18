package DBCENTER;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import data.Player;
import data.Team;

public class DBdata {
	
	//선수 구매시 DB(사용자_PLAYER)로 정보 보냄
	public void putNewPlayer(Player p, String id){
		
		Connection conn = DBconn.getConnection();

		if (conn == null) {
			System.out.println("데이터베이스 연결 실패!!");

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
	
	//old선수 구매시 new선수 구매와 마찬가지로 사용자_PLAYER로 정보보냄
    public void putOldPlayer(Player p, String id){
		
		Connection conn = DBconn.getConnection();

		if (conn == null) {
			System.out.println("데이터베이스 연결 실패!!");

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
    
  //MARKET_PLAYER에 판매 할 선수 정보 전달
    public void sellPlayer(Player p, int pri, String id){
		
		Connection conn = DBconn.getConnection();

		if (conn == null) {
			System.out.println("데이터베이스 연결 실패!!");

		} else {
			System.out.println("데이터베이스 연결 성공!!");

			try {
				
				//MARKET_PLAYER에 판매 할 선수 정보 전달
				
				String sql = "INSERT INTO MARKET_PLAYER(pnum,pname,shoot,dribble,pass,stamina,tackle,"
						+ "steal,speed,gk,price,exp,userid)"
						+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
				
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
				psmt.setString(13, id);
				
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
    
    //친구등록시 DB 친구 테이블로 초기 값 내용 전송 그리고 친구 id에 맞는 닉네임가져옴
    public String putFriends(String userid, String friendid){
    	
    	String friendNick = null;
    	
    	Connection conn = DBconn.getConnection();

		if (conn == null) {
			System.out.println("데이터베이스 연결 실패!!");

		} else {
			System.out.println("데이터베이스 연결 성공!!");

			try {
				
				//테이블에 친구등록시 2개 입력됨 (나,친구)/(친구,나) 이런식으로 일어남
				
				//(나,친구)
				String sql = "INSERT INTO FRIEND_RECORD(ME,FRIEND,WIN,DRAW,"
						+ "LOSE,ME_SCORE,FRIEND_SCORE)"
						+ "VALUES(?,?,0,0,0,0,0)";
				
				PreparedStatement psmt = conn.prepareStatement(sql);
				
				psmt.setString(1, userid);
				psmt.setString(2, friendid);
				
				psmt.executeUpdate();
				
				
				//(친구,나)
				sql = "INSERT INTO FRIEND_RECORD(ME,FRIEND,WIN,DRAW,"
						+ "LOSE,ME_SCORE,FRIEND_SCORE)"
						+ "VALUES(?,?,0,0,0,0,0)";
				
				psmt = conn.prepareStatement(sql);
			
				psmt.setString(1, friendid);
				psmt.setString(2, userid);
				
				psmt.executeUpdate();
				
				
				//친구 이름 검색후 닉네임 받음
				sql = "SELECT * FROM UUSER WHERE UUID = ?";
				
				psmt = conn.prepareStatement(sql);
				
				psmt.setString(1, friendid);
				
				ResultSet rs = null;
				rs = psmt.executeQuery();
				
				while(rs.next()){
				
					friendNick = rs.getString(4);
					
				}
		
				System.out.println("DB로 친구정보 전송완료!!");
				psmt.close();
				
			}
				
			catch(Exception e){
				System.out.println(e.toString());
			}
		}
		
		DBconn.close();
		
		//친구 닉네임 반환
		return friendNick;
    	
    }
    
    //팀변경사항(전술부분) DB에 전송
    public void UpdateUserTeam(Team t,String id){
    	
    	Connection conn = DBconn.getConnection();

		if (conn == null) {
			System.out.println("데이터베이스 연결 실패!!");

		} else {
			System.out.println("데이터베이스 연결 성공!!");

			try {
				
				String sql = "UPDATE USER_TEAM "
						+ "SET FORMATION = ?, STRATEGY1=?, STRATEGY2=?, STRATEGY3=?"
						+ "WHERE USERID = ?";
				
				PreparedStatement psmt = conn.prepareStatement(sql);
				
				psmt.setInt(1, t.strategyF);
				psmt.setInt(2, t.strategyA);
				psmt.setInt(3, t.strategyD);
				psmt.setInt(4, t.strategyT);
				psmt.setString(5, id);
				
				psmt.executeUpdate();
				
				System.out.println("USER_TEAM에 변경사항 적용완료!!");
				psmt.close();
				
			}
			catch(Exception e){
				System.out.println(e.toString());
			}
		}
		
		DBconn.close();
    }
    
    //훈련하기부분 전송
    public void UpdateUserPlayer(Team t,String id){
    	
    	Connection conn = DBconn.getConnection();

		if (conn == null) {
			System.out.println("데이터베이스 연결 실패!!");

		} else {
			System.out.println("데이터베이스 연결 성공!!");

			try {
				
				String sql;
				
				
				for(int i=0; i<t.playerList.size(); i++){
					
					sql = "UPDATE "+id+"_PLAYER "
							+ " SET SHOOT = ?, DRIBBLE=?, PASS=?, STAMINA=?,"
							+ " TACKLE=?, STEAL=?, SPEED=?, GK=?"
							+ " WHERE PNUM = ?";
					
					PreparedStatement psmt = conn.prepareStatement(sql);
					
					
					psmt.setInt(1, t.getPlayer(i).shoot);
					psmt.setInt(2, t.getPlayer(i).dribble);
					psmt.setInt(3, t.getPlayer(i).pass);
					psmt.setInt(4, t.getPlayer(i).stamina);
					psmt.setInt(5, t.getPlayer(i).tackle);
					psmt.setInt(6, t.getPlayer(i).steal);
					psmt.setInt(7, t.getPlayer(i).speed);
					psmt.setInt(8, t.getPlayer(i).gk);
					psmt.setInt(9, t.getPlayer(i).primaryNum);
					
					psmt.executeUpdate();
					
					psmt.close();
					
					
				}
				
				
				
				System.out.println(id+"_PLAYER에 변경사항 적용완료!!");
				
			}
			catch(Exception e){
				System.out.println(e.toString());
			}
		}
		
		DBconn.close();
    }
    
    //게임결과 DB로 전송(스페셜매치)
    
    public void putResult(String id, int myscore, int otherscore){
    	
    	Connection conn = DBconn.getConnection();

		if (conn == null) {
			System.out.println("데이터베이스 연결 실패!!");

		} else {
			System.out.println("데이터베이스 연결 성공!!");
			
			try{
				
				System.out.println("경기결과기록중!!");
				
				//update로 추가해주기 전에 미리 DB에있는 정보가져옴
				
				int win = 0;
				int draw = 0;
				int lose = 0;
				int score = 0;
				int point = 0;
				
				String sql = "SELECT * FROM UUSER WHERE UUID=?";
				
				PreparedStatement psmt = conn.prepareStatement(sql);
				
				psmt.setString(1, id);
				
				ResultSet rs = null;
				rs = psmt.executeQuery();
				
				while(rs.next()){
					
					win = rs.getInt(7);
					draw = rs.getInt(8);
					lose = rs.getInt(9);
					score = rs.getInt(10);
					point = rs.getInt(11);
					
				}
				
				//이제 스코어에따라 승,무,패값들 변경
				if(myscore>otherscore){
					win++;
					point +=50;
				}
				else if(myscore==otherscore){
					draw++;
					
				}
				else{
					lose++;
					point -=50;
				}
				
				//스코어추가
				score += myscore;
			
				
				//이제 다시 DB로 보내는부분
				sql = "UPDATE UUSER "
						+ "SET UWIN=?, UDRAW=?, ULOSE=?, USCORE=?, UPOINT=?"
						+ "WHERE UUID=?";
				
				psmt = conn.prepareStatement(sql);
				
				psmt.setInt(1, win);
				psmt.setInt(2, draw);
				psmt.setInt(3, lose);
				psmt.setInt(4, score);
				psmt.setInt(5, point);
				psmt.setString(6, id);
				
				psmt.executeUpdate();
				
				psmt.close();
				
				System.out.println("경기결과기록완료!!");
				
			}
			catch(Exception e){
				System.out.println(e.toString());
			}
			
		}
		
		DBconn.close();
    }
    
			
    //게임결과 DB로 전송(친구와 게임)
    public void putResult(String id, String friendid, int myscore, int friendscore){
    	
    	Connection conn = DBconn.getConnection();

		if (conn == null) {
			System.out.println("데이터베이스 연결 실패!!");

		} else {
			System.out.println("데이터베이스 연결 성공!!");
			try{
				
				System.out.println("경기결과기록중!!");
				
				int win = 0;
				int draw = 0;
				int lose = 0;
				int mscore = 0;
				int fscore = 0;
				
				//DB에서 저장된 정보들 가져옴
				
				String sql = "SELECT * FROM FRIEND_RECORD WHERE ME = ? AND FRIEND = ?";
				
				PreparedStatement psmt = conn.prepareStatement(sql);
				
				psmt.setString(1, id);
				psmt.setString(2, friendid);
				
				ResultSet rs = null;
				rs=psmt.executeQuery();
				
				while(rs.next()){
					
					win = rs.getInt(3);
					draw = rs.getInt(4);
					lose = rs.getInt(5);
					mscore = rs.getInt(6);
					fscore = rs.getInt(7);
					
				}
				
				//이제 변경된값 적용
				if(myscore>friendscore){
					win++;
					
				}
				else if(myscore==friendscore){
					draw++;
				}
				else{
					lose++;
				}
				
				//스코어변경
				mscore+=myscore;
				fscore+=friendscore;
				
				//이제 DB로 정보보내기
				//(나,친구)부터보내기
				sql = "UPDATE FRIEND_RECORD"
						+ "SET WIN = ?, DRAW = ?, LOSE = ?, ME_SCORE = ?, FRIEND_SCORE =?"
						+ "WHERE ME = ? AND FRIEND = ?";
				
				psmt = conn.prepareStatement(sql);
				
				psmt.setInt(1, win);
				psmt.setInt(2, draw);
				psmt.setInt(3, lose);
				psmt.setInt(4, mscore);
				psmt.setInt(5, fscore);
				psmt.setString(6, id);
				psmt.setString(7, friendid);
				
				psmt.executeUpdate();
				
				//(친구,나)에도 보내기
				sql = "UPDATE FRIEND_RECORD"
						+ "SET WIN = ?, DRAW = ?, LOSE = ?, ME_SCORE = ?, FRIEND_SCORE =?"
						+ "WHERE ME = ? AND FRIEND = ?";
				
				psmt = conn.prepareStatement(sql);
				
				psmt.setInt(1, lose);
				psmt.setInt(2, draw);
				psmt.setInt(3, win);
				psmt.setInt(4, fscore);
				psmt.setInt(5, mscore);
				psmt.setString(6, friendid);
				psmt.setString(7, id);
				
				psmt.executeUpdate();
				
				psmt.close();
				
				System.out.println("경기결과기록완료!!");
			}
			catch(Exception e){
				System.out.println(e.toString());
			}
		}
		
		DBconn.close();
    }
		
				
			

}
