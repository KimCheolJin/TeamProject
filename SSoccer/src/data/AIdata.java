package data;

import java.util.ArrayList;

import DBCENTER.DBload;


public class AIdata {
	
	Team t;
	int randomNumber;
	DBload dbl = new DBload();
	
	public AIdata(){
		
	}
	
	//Player�� list�� ����
	public void setTeam(ArrayList<Player> list){
		
		//0~9����
		randomNumber = (int)(Math.random()*10);
		//0,1 �ΰ��� �켱 ���õǵ��� ��
		randomNumber = randomNumber%2;
		
		String tempName="�ƽ���"; //����Ʈ�� ���� ���ҽ� ����
		ArrayList<Player> templist = new ArrayList<Player>();
		
		int num=0;
		
		//�ش��ϴ� �������� ������
		if(randomNumber==0){
			//�ƽ��η� ���õ�
			
			tempName="�ƽ���";
			
			num=0;
			
		}
		else if(randomNumber==1){
			//�ǽ�Ƽ�� ���õ�
			
			tempName="�ǽ�Ƽ";
			
			num=22;
			
		}
		else{
		
		}
		
		for(int i=num; i<num+21; i++){
			templist.add(list.get(i));
		}
		
		//DB���� ������ ������
		t = dbl.loadAIteam(tempName);
		t.setPlayer(templist);
		
	}
	
	//getTeam�Ҷ����� �������� ���ٲ�
	public Team getTeam(Load data){
		
		this.setTeam(data.getStore().getNewPlayer());
		return this.t;
	}

}
