import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class KeyInput extends KeyAdapter {
	Handler handler; //game handler
	Game game; //game - main
	private BufferedImage shotImage; //bullet image
	private Camera camera; //camera
	
	
	public KeyInput(Handler handler,Game game, Camera camera){ 
		this.handler = handler; //handler initialization
		this.camera = camera; //camera initialization
	}
	
	public void keyPressed(KeyEvent e){ //keyboard input method
		int key = e.getKeyCode(); //get keyboard input
		BufferedImageLoader loader = new BufferedImageLoader(); //image loader class
		shotImage = loader.loadImage("/shot.png"); //bring bullet image

		for(int i = 0;i<handler.sprites.size();i++){ //for all sprites
			Sprite tempSprite = handler.sprites.get(i); //for each sprite

			if(tempSprite.getId() == ObjectId.StarShip){ //starship key event
				//Arrow Key controll starship movement
				if(key == KeyEvent.VK_RIGHT) tempSprite.setVelX(5); //move right 
				if(key == KeyEvent.VK_LEFT) tempSprite.setVelX(-5); //move left
				if(key == KeyEvent.VK_UP) tempSprite.setVelY(-5); //move forward
				if(key == KeyEvent.VK_DOWN) tempSprite.setVelY(5);  //move down
				if(key == KeyEvent.VK_SPACE){ //SPACE BAR = bullet
					if(game.LEVEL == 1) 
						handler.addSprite((new Shot(tempSprite.getX() + 55,tempSprite.getY() + 30, ObjectId.Shot, shotImage, handler, camera)));
					if(game.LEVEL == 2){ //use upgraded bullet image in level 2
						handler.addSprite((new ShotUpgrade(tempSprite.getX() + 28,tempSprite.getY() + 30, ObjectId.Shot, shotImage, handler, camera)));
						handler.addSprite((new ShotUpgrade(tempSprite.getX() + 78,tempSprite.getY() + 30, ObjectId.Shot, shotImage, handler, camera)));
					}
				}	
				
			}

		}
				
		if(key == KeyEvent.VK_ESCAPE){  //esc  = Game over
			System.exit(1);
		}
	}
	
	public void keyReleased(KeyEvent e){ //key realasing method
		int key = e.getKeyCode(); //read keyboard input

		for(int i = 0;i<handler.sprites.size();i++){ //for all game sprites
			Sprite tempSprite = handler.sprites.get(i); //each game sprite
			
			if(tempSprite.getId() == ObjectId.StarShip){ //starship keyevent
				if(key == KeyEvent.VK_RIGHT) tempSprite.setVelX(0); //release right
				if(key == KeyEvent.VK_LEFT) tempSprite.setVelX(0); //release left
				if(key == KeyEvent.VK_UP) tempSprite.setVelY(0); //release up
				if(key == KeyEvent.VK_DOWN) tempSprite.setVelY(0); //release down
			}
		}
				
		
	}
}
