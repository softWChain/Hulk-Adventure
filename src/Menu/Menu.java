package Menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import objects.Hud;
import window.Game;

public class Menu {
	
	
	public int currentChoice = 0;
	private Font font;
	
	private Background bg;
	private Game game;
	
	public String[] options = {
			"START",
			"HELP",
			"EXİT",
	};
	

	
	public Menu(Game game){
		this.game = game;
		
		if(game.gameState == STATE.Menu){
			
			font = new Font("Arial",Font.PLAIN,45);
			bg = new Background("/zz.jpg", 1);
			bg.setVector(-0.08, 0);
		}
		
		
	}
	
	public void tick(){
		
		if(game.gameState == STATE.Menu){
			bg.tick();
		
		}
	}
	
	public void render(Graphics2D g){
		
		if(game.gameState == STATE.Menu){
		g.setFont(font);
			
			bg.render(g);
			for(int i=0;i<options.length;i++){
				if(i == currentChoice){
					g.setColor(Color.CYAN);
				}else{
					g.setColor(Color.RED);
				}
				
				g.drawString(options[i], 270, 160 + i*60);
				g.drawRect(270, 122+ i*55, 150, 50);

			
	
			}
		}
			
		
	}
	
	public void select(){
		
		if(game.gameState == STATE.Menu ){
			if(currentChoice == 0){
				game.gameState = STATE.Game;
			}
			if(currentChoice == 1){
				//HELP
			}
			if(currentChoice == 2){
				System.exit(0);
			}
		}
		}

}
