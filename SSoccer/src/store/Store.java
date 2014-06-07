package store;

import java.util.ArrayList; //arrayList�̿�

import data.Player;
import data.Team;

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
	
	public SPlayer buyNewPlayer(int index, Team team){
		
		SPlayer temp = newPlayer.get(index);
		team.addPlayer(temp);
		return temp;
		
	}
	
    public SPlayer buyOldPlayer(int index, Team team){
		
    	SPlayer temp = oldPlayer.get(index);
		team.addPlayer(temp);
		
		//oldplayer�� ���� ������ ��Ͽ��� ����
		oldPlayer.remove(index);
		op.remove(index);
		
		return temp;
		
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
    
    public SPlayer getNewPlayerIndex(int index){
    	return newPlayer.get(index);
    }
    
    public SPlayer getOldPlayerIndex(int index){
    	return oldPlayer.get(index);
    }
}
