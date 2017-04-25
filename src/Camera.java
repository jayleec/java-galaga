
public class Camera { //camera class

	private float x, y; //x, y coordinate
	
	public Camera(float x, float y){ //camera initialization
		this.x = x; //x coordinate 
		this.y = y; //y coordinate
	}
	
	/*camera movement*/
	public void tick(){ //camera update
		y += 1 ;//move toward upside
	}
	
	public float getX() { //get camera x coordinate
		return x;
	}

	public void setX(float x) { //set camera x coordinate
		this.x = x;
	}

	public float getY() {//get camera y coordinate
		return y;
	}

	public void setY(float y) {//set camera y coordinate
		this.y = y;
	}
	
	
}
