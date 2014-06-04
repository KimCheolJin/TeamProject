package DBCENTER;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class DBLogin {

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

				System.out.println("ȸ��ID�ߺ��˻����!!");
				while (rs.next()) {
					
					String temp = rs.getString(2);
					if (s.equals(temp))
						k++;
				}

			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}

		System.out.println("�ߺ��˻�Ϸ�!!");
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

				System.out.println("ȸ��ID,PW�˻�����!!");
				while (rs.next()) {
					
					String tempid = rs.getString(2);
					String temppw = rs.getString(3);

					if (tempid.equals(uid) && temppw.equals(upw)) {
						// ID, PW ��� Ȯ�ε� ���
						System.out.println("�α����㰡!!");
						return 0;
					} else if (tempid.equals(uid) && (!temppw.equals(upw))) {
						// ID�� ��ġ�ϰ� PW�� �ٸ����
						System.out.println("��й�ȣ����!!");
						return 1;
					} else {
						// �Ѵ� �ٸ����
						System.out.println("�Է���������!!");
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


}
