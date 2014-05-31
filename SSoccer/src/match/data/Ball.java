package match.data;


public class Ball {
	public double X = 360;
	public double Y = 215;
	public MPlayer player;
	public int leftAtk = 1; // 2 왼쪽공격, 0 오른쪽공격, 1은 주인없음.
	public double H = 0;
	public double vX, vY, vH;
	
	public void move(){
		if(H > 0) vH -= 0.02;
		else if(H < 0){
			vH = 0;
			H = 0;
		}
		
		if(vX > 0.1) vX -= 0.1;
		else if (vX < -0.1) vX += 0.1;
		if(vY > 0.1) vY -= 0.1;
		else if(vY < -0.1) vY += 0.1;
		
		X += vX;
		Y += vY;
		H += vH;
	}
}
