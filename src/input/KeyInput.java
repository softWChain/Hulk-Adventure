package input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import Menu.Menu;
import framework.GameObject;
import framework.Handler;
import framework.ID;

public class KeyInput extends KeyAdapter{
	
	private Menu menu;
	private Handler handler;
	
	public KeyInput(Menu menu,Handler handler){
		this.menu = menu;
		this.handler=handler;
	}
	
	public void keyPressed(KeyEvent e){
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			menu.select();
		}
		
		if(e.getKeyCode()== KeyEvent.VK_UP){
			menu.currentChoice--;
			if(menu.currentChoice == -1){
				menu.currentChoice = menu.options.length -1;
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN){
			menu.currentChoice++;
			if(menu.currentChoice == menu.options.length){
				menu.currentChoice = 0;
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
			System.exit(0);
		}
		
		for(int i=0;i<handler.object.size();i++){
			GameObject temp = handler.object.get(i);
			if(temp.getId() == ID.Player){
				if(e.getKeyCode() == KeyEvent.VK_D){
					handler.setRight(true);
				}
				if(e.getKeyCode() == KeyEvent.VK_A){
					handler.setLeft(true);
				}
				if(e.getKeyCode() == KeyEvent.VK_SPACE &&!(temp.isJumping())){
					temp.setJumping(true);
					temp.setVelY(-8);
				}
				if(e.getKeyCode() == KeyEvent.VK_SHIFT){
					handler.setSprith(true);
				}
			}
		}

		
	}
	
	public void keyReleased(KeyEvent e){
		
		for(int i=0;i<handler.object.size();i++){
			GameObject temp = handler.object.get(i);
			if(temp.getId() == ID.Player){
				if(e.getKeyCode() == KeyEvent.VK_D){
					handler.setRight(false);
				}
				if(e.getKeyCode() == KeyEvent.VK_A){
					handler.setLeft(false);
				}
				if(e.getKeyCode() == KeyEvent.VK_SHIFT){
					handler.setSprith(false);
				}
			}
		}
		
	}

}
