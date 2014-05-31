package DBCENTER;

import java.sql.*;

//DB와 연결담당
public class DBconn {
	
	private static Connection dbConn;
	
	public static Connection getConnection(){
		
		if(dbConn==null){
			
			try{
				String url = "jdbc:oracle:thin:@localhost:1521:orcl";
				String user = "TEAM"; //팀 오라클 id 사용
				String pwd = "vmvm22"; //팀 오라클 pwd 사용
				
				//데이터 베이스를 로딩시켜 준다.
				Class.forName("oracle.jdbc.driver.OracleDriver");
				
				//주어진 데이터베이스 URL연결을 해줌.
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
				//isClose()는 현재 오라클 연결객체의 연결상태 확인
				if(!dbConn.isClosed())
					dbConn.close(); //현재 연결된 오라클 객체의 연결상태 닫음
				
			}
			catch(Exception e){
				System.out.println(e.toString());
			}
		}
		
		dbConn = null;
	}
	

}
