package main;

import processing.core.*;

public class Rocket {
	private float xpos;
	private float ypos;
//	private int state;
	private float ySpeed;
	private PApplet parent;

	
	public Rocket(PApplet p, int xpos, int ypos){
		parent = p;
		this.xpos = xpos;
		this.ypos = ypos;
		ySpeed = 0;
	}
	
	public void display() {
		parent.noStroke();
		parent.smooth();
		
		// xpos and ypos are the coordinates of the top left corner of the DEFAULT rectangle
		// 1
		parent.fill(200);
		parent.noStroke();
		parent.ellipse(xpos+28, ypos-23, 36 ,36); 

		// 2
		parent.fill(255);
		parent.ellipse(xpos+28, ypos-23, 36, 36);

		// 3
		parent.fill(255);
		parent.noStroke();
		parent.rect(xpos+10, ypos-25, 36, 100);

		// 4
		parent.fill(150);
		parent.rect(xpos+17, ypos-25, 23, 10);
		parent.fill(100);
		  
		// DEFAULT
		parent.noStroke();
		parent.rect(xpos, ypos, 10, 100);

		// 5
		parent.fill(100);
		parent.noStroke();
		parent.rect(xpos+46, ypos, 10, 100);

		// 6
		parent.fill(100);
		parent.noStroke();
		parent.rect(xpos+23, ypos, 10, 100);
	}
	
	// accelerates the rocket down
	public void gravity() {
		ypos = ypos + ySpeed;
		ySpeed += 0.05;
	}
	
	// moves the rocket left and right
	public void moveRight(){
		xpos += 4;
	}
	
	public void moveLeft(){
		xpos -= 4;
	}
	
	// getters 
	public float getXpos() {
		return xpos;
	}

	public float getYpos() {
		return ypos;
	}
	

}

