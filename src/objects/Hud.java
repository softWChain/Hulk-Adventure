package objects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class Hud {
	
	public static float health = 120;
	public static int point=0;
	
	
	public void tick(){
		
		
	}
	
	public void render(Graphics2D g){
		g.setColor(Color.RED);
		g.fillRect(20, 20, 120, 20);
		g.setColor(Color.RED);		
		g.drawRect(20, 20, 120, 20);
		g.setColor(Color.CYAN);
		g.fillRect(20, 20, (int)health, 20);
		g.setFont(new Font("Gothic Century",Font.PLAIN,16));
		g.drawString("SCORE : " +point, 570, 20);

	}
	
	
	
	public float getHEALTH() {
		return health;
	}
	public void setHEALTH(float hEALTH) {
		health = hEALTH;
	}
	
	

	
	

}
