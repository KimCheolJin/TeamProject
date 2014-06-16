package DBCENTER;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import data.Player;
import store.Store;

public class DBLogin {

	// 회원가입시 정보 DB로 전송
	public void putJoin(String a, String b, String c, String d, String f) {

		Connection conn = DBconn.getConnection();

		if (conn == null) {
			System.out.println("데이터베이스 연결 실패!!");

		} else {
			System.out.println("데이터베이스 연결 성공!!");

			try {
				
				//insert sql의 경우 테이블 뒤에 변수명 반드시 나열해주기!!
				String sql = "INSERT INTO uuser(umoney,uuid,upw,unickname,uemail,uphone,uwin,udraw,ulose,uscore,upoint)"
						+ "VALUES(0,?,?,?,?,?,0,0,0,0,0)";

				PreparedStatement psmt = conn.prepareStatement(sql);

				psmt.setString(1, a);
				psmt.setString(2, b);
				psmt.setString(3, c);
				psmt.setString(4, d);
				psmt.setString(5, f);

				psmt.executeUpdate();

				System.out.println("회원 정보 입력 완료!!");
				psmt.close();
				
			} catch (Exception e) {
				System.out.println(e.toString());
			}

		}
		
		DBconn.close();
	}
	
	//회원가입의 팀 선택시 팀에따른 선수들 설정
	public void putUserPlayer(String team, String id){
		
	
		//DB에서 선수정보 담는곳
		
		ArrayList<Player> list = new ArrayList<Player>();
		
		Connection conn = DBconn.getConnection();
		

		if (conn == null) {
			System.out.println("데이터베이스 연결 실패!!");

		} else {
			System.out.println("데이터베이스 연결 성공!!");

			try {
				
			
				//유저마다 선수테이블 생성시켜줌
				
				String sql = "CREATE TABLE "+ id+"_Player"+
						"( pnum number, "+
						"pname varchar(20), "+
						"shoot number, "+
						"dribble number, "+
						"pass number, "+
						"stamina number, "+
						"tackle number, "+
						"steal number, "+
						"speed number, "+
						"gk number, "+
						"exp number, "+
						"primary key(pnum))";
						
				PreparedStatement psmt = conn.prepareStatement(sql);
			
				psmt.executeUpdate(); //테이블생성시executeUpdate사용
				
				
				//DB로부터 선수테이블읽어옴
				
				 sql = "SELECT * FROM PLAYER";
				
				psmt = conn.prepareStatement(sql);
				
				ResultSet rs = null;
				rs = psmt.executeQuery();
				
				while(rs.next()){
					
					int primaryNum = rs.getInt(1);
					String pname = rs.getString(2);
					int shoot = rs.getInt(3);
					int dribble = rs.getInt(4);
					int pass = rs.getInt(5);
					int stamina = rs.getInt(6);
					int tackle = rs.getInt(7);
					int steal = rs.getInt(8);
					int speed = rs.getInt(9);
					int gk = rs.getInt(10);
					int exp = rs.getInt(12);
					
					list.add(new Player(primaryNum, pname, shoot, dribble, pass, stamina, tackle, steal,
							speed,gk, exp));
				}
				
				System.out.println("DB에서 가져오기 완료!!");
				
				//선택한 team에 따라 초기 선수들 user_player 테이블로 보내줌
				
				int k=0;
				
				if(team=="아스널"){
					
					k=0;
				}
				else if(team=="맨시티"){
					k=21;
				}
				else if(team=="리버풀"){
					k=43;
				}
				else if(team=="맨유"){
					k=65;
				}
				
				
				
				//팀에맞는 선수들 id_PLAYER로 전달하는 부분
				for(int i=k; i<k+21; i++){	
					
					sql = "INSERT INTO "+id+"_PLAYER(pnum,pname,shoot,dribble,pass,stamina,tackle,steal,speed,gk,exp)"+
							"VALUES(?,?,?,?,?,?,?,?,?,?,?)";
					
					psmt = conn.prepareStatement(sql);
					
					psmt.setInt(1,list.get(i).primaryNum);
					psmt.setString(2, list.get(i).name);
					psmt.setInt(3, list.get(i).shoot);
					psmt.setInt(4, list.get(i).dribble);
					psmt.setInt(5, list.get(i).pass);
					psmt.setInt(6, list.get(i).stamina);
					psmt.setInt(7, list.get(i).tackle);
					psmt.setInt(8, list.get(i).steal);
					psmt.setInt(9, list.get(i).speed);
					psmt.setInt(10, list.get(i).gk);
					psmt.setInt(11, list.get(i).exp);
					
					psmt.executeUpdate();
					
				}
				
				psmt.close();
				
			}
			catch(Exception e){
				System.out.println(e.toString());
			}
		}
		
		DBconn.close();
		
	}
	
	//회원가입시 USER_TEAM테이블에 기본설정값 전달
	public void putUserTeam(String team, String id) {

		Connection conn = DBconn.getConnection();

		if (conn == null) {
			System.out.println("데이터베이스 연결 실패!!");

		} else {
			System.out.println("데이터베이스 연결 성공!!");

			try {
				
				//TEAM 테이블로 부터 기본 설정값 가져옴
				String sql = "SELECT * FROM TEAM WHERE TNAME = ?";
				
				PreparedStatement psmt = conn.prepareStatement(sql);
				
				psmt.setString(1, team);
				
				ResultSet rs = null;
				rs = psmt.executeQuery();
				
				while(rs.next()){
				
					int formation = rs.getInt(2);
					int sA = rs.getInt(3);
					int sD = rs.getInt(4);
					int sT = rs.getInt(5);
					int cR = rs.getInt(6);
					int cG = rs.getInt(7);
					int cB = rs.getInt(8);

					System.out.println("1차완료!!");
					
					//USER_TEAM테이블에 사용자의 id와 TEAM설정값 전달
					sql = "INSERT INTO USER_TEAM(userid,tname,formation,strategy1,strategy2,strategy3,red,green,blue)"
							+"VALUES(?,?,?,?,?,?,?,?,?)";
					
					psmt = conn.prepareStatement(sql);
					
					psmt.setString(1, id);
					psmt.setString(2, team);
					psmt.setInt(3, formation);
					psmt.setInt(4, sA);
					psmt.setInt(5, sD);
					psmt.setInt(6, sT);
					psmt.setInt(7, cR);
					psmt.setInt(8, cG);
					psmt.setInt(9, cB);
					
					psmt.executeUpdate();
					
					
					
				}
				
				psmt.close();
			}
			catch(Exception e){
				System.out.println(e.toString());
			}
		}
		System.out.println("USER_TEAM테이블에 기본값 전달완료!!");
		DBconn.close();
	}

	// 회원가입시 중복된 ID DB에서 검색
	public int serchSameID(String id) {

		Connection conn = DBconn.getConnection();

		int k = 0;

		if (conn == null) {
			System.out.println("데이터베이스 연결 실패!!");

		} else {
			System.out.println("데이터베이스 연결 성공!!");

			try {

				String sql = "SELECT * FROM UUSER";

				PreparedStatement psmt = conn.prepareStatement(sql);

				ResultSet rs = null;
				rs = psmt.executeQuery();

				System.out.println("회원ID중복검사실행!!");
				while (rs.next()) {
					
					String temp = rs.getString(2);
					if (id.equals(temp))
						k++;
				}

				psmt.close();
				
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}

		System.out.println("중복검사완료!!");
		DBconn.close();
		// k=0이면 중복된 id 없는것, k=0이 아니면 중복된 id 있는것
		return k;
	}
	

	// 로그인시 DB에서 ID,PW 검색해줌
	public int checkInfo(String uid, String upw) {

		Connection conn = DBconn.getConnection();

		if (conn == null) {
			System.out.println("데이터베이스 연결 실패!!");

		} else {
			System.out.println("데이터베이스 연결 성공!!");

			try {

				String sql = "SELECT * FROM UUSER";

				PreparedStatement psmt = conn.prepareStatement(sql);

				ResultSet rs = null;
				rs = psmt.executeQuery();
				
				//50으로 총 회원수 제한
				String[] tempid = new String[50];
				String[] temppw = new String[50];
				
				int i=0;
				int apt=0;
				int sst=0;

				System.out.println("회원ID,PW검색시작!!");
				
				while (rs.next()) {
					
					tempid[i] = rs.getString(2);
					temppw[i] = rs.getString(3);
					i++;

				}
				
				
				for(int k=0; k<i; k++){
					
					if(tempid[k].equals(uid) && temppw[k].equals(upw)){
						apt++;
					}
					else if(tempid[k].equals(uid) && (!temppw[k].equals(upw))){
						sst++;
					}
				}
				
				if (apt==1) {
					// ID, PW 모두 확인된 경우
					System.out.println("로그인허가!!");
					return 0;
				} else if (sst>=1) {
					// ID만 일치하고 PW는 다른경우
					System.out.println("비밀번호오류!!");
					return 1;
				} else {
					// 둘다 다른경우
					System.out.println("입력정보오류!!");
					return 2;
				}
				
			} catch (Exception e) {
				System.out.println(e.toString());
				// ID,PW중 하나라도 입력이 이뤄지지 않은경우
				return 3;
			}
		}
		
		DBconn.close();

		// 서버접속이 안됬을경우
		return 4;
	}


}
