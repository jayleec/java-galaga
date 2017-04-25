import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.LinkedList;

public class Shot extends Sprite{ //starship Bullet class

	private final float width = 8, height = 10; //bullet size
	private Game game; //game class
	private Handler handler; //handler class
	private Camera camera; //camera class
	
	public Shot(float x, float y, ObjectId id, Image image, Handler handler, Camera camera) {
		super(x, y, id, image); //sprite initialization
		this.velY = -8; // shot direction, speed  // move toward up
		this.handler = handler; //handler initialization
		this.camera = camera; //camera initialization
	}


	
	@Override
	public void tick(LinkedList<Sprite> sprites) { //update bullet 
		y += velY; //default movement

		if(Math.abs(y) > camera.getY()){ //remove shot out of the screen
			handler.removeSprite(this); //remove bullet
			System.out.println("shot removed"); //check removement
		}

	}

	
	@Override
	public void render(Graphics g) { //rendering
		g.setColor(Color.RED); //bullet color
		g.fillRect((int)x, (int)y, (int)width, (int)height); //draw as rectangle
	}

	@Override
	public Rectangle getBounds() { //detect bullet boundaries
		return new Rectangle((int)x, (int)y,(int)width, (int)height );
	}

}
