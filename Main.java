package main;

import java.awt.Dimension;
import java.awt.Toolkit;

import processing.core.PApplet;
import processing.core.PImage;

public class Main extends PApplet {
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int Width = (int) screenSize.getWidth();
	private int Height = (int) screenSize.getHeight();
	private PImage img; // background image
	private boolean rocketLanded = false;
	private boolean rocketCrashed = false;
	private int level = 1;
	
	int xPos = 100;
	int yPos = 100;
	Rocket spaceX = new Rocket(this, Width/2 -28, -100);
	int ASDSxpos = (int)random(0, Width - 100);
	LandingPlatform ASDS = new LandingPlatform(this, ASDSxpos, Height - 50, 200, 50);
	BlackHole bh1 = new BlackHole(this);
	int blackHoleX1 = (int)random(200, 1200);
	int blackHoleY1 = (int)random(150, 750);
	BlackHole bh2 = new BlackHole(this);
	int blackHoleX2 = (int)random(200, 1200);
	int blackHoleY2 = (int)random(150, 750);
	BlackHole bh3 = new BlackHole(this);
	int blackHoleX3 = (int)random(200, 1200);
	int blackHoleY3 = (int)random(150, 750);
	
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
		
		if(spaceX.getXpos() + 55 > ASDSxpos && spaceX.getXpos() < ASDSxpos + 200 && spaceX.getYpos() > Height - 150 && spaceX.getYpos() < Height - 130){
			rocketLanded = true;
		}else{
			if(spaceX.getYpos() > Height - 130 && !rocketCrashed){
				System.out.println("Rocket crashed");
				rocketCrashed = true;
			}
			spaceX.gravity(level);
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
		
		int Rx = (int) spaceX.getXpos();
		int Ry = (int) spaceX.getYpos();
		
		if((Rx + 55 >= blackHoleX1 - 30) && (Ry - 20 < blackHoleY1 + 30) && (Ry + 100 > blackHoleY1 - 30)){
			spaceX.setXpos(0);
			spaceX.setYpos(0);
		}else if((Rx < blackHoleX1 + 20) && (Rx > blackHoleX1 - 20) && (Ry - 20 < blackHoleY1 + 30) && (Ry + 100 > blackHoleY1 - 30)){
			spaceX.setXpos(0);
			spaceX.setYpos(0);
		}
		
		if(rocketLanded){
			rocketLanded = false;
			if(mousePressed){
				level++;
				image(img, 0, 0);
				spaceX = new Rocket(this, Width/2 -28, -100);
				ASDSxpos = (int)random(0, Width - 100);
				ASDS = new LandingPlatform(this, ASDSxpos, Height - 50, 200, 50);
				blackHoleX1 = (int)random(200, 1200);
				blackHoleY1 = (int)random(150, 750);
//				blackHoleX2 = (int)random(200, 1200);
//				blackHoleY2 = (int)random(150, 750);
//				blackHoleX3 = (int)random(200, 1200);
//				blackHoleY3 = (int)random(150, 750);
			}
		}else{
			image(img, 0, 0);
			spaceX.display();
			ASDS.display();
			textSize(20);
			text("Level: " + level, 20, 40);
			bh1.display(blackHoleX1, blackHoleY1);
//			bh2.display(blackHoleX2, blackHoleY2);
//			bh3.display(blackHoleX3, blackHoleY3);
		}
		
	}
}
