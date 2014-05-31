package store;

import data.SPlayer;

import javax.swing.JList;

import java.util.ArrayList;

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
	
	
	//�űԼ��� ��Ͽ� ���� �߰�
	public void addnewPlayer(String n, int s, int d, int p,int st, int tk, int sl, int sp, int gk,int price, int exp){
		
		SPlayer temp = new SPlayer(n,s,d,p,st,tk,sl,sp,gk,price,exp);
		newPlayer.add(temp);
		np.add(temp.toString());
		
		
	}
	
	//�������� ��Ͽ� ���� �߰�
	public void addoldPlayer(String n, int s, int d, int p,int st, int tk, int sl, int sp, int gk, int price, int exp){
		
		SPlayer temp = new SPlayer(n,s,d,p,st,tk,sl,sp,gk,price,exp);
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
    
    //�űԼ��� Stirng[] getter
    public String[] getnp(){
    	
    	String[] temp= this.np.toArray(new String[np.size()]);
    	return temp;
    }

    //��������ڰ� ����ϴ� ���� Stirng[] getter
    public String[] getop(){
    	
    	String[] temp= this.op.toArray(new String[op.size()]);
    	return temp;
    }
}
