import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BufferedImageLoader { //image loader class

	private BufferedImage image;  //BufferedImage 변수 생성
	
	public BufferedImage loadImage(String path){ //initilization 
		try {
			image = ImageIO.read(getClass().getResource(path)); //load image from the path
		} catch (IOException e) {
			e.printStackTrace(); //error print
		}
		return image; 
	}
}
