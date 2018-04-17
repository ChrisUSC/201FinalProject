import java.awt.Dimension;
import java.awt.Toolkit;

import processing.core.PApplet;
import processing.core.PImage;

import java.io.*;
import java.net.*;

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
	Rocket spaceX = new Rocket(this, Width / 2 - 28, -100);
	int ASDSxpos = (int) random(0, Width - 100);
	LandingPlatform ASDS = new LandingPlatform(this, ASDSxpos, Height - 50, 200, 50);
	BlackHole bh1 = new BlackHole(this);
	int blackHoleX1 = (int) random(100, 650);
	int blackHoleY1 = (int) random(100, 400);
	BlackHole bh2 = new BlackHole(this);
	int blackHoleX2 = (int) random(100, 650);
	int blackHoleY2 = (int) random(400, 750);
	BlackHole bh3 = new BlackHole(this);
	int blackHoleX3 = (int) random(700, 1200);
	int blackHoleY3 = (int) random(150, 400);
	BlackHole bh4 = new BlackHole(this);
	int blackHoleX4 = (int) random(700, 1200);
	int blackHoleY4 = (int) random(400, 750);

	int starX1 = (int) random(100, 650);
	int starY1 = (int) random(100, 400);
	int starX2 = (int) random(100, 650);
	int starY2 = (int) random(400, 750);
	int starX3 = (int) random(700, 1200);
	int starY3 = (int) random(150, 400);
	int starX4 = (int) random(700, 1200);
	int starY4 = (int) random(400, 750);
	Star star1 = new Star(this, starX1, starY1, 15, 35, 5);
	Star star2 = new Star(this, starX2, starY2, 15, 35, 5);
	Star star3 = new Star(this, starX3, starY3, 15, 35, 5);
	Star star4 = new Star(this, starX4, starY4, 15, 35, 5);

	boolean star1hit = false;
	boolean star2hit = false;
	boolean star3hit = false;
	boolean star4hit = false;
	
	boolean firstTimeLand = false;
	boolean firstTimeCrash = false;
	boolean guestFunction = false;
	boolean firstTimeGuestDone = false;

	 public Main(String name) {
		 this.name = name;
		 if (name.equals("Guest")) {
			 guestFunction = true;
		 }
	 }

	public void settings() {
		fullScreen();
		 Sound back = new Sound("launch");
		 back.start();
	}

	public void setup() {
		img = loadImage("https://farm1.staticflickr.com/796/40348333165_55d0b788f5_o_d.jpg");
		smooth();
	}

	public void draw() {
		if (keyPressed) {
			if (keyCode == ESC) {
				System.exit(0);
			}
		}
		
		if (rocketLanded || rocketCrashed) {
			LandIt.disableSketch(this);
		}

		if (spaceX.getXpos() + 55 > ASDSxpos && spaceX.getXpos() < ASDSxpos + 200 && spaceX.getYpos() > Height - 150
				&& spaceX.getYpos() < Height - 130) {
			rocketLanded = true;
			if (!firstTimeLand) {
			    Sound back = new Sound("land");
			    back.start();
			    firstTimeLand = true;
			}
		} else {
			if (spaceX.getYpos() > Height - 130 && !rocketCrashed) {
				// send user score
				 try {
					 Socket s = new Socket("localhost", 6789);
					 ScoreMsg sm = new ScoreMsg(name, level);
					 // if it's guest, shouldn't send score
					 if (guestFunction) {
						 sm = new ScoreMsg("Unknown Alien", 0);
					 }
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
			if (keyPressed) {
				if (keyCode == LEFT) {
					spaceX.moveLeft();
				}
				if (keyCode == RIGHT) {
					spaceX.moveRight();
				}
			}
			if (spaceX.getXpos() + 55 < 0) {
				spaceX.setXpos(Width - 55);
			}
			if (spaceX.getXpos() > Width) {
				spaceX.setXpos(0);
			}
		}

		int Rx = (int) spaceX.getXpos();
		int Ry = (int) spaceX.getYpos();

		if ((abs(Rx + 30 - blackHoleX1) < 60) && (abs(Ry + 60 - blackHoleY1)) < 85) {
			spaceX.setXpos(blackHoleX2);
			spaceX.setYpos(blackHoleY2 + 55);
			Sound back = new Sound("blackhole");
			back.start();
		}

		if ((abs(Rx + 30 - blackHoleX2) < 60) && (abs(Ry + 60 - blackHoleY2)) < 85) {
			spaceX.setXpos(blackHoleX3);
			spaceX.setYpos(blackHoleY3 + 55);
			Sound back = new Sound("blackhole");
			back.start();
		}

		if ((abs(Rx + 30 - blackHoleX3) < 60) && (abs(Ry + 60 - blackHoleY3)) < 85) {
			spaceX.setXpos(blackHoleX4);
			spaceX.setYpos(blackHoleY4 + 55);
			Sound back = new Sound("blackhole");
			back.start();
		}

		if ((abs(Rx + 30 - blackHoleX4) < 60) && (abs(Ry + 60 - blackHoleY4)) < 85) {
			spaceX.setXpos(blackHoleX1);
			spaceX.setYpos(blackHoleY1 + 55);
			Sound back = new Sound("blackhole");
			back.start();
		}

		if ((abs(Rx + 30 - star1.getXposition()) < 60) && (abs(Ry + 60 - star1.getYposition())) < 95) {
			if (star1.getXposition() != 2000 && star1hit == false) {
				star1hit = true;
				spaceX.setYspeed((float)(spaceX.getYspeed() * 0.3));
			}
			star1.setXposition(2000);
			star1.setYposition(2000);
			Sound star = new Sound("star");
			star.start();

		}

		if ((abs(Rx + 30 - star2.getXposition()) < 60) && (abs(Ry + 60 - star2.getYposition())) < 95) {
			if (star2.getXposition() != 2000 && star2hit == false) {
				star2hit = true;
				spaceX.setYspeed((float)(spaceX.getYspeed() * 0.3));
			}
			star2.setXposition(2000);
			star2.setYposition(2000);
			Sound star = new Sound("star");
			star.start();
		}

		if ((abs(Rx + 30 - star3.getXposition()) < 60) && (abs(Ry + 60 - star3.getYposition())) < 95) {
			if (star3.getXposition() != 2000 && star3hit == false) {
				star3hit = true;
				spaceX.setYspeed((float)(spaceX.getYspeed() * 0.3));
			}
			star3.setXposition(2000);
			star3.setYposition(2000);
			Sound star = new Sound("star");
			star.start();
		}

		if ((abs(Rx + 30 - star4.getXposition()) < 60) && (abs(Ry + 60 - star4.getYposition())) < 95) {
			if (star4.getXposition() != 2000 && star4hit == false) {
				star4hit = true;
				spaceX.setYspeed((float)(spaceX.getYspeed() * 0.3));
			}
			star4.setXposition(2000);
			star4.setYposition(2000);
			Sound star = new Sound("star");
			star.start();
		}

		if (rocketLanded) {
			// This is to tell the user the landed and what to do next
			textSize(100);
			fill(0, 255 ,0);
			text("LANDED!", Width / 2 - 200, Height / 2 - 100);
			textSize(40);
			fill(0, 255 ,0);
			text("Click to Continue", Width / 2 - 150, Height / 2);
			// End of changes
			rocketLanded = false;

			star1hit = false;
			star2hit = false;
			star3hit = false;
			star4hit = false;
			if (mousePressed) {
				level++;
				image(img, 0, 0);
				spaceX = new Rocket(this, Width / 2 - 28, -100);
				ASDSxpos = (int) random(0, Width - 100);
				ASDS = new LandingPlatform(this, ASDSxpos, Height - 50, 200, 50);
				blackHoleX1 = (int) random(100, 650);
				blackHoleY1 = (int) random(100, 400);
				blackHoleX2 = (int) random(100, 650);
				blackHoleY2 = (int) random(400, 750);
				blackHoleX3 = (int) random(700, 1200);
				blackHoleY3 = (int) random(150, 400);
				blackHoleX4 = (int) random(700, 1200);
				blackHoleY4 = (int) random(400, 750);

				star1 = new Star(this, (int) random(100, 650), (int) random(100, 400), 15, 35, 5);
				star2 = new Star(this, (int) random(100, 650), (int) random(400, 750), 15, 35, 5);
				star3 = new Star(this, (int) random(700, 1200), (int) random(150, 400), 15, 35, 5);
				star4 = new Star(this, (int) random(700, 1200), (int) random(400, 750), 15, 35, 5);
				
				Sound back = new Sound("launch");
				back.start();

				firstTimeLand = false;
			}
		} else {
			image(img, 0, 0);
			spaceX.display();
			ASDS.display();
			textSize(20);
			text("Level: " + level, 20, 40);
			star1.display();
			star2.display();
			star3.display();
			star4.display();
			bh1.display(blackHoleX1, blackHoleY1);
			bh2.display(blackHoleX2, blackHoleY2);
			bh3.display(blackHoleX3, blackHoleY3);
			bh3.display(blackHoleX4, blackHoleY4);
			if (rocketCrashed) {
				fill(255,0 ,0);
				textSize(70);
				text("OH NO, you crashed!", Width / 2 - 350, Height / 2 - 100);
				if (!firstTimeCrash) {
					LandIt.stopAudio();
					Sound back = new Sound("crash");
					back.start();
					back = new Sound("boom");
					back.start();
					firstTimeCrash = true;
				}
			}
		}
		
		// if guest plays more than 5, stop
		if (guestFunction && level > 5 && !firstTimeGuestDone) {
			 try {			
				 LandIt.stopAudio();
				 delay(1000);
				 LandIt.say("Bro, log in next time so you can play more");
				 delay(3000);
				 Sound back = new Sound("crash");
				 back.start();
				 back = new Sound("boom");	
				 back.start();
				 firstTimeGuestDone = true;
				 Socket s = new Socket("localhost", 6789);
				 ScoreMsg sm = new ScoreMsg("Unknown Alien", 0);
				 oos = new ObjectOutputStream(s.getOutputStream());
				 oos.writeObject(sm);
				 oos.flush();
			 } catch (UnknownHostException e) {
				 e.printStackTrace();
			 } catch (IOException e) {
				 e.printStackTrace();
			 }
		}
	}

	 public void keyPressed() {
	 // exit
	 if(keyCode == ESC){
	 LandIt.stopAudio();
	 System.exit(0);
	 }
	
	 // no music
	 if(keyCode == TAB){
	 LandIt.stopAudio();
	 }
	 }
}
