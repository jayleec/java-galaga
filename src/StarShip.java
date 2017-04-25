import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class StarShip extends Sprite{	
	private float width = 70, height = 50;  //starship size
	private Handler handler; //handler
	private Camera camera; //camera
	private Image particleImage; //particle image

	public StarShip(float x, float y, Handler handler, ObjectId id, Image image, Camera camera) {
		super(x, y, id, image);
		// TODO Auto-generated constructor stub
		this.handler = handler;
		this.camera = camera;
		 
		velY--; //basic movement toward front
		BufferedImageLoader loader = new BufferedImageLoader();
		particleImage = loader.loadImage("");
		
	}
	
	@Override
	public void tick(LinkedList<Sprite> sprites) {
		// TODO Auto-generated method stub

		if((velX<0) && x < 0){ //prevent go further than 0
			return;
		}
		
		if((velX>0) && (x> 1290 - 70)){ //prevent go further than 1290
			return;
		}
		x += velX; //x coordinate movement
		y += velY; //y coordinate movement
		Collision(sprites);//collision check method
	}

	
	private void Collision(LinkedList<Sprite> sprites){//collision check method
		
		for(int i=0; i<handler.sprites.size();i++){ //for all sprites
			Sprite tempSprite = handler.sprites.get(i);	 //each sprite check
			//meet level flag switch level	
			if(tempSprite.getId() == ObjectId.LevelUp){  //if starship meet Level up coin
				if(getBounds().intersects(tempSprite.getBounds())){
					handler.switchLevel(); //remove all sprite than change level
					handler.removeSprite(tempSprite); //remove level up coin
				}
			}
			
			if(tempSprite.getId() == ObjectId.AlienType2){  //if starship meet AlienType2 (Level 2)
				if(getBounds().intersects(tempSprite.getBounds())){
					//particle effect
					handler.addSprite(new Particle(x, y,ObjectId.Particle,particleImage, handler));
				}
			}
			
			if(tempSprite.getId() == ObjectId.Alien){//if starship meet Alien (Level 1)
				if(getBounds().intersects(tempSprite.getBounds())){
					//particle effect
					handler.addSprite(new Particle(x, y,ObjectId.Particle,particleImage,handler));
				}	
			}
			
			if(tempSprite.getId() == ObjectId.AlienEgg){ //if starship meet AlienEgg (Level 2)
				if(getBounds().intersects(tempSprite.getBounds())){
					Health.HEALTH -= 5; //decrease health 5
					handler.removeSprite(tempSprite); //remove AlienEgg
				}
			}
			
			if(tempSprite.getId() == ObjectId.AlienBoss){ //if starship meet AlienBoss (Level 2)
				if(getBounds().intersects(tempSprite.getBounds())){
					Health.HEALTH -= 5; //decrease health 5
				}
			}

		}
		
	}
	
	@Override
	public void render(Graphics g) { //rendering
		g.drawImage(image, (int)x, (int)y,  null);
	}

	@Override
	public Rectangle getBounds() { //detect starship boundaries
		return new Rectangle((int)x, (int)y, (int)width, (int)height);
	}
	
	
}
