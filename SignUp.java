import processing.core.*;
import controlP5.*;
import controlP5.Label;

import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;

public class SignUp extends PApplet {
	ControlP5 cp5;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int Width = (int) screenSize.getWidth();
	int Height = (int) screenSize.getHeight();
	Data data;
	private static Gson gson = new Gson();
	private String file_path = "data.json"; // static file path
	PImage bg; // background image

	String err_msg = "";
	boolean firstTimeErr = true; // prevent incorrect submit at first time
	boolean firstTimeBack = true;
	
	SignUp(Data data) {
		this.data = data;
	}
	
	public void settings(){
		fullScreen();
	}
	
	public void setup() {
		  PFont font = createFont("arial",40);
		  cp5 = new ControlP5(this);

		  // Add Logo and name of login		  
		  
		  Textfield t1 = cp5.addTextfield("Username")
		    .setPosition(Width/4,4*Height/9)
		    .setSize(Width/2, 40)
		    .setFont(font)
		    .setAutoClear(true)
		    .setColorBackground(0xffffff)
		    .setColor(color(255, 255, 255));
		  
		  Textfield t2 = cp5.addTextfield("Password")
		    .setPosition(Width/4, 5*Height/8)
		    .setSize(Width/2, 40)
		    .setFont(font)
		    .setAutoClear(true)
		    .setPasswordMode(true)
		    .setColorBackground(0xffffff)
		    .setColor(color(255, 255, 255));
		 
		  textFont(font);
		  Label label1 = t1.getCaptionLabel(); 
		  label1.getStyle().setPaddingLeft(Width/6);
		  label1.getStyle().setPaddingTop(-130);
		  
		  Label label2 = t2.getCaptionLabel(); 
		  label2.getStyle().setPaddingLeft(Width/6);
		  label2.getStyle().setPaddingTop(-100);
		  
		  cp5.addButton("SignUp")
		   .setPosition((float) (3.3*Width/8),3*Height/4)
		   .setImages(loadImage("signButton.png"), loadImage("signButton.png"), loadImage("signButton.png"))
		   .updateSize()
		   .setValue(0)
		   .activateBy(ControlP5.PRESSED);
		   ;
		   
		  cp5.addButton("Back")
		   .setPosition(50,50)
		   .setImages(loadImage("back.png"), loadImage("back.png"), loadImage("back.png"))
		   .updateSize()
		   .setValue(0)
		   .activateBy(ControlP5.PRESSED);
		} 

	public void draw() {
		if (cp5.isMouseOver(cp5.getController("SignUp")) || cp5.isMouseOver(cp5.getController("Back"))){
			cursor(HAND);
		} else if (cp5.isMouseOver(cp5.getController("Username")) || cp5.isMouseOver(cp5.getController("Password"))) {
			cursor(TEXT);
		} else {
			cursor(ARROW);
		}
		background(bg);
		textSize(25); 
		fill(255, 0, 0);
		//text(err_msg, 2*Width/8, 7*Height/8);
		text(err_msg, 2*Width/8, (float) (4.5*Height/8));
	}
	
	// get called when button pressed
	public void SignUp(int value) throws IOException{
		bg = loadImage("bg.jpg");
		smooth();
		if (firstTimeErr) {
			firstTimeErr = false;
			return;
		}
		String username = cp5.get(Textfield.class, "Username").getText();
		String password = cp5.get(Textfield.class, "Password").getText();
		print("username: ");
		println(username);
		print("password: ");
		println(password);
		
		if (username.length() > 20) {
			System.out.println("name can't be more than 20 chars");
			err_msg = "name can't be more than 20 chars";
		} else if (password.length() > 20) {
			System.out.println("password can't be more than 20 chars");
			err_msg = "password can't be more than 20 chars";
		} else if (username.isEmpty() || username == null) {
			System.out.println("name can't be empty");
			err_msg = "name can't be empty";
		} else if (password.isEmpty() || password == null) {
			System.out.println("password can't be empty");
			err_msg = "password can't be empty";
		} else {
			boolean taken = false;
			for (User user: data.getUsers()) {
				if (username.toLowerCase().equals(user.getName())) {
					System.out.println("Username Taken");
					err_msg = "Username Taken";
					taken = true;
					break;
				}
			}
			if (!taken) {
				User user = new User(username, password);
				data.getUsers().add(user);
				String jsonString = gson.toJson(data);
				FileWriter fileWriter = new FileWriter(file_path);
				fileWriter.write(jsonString);
				fileWriter.close();
				System.out.println("Sign up successful!");
				err_msg = "Sign up successful!";
				PApplet.runSketch(new String[] {this.getClass().getSimpleName()}, new Main(username));
				LandIt.disableSketch(this);
			}
		}
	}
	
	public void Back(int value) {
		if (firstTimeBack) {
			firstTimeBack = false;
			return;
		}
		PApplet.main("LandIt");
	}

	public void keyPressed() {
		if(keyCode == ESC){
			LandIt.stopAudio();
			System.exit(0);
		}
	}
	
}
