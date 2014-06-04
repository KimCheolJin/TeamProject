package DBCENTER;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import store.Store;

public class DBdata {

	// 회원가입시 정보 DB로 전송
	public void putJoin(String a, String b, String c, String d, String f) {

		Connection conn = DBconn.getConnection();

		if (conn == null) {
			System.out.println("데이터베이스 연결 실패!!");
			System.exit(0);
		} else {
			System.out.println("데이터베이스 연결 성공!!");

			try {
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

	// 회원가입시 중복된 ID DB에서 검색
	public int serchSameID(String s) {

		Connection conn = DBconn.getConnection();

		int k = 0;

		if (conn == null) {
			System.out.println("데이터베이스 연결 실패!!");
			System.exit(0);
		} else {
			System.out.println("데이터베이스 연결 성공!!");

			try {

				String sql = "SELECT * FROM UUSER";

				PreparedStatement psmt = conn.prepareStatement(sql);

				ResultSet rs = null;
				rs = psmt.executeQuery();

				while (rs.next()) {

					String temp = rs.getString(2);
					if (s.equals(temp))
						k++;
				}

			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}

		DBconn.close();
		// k=0이면 중복된 id 없는것, k=0이 아니면 중복된 id 있는것
		return k;
	}

	// 로그인시 DB에서 ID,PW 검색해줌
	public int checkInfo(String uid, String upw) {

		Connection conn = DBconn.getConnection();

		if (conn == null) {
			System.out.println("데이터베이스 연결 실패!!");
			System.exit(0);
		} else {
			System.out.println("데이터베이스 연결 성공!!");

			try {

				String sql = "SELECT * FROM UUSER";

				PreparedStatement psmt = conn.prepareStatement(sql);

				ResultSet rs = null;
				rs = psmt.executeQuery();

				while (rs.next()) {

					String tempid = rs.getString(2);
					String temppw = rs.getString(3);

					if (tempid.equals(uid) && temppw.equals(upw)) {
						// ID, PW 모두 확인된 경우
						return 0;
					} else if (tempid.equals(uid) && (!temppw.equals(upw))) {
						// ID만 일치하고 PW는 다른경우
						return 1;
					} else {
						// 둘다 다른경우
						return 2;
					}
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

	//DB로부터 Store정보 로드
	public Store loadStore() {

		Store st = new Store();

		Connection conn = DBconn.getConnection();

		if (conn == null) {
			System.out.println("데이터베이스 연결 실패!!");
			System.exit(0);
		} else {
			System.out.println("데이터베이스 연결 성공!!");

			try {

				String sql = "SELECT * FROM PLAYER";

				PreparedStatement psmt = conn.prepareStatement(sql);

				ResultSet rs = null;
				rs = psmt.executeQuery();

				while (rs.next()) {

					System.out.println("로드1실행");
					int pirmaryNum = rs.getInt(1);
					String pname = rs.getString(2);
					int shoot = rs.getInt(3);
					int dribble = rs.getInt(4);
					int pass = rs.getInt(5);
					int stamina = rs.getInt(6);
					int tackle = rs.getInt(7);
					int steal = rs.getInt(8);
					int speed = rs.getInt(9);
					int gk = rs.getInt(10);
					int price = rs.getInt(11);
					int exp = rs.getInt(12);


					st.addnewPlayer(pirmaryNum, pname, shoot, dribble, pass, stamina,
							tackle, steal, speed, gk, exp, price);

				}

				String sql2 = "SELECT * FROM MARKET_PLAYER";

				PreparedStatement psmt2 = conn.prepareStatement(sql2);

				ResultSet rs2 = null;
				rs2 = psmt2.executeQuery();

				while (rs2.next()) {

					System.out.println("로드2실행");
					int pirmaryNum = rs2.getInt(1);
					String pname = rs2.getString(2);
					int shoot = rs2.getInt(3);
					int dribble = rs2.getInt(4);
					int pass = rs2.getInt(5);
					int stamina = rs2.getInt(6);
					int tackle = rs2.getInt(7);
					int steal = rs2.getInt(8);
					int speed = rs2.getInt(9);
					int gk = rs2.getInt(10);
					int price = rs2.getInt(11);
					int exp = rs2.getInt(12);
					

					st.addoldPlayer(pirmaryNum, pname, shoot, dribble, pass, stamina,
							tackle, steal, speed, gk, exp, price);

				}
			} catch (Exception e) {

			}

		}

		DBconn.close();

		return st; //Store반환하면서 db종료
	}

}
