import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Health {//starship health
	
	public static int HEALTH = 100; // default full = 100
	private Handler handler; //handler
	private int score = 0; //set score 0

	public Health(Handler handler){
		this.handler = handler; //handler initialization
	}
	
	public void tick(){ //update health
		HEALTH = Game.clamp(HEALTH, 0, 100); //set health 0-100
	}
	
	public void render(Graphics g){
		g.setColor(Color.white);//box container 
		g.fillRect(38, 18, 204, 54);  //container size
		g.setColor(Color.gray); //health bar back
		g.fillRect(40, 20, 200, 50); //health bar back size
		g.setColor(Color.green);  //Health color
		g.fillRect(40, 20, HEALTH*2, 50);//health bar back size

		g.drawString("Score: "+ score, 40, 100); //write current score
		g.drawString("Level: "+ Game.LEVEL, 40, 120); //write current level
		
		//GAME OVER STRING
		if(HEALTH <= 0 ){//when starship has no heath
			g.setFont(new Font("TimesRoman", Font.PLAIN, 60)); //font setting
			g.setColor(Color.WHITE); //set text white color
			g.drawString("GAME OVER", 500, 500); //game over write
			handler.clearLevel(); //remove all sprites left
		}
	}

	public int getScore() { //get current score
		return score;
	}

	public void setScore(int score) { //set new score
		this.score = score;
	}

}
