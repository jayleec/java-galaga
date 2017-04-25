
public class Spawn { //score bar context
	private Handler handler; 
	private Health health;
	
	public Spawn(Handler handler, Health health){
		this.handler = handler; //handler
		this.health = health; //health bar
	}
	
	public void tick(){ //update
		health.tick();
	}
	
}
