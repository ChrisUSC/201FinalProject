package main;

import processing.core.*;

public class Main extends PApplet {
	int xPos = 100;
	int yPos = 100;
	Rocket spaceX = new Rocket(this, 200, 200);
	int ASDSxpos = (int)random(0, 1340);
	LandingPlatform ASDS = new LandingPlatform(this, ASDSxpos, 870, 200, 30);
	
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
