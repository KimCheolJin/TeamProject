package store;

import java.util.ArrayList; //arrayList이용

import data.Player;
import data.Team;

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
	
	public SPlayer buyNewPlayer(int index, Team team){
		
		SPlayer temp = newPlayer.get(index);
		team.addPlayer(temp);
		return temp;
		
	}
	
    public SPlayer buyOldPlayer(int index, Team team){
		
    	SPlayer temp = oldPlayer.get(index);
		team.addPlayer(temp);
		
		//oldplayer의 경우는 상점과 목록에서 삭제
		oldPlayer.remove(index);
		op.remove(index);
		
		return temp;
		
	}
	
	
	//신규선수 목록에 선수 추가 + String목록에도 추가
	public void addnewPlayer(int pN, String n, int s, int d, int p, int st, int tk, int sl,
			int sp, int gk, int exp, int price){
		
		SPlayer temp = new SPlayer(pN, n,s,d,p,st,tk,sl,sp,gk,exp,price);
		newPlayer.add(temp);
		np.add(temp.toString());
		
		
	}
	
	//기존선수 목록에 선수 추가 + String목록에도 추가
	public void addoldPlayer(int pN, String n, int s, int d, int p, int st, int tk, int sl,
			int sp, int gk, int exp,  int price){
		
		SPlayer temp = new SPlayer(pN, n,s,d,p,st,tk,sl,sp,gk,exp,price);
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
    
    //신규선수 String[] getter
    public String[] getnp(){
    	
    	String[] temp= this.np.toArray(new String[np.size()]); //arrayList -> String[]	
    	return temp;
    }

    //기존사용자가 사용하던 선수 String[] getter
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
    
    //능력치검색 부분
    //신규선수 능력치검색
    public String[] searchNewPlayer(String s, String d, String p, String st, String t, String sl, String sp, String gk){
    	
    	//콤보박스로 전달받은 값들 int로 변환
    	int snum = Integer.parseInt(s);
    	int dnum = Integer.parseInt(d);
    	int pnum = Integer.parseInt(p);
    	int stnum = Integer.parseInt(st);
    	int tnum = Integer.parseInt(t);
    	int slnum = Integer.parseInt(sl);
    	int spnum = Integer.parseInt(sp);
    	int gknum = Integer.parseInt(gk);
    	
    	//조건에 해당하는 선수들 받는 임시 리스트 
    	ArrayList<SPlayer> templist = new ArrayList<SPlayer>();
    	
    	for(int i=0; i<newPlayer.size(); i++){
    		
    		//비교를 위해 해당선수의 변수들 가져옴
    		int psnum = newPlayer.get(i).shoot;
    		int pdnum = newPlayer.get(i).dribble;
    		int ppnum =  newPlayer.get(i).pass;
    		int pstnum =  newPlayer.get(i).stamina;
    		int ptnum =  newPlayer.get(i).tackle;
    		int pslnum =  newPlayer.get(i).steal;
    		int pspnum =  newPlayer.get(i).speed;
    		int pgknum =  newPlayer.get(i).gk;
    		
    		//무지긴 조건 발생 .. . . .  8가지 .. ㅋㅋㅋ8가지조건맞추는건 자유도 높고 좋아보이나 실질적으론 오히려역효과날듯ㅠ
    		if(psnum>=snum && pdnum>=dnum && ppnum>=pnum && pstnum>=stnum && ptnum>=tnum && pslnum>=slnum && pspnum>=spnum && pgknum>=gknum){
    			
    			//조건에 해당하는 선수 임시리스트에 추가
    			templist.add(newPlayer.get(i));
    			
    		}
    		
    		//gk같이 and쓰니 검색이 너무 안나와서 따로 빼놈 골키퍼는 gk빼고 모든 능력치가
    		//50 이하라서 다행이도 위에서 중복되지 않음 ㅎㅎ
    		//이 걸로 골키퍼만 볼 수 있지만 선수들 보려면 00이상으로 설정해야함
    		if(pgknum>=gknum){
    			templist.add(newPlayer.get(i));
    		}
    		
    	}
    	
    	//이제 조건에 해당하는 선수들 String[] 바꿔주기
    	
    	String[] temp = new String[templist.size()];
    	
    	for(int i=0; i<templist.size(); i++){
    		temp[i] = templist.get(i).toString();
    	}
    	
    	return temp;
   
    }
    
  //기존선수 능력치검색
    public String[] searchOldPlayer(String s, String d, String p, String st, String t, String sl, String sp, String gk){
    	
    	//콤보박스로 전달받은 값들 int로 변환
    	int snum = Integer.parseInt(s);
    	int dnum = Integer.parseInt(d);
    	int pnum = Integer.parseInt(p);
    	int stnum = Integer.parseInt(st);
    	int tnum = Integer.parseInt(t);
    	int slnum = Integer.parseInt(sl);
    	int spnum = Integer.parseInt(sp);
    	int gknum = Integer.parseInt(gk);
    	
    	//조건에 해당하는 선수들 받는 임시 리스트 
    	ArrayList<SPlayer> templist = new ArrayList<SPlayer>();
    	
    	for(int i=0; i<oldPlayer.size(); i++){
    		
    		//비교를 위해 해당선수의 변수들 가져옴
    		int psnum = oldPlayer.get(i).shoot;
    		int pdnum = oldPlayer.get(i).dribble;
    		int ppnum =  oldPlayer.get(i).pass;
    		int pstnum =  oldPlayer.get(i).stamina;
    		int ptnum =  oldPlayer.get(i).tackle;
    		int pslnum =  oldPlayer.get(i).steal;
    		int pspnum =  oldPlayer.get(i).speed;
    		int pgknum =  oldPlayer.get(i).gk;
    		
    		//무지긴 조건 발생 .. . . .  8가지 .. ㅋㅋㅋ8가지조건맞추는건 자유도 높고 좋아보이나 실질적으론 오히려역효과날듯ㅠ
    		if(psnum>=snum && pdnum>=dnum && ppnum>=pnum && pstnum>=stnum && ptnum>=tnum && pslnum>=slnum && pspnum>=spnum){
    			
    			//조건에 해당하는 선수 임시리스트에 추가
    			templist.add(oldPlayer.get(i));
    			
    		}
    		
    		//gk같이 and쓰니 검색이 너무 안나와서 따로 빼놈 골키퍼는 gk빼고 모든 능력치가
    		//50 이하라서 다행이도 위에서 중복되지 않음 ㅎㅎ
    		//이 걸로 골키퍼만 볼 수 있지만 선수들 보려면 00이상으로 설정해야함
    		if(pgknum>=gknum){
    			templist.add(oldPlayer.get(i));
    		}
    		
    	}
    	
    	//이제 조건에 해당하는 선수들 String[] 바꿔주기
    	
    	String[] temp = new String[templist.size()];
    	
    	for(int i=0; i<templist.size(); i++){
    		temp[i] = templist.get(i).toString();
    	}
    	
    	return temp;
   
    }
    
}
