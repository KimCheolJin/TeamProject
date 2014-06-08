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
    
    //�ɷ�ġ�˻� �κ�
    //�űԼ��� �ɷ�ġ�˻�
    public String[] searchNewPlayer(String s, String d, String p, String st, String t, String sl, String sp, String gk){
    	
    	//�޺��ڽ��� ���޹��� ���� int�� ��ȯ
    	int snum = Integer.parseInt(s);
    	int dnum = Integer.parseInt(d);
    	int pnum = Integer.parseInt(p);
    	int stnum = Integer.parseInt(st);
    	int tnum = Integer.parseInt(t);
    	int slnum = Integer.parseInt(sl);
    	int spnum = Integer.parseInt(sp);
    	int gknum = Integer.parseInt(gk);
    	
    	//���ǿ� �ش��ϴ� ������ �޴� �ӽ� ����Ʈ 
    	ArrayList<SPlayer> templist = new ArrayList<SPlayer>();
    	
    	for(int i=0; i<newPlayer.size(); i++){
    		
    		//�񱳸� ���� �ش缱���� ������ ������
    		int psnum = newPlayer.get(i).shoot;
    		int pdnum = newPlayer.get(i).dribble;
    		int ppnum =  newPlayer.get(i).pass;
    		int pstnum =  newPlayer.get(i).stamina;
    		int ptnum =  newPlayer.get(i).tackle;
    		int pslnum =  newPlayer.get(i).steal;
    		int pspnum =  newPlayer.get(i).speed;
    		int pgknum =  newPlayer.get(i).gk;
    		
    		//������ ���� �߻� .. . . .  8���� .. ������8�������Ǹ��ߴ°� ������ ���� ���ƺ��̳� ���������� ��������ȿ�������
    		if(psnum>=snum && pdnum>=dnum && ppnum>=pnum && pstnum>=stnum && ptnum>=tnum && pslnum>=slnum && pspnum>=spnum && pgknum>=gknum){
    			
    			//���ǿ� �ش��ϴ� ���� �ӽø���Ʈ�� �߰�
    			templist.add(newPlayer.get(i));
    			
    		}
    		
    		//gk���� and���� �˻��� �ʹ� �ȳ��ͼ� ���� ���� ��Ű�۴� gk���� ��� �ɷ�ġ��
    		//50 ���϶� �����̵� ������ �ߺ����� ���� ����
    		//�� �ɷ� ��Ű�۸� �� �� ������ ������ ������ 00�̻����� �����ؾ���
    		if(pgknum>=gknum){
    			templist.add(newPlayer.get(i));
    		}
    		
    	}
    	
    	//���� ���ǿ� �ش��ϴ� ������ String[] �ٲ��ֱ�
    	
    	String[] temp = new String[templist.size()];
    	
    	for(int i=0; i<templist.size(); i++){
    		temp[i] = templist.get(i).toString();
    	}
    	
    	return temp;
   
    }
    
  //�������� �ɷ�ġ�˻�
    public String[] searchOldPlayer(String s, String d, String p, String st, String t, String sl, String sp, String gk){
    	
    	//�޺��ڽ��� ���޹��� ���� int�� ��ȯ
    	int snum = Integer.parseInt(s);
    	int dnum = Integer.parseInt(d);
    	int pnum = Integer.parseInt(p);
    	int stnum = Integer.parseInt(st);
    	int tnum = Integer.parseInt(t);
    	int slnum = Integer.parseInt(sl);
    	int spnum = Integer.parseInt(sp);
    	int gknum = Integer.parseInt(gk);
    	
    	//���ǿ� �ش��ϴ� ������ �޴� �ӽ� ����Ʈ 
    	ArrayList<SPlayer> templist = new ArrayList<SPlayer>();
    	
    	for(int i=0; i<oldPlayer.size(); i++){
    		
    		//�񱳸� ���� �ش缱���� ������ ������
    		int psnum = oldPlayer.get(i).shoot;
    		int pdnum = oldPlayer.get(i).dribble;
    		int ppnum =  oldPlayer.get(i).pass;
    		int pstnum =  oldPlayer.get(i).stamina;
    		int ptnum =  oldPlayer.get(i).tackle;
    		int pslnum =  oldPlayer.get(i).steal;
    		int pspnum =  oldPlayer.get(i).speed;
    		int pgknum =  oldPlayer.get(i).gk;
    		
    		//������ ���� �߻� .. . . .  8���� .. ������8�������Ǹ��ߴ°� ������ ���� ���ƺ��̳� ���������� ��������ȿ�������
    		if(psnum>=snum && pdnum>=dnum && ppnum>=pnum && pstnum>=stnum && ptnum>=tnum && pslnum>=slnum && pspnum>=spnum){
    			
    			//���ǿ� �ش��ϴ� ���� �ӽø���Ʈ�� �߰�
    			templist.add(oldPlayer.get(i));
    			
    		}
    		
    		//gk���� and���� �˻��� �ʹ� �ȳ��ͼ� ���� ���� ��Ű�۴� gk���� ��� �ɷ�ġ��
    		//50 ���϶� �����̵� ������ �ߺ����� ���� ����
    		//�� �ɷ� ��Ű�۸� �� �� ������ ������ ������ 00�̻����� �����ؾ���
    		if(pgknum>=gknum){
    			templist.add(oldPlayer.get(i));
    		}
    		
    	}
    	
    	//���� ���ǿ� �ش��ϴ� ������ String[] �ٲ��ֱ�
    	
    	String[] temp = new String[templist.size()];
    	
    	for(int i=0; i<templist.size(); i++){
    		temp[i] = templist.get(i).toString();
    	}
    	
    	return temp;
   
    }
    
}
