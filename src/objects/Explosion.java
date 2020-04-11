package objects;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import framework.GameObject;
import framework.Handler;
import framework.ID;
import window.Animation;
import window.SpriteSheet;

public class Explosion extends GameObject{
	
	private Handler handler;
	private BufferedImage[] explosion_image = new BufferedImage[4];
	private Animation explosionAnimation;
	
	
	public Explosion(float x, float y, ID id,Handler handler,SpriteSheet ss) {
		super(x, y, id);
		this.handler = handler;
		width  = 65;
		height = 65;
		
		try{
			
			explosion_image[0] = ss.grabImage(209, 139, 152, 164);
			explosion_image[1] = ss.grabImage(238, 340, 101, 215);
			explosion_image[2] = ss.grabImage(241, 508, 95, 90);
			explosion_image[3] = ss.grabImage(251, 673, 86, 65);
			
			explosionAnimation = new Animation(5, explosion_image[0],explosion_image[1],explosion_image[2],explosion_image[3]);
			
		}
		catch(Exception e){
			System.err.println("SYSTEM ERROR : ");
			e.printStackTrace();
		}
	}


	
	
	public void tick(){
		explosionAnimation.runAnimation();
		
	}
	
	public void render(Graphics2D g){
		
		for(int i=0;i<handler.object.size();i++){
			GameObject temp = handler.object.get(i);
			if(temp.getId() == ID.SmartEnemy){
				if(temp.getHealth() <=4){
					explosionAnimation.drawAnimation(g, temp.getX(), temp.getY(), width, height);
				}
			}
		}
		
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x,(int)y,width,height);
	}

}
