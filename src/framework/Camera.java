package framework;

import window.Game;

public class Camera {
	
	private float x,y;
	
	public Camera(float x,float y){
		this.x=x;
		this.y=y;
	}
	
	public void tick(GameObject object){
		x =  -object.getX() + Game.WIDTH*Game.SCALE / 2;
		y =  -object.getY() + Game.HEIGHT * Game.SCALE /2 +100;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	
	
	 

}
