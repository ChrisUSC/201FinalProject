import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.*;
import java.net.*;

import processing.core.*;

public class Main extends PApplet {
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int Width = (int) screenSize.getWidth();
	private int Height = (int) screenSize.getHeight();
	private PImage img; // background image
	private boolean rocketLanded = false;
	private boolean rocketCrashed = false;
	private int level = 1;
	private ObjectOutputStream oos;
	private String name = "";
	
	int xPos = 100;
	int yPos = 100;
	Rocket spaceX = new Rocket(this, 200, 200);
	int ASDSxpos = (int)random(0, Width - 100);
	LandingPlatform ASDS = new LandingPlatform(this, ASDSxpos, Height - 50, 200, 50);
	
	public static void main(String[] args) {
		//PApplet.runSketch(new String[] {this.getClass().getSimpleName()}, new Main(""));	
    }
	
	public Main(String name) {
		this.name = name;
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
				try {
					Socket s = new Socket("localhost", 6789);
					ScoreMsg sm = new ScoreMsg(name, level);
					oos = new ObjectOutputStream(s.getOutputStream());
					oos.writeObject(sm);
					oos.flush();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
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
		if(rocketLanded){
			rocketLanded = false;
			if(mousePressed){
				level++;
				image(img, 0, 0);
				spaceX = new Rocket(this, 200, 200);
				ASDSxpos = (int)random(0, Width - 100);
				ASDS = new LandingPlatform(this, ASDSxpos, Height - 50, 200, 50);	
			}
		}else{
			image(img, 0, 0);
			spaceX.display();
			ASDS.display();
			textSize(20);
			text("Level: " + level, 20, 40);
		}
		
	}
}
