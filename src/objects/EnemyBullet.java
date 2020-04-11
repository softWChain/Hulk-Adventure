package objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import framework.GameObject;
import framework.Handler;
import framework.ID;
import window.Animation;
import window.Game;
import window.SpriteSheet;

public class EnemyBullet extends GameObject{
	
	private Point2D dest;

	private Handler handler;
	private float mx,my;
	private BufferedImage bullet_image;
	private Animation bulletAnimation;
	
	public EnemyBullet(float x, float y, ID id,Handler handler,float mx,float my,SpriteSheet ss) {
		super(x, y, id);
		this.handler = handler;
		width = 18;
		height = 18;
		speed = 6;
		this.mx=mx;
		this.my=my;
		followBullet();
		
		try{
			
			bullet_image = ss.grabImage(4, 1, 30, 44);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}	
	}

	@Override
	public void tick() {
	
		x += velX;
		y += velY;
		
		collosion();
	
	}
	@Override
	public void render(Graphics2D g) {
		g.drawImage(bullet_image, (int)x,(int)y,width,height,null);
		
		
		
		for(int i=0;i<handler.object.size();i++){
			GameObject temp = handler.object.get(i);
			if(temp.getId() == ID.EnemyBullet){
				if(x >Game.WIDTH*Game.SCALE*9 || x<-100 ||
						y>Game.HEIGHT*Game.SCALE*9  || y<-300){
					handler.removeObject(this);
				
				}
			}


		}
		
		
	}
	
	public void followBullet(){
		
		double diffX = mx -x ;
		double diffY = my -y ;
		
		double distance = Math.sqrt(diffX*diffX + diffY*diffY);
		
		
		velX =  (float) ((1/distance)*diffX)*speed;
		velY =  (float) ((1/distance)*diffY)*speed;

	}
	
	
	
	private void collosion(){
		for(int i=0;i<handler.object.size();i++){
			GameObject temp = handler.object.get(i);
			if(temp.getId() == ID.BlockGrass || temp.getId() == ID.BlockPowder ){
				if(getBounds().intersects(temp.getBounds())){
					handler.removeObject(this);
				}
			}
			if(temp.getId() == ID.Player){
				if(getBounds().intersects(temp.getBounds())){
					Hud.health -=5;
					if(Hud.health<=0){
						Hud.health =0;
					}
					
					handler.removeObject(this);
				}
			}
			
		}
	}


	@Override
	public Rectangle getBounds() {
		
		return new Rectangle((int) x,(int) y,width,height);
	}

}
