package Menu;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import window.Game;

public class Background {
	
	private BufferedImage image;
	
	private double moveScale = 0;
	private double x;
	private double y;
	private double dx;
	private double dy;
	
	public Background(String s,double ms){
		
		this.moveScale = ms;
		try{
			
			image = ImageIO.read(getClass().getResourceAsStream(s));
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void setPoint(double x,double y){
		this.x = (x *moveScale) % (Game.WIDTH*Game.SCALE);
		this.y = (y* moveScale ) % (Game.HEIGHT*Game.SCALE);
	}
	
	public void setVector(double dx,double dy){
		this.dx = dx;
		this.dy = dy;
	}
	
	public void tick(){
		x +=dx;
		y +=dy;
	}
	
	public void render(Graphics2D g){

		g.drawImage(image, (int)x, (int)y,(Game.WIDTH*Game.SCALE) ,(Game.HEIGHT*Game.SCALE) ,null);
		
		if(x<0){
			g.drawImage(image, (int)x + (Game.WIDTH*Game.SCALE) , (int)y, (Game.WIDTH*Game.SCALE) ,(Game.HEIGHT*Game.SCALE) ,null);

		}
		if(x>0){
			g.drawImage(image, (int ) x -(Game.WIDTH*Game.SCALE) , (int) y,(Game.WIDTH*Game.SCALE) ,(Game.HEIGHT*Game.SCALE) ,null);
		}
	}

}
