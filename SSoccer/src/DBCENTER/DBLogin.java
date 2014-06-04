package DBCENTER;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class DBLogin {

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

				System.out.println("회원ID중복검사실행!!");
				while (rs.next()) {
					
					String temp = rs.getString(2);
					if (s.equals(temp))
						k++;
				}

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
			System.exit(0);
		} else {
			System.out.println("데이터베이스 연결 성공!!");

			try {

				String sql = "SELECT * FROM UUSER";

				PreparedStatement psmt = conn.prepareStatement(sql);

				ResultSet rs = null;
				rs = psmt.executeQuery();

				System.out.println("회원ID,PW검색시작!!");
				while (rs.next()) {
					
					String tempid = rs.getString(2);
					String temppw = rs.getString(3);

					if (tempid.equals(uid) && temppw.equals(upw)) {
						// ID, PW 모두 확인된 경우
						System.out.println("로그인허가!!");
						return 0;
					} else if (tempid.equals(uid) && (!temppw.equals(upw))) {
						// ID만 일치하고 PW는 다른경우
						System.out.println("비밀번호오류!!");
						return 1;
					} else {
						// 둘다 다른경우
						System.out.println("입력정보오류!!");
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


}
