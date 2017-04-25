import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Particle extends Sprite{ //Particle class
	private int life; //particle duration
	private float xa, ya; //acceleration
	private Handler handler; //game handler
	
	public Particle(float x, float y, ObjectId id, Image image, Handler handler){ 
		super(x, y, id, image); //sprite initialization
		this.life = 0; //set life 0
		this.handler = handler; //bring handler
		
		Random random = new Random(); //random number generation
		this.xa = (float)random.nextGaussian();  //random direction x
		this.ya = (float)random.nextGaussian();	 //random direction y
	}
	
	@Override
	public void tick(LinkedList<Sprite> sprites) { //particle update
		this.x += xa; //default x movement
		this.y += ya; //default y movement 

		life++; //increase life var
		if(life > 50){ //life over 50
			handler.removeSprite(this);  //delete particle
			life = 0; //reset life
		}
	}

	@Override
	public void render(Graphics g) { //particle rendering
		g.setColor(Color.yellow); //yellow box
		g.fillRect((int)x, (int)y, 5, 5); //box size
	}

	@Override
	public Rectangle getBounds() { //detect particle rectangle
		return new Rectangle((int)x, (int)y, 5, 5);
	}

}
