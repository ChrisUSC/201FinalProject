package main;

import java.awt.Dimension;
import java.awt.Toolkit;

import processing.core.*;

public class Main extends PApplet {
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int Width = (int) screenSize.getWidth();
	int Height = (int) screenSize.getHeight();
	PImage img; // background image
	boolean rocketLanded = false;
	boolean rocketCrashed = false;
	
	int xPos = 100;
	int yPos = 100;
	Rocket spaceX = new Rocket(this, 200, 200);
	int ASDSxpos = (int)random(0, Width - 100);
	LandingPlatform ASDS = new LandingPlatform(this, ASDSxpos, Height - 30, 200, 30);
	
	public static void main(String[] args) {
		PApplet.main("main.Main");	
    }
	
	public void settings(){
		fullScreen();
	}
	
	public void setup(){
		img = loadImage("https://farm1.staticflickr.com/796/40348333165_55d0b788f5_o_d.jpg");

		smooth();
	}

	public void draw(){
		if(keyPressed){
			if(keyCode == ESC){
				System.exit(0);
			}
		}
		
		if(spaceX.getXpos() + 55 > ASDSxpos && spaceX.getXpos() < ASDSxpos + 200 && spaceX.getYpos() > Height - 134 && spaceX.getYpos() < Height - 130){
			rocketLanded = true;
		}else{
			if(spaceX.getYpos() > Height - 130 && !rocketCrashed){
				System.out.println("Rocket crashed");
				rocketCrashed = true;
			}
			spaceX.gravity();
			if(keyPressed){
				if(keyCode == LEFT){
					spaceX.moveLeft();
				}
				if(keyCode == RIGHT){
					spaceX.moveRight();
				}
			}
			if(spaceX.getXpos() + 55 < 0){
				spaceX.setXpos(Width - 55);
			}
			if(spaceX.getXpos() > Width){
				spaceX.setXpos(0);
			}
		}
		if(rocketLanded){
			rocketLanded = false;
			if(mousePressed){
				image(img, 0, 0);
				spaceX = new Rocket(this, 200, 200);
				ASDSxpos = (int)random(0, Width - 100);
				ASDS = new LandingPlatform(this, ASDSxpos, Height - 30, 200, 30);	
			}
		}else{
			image(img, 0, 0);
			spaceX.display();
			ASDS.display();
		}
		
	}
}
