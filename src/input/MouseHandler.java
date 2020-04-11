package input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import framework.Camera;
import framework.GameObject;
import framework.Handler;
import framework.ID;
import objects.Bullet;
import window.Game;

public class MouseHandler extends MouseAdapter{

	private Game game;
	private Handler handler;
	private Camera camera;
	
	public MouseHandler(Game game,Handler handler,Camera camera){
		this.game=game;
		this.handler=handler;
		this.camera = camera;
	}


	public void mousePressed(MouseEvent e) {
		float mx = e.getX() -camera.getX();
		float my = e.getY() -camera.getY();
		
		for(int i=0;i<handler.object.size();i++){
			GameObject temp = handler.object.get(i);
			if(temp.getId() == ID.Player){
				handler.addObject(new Bullet(temp.getX() +16, temp.getY() +16, ID.Bullet, handler, mx, my,game.bullet_sheet));
			}
		}
		
	}

	
	public void mouseReleased(MouseEvent e) {
		
		
	}

}
