import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.LinkedList;

public class LevelUpCoin extends Sprite { //level up coin class
	
	public LevelUpCoin(float x, float y, ObjectId id, Image image) {//initialization
		super(x, y, id, image); //sprite initialization
		this.velY = 1;		//set default y speed
	}

	@Override
	public void tick(LinkedList<Sprite> sprites) { //update coi 
		y +=velY; //to make coin fixed on the screen
	}

	@Override
	public void render(Graphics g) { //rendering
//		g.setColor(Color.YELLOW);
//		g.fillOval((int)x,(int) y, 30, 30);
		g.drawImage(image, (int)x, (int)y,  null);		
	}

	@Override
	public Rectangle getBounds() { //detect coin boundaries
		return new Rectangle((int)x, (int)y,(int)30, (int)30 );
	}

}
