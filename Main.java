package main;

import processing.core.*;

public class Main extends PApplet {
	int xPos = 100;
	int yPos = 100;
	Rocket r = new Rocket(this, 200, 200);
	
	public static void main(String[] args) {
		PApplet.main("main.Main");
		
		
    }
	public void settings(){
		fullScreen();

	}
	
	public void setup(){
		background(100);
		smooth();
	}

	public void draw(){
		if(mousePressed) {
			System.exit(0);
		}
		r.run();
		r.gravity();
		if(keyPressed){
			r.accelerate();
		}
		background(100);
		r.display();
		
	}
}
