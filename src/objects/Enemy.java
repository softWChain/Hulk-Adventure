package objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import framework.GameObject;
import framework.Handler;
import framework.ID;
import window.Animation;
import window.SpriteSheet;

public class Enemy extends GameObject {

	private Handler handler;
	private BufferedImage[] enemy_image = new BufferedImage[8];
	private Animation animationLeft,animationRight;
	
	public Enemy(float x, float y, ID id,Handler handler,SpriteSheet ss) {
		super(x, y, id);
		this.handler = handler;
		width=32;
		height=32;
		health =5;
		setVelX(1);
		try{
			
			//LEFT
			enemy_image[0] = ss.grabImage(10, 51, 26, 42);
			enemy_image[1] = ss.grabImage(51, 51, 29, 42);
			enemy_image[2] = ss.grabImage(94, 51, 24, 42);
			enemy_image[3] = ss.grabImage(136, 53, 28, 40);
			
			//RIGHT
			enemy_image[4] = ss.grabImage(2, 99, 25, 42);
			enemy_image[5] = ss.grabImage(43, 100, 29, 41);
			enemy_image[6] = ss.grabImage(89, 99, 21, 51);
			enemy_image[7] = ss.grabImage(127, 101, 28, 39);
			
			animationLeft = new Animation(30, enemy_image[0],enemy_image[1],enemy_image[2],enemy_image[3]);
			animationRight = new Animation(30, enemy_image[4],enemy_image[5],enemy_image[6],enemy_image[7]);
			
		}catch(Exception e){
			e.printStackTrace();
		}

	}

	@Override
	public void tick() {
		
		x +=velX;
		y +=velY;
		animationLeft.runAnimation();
		animationRight.runAnimation();
		
		collosion();

	}

	@Override
	public void render(Graphics2D g) {
		
		for(int i=0;i<handler.object.size();i++){
			GameObject temp = handler.object.get(i);
			if(temp.getId() == ID.Enemy){
				if(velX<0){
					animationLeft.drawAnimation(g, (int)x, (int)y, width, height);
				}
				else if(velX>0){
					animationRight.drawAnimation(g, (int)x, (int)y, width, height);
				}
			}
		}
		
		/*
		g.setColor(Color.CYAN);
		g.draw(getBoundsTop());
		g.draw(getBoundsBottom());
		g.draw(getBoundsLeft());
		g.draw(getBoundsRight());
		
	*/
		
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
			
			if(temp.getId() == ID.Bullet){
				if(getBoundsLeft().intersects(temp.getBounds()) ||
						getBoundsRight().intersects(temp.getBounds())||
						getBoundsTop().intersects(temp.getBounds())||
						getBoundsBottom().intersects(temp.getBounds())){
					
					health--;
					if(health<=0){
						handler.removeObject(this);
					}
				}
			}
			


		}
	}

	@Override
	public Rectangle getBounds() {
		
		return new Rectangle((int)x,(int) y,width,height);
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
	public Rectangle getBoundsNew() {
		
		return new Rectangle((int)x - width/2,(int) y -height/2,width*2,height*2);
	}
	

}
