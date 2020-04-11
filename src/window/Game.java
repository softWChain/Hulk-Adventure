package window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import Menu.Menu;
import Menu.STATE;
import framework.Camera;
import framework.Handler;
import framework.ID;
import input.KeyInput;
import input.MouseHandler;
import objects.BlockGrass;
import objects.BlockPowder;
import objects.Enemy;
import objects.Explosion;
import objects.Hud;
import objects.MovingSquareHorizontal;
import objects.MovingSquareVertical;
import objects.Phoenix;
import objects.Player;
import objects.PowerUp;
import objects.Sharp;
import objects.SmartEnemy;

@SuppressWarnings("serial")
public class Game extends Canvas implements Runnable{
	
	
	public static int WIDTH = 352;
	public static int HEIGHT = 256;
	public static int SCALE = 2;
	public String TITLE = "Pre-Alpha 0.0.3";
	public STATE gameState = STATE.Menu;
	
	public static Point2D mousePoint = new Point(0,0);
	
	private boolean running = false;
	private Window window;
	private Thread thread;
	private Menu menu;
	private Handler handler;
	private Camera camera;
	private Hud hud;
	

	private BufferedImage level;
	
	private SpriteSheet block_grass_sheet;
	private SpriteSheet block_powder_sheet;
	private BufferedImage block_image;
	
	private SpriteSheet player_sheet;
	private BufferedImage player_image;
	
	private SpriteSheet enemy_sheet;
	private BufferedImage enemy_image;

	private SpriteSheet power_sheet;
	private BufferedImage power_image;
	
	private SpriteSheet smartEnemy_sheet;
	private BufferedImage smartEnemy_image;
	
	private SpriteSheet sharp_sheet;
	private BufferedImage sharp_image;
	
	private SpriteSheet movingSquareHorizontal_sheet;
	private SpriteSheet movingSquareVertical_sheet;
	private BufferedImage movingSquare_image;
	
	private SpriteSheet phoenix_sheet;
	private BufferedImage phoenix_image;
	
	public static SpriteSheet explosion_sheet;
	public static BufferedImage explosion_image;
	
	public static SpriteSheet bullet_sheet;
	public static BufferedImage bullet_image;
	
	public static BufferedImage enemyBullet_image;
	public static SpriteSheet enemyBullet_sheet;
	
	
	private int ticks=0;
	
	
	public Game(){
		setFocusable(true);
		window = new Window(WIDTH,HEIGHT,SCALE,TITLE,this);
		
	}
	
	public void init(){
		menu = new Menu(this);
		handler = new Handler();
		camera = new Camera(0,0);
		hud = new Hud();
		BufferedImageLoader loader = new BufferedImageLoader();

		level = loader.Loader("/level1.png");
	
		
	
		player_image = loader.Loader("/hulk.png");
		player_sheet = new SpriteSheet(player_image);
		
		block_image = loader.Loader("/block_image.png");
		block_grass_sheet = new SpriteSheet(block_image);
		block_powder_sheet = new SpriteSheet(block_image);
		
		enemy_image = loader.Loader("/shiva.png");
		enemy_sheet= new SpriteSheet(enemy_image);
		
		power_image = loader.Loader("/items.png");
		power_sheet = new SpriteSheet(power_image);
		
		smartEnemy_image = loader.Loader("/tower.png");
		smartEnemy_sheet = new SpriteSheet(smartEnemy_image);
		
		bullet_image = loader.Loader("/fire2.png");
		bullet_sheet = new SpriteSheet(bullet_image);
		
		enemyBullet_image = loader.Loader("/enemy_fire.png");
		enemyBullet_sheet = new SpriteSheet(enemyBullet_image);
		
		sharp_image = loader.Loader("/sharp.jpg");
		sharp_sheet = new SpriteSheet(sharp_image);
		
		movingSquare_image = loader.Loader("/hqdefault.jpg");
		movingSquareHorizontal_sheet = new SpriteSheet(movingSquare_image);
		movingSquareVertical_sheet = new SpriteSheet(movingSquare_image);
		
		explosion_image = loader.Loader("/explosion.png");
		explosion_sheet = new SpriteSheet(explosion_image);
		
		phoenix_image = loader.Loader("/phoenix.png");
		phoenix_sheet = new SpriteSheet(phoenix_image);
		
		mapLoading(level);
		addKeyListener(new KeyInput(menu,handler));
		addMouseListener(new MouseHandler(this,handler,camera));
	}
	
	public void tick(){
		
		if(gameState == STATE.Game){
			
			if(Hud.health !=0 && Hud.health>0){
				for(int i=0;i<handler.object.size();i++){
					if(handler.object.get(i).getId() == ID.Player){
						camera.tick(handler.object.get(i));
					}
				}
				
				handler.tick();
				
		
				hud.tick();
			}
	
		}
		else if(gameState == STATE.Menu){
			menu.tick();
		}
			
	}
	public void render(){
		
		BufferStrategy bs= this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics2D g = (Graphics2D)  bs.getDrawGraphics();
		
		if(gameState == STATE.Game){
		
			if(Hud.health != 0 && Hud.health > 0){
				ticks++;
				g.setColor(Color.BLACK);
				g.fillRect(0, 0, WIDTH*SCALE, HEIGHT*SCALE);
				g.translate(camera.getX(), camera.getY());
				handler.render(g);
				g.translate(-camera.getX(), -camera.getY());
		
				if(ticks<100){
					if(ticks%5 == 0){
						g.setColor(Color.BLACK);
						g.fillRect(0, 0, WIDTH*SCALE, HEIGHT*SCALE);
						g.setFont(new Font("Century Gothics",Font.PLAIN,18));
						String s = "  -   M A P 1   -";
						int length = (int) g.getFontMetrics().getStringBounds(s, g).getWidth();
						g.setColor(new Color(255,255,255));
						g.drawString(s, (WIDTH*SCALE - (length))/2 , (HEIGHT*SCALE)/2);
					}
				}
				else {
					ticks = 101;
				}

				hud.render(g);
			}

			else if(Hud.health<= 0){
				g.setColor(Color.BLACK);
				g.fillRect(0, 0, WIDTH*SCALE, HEIGHT*SCALE);
				g.setFont(new Font("Century Gothics",Font.PLAIN,18));
				String s = "  - G A M E  -  O V E R   -";
				int length = (int) g.getFontMetrics().getStringBounds(s, g).getWidth();
				g.setColor(new Color(255,255,255));
				g.drawString(s, (WIDTH*SCALE - (length))/2 , (HEIGHT*SCALE)/2);
			}
			
			
			
		}
		else if(gameState == STATE.Menu){
			menu.render(g);
		}

		bs.show();
		g.dispose();
	}
	
	private void mapLoading(BufferedImage img){
		int width= img.getWidth();
		int height = img.getHeight();
		
		for(int yy=0;yy<height;yy++){
			for(int xx=0;xx<width;xx++){
				
				int pixel = img.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int blue = (pixel >> 8) & 0xff;
				int green = (pixel) & 0xff;

				
				if(red == 64 && blue == 64 && green == 64){
					handler.addObject(new BlockGrass(xx*32, yy*32, ID.BlockGrass,handler,block_grass_sheet));
				}
				if(red == 255 && blue == 255 && green == 255){
					handler.addObject(new BlockPowder(xx*32, yy*32, ID.BlockPowder,handler,block_powder_sheet));
				}
			
				if(red == 255 && blue == 0 && green == 0){
					handler.addObject(new Player(xx*32, yy*32, this,ID.Player, handler,player_sheet));
				}
				
				if(red == 127 &&  green == 0 && blue == 0){
					handler.addObject(new Enemy(xx*32, yy*32, ID.Enemy, handler,enemy_sheet));
				}
				
				if(red == 192 &&  green == 192 && blue == 192){
					handler.addObject(new PowerUp(xx*32, yy*32, ID.PowerUp, handler,power_sheet));
				}
				if(red == 48 &&  green == 48 && blue == 48){
					handler.addObject(new SmartEnemy(xx*32, yy*32, ID.SmartEnemy, handler,this,smartEnemy_sheet));
				}
				if(red == 96 &&  green == 96 && blue == 96){
					handler.addObject(new Sharp(xx*32, yy*32, ID.Sharp, handler,sharp_sheet));
				}
				if(red == 127 &&  green == 63 && blue == 63){
					handler.addObject(new MovingSquareHorizontal(xx*32, yy*32, ID.MovingSaquareHorizontal, handler,movingSquareHorizontal_sheet));
				}
				if(red == 191 &&  green == 191 && blue == 191){
					handler.addObject(new MovingSquareVertical(xx*32, yy*32, ID.MovingSaquareVertical, handler,movingSquareVertical_sheet));
				}
				if(red == 48 &&  green == 48 && blue == 48){
					handler.addObject(new Explosion(xx*32, yy*32, ID.Explosion, handler,explosion_sheet));
				}
				if(red == 160 &&  green == 160 && blue == 160){
					handler.addObject(new Phoenix(xx*32, yy*32, ID.Phoenix, handler,phoenix_sheet));
				}


			}
				
		}
	}
	
	public void run(){
		
		init();
		
		double FPS = 60.0;
		double delta = 0;
		double framePerSecond = 1000000000 / FPS;
		
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		long now;
		
		int ticks = 0;
		int updates = 0;
		
		while(running){
			now = System.nanoTime();
			delta += (now - lastTime ) / framePerSecond;
			lastTime = now;
			if(delta >= 1){
				tick();
				ticks++;
				delta--;
			}
			render();
			updates++;
			
			if(System.currentTimeMillis() - timer >= 1000){
				timer += 1000;
				//System.err.println((TITLE + "    -   FPS : " + ticks + " UPDATES : " + updates));
				window.getFrame().setTitle((TITLE + "    -   FPS : " + ticks + " UPDATES : " + updates));
				ticks = 0;
				updates = 0;
				
			}
		}
		
		stop();
		
	}
	

	
	public synchronized void start(){
		
		if(running)
			return;
		running = true;
		thread = new Thread(this,"THREAD");
		thread.start();
	}
	
	public synchronized void stop(){
		
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	

	public static void main(String[] args){
		
		new Game().start();
		
	}

}
