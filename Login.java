import processing.core.*;
import controlP5.*;
import controlP5.Label;

import java.awt.*;

public class Login extends PApplet {
	ControlP5 cp5;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int Width = (int) screenSize.getWidth();
	int Height = (int) screenSize.getHeight();
	Data data;
	

	String err_msg = "";
	boolean firstTimeErr = true; // prevent incorrect submit at first time
	boolean firstTimeBack = true;
	
	Login(Data data) {
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
		  
		  cp5.addButton("LogIn")
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
	  if (err_msg.equals("Incorrect password")) {
		  text(err_msg, 3*Width/8, 7*Height/8);
	  } else {
		  text(err_msg, 100, 7*Height/8);
	  }
	  fill(255);
	  textSize(60); 
	  text("Log In", 7*Width/16, Height/3);
	}
	
	// get called when button pressed
	public void LogIn(int value){
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
		
		boolean checked = false;
		int count = 0;
		for (User user: data.getUsers()) {
			count++;
			if (username.toLowerCase().equals(user.getName()) && password.equals(user.getPassword())) {
				System.out.println("Successfully logged in!");
				err_msg = "Successfully logged in!";
				checked = true;
				PApplet.main("Main");
				break;
			} else if (username.toLowerCase().equals(user.getName())) {
				// gets here b/c password incorrect
				System.out.println("Incorrect password");
				err_msg = "Incorrect password";
				checked = true;
			}
		}
		if (count == data.getUsers().size() && !checked) {
			System.out.println("user does not exit, please check your account or create a new user");
			fill(0);
			err_msg = "user does not exit, please check your account or create a new user";
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
