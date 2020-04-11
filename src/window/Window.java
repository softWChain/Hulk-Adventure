package window;



import java.awt.Dimension;

import javax.swing.JFrame;

public class Window {
	private JFrame frame;
	
	public Window(int width,int height,int scale,String title,Game game){
		
		frame = new JFrame();
		
		game.setPreferredSize(new Dimension(width*scale,height*scale));
		game.setMaximumSize(new Dimension(width*scale,height*scale));
		game.setMaximumSize(new Dimension(width*scale,height*scale));
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(game);
		frame.pack();
		frame.setTitle(title);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public JFrame getFrame(){
		return frame;
	}

}
