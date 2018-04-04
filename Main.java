
import java.awt.Dimension;
import java.awt.Toolkit;

import processing.core.PApplet;

public class Main extends PApplet {
	//Add this to get screen height and width
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int Width = (int) screenSize.getWidth();
	int Height = (int) screenSize.getHeight();
	
	int xPos = 100;
	int yPos = 100;
	int ASDSxpos = (int)random(0, Width - 100);
	LandingPlatform ASDS = new LandingPlatform(this, ASDSxpos, Height - 30, 200, 30);
	Rocket spaceX = new Rocket(this, 200, 200);

	public static void main(String[] args) {
		PApplet.main("main.Main");
		
    }
	public void settings(){
		fullScreen();
	}
	
	public void setup(){
		background(0);
		smooth();
	}

	public void draw(){
		if(mousePressed) {
			System.exit(0);
		}
		
		if(spaceX.getYpos() + 134 < height){
			spaceX.gravity();
			if(keyPressed){
				if(keyCode == LEFT){
					spaceX.moveLeft();
				}
				if(keyCode == RIGHT){
					spaceX.moveRight();
				}
			}
		}
		background(0);
		spaceX.display();
		ASDS.display();
		
	}
}