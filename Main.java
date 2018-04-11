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
	int blackHoleX1 = (int)random(100, 650);
	int blackHoleY1 = (int)random(100, 400);
	BlackHole bh2 = new BlackHole(this);
	int blackHoleX2 = (int)random(100, 650);
	int blackHoleY2 = (int)random(400, 750);
	BlackHole bh3 = new BlackHole(this);
	int blackHoleX3 = (int)random(700, 1200);
	int blackHoleY3 = (int)random(150, 400);
	BlackHole bh4 = new BlackHole(this);
	int blackHoleX4 = (int)random(700, 1200);
	int blackHoleY4 = (int)random(400, 750);
	
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
		
		//BH1 to BH2
		if((Rx + 55 >= blackHoleX1 - 30) && (Ry - 20 < blackHoleY1 + 30) && (Ry + 100 > blackHoleY1 - 30)){
			spaceX.setXpos(blackHoleX2);
			spaceX.setYpos(blackHoleY2 + 100);
		}else if((Rx < blackHoleX1 + 20) && (Rx > blackHoleX1 - 20) && (Ry - 20 < blackHoleY1 + 30) && (Ry + 100 > blackHoleY1 - 30)){
			spaceX.setXpos(blackHoleX2);
			spaceX.setYpos(blackHoleY2 + 100);
		}
		
		//BH2 to BH3
		if((Rx + 55 >= blackHoleX2 - 30) && (Ry - 20 < blackHoleY2 + 30) && (Ry + 100 > blackHoleY2 - 30)){
			spaceX.setXpos(blackHoleX3);
			spaceX.setYpos(blackHoleY3 + 300);
		}else if((Rx < blackHoleX2 + 20) && (Rx > blackHoleX2 - 20) && (Ry - 20 < blackHoleY2 + 30) && (Ry + 100 > blackHoleY2 - 30)){
			spaceX.setXpos(blackHoleX3);
			spaceX.setYpos(blackHoleY3 + 300);
		}
		
		//BH3 to BH4
		if((Rx + 55 >= blackHoleX3 - 30) && (Ry - 20 < blackHoleY3 + 30) && (Ry + 100 > blackHoleY3 - 30)){
			spaceX.setXpos(blackHoleX4);
			spaceX.setYpos(blackHoleY4 + 100);
		}else if((Rx < blackHoleX3 + 20) && (Rx > blackHoleX3 - 20) && (Ry - 20 < blackHoleY3 + 30) && (Ry + 100 > blackHoleY3 - 30)){
			spaceX.setXpos(blackHoleX4);
			spaceX.setYpos(blackHoleY4 + 100);
		}
		
		//BH4 to BH1
		if((Rx + 55 >= blackHoleX4 - 30) && (Ry - 20 < blackHoleY4 + 30) && (Ry + 100 > blackHoleY4 - 30)){
			spaceX.setXpos(blackHoleX1);
			spaceX.setYpos(blackHoleY1 + 100);
		}else if((Rx < blackHoleX4 + 20) && (Rx > blackHoleX4 - 20) && (Ry - 20 < blackHoleY4 + 30) && (Ry + 100 > blackHoleY4 - 30)){
			spaceX.setXpos(blackHoleX1);
			spaceX.setYpos(blackHoleY1 + 100);
		}
		
		if(rocketLanded){
			rocketLanded = false;
			if(mousePressed){
				level++;
				image(img, 0, 0);
				spaceX = new Rocket(this, Width/2 -28, -100);
				ASDSxpos = (int)random(0, Width - 100);
				ASDS = new LandingPlatform(this, ASDSxpos, Height - 50, 200, 50);
				blackHoleX1 = (int)random(100, 650);
				blackHoleY1 = (int)random(100, 400);
				blackHoleX2 = (int)random(100, 650);
				blackHoleY2 = (int)random(400, 750);
				blackHoleX3 = (int)random(700, 1200);
				blackHoleY3 = (int)random(150, 400);
				blackHoleX4 = (int)random(700, 1200);
				blackHoleY4 = (int)random(400, 750);
			}
		}else{
			image(img, 0, 0);
			spaceX.display();
			ASDS.display();
			textSize(20);
			text("Level: " + level, 20, 40);
			bh1.display(blackHoleX1, blackHoleY1);
			bh2.display(blackHoleX2, blackHoleY2);
			bh3.display(blackHoleX3, blackHoleY3);
			bh3.display(blackHoleX4, blackHoleY4);

		}
		
	}
}
