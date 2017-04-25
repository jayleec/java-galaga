import java.awt.Dimension;

import javax.swing.JFrame;

public class InsideWindow {  //Game window class
	public InsideWindow(int w, int h,String title, Game game){//initilization
		game.setPreferredSize(new Dimension(w, h));//set size
		
		//JFrame setup
		JFrame frame = new JFrame(title); //set JFrame
		frame.add(game); //add game frame
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //default close option
		frame.setResizable(false); 
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		game.start(); //start game
	}
}
