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

public class SmartEnemy extends GameObject{
	
	private Handler handler;
	private Game game;
	private BufferedImage[] smart_enemy = new BufferedImage[15];
	private Animation rightAnimation1,rightAnimation2,rightAnimation3;
	private Animation leftAnimation1,leftAnimation2,leftAnimation3;
	private int ticks = 0;
	private int ticks1 = 0;
	


	public SmartEnemy(float x, float y, ID id,Handler handler,Game game,SpriteSheet ss) {
		super(x, y, id);
		this.handler = handler;
		this.game = game;
		width=64;
		height=65;
		health =width;

		
		try{

			//NORMAL
			smart_enemy[0] = ss.grabImage(907, 405, 83, 104);
			
			//RIGHT
			smart_enemy[1] = ss.grabImage(781, 406, 82, 94);
			smart_enemy[2] = ss.grabImage(653, 406, 82, 94);
			smart_enemy[3] = ss.grabImage(525, 406, 82, 94);
			smart_enemy[4] = ss.grabImage(397, 406, 82, 94);
			smart_enemy[5] = ss.grabImage(269, 406, 82, 94);
			smart_enemy[6] = ss.grabImage(141, 406, 82, 94);
			smart_enemy[7] = ss.grabImage(13, 406, 82, 94);

			
			//LEFT
			smart_enemy[8] = ss.grabImage(12, 532, 82, 94);
			smart_enemy[9] = ss.grabImage(140, 532, 82, 94);
			smart_enemy[10] = ss.grabImage(268, 532, 82, 94);
			smart_enemy[11] = ss.grabImage(396, 532, 82, 94);
			smart_enemy[12] = ss.grabImage(524, 532, 82, 94);
			smart_enemy[13] = ss.grabImage(652, 532, 82, 94);
			smart_enemy[14] = ss.grabImage(780, 532, 82, 94);
			
			
			rightAnimation1 = new Animation(8, smart_enemy[7] ,smart_enemy[6]);
			rightAnimation2 = new Animation(8, smart_enemy[5],smart_enemy[4]);
			rightAnimation3 = new Animation(8, smart_enemy[3],smart_enemy[2],smart_enemy[1]);
			
			leftAnimation1 = new Animation(8, smart_enemy[8] ,smart_enemy[9]);
			leftAnimation2 = new Animation(8, smart_enemy[10],smart_enemy[11]);
			leftAnimation3 = new Animation(8, smart_enemy[12],smart_enemy[13],smart_enemy[14]);
			
			
			/*
			rightAnimation = new Animation(8,smart_enemy[1],smart_enemy[2],smart_enemy[3],
					smart_enemy[4],smart_enemy[5],smart_enemy[6],smart_enemy[7]);
			leftAnimation = new Animation(8, smart_enemy[8],smart_enemy[9],smart_enemy[10],
					smart_enemy[11],smart_enemy[12],smart_enemy[13],smart_enemy[14]);
					*/

			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
	

	@Override
	public void tick() {
		collosion();
		rightAnimation1.runAnimation();
		rightAnimation2.runAnimation();
		rightAnimation3.runAnimation();
		leftAnimation1.runAnimation();
		leftAnimation2.runAnimation();
		leftAnimation3.runAnimation();
		
		ticks1++;
		if(ticks>=1001){
			ticks=0;
		}
		for(int i=0;i<handler.object.size();i++){
			GameObject temp = handler.object.get(i);
			if(temp.getId() == ID.Player){
				if(y<temp.getY()){
					if(ticks%100==0){
						handler.addObject(new EnemyBullet((int)x +20, (int)y +70, ID.EnemyBullet, 
								handler, temp.getX(), temp.getY(), game.enemyBullet_sheet));

					}
					
				}
				}
		}
		
		
		
	}

	@Override
	public void render(Graphics2D g) {
		ticks++;
		
		//SMART ENEMY HEALTH LOOKİNG
		g.setColor(Color.RED);
		g.fillRect((int)x, (int)y - 10, 65, 10);
		g.setColor(Color.CYAN);
		g.fillRect((int)x, (int)y - 10, health, 10);
		
		//SMART ENEMY ANİMATİON RIGHTT
		for(int i=0;i<handler.object.size();i++){
			GameObject temp = handler.object.get(i);
			if(temp.getId() == ID.Player){
				if(temp.getX() - x > 350){
					if(ticks%10 == 0){
						rightAnimation1.drawAnimation(g, (int)x, (int)y, width, height);
						if(ticks>=1001){
							ticks=0;
						}
					}
					else{
						g.drawImage(smart_enemy[6],(int)x, (int)y,width,height,null);
						g.drawImage(smart_enemy[7],(int)x, (int)y,width,height,null);
					}
				}
				else if(150 < temp.getX() - x ){
					if(ticks%10 == 0){
						rightAnimation2.drawAnimation(g, (int)x, (int)y, width, height);
						if(ticks>=1001){
							ticks=0;
						}
					}
					else{
						g.drawImage(smart_enemy[5],(int)x, (int)y,width,height,null);
					}
				}
				else if(temp.getX() - x > 0 ){
					if(ticks%10 == 0){
						rightAnimation3.drawAnimation(g, (int)x, (int)y, width, height);
						if(ticks>=1001){
							ticks=0;
						}
					}
					else{
						g.drawImage(smart_enemy[2],(int)x, (int)y,width,height,null);
					}
				}
				
				
				
				//SMART ENEMY ANİMATİON LEFTT
				if(temp.getX() - x < -1){
					if(ticks%10 == 0){
						leftAnimation1.drawAnimation(g, (int)x, (int)y, width, height);
						if(ticks>=1001){
							ticks=0;
						}
					}
					else{
						g.drawImage(smart_enemy[8],(int)x, (int)y,width,height,null);
						g.drawImage(smart_enemy[9],(int)x, (int)y,width,height,null);
					}
				}
				else if(temp.getX() - x < -60){
					if(ticks%10 == 0){
						leftAnimation2.drawAnimation(g, (int)x, (int)y, width, height);
						if(ticks>=1001){
							ticks=0;
						}
					}
					else{
						g.drawImage(smart_enemy[11],(int)x, (int)y,width,height,null);
						g.drawImage(smart_enemy[12],(int)x, (int)y,width,height,null);
					}
				}
				if(temp.getX() - x < -100){
					if(ticks%10 == 0){
						leftAnimation3.drawAnimation(g, (int)x, (int)y, width, height);
						if(ticks>=1001){
							ticks=0;
						}
					}
					else{
						g.drawImage(smart_enemy[13],(int)x, (int)y,width,height,null);
						g.drawImage(smart_enemy[14],(int)x, (int)y,width,height,null);
					}
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
	
	public void collosion(){
		for(int i=0;i<handler.object.size();i++){
			GameObject temp = handler.object.get(i);
			if(temp.getId() == ID.Bullet){
				if(getBoundsBottom().intersects(temp.getBounds()) ||
						getBoundsLeft().intersects(temp.getBounds())||
						getBoundsRight().intersects(temp.getBounds())){
					Hud.point +=15;
					handler.removeObject(temp);
					health -=4;
					if(health<=0){
						handler.removeObject(this);
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
		
		return new Rectangle( (int)x +width/4,(int) y -height/8, width/2,height/2);
	}
	public Rectangle getBoundsBottom() {
		
		return new Rectangle( (int)x + width/4,(int) y +height/2, width/2,height/2);
	}
	public Rectangle getBoundsLeft() {
		
		return new Rectangle( (int)x - width/8,(int) y + height/4, width/4,height/2 + height/4 );
	}
	public Rectangle getBoundsRight() {
		
		return new Rectangle( (int)x + width +width/16 -width/8  ,(int) y + height/4, width/4,height/2 +height/4);
	}

}
