import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.LinkedList;

public class Alien extends Sprite { //Alien 1 class

	private final float width = 50, height = 50; //alien size
	private Handler handler; //handler
	
	public Alien(float x, float y, Handler handler, ObjectId id, Image image) {
		super(x, y, id, image); //sprite initialization
		
		this.velX = -3; //x 축 방향 속도
		this.handler = handler; //handler
	}

	@Override
	public void tick(LinkedList<Sprite> sprites) { //alien update

		if(((velX<0)&& (x<0)) || ((velX>0) && (x>1280 - 10))){ //경계에 닿으면 방향 전환, 한 칸 밑으로 내려옴
			velX = -velX; //x speed
			y += 40; //y coordinate change
		}
		
		x += velX; //x movement
		y += velY; //y movement
		
		Collision(sprites); //collision check method
	}

	private void Collision(LinkedList<Sprite> sprites){ //collision check method
		
		for(int i=0; i<handler.sprites.size();i++){ //for all sprites
			Sprite tempSprite = handler.sprites.get(i);	 //check each sprite
			
			if(tempSprite.getId() == ObjectId.Shot){ //if alien meet gun bullet
				if(getBounds().intersects(tempSprite.getBounds())){
					handler.removeSprite(this); //remove alien
					handler.removeSprite(tempSprite);  //remove bullet
				}
			}

		}
		
	}
	
	
	@Override
	public void render(Graphics g) { //alien rendering
//		g.setColor(Color.yellow);
//		g.fillRect((int)x, (int)y, (int)width, (int)height);
		g.drawImage(image, (int)x, (int)y,  null); //load image
	}

	@Override
	public Rectangle getBounds() { //detect alien boundary
		return new Rectangle((int)x, (int)y, (int)width, (int)height);
	}
}
