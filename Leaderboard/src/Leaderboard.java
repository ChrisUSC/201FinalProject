import java.util.ArrayList;
import controlP5.*;
import processing.core.PApplet;
import processing.core.PFont;

public class Leaderboard extends PApplet{
	
	ArrayList<Integer> rank;
	ArrayList<String> name;
	ArrayList<Integer> score;
	ControlP5 cp5;
	boolean t = false;
	
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
		
		cp5.addListBox("A").setPosition(300,250).setSize(0,0).setFont(font);
		cp5.addListBox("B").setPosition(300,300).setSize(0,0).setFont(font);
		cp5.addListBox("C").setPosition(300,350).setSize(0,0).setFont(font);
		cp5.addListBox("D").setPosition(300,400).setSize(0,0).setFont(font);
		cp5.addListBox("E").setPosition(300,450).setSize(0,0).setFont(font);
		
	}
	
	public void draw() {
		background(0);
	}
	
	
	public static void main(String[] args) {
		PApplet.main("Leaderboard");
	}
	
	public void populate(ArrayList<User> users) {
		
		ArrayList<Integer> rank;
		ArrayList<String> name;
		ArrayList<Integer> score;
		
	}
}
