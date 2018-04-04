package main;

import processing.core.*;

public class Rocket {
	float xPos;
	float yPos;
	int state;
	float xSpeed;
	float ySpeed;
	PApplet parent; // The parent PApplet that we will render ourselves onto

	
	public Rocket(PApplet p, int xPos, int yPos){
		parent = p;
		this.xPos = xPos;
		this.yPos = yPos;
		xSpeed = 0;
		ySpeed = 0;
	}
	
	public void display() {
		parent.noStroke();
		parent.smooth();
		// body
		parent.fill(255, 255, 255);
		parent.rect(xPos, yPos, 80, 50);
		// head
		parent.fill(0, 0, 0);
		parent.triangle(xPos+80, yPos, xPos+100, yPos+25, xPos+80, yPos+50);
		// porthole
		parent.stroke(1);
		parent.fill(0, 0, 255);
		parent.ellipse(xPos+50, yPos+25, 15, 15);
		// upper wing
		parent.noStroke();
		parent.fill(0, 0, 0);
		parent.triangle(xPos, yPos, xPos, yPos-30, xPos+30, yPos);
		// lower wing
		parent.fill(0, 0, 0);
		parent.triangle(xPos, yPos+50, xPos, yPos+80, xPos+30, yPos+50);
		// flames
		parent.fill(255, 0, 0);
		parent.triangle(xPos, yPos, xPos, yPos+16, xPos-16, yPos+8);
		parent.triangle(xPos, yPos+16, xPos, yPos+33, xPos-16, yPos+25);
		parent.triangle(xPos, yPos +33, xPos, yPos+50, xPos-16, yPos+42);
	}
	
	public void gravity() {
		ySpeed += 0.1;
	}
	
	public void accelerate(){
		xSpeed += 0.2;
	}
	
	public void run() {
	    xPos = xPos + xSpeed;
	    yPos = yPos + ySpeed;
	}

}

