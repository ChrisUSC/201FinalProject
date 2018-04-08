import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import controlP5.ControlP5;
import processing.core.PApplet;
import processing.core.PFont;

public class LeaderBoard extends PApplet {

	ArrayList<Integer> rank;
	ArrayList<String> name;
	int score;
	ControlP5 cp5;
	boolean t = false;
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
	}

	public void draw(ScoreMsg sc) {
		background(0);
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
		try {
			System.out.println("Binding to port 6789");
			ServerSocket ss = new ServerSocket(6789);
			System.out.println("Bound to port " + 6789);
			while (true) {
				Socket s = ss.accept(); // blocking
				System.out.println("Connection from: " + s.getInetAddress());
				ois = new ObjectInputStream(s.getInputStream());
				ScoreMsg sm = (ScoreMsg) ois.readObject();
				draw(sm);
				setup(sm);
				System.out.println(sm.getScore());
				System.out.println(sm.getUsername());
				PApplet.main("LeaderBoard");
			}
		} catch (IOException ioe) {
			System.out.println("ioe in ChatRoom constructor: " + ioe.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
