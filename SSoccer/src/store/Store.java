package store;

import data.SPlayer;

import javax.swing.JList;

import java.util.ArrayList;

public class Store {

	//신규선수 목록
	ArrayList<SPlayer> newPlayer = new ArrayList<SPlayer>();
	//기존사용자가 사용하던 선수목록
	ArrayList<SPlayer> oldPlayer = new ArrayList<SPlayer>();
	//신규선수 String 목록
	ArrayList<String> np = new ArrayList<String>();
	//기존사용자 String 목록
	ArrayList<String> op = new ArrayList<String>();

	
	
	public Store(){
		
	}
	
	
	//신규선수 목록에 선수 추가
	public void addnewPlayer(String n, int s, int d, int p,int st, int tk, int sl, int sp, int gk,int price, int exp){
		
		SPlayer temp = new SPlayer(n,s,d,p,st,tk,sl,sp,gk,price,exp);
		newPlayer.add(temp);
		np.add(temp.toString());
		
		
	}
	
	//기존선수 목록에 선수 추가
	public void addoldPlayer(String n, int s, int d, int p,int st, int tk, int sl, int sp, int gk, int price, int exp){
		
		SPlayer temp = new SPlayer(n,s,d,p,st,tk,sl,sp,gk,price,exp);
		oldPlayer.add(temp);
		op.add(temp.toString());
	}
	
	//신규선수 getter
	public ArrayList getNewPlayer(){
		
		return this.newPlayer;
		
	}
	
	//기존사용자가 사용하던 선수 getter
    public ArrayList getOldPlayer(){
		
		return this.oldPlayer;
		
	}
    
    //신규선수 Stirng[] getter
    public String[] getnp(){
    	
    	String[] temp= this.np.toArray(new String[np.size()]);
    	return temp;
    }

    //기존사용자가 사용하던 선수 Stirng[] getter
    public String[] getop(){
    	
    	String[] temp= this.op.toArray(new String[op.size()]);
    	return temp;
    }
}
