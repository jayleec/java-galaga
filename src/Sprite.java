import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.LinkedList;

public abstract class Sprite { 
	
	protected float x, y; //x, y coordination
	protected float velX = 0, velY = 0; //x speed, y speed
	protected ObjectId id; //classify all sprites as id, enum
	protected Image image; //sprite image
	
	public Sprite(float x, float y, ObjectId id, Image image){ //initialization
		this.x = x; //default x  coordinate
		this.y = y; //defaul y coordinate 
		this.id = id; //set id
		this.image = image; //sprite image 
	}

	public abstract void tick(LinkedList<Sprite> sprites);
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();

	
	public float getX(){
			return x;
	}
	public float getY(){
			return y;
	}
	public void setX(float x){
		this.x = x;
	}
	public void setY(float y){
		this.y = y;
	}
	
	public float getVelX(){
		return velX;
	}
	public float getVelY(){
		return velY;
	}
	public void setVelX(float velX){
		this.velX = velX;
	}
	public void setVelY(float velY){
		this.velY = velY;
	}
	
	public ObjectId getId(){
		return id;
	}
}
