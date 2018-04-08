import java.io.IOException;
<<<<<<< HEAD
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
=======
import java.net.*;
>>>>>>> f6e7436792cdf468ab9b2ab6cea57600feae617f
import java.util.ArrayList;

import controlP5.ControlP5;
import processing.core.PApplet;
import processing.core.PFont;
import java.io.*;

<<<<<<< HEAD
public class LeaderBoard extends PApplet {

=======
public class LeaderBoard extends PApplet{
	
>>>>>>> f6e7436792cdf468ab9b2ab6cea57600feae617f
	ArrayList<Integer> rank;
	ArrayList<String> name;
	int score;
	ControlP5 cp5;
	ServerSocket ss = null;
	boolean t = false;
<<<<<<< HEAD
	ServerSocket ss;
	private String username;
	ArrayList<ScoreMsg> sm = new ArrayList();
	private static ObjectInputStream ois;

	public void settings() {
		size(800, 800);
	}

	public void setup(ArrayList<ScoreMsg> sm) {

		PFont font = createFont("arial", 25);
		size(800, 800);
=======
	private BufferedReader br;
	boolean a = false;
	private static ObjectInputStream ois;
	
	public void settings() {
		  size(800, 800);
	}
	
	public void setup() {
		PFont font = createFont("arial",25);
		size(800,800);
>>>>>>> f6e7436792cdf468ab9b2ab6cea57600feae617f
		cp5 = new ControlP5(this);
		cp5.addListBox("RANK").setPosition(200, 200).setSize(0, 0).setFont(font);
		cp5.addListBox("USERNAME").setPosition(300, 200).setSize(0, 0).setFont(font);
		cp5.addListBox("SCORE").setPosition(500, 200).setSize(0, 0).setFont(font);

		for (int i = 0; i < 5; i++) {
			cp5.addListBox(Integer.toString(i)).setPosition(200, 250 + (50 * i)).setSize(0, 0).setFont(font);
			cp5.addListBox(Integer.toString(450 - (50 * i))).setPosition(500, 250 + (50 * i)).setSize(0, 0)
					.setFont(font);
			cp5.addListBox(sm.get(i).getUsername()).setPosition(300, 250 + (50 * i)).setSize(0, 0).setFont(font);
		}
<<<<<<< HEAD
=======
		
		cp5.addListBox("A").setPosition(300,250).setSize(100,100).setFont(font);
		cp5.addListBox("B").setPosition(300,300).setSize(0,0).setFont(font);
		cp5.addListBox("C").setPosition(300,350).setSize(0,0).setFont(font);
		cp5.addListBox("D").setPosition(300,400).setSize(0,0).setFont(font);
		cp5.addListBox("E").setPosition(300,450).setSize(0,0).setFont(font);
		
>>>>>>> f6e7436792cdf468ab9b2ab6cea57600feae617f
	}

	public void draw(ScoreMsg sc) {
		background(0);
<<<<<<< HEAD
		sm.add(sc);
		// for (int i = 0; i < sm.size(); i++) {
		// if (sm.get(i + 1) == null) {
		// break;
		// } else if (sm.get(i).getScore() < sm.get(i + 1).getScore()) {
		// Collections.swap(sm, i, i + 1);
		// }
		// }
		sm.sort(ScoreMsg.ScoreComp);
	}

	public static void main(String[] args) {
=======
		// TODO: add display stuff here, and read from the data structure
	}
	
	public static void main(String[] args) {
		//PApplet.main("LeaderBoard");
>>>>>>> f6e7436792cdf468ab9b2ab6cea57600feae617f
		try {
			System.out.println("Binding to port 6789");
			ServerSocket ss = new ServerSocket(6789);
			System.out.println("Bound to port " + 6789);
<<<<<<< HEAD
			while (true) {
				Socket s = ss.accept(); // blocking
				System.out.println("Connection from: " + s.getInetAddress());
				ois = new ObjectInputStream(s.getInputStream());
				ScoreMsg sm = (ScoreMsg) ois.readObject();
				draw(sm);
				setup(sm);
				System.out.println(sm.getScore());
				System.out.println(sm.getUsername());
=======
			while(true) {
				Socket s = ss.accept(); // blocking
				System.out.println("Connection from: " + s.getInetAddress());
				ois = new ObjectInputStream(s.getInputStream());
				ScoreMsg sm = (ScoreMsg)ois.readObject();
				System.out.println(sm.getScore());
				System.out.println(sm.getUsername());
				// TODO: add sm to a datastructure
>>>>>>> f6e7436792cdf468ab9b2ab6cea57600feae617f
				PApplet.main("LeaderBoard");
			}
		} catch (IOException ioe) {
			System.out.println("ioe in ChatRoom constructor: " + ioe.getMessage());
		} catch (ClassNotFoundException e) {
<<<<<<< HEAD
			e.printStackTrace();
		}
=======
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void populate(ArrayList<User> users) {
		ArrayList<Integer> rank;
		ArrayList<String> name;
		ArrayList<Integer> score;
		
>>>>>>> f6e7436792cdf468ab9b2ab6cea57600feae617f
	}
}
