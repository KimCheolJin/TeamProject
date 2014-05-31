package DBCENTER;

import java.sql.*;

//DB�� ������
public class DBconn {
	
	private static Connection dbConn;
	
	public static Connection getConnection(){
		
		if(dbConn==null){
			
			try{
				String url = "jdbc:oracle:thin:@localhost:1521:orcl";
				String user = "TEAM"; //�� ����Ŭ id ���
				String pwd = "vmvm22"; //�� ����Ŭ pwd ���
				
				//������ ���̽��� �ε����� �ش�.
				Class.forName("oracle.jdbc.driver.OracleDriver");
				
				//�־��� �����ͺ��̽� URL������ ����.
				dbConn = DriverManager.getConnection(url, user, pwd);
			}
			catch(Exception e){
				System.out.println(e.toString());
			}
		}
		
		return dbConn;
	}
	
	public static void close(){
		if(dbConn != null){
			
			try{
				//isClose()�� ���� ����Ŭ ���ᰴü�� ������� Ȯ��
				if(!dbConn.isClosed())
					dbConn.close(); //���� ����� ����Ŭ ��ü�� ������� ����
				
			}
			catch(Exception e){
				System.out.println(e.toString());
			}
		}
		
		dbConn = null;
	}
	

}
