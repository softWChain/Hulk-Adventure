package objects;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import framework.GameObject;
import framework.Handler;
import framework.ID;
import window.SpriteSheet;

public class MovingSquareHorizontal extends GameObject{
	
	private Handler handler;
	private BufferedImage image;

	public MovingSquareHorizontal(float x, float y, ID id,Handler handler,SpriteSheet ss) {
		super(x, y, id);
		this.handler = handler;
		width=32;
		height = 32;
		setVelX(1);
		try{
			
			image = ss.grabImage(94, 122, 104, 128);
			
		}catch(Exception e){
			
			e.printStackTrace();
			
		}

	}

	@Override
	public void tick() {
		x +=velX;
		collosion();
		
		
	}

	@Override
	public void render(Graphics2D g) {
		g.drawImage(image, (int)x,(int)y,width,height,null);
		
		
	}
	
	private void collosion(){
		for(int i=0;i<handler.object.size();i++){
			GameObject temp = handler.object.get(i);
			if(temp.getId() == ID.BlockPowder|| temp.getId() == ID.BlockGrass){
				if(getBoundsRight().intersects(temp.getBounds())){
					velX =-1;
				}
				if(getBoundsLeft().intersects(temp.getBounds())){
					velX =1;
				}
			}


		}
	}

	@Override
	public Rectangle getBounds() {
		
		return new Rectangle((int)x,(int)y,width,height);
	}
	
	public Rectangle getBoundsTop(){
		
		return new Rectangle( (int)x +width/4,(int) y -height/8, width/2,height/2);
	}
	public Rectangle getBoundsBottom() {
		
		return new Rectangle( (int)x + width/4,(int) y +height/2, width/2,height/2);
	}
	public Rectangle getBoundsLeft() {
		
		return new Rectangle( (int)x - width/8,(int) y + height/4, width/4,height/2 );
	}
	public Rectangle getBoundsRight() {
		
		return new Rectangle( (int)x + width +width/16 -width/8  ,(int) y + height/4, width/4,height/2);
	}

}
