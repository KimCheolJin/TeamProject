package DBCENTER;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import store.Store;

public class DBdata {

	// ȸ�����Խ� ���� DB�� ����
	public void putJoin(String a, String b, String c, String d, String f) {

		Connection conn = DBconn.getConnection();

		if (conn == null) {
			System.out.println("�����ͺ��̽� ���� ����!!");
			System.exit(0);
		} else {
			System.out.println("�����ͺ��̽� ���� ����!!");

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

				System.out.println("ȸ�� ���� �Է� �Ϸ�!!");
				psmt.close();
			} catch (Exception e) {
				System.out.println(e.toString());
			}

		}

		DBconn.close();
	}

	// ȸ�����Խ� �ߺ��� ID DB���� �˻�
	public int serchSameID(String s) {

		Connection conn = DBconn.getConnection();

		int k = 0;

		if (conn == null) {
			System.out.println("�����ͺ��̽� ���� ����!!");
			System.exit(0);
		} else {
			System.out.println("�����ͺ��̽� ���� ����!!");

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
		// k=0�̸� �ߺ��� id ���°�, k=0�� �ƴϸ� �ߺ��� id �ִ°�
		return k;
	}

	// �α��ν� DB���� ID,PW �˻�����
	public int checkInfo(String uid, String upw) {

		Connection conn = DBconn.getConnection();

		if (conn == null) {
			System.out.println("�����ͺ��̽� ���� ����!!");
			System.exit(0);
		} else {
			System.out.println("�����ͺ��̽� ���� ����!!");

			try {

				String sql = "SELECT * FROM UUSER";

				PreparedStatement psmt = conn.prepareStatement(sql);

				ResultSet rs = null;
				rs = psmt.executeQuery();

				while (rs.next()) {

					String tempid = rs.getString(2);
					String temppw = rs.getString(3);

					if (tempid.equals(uid) && temppw.equals(upw)) {
						// ID, PW ��� Ȯ�ε� ���
						return 0;
					} else if (tempid.equals(uid) && (!temppw.equals(upw))) {
						// ID�� ��ġ�ϰ� PW�� �ٸ����
						return 1;
					} else {
						// �Ѵ� �ٸ����
						return 2;
					}
				}
			} catch (Exception e) {
				System.out.println(e.toString());
				// ID,PW�� �ϳ��� �Է��� �̷����� �������
				return 3;
			}
		}

		DBconn.close();

		// ���������� �ȉ������
		return 4;
	}

	//DB�κ��� Store���� �ε�
	public Store loadStore() {

		Store st = new Store();

		Connection conn = DBconn.getConnection();

		if (conn == null) {
			System.out.println("�����ͺ��̽� ���� ����!!");
			System.exit(0);
		} else {
			System.out.println("�����ͺ��̽� ���� ����!!");

			try {

				String sql = "SELECT * FROM PLAYER";

				PreparedStatement psmt = conn.prepareStatement(sql);

				ResultSet rs = null;
				rs = psmt.executeQuery();

				while (rs.next()) {

					System.out.println("�ε�1����");
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

					System.out.println("�ε�2����");
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

		return st; //Store��ȯ�ϸ鼭 db����
	}

}
