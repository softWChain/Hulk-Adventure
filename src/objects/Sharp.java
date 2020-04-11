package objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import framework.GameObject;
import framework.Handler;
import framework.ID;
import window.Game;
import window.SpriteSheet;

public class Sharp extends GameObject{

	private Handler handler;
	private BufferedImage image;
	private int ticks =0;
	
	public Sharp(float x, float y, ID id,Handler handler,SpriteSheet ss) {
		super(x, y, id);
		width=32;
		height=32;
		this.handler=handler;
		
		image = ss.grabImage(150, 38, 145, 141);
		
	}

	@Override
	public void tick() {

	}

	@Override
	public void render(Graphics2D g) {
		g.drawImage(image, (int)x,(int)y,width,height,null);
		ticks++;
		
		if(ticks>=101){
			ticks=0;
		}
		
		for(int i=0;i<handler.object.size();i++){
			GameObject temp = handler.object.get(i);
			if(temp.getId() == ID.Player){
				if(getBounds().intersects(temp.getBounds())){
					if(ticks%50 == 0){
						Hud.health -= 1.5f;
						if(Hud.health<=0){
							Hud.health=0;
						}
						
					}
				}
			}
		}

	}

	@Override
	public Rectangle getBounds() {
		
		return new Rectangle((int)x,(int)y,width,height);
	}
	
	public Rectangle getBoundsTop(){
		
		return new Rectangle( (int)x + width/4,(int) y + width/8, width/2,height/2);
	}

}
