package DBCENTER;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import store.Store;
import data.User;
import data.Team;
import data.Player;
import friend.Friend;

public class DBload {
	
	
	//DB�� ���� USER���� �ε�
	public User loadUser(String id){
		
		User u = new User();
		
		Connection conn = DBconn.getConnection();

		if (conn == null) {
			System.out.println("�����ͺ��̽� ���� ����!!");
			System.exit(0);
		} else {
			System.out.println("�����ͺ��̽� ���� ����!!");

			try {
				
				//UUID�� id�� �ش��ϴ°��� �������� ã��
				String sql = "SELECT * FROM UUSER WHERE UUID=?";
					
				PreparedStatement psmt = conn.prepareStatement(sql);
				
				psmt.setString(1, id);

				ResultSet rs = null;
				rs = psmt.executeQuery();
				
				while(rs.next()){
					
					System.out.println("���������ε����!!");
					
					String uid = rs.getString(2);
					String nick = rs.getString(4);
					int money = rs.getInt(1);
					int win = rs.getInt(7);
					int draw = rs.getInt(8);
					int lose = rs.getInt(9);
					int score = rs.getInt(10);
					int point = rs.getInt(11);
					
					u = new User(uid,nick,money,win,draw,lose,score,point);
					
				}
				
				psmt.close();
			}
			catch(Exception e){
				System.out.println(e.toString());
			}
		}
		System.out.println("���������ε�Ϸ�!!");
		DBconn.close();
		
		return u;
	}
	
	//DB�� ���� USER_PLAYER���� �ε�
    public ArrayList<Player> loadPlayer(String id){
		
		ArrayList<Player> list = new ArrayList<Player>();
		
		Connection conn = DBconn.getConnection();

		if (conn == null) {
			System.out.println("�����ͺ��̽� ���� ����!!");
			System.exit(0);
		} else {
			System.out.println("�����ͺ��̽� ���� ����!!");

			try {
				
				//���̺��߿��� �ش� ������ ���� ���̺��� ã��
				String sql = "SELECT * FROM "+id+"_PLAYER"; //�����߻�
					
				PreparedStatement psmt = conn.prepareStatement(sql);

				ResultSet rs = null;
				rs = psmt.executeQuery();
				
				int i=0;
				
				while(rs.next()){
					
					
					System.out.println("���������ε���---"+i);
					
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
					int exp = rs.getInt(11);
					
					list.add(new Player(primaryNum, pname, shoot, dribble, pass, stamina,
							tackle, steal, speed, gk, exp));
					
					i++;
					
				}
				
				psmt.close();
			}
			catch(Exception e){
				System.out.println(e.toString());
			}
		}
		
		System.out.println("���������ε�Ϸ�!!");
		DBconn.close();
		
		return list;
    }
	
	
	//DB�� ���� USER_TEAM���� �ε�
	public Team loadTeam(String id){
		
		Team t = null;
		
		Connection conn = DBconn.getConnection();

		if (conn == null) {
			System.out.println("�����ͺ��̽� ���� ����!!");
			System.exit(0);
		} else {
			System.out.println("�����ͺ��̽� ���� ����!!");

			try {
				
				//�������̺� �߿��� �ش� id�� �´� �� ã��
				String sql = "SELECT * FROM USER_TEAM WHERE USERID=?";
					
				PreparedStatement psmt = conn.prepareStatement(sql);

				psmt.setString(1, id);
				
				ResultSet rs = null;
				rs = psmt.executeQuery();
				
				while(rs.next()){
					
					System.out.println("�����������ε����!!");
					String name = rs.getString(2);
					int strategyA= rs.getInt(4);
					int strategyD= rs.getInt(5);
					int strategyT= rs.getInt(6);
					int strategyF= rs.getInt(3);
					int colorR= rs.getInt(7);
					int colorG= rs.getInt(8);
					int colorB= rs.getInt(9);
					
					t = new Team(name,strategyA, strategyD,strategyT, strategyF,colorR,colorG,colorB );
					
				}
				
				System.out.println("�����������ε�Ϸ�!!");
				psmt.close();
				
			}
			catch(Exception e){
				System.out.println(e.toString());
			}
		}

		
		DBconn.close();
		
		return t;
    }
	
	//DB�� ���� Friend_Recode���� �ε�
	public Friend loadFriend(String id){
		
		Friend f = new Friend();
		
		Connection conn = DBconn.getConnection();

		if (conn == null) {
			System.out.println("�����ͺ��̽� ���� ����!!");
			System.exit(0);
		} else {
			System.out.println("�����ͺ��̽� ���� ����!!");

			try {
				
				String sql = "SELECT * FROM Friend_Record WHERE me=?";
				
				PreparedStatement psmt = conn.prepareStatement(sql);

				psmt.setString(1, id);
				
				ResultSet rs = null;
				rs = psmt.executeQuery();
				
				while(rs.next()){
					
					System.out.println("ģ�������ҷ�������--");
					//win, draw, lose, score1, score2 �޵��� ����
					String fid = rs.getString(2); //ģ��ID����
					
					int win = rs.getInt(3);
					int draw = rs.getInt(4);
					int lose = rs.getInt(5);
					int score1 = rs.getInt(6);
					int score2 = rs.getInt(7);
					
					
					String sql2 = "SELECT UUNAME FROM UUSER WHERE UUID = ?";
					
					PreparedStatement psmt2 = conn.prepareStatement(sql2);
					
					psmt2.setString(1,fid);
					
					ResultSet rs2 = null;
					rs2 = psmt2.executeQuery();
					
					String nick = rs2.getString(4);
					
					//id�� �´� nick�����ͼ� id,nick�� ģ����Ͽ�����
					f.addFriend(fid,nick,win,draw,lose,score1,score2);
					
					psmt2.close();
					
				}
				
				System.out.println("ģ�������ҷ�����Ϸ�!!");
				psmt.close();
				
			}
			catch(Exception e){
				System.out.println(e.toString());
			}
		}
		
		DBconn.close();
		
		return f;
	}

	
	 //DB�� ���� Store(Player,Market_Player)���� �ε�
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

					int i=0; //������ǳ� Ȯ���Ϸ��� ���
					while (rs.next()) {

						System.out.println("�ε�1������---"+i);
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
						int price = rs.getInt(11);
						int exp = rs.getInt(12);


						st.addnewPlayer(primaryNum, pname, shoot, dribble, pass, stamina,
								tackle, steal, speed, gk, exp, price);
						i++;

					}
					
					psmt.close();
					
					System.out.println("�ε�1����Ϸ�!!");

					String sql2 = "SELECT * FROM MARKET_PLAYER";

					PreparedStatement psmt2 = conn.prepareStatement(sql2);

					ResultSet rs2 = null;
					rs2 = psmt2.executeQuery();

					i=0;
					while (rs2.next()) {
						
						//�������ʿ���
						System.out.println("�ε�2������---"+i);
						int primaryNum = rs2.getInt(1);
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
						

						st.addoldPlayer(primaryNum, pname, shoot, dribble, pass, stamina,
								tackle, steal, speed, gk, exp, price);
						
						i++;

					}
					
					psmt2.close();
					
					System.out.println("�ε�2����Ϸ�!!");
				} catch (Exception e) {
					System.out.println(e.toString());
				}

			}

			System.out.println("�����ε�Ϸ�!!");
			DBconn.close();

			return st; //Store��ȯ�ϸ鼭 db����
		}

}
