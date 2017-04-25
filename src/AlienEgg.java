import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.LinkedList;

public class AlienEgg extends Sprite { //Alien egg class

	public AlienEgg(float x, float y, ObjectId id, Image image) { //initilization
		super(x, y, id, image); //sprite initialization
		this.velY = 1;	 //y speed
	}

	@Override
	public void tick(LinkedList<Sprite> sprites) { //update 
		y +=velY; //y speed
	}

	@Override
	public void render(Graphics g) { //rendering
		g.setColor(Color.blue); //set blue color
		g.fillOval((int)x,(int) y, 25, 25); //alien egg size
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle((int)x, (int)y,(int)25, (int)25 );
	}


	
}
