package objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import framework.GameObject;
import framework.Handler;
import framework.ID;
import window.Animation;
import window.Game;
import window.SpriteSheet;

public class Player extends GameObject{
	
	private Handler handler;
	private BufferedImage[] player_Images = new BufferedImage[9];
	private float gravity = 0.2f;
	private float MAX_SPEED = 10f;
	
	private Animation leftAnimation,rightAnimation;

	public Player(float x, float y, Game game, ID id ,Handler handler,SpriteSheet ss) {
		super(x, y, id);
		this.handler =handler;
		width =24;
		height =64;
		speed=1;
		try{
			//NORMAL
			player_Images[0] =ss.grabImage(2, 1, 34, 52);
			
			//LEFT
			player_Images[1] =ss.grabImage(9, 56, 21, 53);
			player_Images[2] =ss.grabImage(49, 57, 23, 52);
			player_Images[3] =ss.grabImage(89, 57, 21, 52);
			player_Images[4] =ss.grabImage(128, 57, 24, 53);
			
			//RIGHT
			player_Images[5] =ss.grabImage(9, 112, 18, 53);
			player_Images[6] =ss.grabImage(48, 113, 21, 52);
			player_Images[7] =ss.grabImage(89, 112, 19, 53);
			player_Images[8] =ss.grabImage(127, 113, 22, 52);
			
			leftAnimation = new Animation(10, player_Images[1],player_Images[2],player_Images[3],player_Images[4]);
			rightAnimation = new Animation(10,player_Images[5],player_Images[6],player_Images[7],player_Images[8]);
			
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void tick() {
		
		x +=velX*speed;
		y +=velY*speed;
		
		
		if(falling || jumping){
			velY += gravity;
			if(velY>=MAX_SPEED){
				velY = MAX_SPEED;
			}
			
		}
		
		leftAnimation.runAnimation();
		rightAnimation.runAnimation();
		
		if(handler.isRight()){
			velX = 3;
		}
		else if(!handler.isLeft()){
			velX =0;
		}
		if(handler.isLeft()){
			velX = -3;
		}
		else if(!handler.isRight()){
			velX  = 0;
		}
		
		if(handler.isSprith()){
			speed=1.5f;
		}else{
			speed=1;
		}
		
		collosion();


		
	}

	@Override
	public void render(Graphics2D g) {
		
		if(velX<0){
			leftAnimation.drawAnimation(g, (int)x, (int) y,width-3,height);
			
		}else if(velX>0){
			rightAnimation.drawAnimation(g, (int)x , (int) y,width-3,height);
		}else{
			g.drawImage(player_Images[0], (int) x, (int) y,width+8,height,null);
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
			if(temp.getId() == ID.BlockGrass ){
				if(getBoundsBottom().intersects(temp.getBounds())){
					y = temp.getY() - height;
					velY = 0;
					falling = false;
					jumping = false;
				}
				else{
					falling = true;
				}
				
				if(getBoundsLeft().intersects(temp.getBounds())){
					x = temp.getX() + temp.getWidth();
					velX=0;
				}
				if(getBoundsRight().intersects(temp.getBounds())){
					x = temp.getX() - temp.getWidth();
					velX=0;
				}

			}
			if(temp.getId() == ID.BlockPowder ){
				
				if(getBoundsBottom().intersects(temp.getBounds())){
					y = temp.getY() - height;
					velY = 0;
					falling = false;
					jumping = false;
				}
				else{
					falling = true;
				}
				
				if(getBoundsTop().intersects(temp.getBounds())){
					y = temp.getY() +temp.getHeight();
					velY=0;
				}
				if(getBoundsLeft().intersects(temp.getBounds())){
					x = temp.getX() + temp.getWidth();
					velX=0;
				}
				if(getBoundsRight().intersects(temp.getBounds())){
					x = temp.getX() - temp.getWidth();
					velX=0;
				}
			}
			if(temp.getId() == ID.MovingSaquareHorizontal){
				
				if(getBoundsBottom().intersects(temp.getBounds())){
					y = temp.getY() - height;
					velY = 0;
					falling = false;
					jumping = false;
				}
				else{
					falling = true;
				}
				
				if(getBoundsTop().intersects(temp.getBounds())){
					y = temp.getY() +temp.getHeight();
					velY=0;
				}
				if(getBoundsLeft().intersects(temp.getBounds())){
					x = temp.getX() + temp.getWidth() ;
					velX=0;
				}
				if(getBoundsRight().intersects(temp.getBounds())){
					x = temp.getX() - temp.getWidth();
					velX=0;
				}
			}
			
			if(temp.getId() == ID.MovingSaquareVertical){
				
				if(getBoundsBottom().intersects(temp.getBounds())){
					y = temp.getY() - height;
					velY = 0;
					falling = false;
					jumping = false;
				}
				else{
					falling = true;
				}
				
				if(getBoundsTop().intersects(temp.getBounds())){
					y = temp.getY() +temp.getHeight();
					velY=0;
				}
				if(getBoundsLeft().intersects(temp.getBounds())){
					x = temp.getX() + temp.getWidth() ;
					velX=0;
				}
				if(getBoundsRight().intersects(temp.getBounds())){
					x = temp.getX() - temp.getWidth();
					velX=0;
				}
			}
			
			if(temp.getId() == ID.Enemy){
				if(getBounds().intersects(temp.getBounds())){
					
					Hud.health -= 0.1f;
					if(Hud.health<=0){
						Hud.health=0;
					}
				}
				
			}

		}
		
	}

	@Override
	public Rectangle getBounds() {
		
		return new Rectangle( (int)x,(int) y, width,height);
	}
	public Rectangle getBoundsTop(){
		
		return new Rectangle( (int)x + width/4,(int) y, width/2,height/2);
	}
	public Rectangle getBoundsBottom() {
		
		return new Rectangle( (int)x + width/4,(int) y +height/2, width/2,height/2);
	}
	public Rectangle getBoundsLeft() {
		
		return new Rectangle( (int)x,(int) y + height/8, width/4,height/2 + height/4 );
	}
	public Rectangle getBoundsRight() {
		
		return new Rectangle( (int)x + width/2 + width/4,(int) y + height/8, width/2,height/2 + height/4);
	}

}
