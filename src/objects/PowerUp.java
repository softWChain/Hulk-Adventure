package objects;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import framework.GameObject;
import framework.Handler;
import framework.ID;
import window.SpriteSheet;

public class PowerUp extends GameObject{
	
	private Handler handler;
	private BufferedImage power_image;
	

	public PowerUp(float x, float y, ID id,Handler handler,SpriteSheet ss) {
		super(x, y, id);
		this.handler= handler;
		width=32;
		height=32;
		
		power_image = ss.grabImage(5, 35, 18, 18);
		
	}

	@Override
	public void tick() {
		
		collosion();
	
		
	}

	@Override
	public void render(Graphics2D g) {
		
		g.drawImage(power_image, (int)x,(int)y,null);
		
	}
	
	public void collosion(){
		for(int i=0;i<handler.object.size();i++){
			GameObject temp = handler.object.get(i);
			if(temp.getId() == ID.Player){
				if(getBounds().intersects(temp.getBounds())){
					handler.removeObject(this);
					Hud.health +=5;
					if(Hud.health>=120){
						Hud.health = 120;
					}
				}
			}
		}
	}

	@Override
	public Rectangle getBounds() {
		
		return new Rectangle((int)x,(int)y,width,height);
	}

}
