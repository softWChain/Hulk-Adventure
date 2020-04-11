package objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import framework.GameObject;
import framework.Handler;
import framework.ID;
import window.SpriteSheet;

public class BlockGrass extends GameObject{
	
	private Handler handler;
	private BufferedImage imageGrass,imagePowder;

	public BlockGrass(float x, float y, ID id,Handler handler,SpriteSheet ss) {
		super(x, y, id);
		this.handler = handler;
		width=32;
		height=32;
		
		try
		{
			imageGrass = ss.grabImage(2, 2, 32, 32);

		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void tick() {
	
		
	}

	@Override
	public void render(Graphics2D g) {

		g.drawImage(imageGrass, (int) x, (int) y,null);

	}

	@Override
	public Rectangle getBounds() {
	
		return new Rectangle( (int)x,(int) y, width,height);
	}

}
