package store;

import java.util.ArrayList; //arrayList�̿�

public class Store {

	//�űԼ��� ���
	ArrayList<SPlayer> newPlayer = new ArrayList<SPlayer>();
	//��������ڰ� ����ϴ� �������
	ArrayList<SPlayer> oldPlayer = new ArrayList<SPlayer>();
	//�űԼ��� String ���
	ArrayList<String> np = new ArrayList<String>();
	//��������� String ���
	ArrayList<String> op = new ArrayList<String>();

	
	
	public Store(){
		
	}
	
	
	//�űԼ��� ��Ͽ� ���� �߰� + String��Ͽ��� �߰�
	public void addnewPlayer(int pN, String n, int s, int d, int p, int st, int tk, int sl,
			int sp, int gk, int exp, int price){
		
		SPlayer temp = new SPlayer(pN, n,s,d,p,st,tk,sl,sp,gk,exp,price);
		newPlayer.add(temp);
		np.add(temp.toString());
		
		
	}
	
	//�������� ��Ͽ� ���� �߰� + String��Ͽ��� �߰�
	public void addoldPlayer(int pN, String n, int s, int d, int p, int st, int tk, int sl,
			int sp, int gk, int exp,  int price){
		
		SPlayer temp = new SPlayer(pN, n,s,d,p,st,tk,sl,sp,gk,exp,price);
		oldPlayer.add(temp);
		op.add(temp.toString());
	}
	
	//�űԼ��� getter
	public ArrayList getNewPlayer(){
		
		return this.newPlayer;
		
	}
	
	//��������ڰ� ����ϴ� ���� getter
    public ArrayList getOldPlayer(){
		
		return this.oldPlayer;
		
	}
    
    //�űԼ��� String[] getter
    public String[] getnp(){
    	
    	String[] temp= this.np.toArray(new String[np.size()]); //arrayList -> String[]	
    	return temp;
    }

    //��������ڰ� ����ϴ� ���� String[] getter
    public String[] getop(){
    	
    	String[] temp= this.op.toArray(new String[op.size()]); //arrayList -> String[]	
    	return temp;
    }
}
