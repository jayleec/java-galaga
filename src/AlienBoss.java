import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.LinkedList;

public class AlienBoss extends Sprite { //alien boss class
	private Handler handler; //handler
	private final static int width = 200, height = 200; //boss size
	public AlienBoss(float x, float y, ObjectId id, Image image, Handler handler) {
		super(x, y, id, image);
		// TODO Auto-generated constructor stub
		this.handler = handler; //handler
		this.velX = -1; //set default x speed
	}

	@Override
	public void tick(LinkedList<Sprite> sprites) { //boss update
		//if reach the edge of frame go down
		if(((velX<0)&& (x<Game.WIDTH/2-50)) || ((velX>0) && (x>Game.WIDTH/2+50))){ 
			velX = -velX; //change direction
		}
		
		x += velX; //basic move right or left
		
		Collision(sprites); //collision check
		
	}

	@Override
	public void render(Graphics g) {//rendering
		g.drawImage(image, (int)x, (int)y,  null);
	}

	@Override
	public Rectangle getBounds() { //detect boss boundaries
		return new Rectangle((int)x, (int)y, (int)width, (int)height);
	}

	private void Collision(LinkedList<Sprite> sprites){ //collision check
		
		for(int i=0; i<handler.sprites.size();i++){ //for all sprites
			Sprite tempSprite = handler.sprites.get(i);	//each sprite
			
			if(tempSprite.getId() == ObjectId.Shot){  //if boss takes gun bullet
				if(getBounds().intersects(tempSprite.getBounds())){
					handler.removeSprite(this); //remove boss
					handler.removeSprite(tempSprite);  //remove bullet
					System.out.println("alien boss y = "+y); //test print
				}
			}

		}
		
	}
	
}
