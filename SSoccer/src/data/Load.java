package data;

import DBCENTER.DBdata;
import store.Store;

//�α��ν� DB���� �ҷ����� �͵� ��������ִ°�
public class Load {
	
	//DB�̿�
	DBdata db = new DBdata();
	//�������ִº�����
	Store st;
	
	public Load(){
		
		st = db.loadStore();
		
		
	}
	
	public Store getStore(){
		return this.st;
	}

}
