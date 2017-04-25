import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Game extends Canvas implements Runnable { //Game (main) class

	public static final int WIDTH = 1280, HEIGHT = WIDTH/12*9; //frame size

	private boolean running = false;  //default running false
	private Thread thread;  //thread
	private Camera camera; //camera
	public boolean cameraOn = true;  //camera on /off
	private Health health;  //score bar
	private Spawn spawn;  //health bar

	private BufferedImage starsImage; //background image
	
	//Game Level
	public static int LEVEL = 1; //starting level = 1
	
	//Sprites handler
	Handler handler;
	
	private void init(){ //game initialization
		BufferedImageLoader loader = new BufferedImageLoader(); //load image class

		starsImage = loader.loadImage("/stars.png"); //background stars
		handler = new Handler(this, camera); //HANDLER 
		camera = new Camera(0,880); //CAMERA initial location
		this.addKeyListener(new KeyInput(handler, this, camera));//KEYLISTENER
		
		handler.defaultLevel(); //initialize level 1
		
		health = new Health(handler); //starship health
		spawn = new Spawn(handler, health); //health box
	}

	
	public synchronized void start(){ //GAME START
		if(running) return; //already running do nothing
		
		running = true; //change running to true
		thread = new Thread(this); 
		thread.start(); //start thread
	}
	
	public void threadSleep(int sec) throws InterruptedException{ //thread sleep method
		thread.sleep(sec);
	}
	
	public synchronized void stop() throws InterruptedException{ //GAME STOP
		if(!running) return; //already not running do nothing
		
		running = false; //change running to false
		thread.join(); //thread stop
	}
	

	public void run(){ //game running
		init(); //initialize game sprites, background, level
		this.requestFocus();
		
		long lastTime = System.nanoTime();  //check nanoseconds (=> one billion ns = 1 sec)
		double amountOfTicks = 60.0; //ticks per frame
		double ns = 1000000000 / amountOfTicks; //ns per frame
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;


	//GAME LOOP
		while(running){
			long now = System.nanoTime(); //get current nanoseconds
			delta += (now - lastTime)/ns; //term between now and last tick() as nonosec
			lastTime = now; //update last time
			while(delta >= 1){ //game updates
				tick(); //game update
				updates++; 
				delta--;
			}

			render(); //game rendering
			frames++; //
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: "+frames + "TICKS: "+updates);//print on console
				frames = 0;
				updates = 0;
			}

		}//end of while(running)
	}//end of run
	
	
	private void tick(){ 	//updates game
		handler.tick(); //update handler
		health.tick(); //update health of starship
		
		if(cameraOn == true){ //when camera set on
			camera.tick(); //update camera
		}
	}
	
	
	private void render(){ //game rendering method
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);  // to organize memory of Canvas //maximum 3
			return;
		}
		Graphics g = bs.getDrawGraphics(); //to draw game 
		Graphics2D g2d = (Graphics2D)g;  //for camera change to graphics2d
		
		//////////////////graphics goes here

		g.setColor(Color.BLACK);  //backgound color
		g.fillRect(0, 0, getWidth(), getHeight()); //fill background
		
		health.render(g);  //set up fixed score bar

		g2d.translate(camera.getX(), camera.getY()); //BEGIN OF CAMERA

		for(int x = 0; x<starsImage.getHeight()*10; x += starsImage.getHeight())  //background start image
			g.drawImage(starsImage, 300, -x, this); //repeat background image
		
			handler.render(g); //RENDERING
		
		g2d.translate(-camera.getX(), -camera.getY());  //END OF CAMERA
	
		g.dispose();
		bs.show(); //show graphics
	}


	public static int clamp(int var, int min, int max){ //prevent escaping from game frame
		if(var >= max) //maximum coordinate
			return var = max;
		else if(var <=min) //minimum coordinate
			return var = min;
		else
			return var;
	}
	
	
	public static void main(String args[]){ //Game main method
		new InsideWindow(WIDTH, HEIGHT, "Galaga 2016", new Game());
	}


}
