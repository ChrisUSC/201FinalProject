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
		    .setColor(color(255, 255, 255));
		  
		  Textfield t2 = cp5.addTextfield("Password")
		    .setPosition(Width/4, 5*Height/8)
		    .setSize(Width/2, 40)
		    .setFont(font)
		    .setAutoClear(true)
		    .setColor(color(255, 255, 255));
		 
		  textFont(font);
		  Label label1 = t1.getCaptionLabel(); 
		  label1.getStyle().setPaddingLeft(Width/6);
		  label1.getStyle().setPaddingTop(-100);
		  
		  Label label2 = t2.getCaptionLabel(); 
		  label2.getStyle().setPaddingLeft(Width/6);
		  label2.getStyle().setPaddingTop(-100);
		  
		  cp5.addButton("SignUp")
		   //Set the position of the button : (X,Y)
		   .setPosition(3*Width/8,3*Height/4)
		   //Set the size of the button : (X,Y)
		   .setSize(Width/4,50)
		   //Set the pre-defined Value of the button : (int)
		   .setValue(0)
		   //set the way it is activated : RELEASE the mouseboutton or PRESS it
		   .activateBy(ControlP5.PRESSED);
		   ;
		   
		  cp5.addButton("Back")
		   //Set the position of the button : (X,Y)
		   .setPosition(50,50)
		   //Set the size of the button : (X,Y)
		   .setSize(100,80)
		   //Set the pre-defined Value of the button : (int)
		   .setValue(0)
		   //set the way it is activated : RELEASE the mouseboutton or PRESS it
		   .activateBy(ControlP5.PRESSED);
		   ;
		} 

	public void draw() {
	  background(0);
	  fill(255, 0, 0);
	  text(err_msg, 3*Width/8, 7*Height/8);
	  fill(255);
	  textSize(60); 
	  text("Sign Up", 7*Width/16, Height/3);
	}
	
	// get called when button pressed
	public void SignUp(int value) throws IOException{
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

	/*
	public void controlEvent(ControlEvent theEvent) {
	  if(theEvent.isAssignableFrom(Textfield.class)) {
	    println("controlEvent: accessing a string from controller '"
	            +theEvent.getName()+"': "
	            +theEvent.getStringValue()
	            );
	  }
	}
	*/
	
}
