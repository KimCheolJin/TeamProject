package DBCENTER;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import data.Player;
import store.Store;

public class DBLogin {

	// ȸ�����Խ� ���� DB�� ����
	public void putJoin(String a, String b, String c, String d, String f) {

		Connection conn = DBconn.getConnection();

		if (conn == null) {
			System.out.println("�����ͺ��̽� ���� ����!!");

		} else {
			System.out.println("�����ͺ��̽� ���� ����!!");

			try {
				
				//insert sql�� ��� ���̺� �ڿ� ������ �ݵ�� �������ֱ�!!
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
	
	//ȸ�������� �� ���ý� �������� ������ ����
	public void putUserPlayer(String team, String id){
		
	
		//DB���� �������� ��°�
		
		ArrayList<Player> list = new ArrayList<Player>();
		
		Connection conn = DBconn.getConnection();
		

		if (conn == null) {
			System.out.println("�����ͺ��̽� ���� ����!!");

		} else {
			System.out.println("�����ͺ��̽� ���� ����!!");

			try {
				
			
				//�������� �������̺� ����������
				
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
			
				psmt.executeUpdate(); //���̺������executeUpdate���
				
				
				//DB�κ��� �������̺��о��
				
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
				
				System.out.println("DB���� �������� �Ϸ�!!");
				
				//������ team�� ���� �ʱ� ������ user_player ���̺�� ������
				
				int k=0;
				
				if(team=="�ƽ���"){
					
					k=0;
				}
				else if(team=="�ǽ�Ƽ"){
					k=21;
				}
				else if(team=="����Ǯ"){
					k=43;
				}
				else if(team=="����"){
					k=65;
				}
				
				
				
				//�����´� ������ id_PLAYER�� �����ϴ� �κ�
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
	
	//ȸ�����Խ� USER_TEAM���̺� �⺻������ ����
	public void putUserTeam(String team, String id) {

		Connection conn = DBconn.getConnection();

		if (conn == null) {
			System.out.println("�����ͺ��̽� ���� ����!!");

		} else {
			System.out.println("�����ͺ��̽� ���� ����!!");

			try {
				
				//TEAM ���̺�� ���� �⺻ ������ ������
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

					System.out.println("1���Ϸ�!!");
					
					//USER_TEAM���̺� ������� id�� TEAM������ ����
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
		System.out.println("USER_TEAM���̺� �⺻�� ���޿Ϸ�!!");
		DBconn.close();
	}

	// ȸ�����Խ� �ߺ��� ID DB���� �˻�
	public int serchSameID(String id) {

		Connection conn = DBconn.getConnection();

		int k = 0;

		if (conn == null) {
			System.out.println("�����ͺ��̽� ���� ����!!");

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
					if (id.equals(temp))
						k++;
				}

				psmt.close();
				
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

		} else {
			System.out.println("�����ͺ��̽� ���� ����!!");

			try {

				String sql = "SELECT * FROM UUSER";

				PreparedStatement psmt = conn.prepareStatement(sql);

				ResultSet rs = null;
				rs = psmt.executeQuery();
				
				//50���� �� ȸ���� ����
				String[] tempid = new String[50];
				String[] temppw = new String[50];
				
				int i=0;
				int apt=0;
				int sst=0;

				System.out.println("ȸ��ID,PW�˻�����!!");
				
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
					// ID, PW ��� Ȯ�ε� ���
					System.out.println("�α����㰡!!");
					return 0;
				} else if (sst>=1) {
					// ID�� ��ġ�ϰ� PW�� �ٸ����
					System.out.println("��й�ȣ����!!");
					return 1;
				} else {
					// �Ѵ� �ٸ����
					System.out.println("�Է���������!!");
					return 2;
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
