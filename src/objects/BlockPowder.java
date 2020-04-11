package objects;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import framework.GameObject;
import framework.Handler;
import framework.ID;
import window.SpriteSheet;

public class BlockPowder extends GameObject{
	
	private Handler handler;
	private BufferedImage imagePowder;

	public BlockPowder(float x, float y, ID id,Handler handler,SpriteSheet ss) {
		super(x, y, id);
		this.handler = handler;
		width=32;
		height=32;
		
		try
		{
			imagePowder = ss.grabImage(192, 66, 32, 32);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void tick() {
	
		
	}

	@Override
	public void render(Graphics2D g) {

		g.drawImage(imagePowder, (int) x, (int) y,null);
	}

	@Override
	public Rectangle getBounds() {
	
		return new Rectangle( (int)x,(int) y, width,height);
	}

}
