package data;

import DBCENTER.DBdata;
import store.Store;

//로그인시 DB에서 불러오는 것들 실행시켜주는곳
public class Load {
	
	//DB이용
	DBdata db = new DBdata();
	//가지고있는변수들
	Store st;
	
	public Load(){
		
		st = db.loadStore();
		
		
	}
	
	public Store getStore(){
		return this.st;
	}

}
