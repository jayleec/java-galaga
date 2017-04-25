import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class ShotUpgrade extends Shot { //shot in Level 2
	private final float width = 8, height = 20; //default size
	public ShotUpgrade(float x, float y, ObjectId id, Image image, Handler handler, Camera camera) {
		super(x, y, id, image, handler, camera); //default set for shot class
	}

	@Override
	public void render(Graphics g) { //diffrent color shot 
		g.setColor(Color.YELLOW); //yellow color
		g.fillRect((int)x, (int)y, (int)width, (int)height); //draw
	}

}
