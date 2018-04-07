import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import controlP5.*;
import processing.core.PApplet;
import processing.core.PFont;
import java.io.*;

public class LeaderBoard extends PApplet{
	
	ArrayList<Integer> rank;
	ArrayList<String> name;
	ArrayList<Integer> score;
	ControlP5 cp5;
	ServerSocket ss = null;
	boolean t = false;
	private BufferedReader br;
	boolean a = false;
	private static ObjectInputStream ois;
	
	public void settings() {
		  size(800, 800);
	}
	
	public void setup() {
		PFont font = createFont("arial",25);
		size(800,800);
		cp5 = new ControlP5(this);
		cp5.addListBox("RANK").setPosition(200,200).setSize(0,0).setFont(font);
		cp5.addListBox("USERNAME").setPosition(300,200).setSize(0,0).setFont(font);
		cp5.addListBox("SCORE").setPosition(500,200).setSize(0,0).setFont(font);
		
		String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		
		for(int i=1; i<6; i++) {
			cp5.addListBox(Integer.toString(i)).setPosition(200,200+(50*i)).setSize(0,0).setFont(font);
			cp5.addListBox(Integer.toString(500-(50*i))).setPosition(500,200+(50*i)).setSize(0,0).setFont(font);
		}
		
		cp5.addListBox("A").setPosition(300,250).setSize(100,100).setFont(font);
		cp5.addListBox("B").setPosition(300,300).setSize(0,0).setFont(font);
		cp5.addListBox("C").setPosition(300,350).setSize(0,0).setFont(font);
		cp5.addListBox("D").setPosition(300,400).setSize(0,0).setFont(font);
		cp5.addListBox("E").setPosition(300,450).setSize(0,0).setFont(font);
		
	}
	
	public void draw() {
		background(0);
		// TODO: add display stuff here, and read from the data structure
	}
	
	public static void main(String[] args) {
		//PApplet.main("LeaderBoard");
		try {
			System.out.println("Binding to port 6789");
			ServerSocket ss = new ServerSocket(6789);
			System.out.println("Bound to port " + 6789);
			while(true) {
				Socket s = ss.accept(); // blocking
				System.out.println("Connection from: " + s.getInetAddress());
				ois = new ObjectInputStream(s.getInputStream());
				ScoreMsg sm = (ScoreMsg)ois.readObject();
				System.out.println(sm.getScore());
				System.out.println(sm.getUsername());
				// TODO: add sm to a datastructure
				PApplet.main("LeaderBoard");
			}
		} catch (IOException ioe) {
			System.out.println("ioe in ChatRoom constructor: " + ioe.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void populate(ArrayList<User> users) {
		ArrayList<Integer> rank;
		ArrayList<String> name;
		ArrayList<Integer> score;
		
	}
}
