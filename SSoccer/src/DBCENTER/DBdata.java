package DBCENTER;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import data.Player;
import data.Team;

public class DBdata {
	
	//���� ���Ž� DB(�����_PLAYER)�� ���� ����
	public void putNewPlayer(Player p, String id){
		
		Connection conn = DBconn.getConnection();

		if (conn == null) {
			System.out.println("�����ͺ��̽� ���� ����!!");

		} else {
			System.out.println("�����ͺ��̽� ���� ����!!");

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
	
	//old���� ���Ž� new���� ���ſ� ���������� �����_PLAYER�� ��������
    public void putOldPlayer(Player p, String id){
		
		Connection conn = DBconn.getConnection();

		if (conn == null) {
			System.out.println("�����ͺ��̽� ���� ����!!");

		} else {
			System.out.println("�����ͺ��̽� ���� ����!!");

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
				
				//MARKET_PLAYER���� ���ŵ� ���� ��������
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
    
  //MARKET_PLAYER�� �Ǹ� �� ���� ���� ����
    public void sellPlayer(Player p, int pri, String id){
		
		Connection conn = DBconn.getConnection();

		if (conn == null) {
			System.out.println("�����ͺ��̽� ���� ����!!");

		} else {
			System.out.println("�����ͺ��̽� ���� ����!!");

			try {
				
				//MARKET_PLAYER�� �Ǹ� �� ���� ���� ����
				
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
				
				//�����_PLAYER���� �Ǹ��� ���� ����
				
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
    
    //ģ����Ͻ� DB ģ�� ���̺�� �ʱ� �� ���� ���� �׸��� ģ�� id�� �´� �г��Ӱ�����
    public String putFriends(String userid, String friendid){
    	
    	String friendNick = null;
    	
    	Connection conn = DBconn.getConnection();

		if (conn == null) {
			System.out.println("�����ͺ��̽� ���� ����!!");

		} else {
			System.out.println("�����ͺ��̽� ���� ����!!");

			try {
				
				//���̺� ģ����Ͻ� 2�� �Էµ� (��,ģ��)/(ģ��,��) �̷������� �Ͼ
				
				//(��,ģ��)
				String sql = "INSERT INTO FRIEND_RECORD(ME,FRIEND,WIN,DRAW,"
						+ "LOSE,ME_SCORE,FRIEND_SCORE)"
						+ "VALUES(?,?,0,0,0,0,0)";
				
				PreparedStatement psmt = conn.prepareStatement(sql);
				
				psmt.setString(1, userid);
				psmt.setString(2, friendid);
				
				psmt.executeUpdate();
				
				
				//(ģ��,��)
				sql = "INSERT INTO FRIEND_RECORD(ME,FRIEND,WIN,DRAW,"
						+ "LOSE,ME_SCORE,FRIEND_SCORE)"
						+ "VALUES(?,?,0,0,0,0,0)";
				
				psmt = conn.prepareStatement(sql);
			
				psmt.setString(1, friendid);
				psmt.setString(2, userid);
				
				psmt.executeUpdate();
				
				
				//ģ�� �̸� �˻��� �г��� ����
				sql = "SELECT * FROM UUSER WHERE UUID = ?";
				
				psmt = conn.prepareStatement(sql);
				
				psmt.setString(1, friendid);
				
				ResultSet rs = null;
				rs = psmt.executeQuery();
				
				while(rs.next()){
				
					friendNick = rs.getString(4);
					
				}
		
				System.out.println("DB�� ģ������ ���ۿϷ�!!");
				psmt.close();
				
			}
				
			catch(Exception e){
				System.out.println(e.toString());
			}
		}
		
		DBconn.close();
		
		//ģ�� �г��� ��ȯ
		return friendNick;
    	
    }
    
    //���������(�����κ�) DB�� ����
    public void UpdateUserTeam(Team t,String id){
    	
    	Connection conn = DBconn.getConnection();

		if (conn == null) {
			System.out.println("�����ͺ��̽� ���� ����!!");

		} else {
			System.out.println("�����ͺ��̽� ���� ����!!");

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
				
				System.out.println("USER_TEAM�� ������� ����Ϸ�!!");
				psmt.close();
				
			}
			catch(Exception e){
				System.out.println(e.toString());
			}
		}
		
		DBconn.close();
    }
    
    //�Ʒ��ϱ�κ� ����
    public void UpdateUserPlayer(Team t,String id){
    	
    	Connection conn = DBconn.getConnection();

		if (conn == null) {
			System.out.println("�����ͺ��̽� ���� ����!!");

		} else {
			System.out.println("�����ͺ��̽� ���� ����!!");

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
				
				
				
				System.out.println(id+"_PLAYER�� ������� ����Ϸ�!!");
				
			}
			catch(Exception e){
				System.out.println(e.toString());
			}
		}
		
		DBconn.close();
    }
    
    //���Ӱ�� DB�� ����(����ȸ�ġ)
    
    public void putResult(String id, int myscore, int otherscore){
    	
    	Connection conn = DBconn.getConnection();

		if (conn == null) {
			System.out.println("�����ͺ��̽� ���� ����!!");

		} else {
			System.out.println("�����ͺ��̽� ���� ����!!");
			
			try{
				
				System.out.println("����������!!");
				
				//update�� �߰����ֱ� ���� �̸� DB���ִ� ����������
				
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
				
				//���� ���ھ���� ��,��,�а��� ����
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
				
				//���ھ��߰�
				score += myscore;
			
				
				//���� �ٽ� DB�� �����ºκ�
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
				
				System.out.println("�������ϿϷ�!!");
				
			}
			catch(Exception e){
				System.out.println(e.toString());
			}
			
		}
		
		DBconn.close();
    }
    
			
    //���Ӱ�� DB�� ����(ģ���� ����)
    public void putResult(String id, String friendid, int myscore, int friendscore){
    	
    	Connection conn = DBconn.getConnection();

		if (conn == null) {
			System.out.println("�����ͺ��̽� ���� ����!!");

		} else {
			System.out.println("�����ͺ��̽� ���� ����!!");
			try{
				
				System.out.println("����������!!");
				
				int win = 0;
				int draw = 0;
				int lose = 0;
				int mscore = 0;
				int fscore = 0;
				
				//DB���� ����� ������ ������
				
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
				
				//���� ����Ȱ� ����
				if(myscore>friendscore){
					win++;
					
				}
				else if(myscore==friendscore){
					draw++;
				}
				else{
					lose++;
				}
				
				//���ھ��
				mscore+=myscore;
				fscore+=friendscore;
				
				//���� DB�� ����������
				//(��,ģ��)���ͺ�����
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
				
				//(ģ��,��)���� ������
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
				
				System.out.println("�������ϿϷ�!!");
			}
			catch(Exception e){
				System.out.println(e.toString());
			}
		}
		
		DBconn.close();
    }
		
				
			

}
