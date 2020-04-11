package window;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	
	private BufferedImage img;
	
	public SpriteSheet (BufferedImage img){
		this.img = img;
	}
	
	public BufferedImage grabImage(int x,int y,int width,int height){
		BufferedImage image = img.getSubimage(x, y, width, height);
		return image;
	}

}
