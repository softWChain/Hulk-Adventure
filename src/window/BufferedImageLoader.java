package window;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class BufferedImageLoader {
	
	private BufferedImage image;
	
	public BufferedImage Loader(String path){
		try{
			image = ImageIO.read(getClass().getResource(path));
		}catch(Exception e){
			System.err.println("SYSTEM ERROR");
			e.printStackTrace();
		}
		return image;
		
	}
	
	
}
