import java.net.*;
import java.util.*;
import controlP5.*;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.*;

public class LeaderBoard extends PApplet{
	
	// for GUI
	ControlP5 cp5;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int Width = (int) screenSize.getWidth();
	int Height = (int) screenSize.getHeight();
	PImage bg;
	PImage logo;
	boolean firstTimeBack = true;
	
	// for Networking
	ServerSocket ss = null;
	private BufferedReader br;
	private static ObjectInputStream ois;
	static ArrayList<ScoreMsg> board = new ArrayList<ScoreMsg>();
		
	public void settings() {
		fullScreen();
	}
	
	public void setup() {
		textSize(40); 
		bg = loadImage("login.jpg");
		logo = loadImage("leader.png");
		
		cp5 = new ControlP5(this);
		
		cp5.addButton("Back")
		.setPosition(25,25)
		.setImages(loadImage("back.png"), loadImage("back.png"), loadImage("back.png"))
		.updateSize()
		.setValue(0)
		.activateBy(ControlP5.PRESSED);	
	}
	
	public void draw() {
		PFont font = createFont("arial",25);
		background(bg);
		image(logo, (float)(2.4*Width/8), (float) (0.3*Height/8));
		
		
		text("Rank",  (float)(1.6*Width/8), (float) (2*Height/8));
		text("Username",  (float) (3.3*Width/8), (float) (2*Height/8));
		text("Score", (float) (5.8*Width/8), (float) (2*Height/8));
		
		for(int i=1; i<6; i++) {
			text(Integer.toString(i),  (float)(1.6*Width/8), (float) ((2+0.8*i)*Height/8));
			text(board.get(i-1).getUsername(), (float) (3.3*Width/8),  (float) ((2+0.8*i)*Height/8));
			text(board.get(i-1).getScore(), (float) (5.8*Width/8),  (float) ((2+0.8*i)*Height/8));
		}		
		
	}
	
//	public static void main(String[] args) {
////		//initializeBoard(board);
////		//PApplet.main("LeaderBoard");
////		try {
////			System.out.println("Binding to port 6789");
////			ServerSocket ss = new ServerSocket(6789);
////			System.out.println("Bound to port " + 6789);
////			while(true) {
////				Socket s = ss.accept();
////				System.out.println("Connection from: " + s.getInetAddress());
////				ois = new ObjectInputStream(s.getInputStream());
////				ScoreMsg sm = (ScoreMsg)ois.readObject();
////				System.out.println(sm.getScore());
////				System.out.println(sm.getUsername());
////				//addScore(board, sm);
////				PApplet.main("LeaderBoard");
////			}
////		} catch (IOException ioe) {
////			System.out.println("ioe in networking: " + ioe.getMessage());
////		} catch (ClassNotFoundException e) {
////			e.printStackTrace();
////		}
//	}
	
	// based on the assumption the board is sorted
	// add sm to a correct position
	public static void addScore(ScoreMsg sm) {
		for (int i = 0; i<board.size(); i++) {
			if(board.get(i).getScore() < sm.getScore()) {
				board.add(i, sm);
				return;
			}
		}
		// get here because sm is lowest
		board.add(sm);
		return;
	}
	
	public static void initializeBoard() {
		for (int i = 0; i<5; i++) {
			ScoreMsg sm = new ScoreMsg("Unknown Alien", 0);
			board.add(sm);
		}
		
	}
	
	public void Back(int value) {
		if (firstTimeBack) {
			firstTimeBack = false;
			return;
		}
		PApplet.main("LandIt");
		LandIt.disableSketch(this);
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