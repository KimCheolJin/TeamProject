package match.data;


public class Ball {
	public double X = 360;
	public double Y = 215;
	public MPlayer player;
	public int leftAtk = 1; // 2 ���ʰ���, 0 �����ʰ���, 1�� ���ξ���.
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
