package objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import framework.GameObject;
import framework.Handler;
import framework.ID;
import window.Animation;
import window.SpriteSheet;

public class Phoenix extends GameObject{
	
	private Handler handler;
	private BufferedImage[] phoenix_image = new BufferedImage[16];
	private Animation animFront,animLeft,animRight,animBack;
	private float diffX,diffY,distance;
	private Random rand = new Random();
	private int choose = 0;
	private int ticks = 0;

	public Phoenix(float x, float y, ID id,Handler handler,SpriteSheet ss) {
		super(x, y, id);
		this.handler = handler;
		health=30;
		width=48;
		height=48;
		
		try{
			//FRONT
			phoenix_image[0] = ss.grabImage(0, 20, 96, 49);
			phoenix_image[1] = ss.grabImage(103, 32, 81, 51);
			phoenix_image[2] = ss.grabImage(191, 17, 96, 45);
			phoenix_image[3] = ss.grabImage(290, 12, 86, 51);
			
			//LEFT
			phoenix_image[4] = ss.grabImage(12, 114, 71, 58);
			phoenix_image[5] = ss.grabImage(104, 126, 73, 58);
			phoenix_image[6] = ss.grabImage(203, 112, 74, 58);
			phoenix_image[7] = ss.grabImage(297, 98, 75, 75);
			
			//RIGHT
			phoenix_image[8] = ss.grabImage(12, 208, 72, 59);
			phoenix_image[9] = ss.grabImage(110, 220, 76, 60);
			phoenix_image[10] = ss.grabImage(204, 211, 72, 54);
			phoenix_image[11] = ss.grabImage(298, 191, 75, 77);
			
			//BACK
			phoenix_image[12] = ss.grabImage(0, 308, 95, 59);
			phoenix_image[13] = ss.grabImage(98, 311, 91, 44);
			phoenix_image[14] = ss.grabImage(188, 310, 98, 58);
			phoenix_image[15] = ss.grabImage(293, 284, 84, 88);
			
			animFront = new Animation(8, phoenix_image[0],phoenix_image[1],phoenix_image[2],phoenix_image[3]);
			animLeft = new Animation(8, phoenix_image[4],phoenix_image[5],phoenix_image[6],phoenix_image[7]);
			animRight = new Animation(8, phoenix_image[8],phoenix_image[9],phoenix_image[10],phoenix_image[11]);
			animBack = new Animation(8, phoenix_image[12],phoenix_image[13],phoenix_image[14],phoenix_image[15]);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void tick() {
		
		x +=velX;
		y +=velY;
		ticks++;
		if(ticks<=105){
			ticks=0;
		}
		
		choose = rand.nextInt(30);
		
		animFront.runAnimation();
		animLeft.runAnimation();
		animRight.runAnimation();
		animBack.runAnimation();
		
		
		
		
		for(int i=0;i<handler.object.size();i++){
			GameObject temp = handler.object.get(i);
			if(temp.getId() == ID.Player){
				
				diffX = x - temp.getX() - width;
				diffY = y - temp.getY() - height;
				
				distance = (float) Math.sqrt((x - temp.getX())*(x - temp.getX())+(y - temp.getY())*(y - temp.getY()));
			}
			
			if(temp.getId() == ID.BlockGrass){
				if(getBoundsBig().intersects(temp.getBounds())){
					velX += (velX*2) * -1;
					velY += (velY*2) * -1;
				}
				else if(choose == 0){
					velX = (rand.nextInt(2 - -2) + -2);
					velY = (rand.nextInt(2 - -2) + -2);
					
				}
			}
			if(temp.getId() == ID.BlockPowder){
				if(getBoundsBig().intersects(temp.getBounds())){
					velX += (velX*2) * -1;
					velY += (velY*2) * -1;
				}
				else if(choose == 0){
					velX = (rand.nextInt(4 - -4) + -4);
					velY = (rand.nextInt(4 - -4) + -4);
					
				}
			}
			if(temp.getId() == ID.Bullet){
				if(getBounds().intersects(temp.getBounds())){
					health = health - 3;
						
					handler.removeObject(temp);
					if(health<=0){
						handler.removeObject(this);
					}
				}
			}
		}
		
		if(distance<300){
			velX = (float)(-1/distance)*diffX;
			velY = (float)(-1/distance)*diffY;
		}
		else if(choose != 0){
			velX = 0;
			velY = 0;
		}
		 
	}

	@Override
	public void render(Graphics2D g) {
		
		for(int i=0;i<handler.object.size();i++){
			GameObject temp = handler.object.get(i);
			if(temp.getId() == ID.Player){
				if(velX == 0 && velY == 0){
					animFront.drawAnimation(g, (int)x, (int)y, width, height);
				}
				else if(Math.abs(temp.getX() - x) > Math.abs(temp.getY() - y)){
					if(velX>0){
						animRight.drawAnimation(g, (int)x, (int)y, width, height);
					}
					if(velX<0){
						animLeft.drawAnimation(g, (int)x, (int)y, width, height);
					}
				}
				else{
					animBack.drawAnimation(g, (int)x, (int)y, width, height);
				}
			}
			if(temp.getId() == ID.Player){
				if(getBounds().intersects(temp.getBounds())){
					
					if(ticks%50==0){
						Hud.health -=0.1f;
					}
					if(Hud.health <=0){
						Hud.health = 0;
					}
				}
			}
		}

		
	}

	@Override
	public Rectangle getBounds() {
		
		return new Rectangle((int)x, (int)y, width, height);
	}

	
	public Rectangle getBoundsBig() {
		
		return new Rectangle((int)x - width/4, (int)y - height/4, width + width/2, height + height/2);
	}
	
	

}
