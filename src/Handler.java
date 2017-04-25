import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;

public class Handler { //Handling all sprites, game level set up

	public LinkedList<Sprite> sprites = new LinkedList<Sprite>(); //all sprites in game
	private Game game; //게임 변수
	private Camera camera; //카메라 
	private Sprite tempSprite; //임시 sprite 넣을 곳
	
	
	/*Image loading*/
	private BufferedImage level1; //레벨 1 게임 배경(sprite 생성)
	private BufferedImage level2; //레벨 2 게임 배경
	private BufferedImage alienImage; //외계인 1
	private BufferedImage alienType2; //외계인 2
	private BufferedImage alienBossImage; //외계인 보스
	private BufferedImage starShipImage; //주인공 비행기starship
	private BufferedImage levelUpCoinImage; //레벨 업그레이드 동전
	private BufferedImage alienEgg; //외계인 알
	
	public Handler(Game game, Camera camera){ //핸들러 생성자
		this.game = game; //게임 불러옴
		this.camera = camera; //카메라 불러옴
		
		BufferedImageLoader loader = new BufferedImageLoader(); //이미지 처리 클래스
		/*load image*/
		level1 = loader.loadImage("/level1.png"); 	// level 1 set up 
		level2 = loader.loadImage("/level2.png");   // level 2 set up 
		alienImage = loader.loadImage("/alien.png"); // alien image load
		alienType2 = loader.loadImage("/alienType2.png"); //load alien 2 image
		alienBossImage = loader.loadImage("/alienBoss.png"); //load alien boss image
		starShipImage = loader.loadImage("/starship.png"); //load starship image
		levelUpCoinImage = loader.loadImage("/levelup.png"); //load level up image
		alienEgg = loader.loadImage(""); //no image //to use sprite class
	}
	
	
	public void tick(){ //game update
		for(int i=0;i< sprites.size(); i++){ //for each sprite
			tempSprite = sprites.get(i); //put in temp 
			tempSprite.tick(sprites); //update game sprite in the list
		}
	}
	

	public void switchLevel(){	//game level change
		clearLevel(); //remove all sprites first
		game.LEVEL++; //level label upgrade
		loadImageLevel(level2);	//load new level image
	}
	
	public void defaultLevel(){ //set default level
		loadImageLevel(level1); //defalt = level1
	}
		
	//Game Level set up based on RGB Color
	public void loadImageLevel(BufferedImage image){ //load image method
		int w = image.getWidth(); //get width of image
		int h = image.getHeight(); //get height of image
		
		System.out.println("width, height"+w+" "+h); //check load level image
		
		for(int xx = 0; xx< h; xx++){ //read all x coordinate
			for(int yy = 0; yy< w; yy++){ //read all y coordinate
				int pixel = image.getRGB(xx, yy); //get pixel from png
				int red = (pixel >> 16) & 0xff; //set red rbg color
				int green = (pixel >> 8) & 0xff; //set green
				int blue = (pixel) & 0xff; //set blue
				
				if(red == 255 && green == 255 && blue == 255) {  //white color  = Alien 	
					//addSprite(new Alien(xx*32, yy*32, this, ObjectId.Alien, alienImage));
					addSprite(new Alien(xx*32, yy*(-32),this, ObjectId.Alien, alienImage));
				}
				
				if(red == 255 && green == 238 && blue == 30){  //yellow color = Alien Boss
					addSprite(new AlienBoss(xx*32, yy*(-32), ObjectId.AlienBoss, alienBossImage, this));
				}
				
				if(red == 30 && green == 10 && blue == 255){  //Blue color =  starship
					addSprite(new StarShip(xx*32, yy*(-32), this, ObjectId.StarShip, starShipImage, camera));
				}
				
				if(red == 156 && green == 215 && blue == 30){  //green color =  levelUpCoin
					addSprite(new LevelUpCoin(xx*32, yy*(-32), ObjectId.LevelUp,levelUpCoinImage ));
				}
				
				if(red == 236 && green == 1 && blue == 1){  //red color AlienType2    
					addSprite(new AlienType2(xx*32, yy*(-32),this, ObjectId.AlienType2,alienType2 ));
				}
				
				if(red == 255 && green == 7 && blue == 141){  //pink color Alienegg   
					addSprite(new AlienEgg(xx*32, yy*(-32), ObjectId.AlienEgg ,alienEgg ));
				}
			}
		}
	}

	
	public void clearLevel(){  //clear all game sprites
		sprites.removeAll(sprites); //remove all sprites	
	}
	
	
	public void render(Graphics g){ //game rendering
		for(int i=0;i< sprites.size(); i++){ //for all game sprites
			tempSprite = sprites.get(i); //for each
			tempSprite.render(g); // rendering
		}
	}
	
	public void addSprite(Sprite sp){ //add new sprite
		this.sprites.add(sp); //add new sprite on the list
	}
	
	
	public void removeSprite(Sprite sp){ //remove a sprite
		this.sprites.remove(sp); //delete a sprite on the list
	}
	
	

}
