package objects;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import framework.Camera;
import framework.GameObject;
import framework.Handler;
import framework.ID;
import window.Animation;
import window.Game;
import window.SpriteSheet;

public class Bullet extends GameObject{
	
	private Point2D dest;

	private Handler handler;
	private float mx,my;
	private BufferedImage bullet_image;
	private Animation bulletAnimation;
	private Camera camera;
	
	public Bullet(float x, float y, ID id,Handler handler,float mx,float my,SpriteSheet ss) {
		super(x, y, id);
		this.handler = handler;
		width = 18;
		height = 18;
		speed = 5;
		camera = new Camera(0,0);
		this.mx=mx;
		this.my=my;
		followBullet();
		
		try{
			
			bullet_image = ss.grabImage(87, 3, 23, 21);
			
			
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
			if(temp.getId() == ID.Bullet){
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
			if(temp.getId() == ID.Enemy){
				if(getBounds().intersects(temp.getBounds())){
					handler.removeObject(this);
					Hud.point +=5;
				}
			}
		}
	}


	@Override
	public Rectangle getBounds() {
		
		return new Rectangle((int) x,(int) y,width,height);
	}

}
