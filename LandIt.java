import processing.core.*;
import java.io.*;
import java.util.*;
import com.google.gson.*;
import controlP5.ControlP5;
import java.awt.*;

public class LandIt extends PApplet {
	// for json
	private static Data data = new Data(new ArrayList<User>()); // global static variable, all function get access to it
	private static Gson gson = new Gson();
	private String file_path = "data.json"; // static file path
	Scanner scanner = new Scanner(System.in);
	
	PImage bg;
	PImage logo;
	
	ControlP5 cp5;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int Width = (int) screenSize.getWidth();
	int Height = (int) screenSize.getHeight();
	
	// prevent submit at first time in page
	boolean firstTimePlay = true; 
	boolean firstTimeLog = true;
	boolean firstTimeSign = true;
	
	public void settings(){
		fullScreen();
	}
	
	public void draw() {
		background(bg);
		fill(255);
		textSize(12); 
		text("CSCI201L@\nEric Yihan Chen\nChristopher Pack\nDamiano Carrioli\nAnant Chandra", 13*Width/14, 7*Height/8);
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
	
	public void setup() {		
		PFont font = createFont("arial",40);
		cp5 = new ControlP5(this);
		
		bg = loadImage("files/login.jpg");
		logo = loadImage("files/logo.png");
		smooth();
		  
		cp5.addButton("Play")
			.setPosition(Width/4,Height/8)
		    .setImages(loadImage("files/logo.png"), loadImage("files/logo.png"), loadImage("files/logo.png"))
		    .updateSize()
		   	.setValue(0)
		   	.activateBy(ControlP5.PRESSED);
			
		cp5.addButton("LogIn")
		.setPosition(1*Width/8,5*Height/8)
	    .setImages(loadImage("files/logButton.png"), loadImage("files/logButton.png"), loadImage("files/logButton.png"))
	    .updateSize()
	   	.setValue(0)
	   	.activateBy(ControlP5.PRESSED);
		   
		cp5.addButton("SignUp")
		     .setPosition(5*Width/8,5*Height/8)
		     .setImages(loadImage("files/signButton.png"), loadImage("files/signButton.png"), loadImage("files/signButton.png"))
		     .updateSize()
		   	 .setValue(0)
		   	 .activateBy(ControlP5.PRESSED);
	}
	
	public LandIt() {
		//initializeData();
		//loadData();
		//askChoiceLogin();
		loadData();
		//clearData();
	}
	
	public static void main(String[] args) {
	    Sound back = new Sound("background");
	    back.start();
	 	PApplet.main("LandIt");
		//stopAudio();
    }
	
	public static void playAudio(String name) {
		String[] cmd = {"afplay", name};
		try {
			Process p = Runtime.getRuntime().exec(cmd);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void stopAudio() {
		String[] cmd = {"killall", "afplay"};
		try {
			Process p = Runtime.getRuntime().exec(cmd);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void Play(int value) {
		if (firstTimePlay) {
			firstTimePlay = false;
			return;
		}
		//PApplet.main("Main");
		PApplet.runSketch(new String[] {this.getClass().getSimpleName()}, new Main("Guest"));
	}
	
	public void LogIn(int value) {
		if (firstTimeLog) {
			firstTimeLog = false;
			return;
		}
		PApplet.runSketch(new String[] {this.getClass().getSimpleName()}, new Login(data));
	}
	
	public void SignUp(int value) {
		if (firstTimeSign) {
			firstTimeSign = false;
			return;
		}
		PApplet.runSketch(new String[] {this.getClass().getSimpleName()}, new SignUp(data));
	}
	
	// function to ask if user wants login or sign up
	// take care of invalid number, and string input
	private void askChoiceLogin() {
		int choice;
		while (true) {
			try {
				System.out.println("Welcome!\n1)Sign Up\n2)Login");
				String input = scanner.next();
				choice = Integer.parseInt(input);
				if (choice == 1) {
					signUp();
					break;
				} else if (choice == 2) {
					logIn();
					break;
				} else {
					System.out.println("Invalid!");
				}
			} catch (NumberFormatException nfe) {
				System.out.println("Invalid!");
			}
		}

	}
	
	// function for check in
	// verify for password mismatch
	// verify for name case insensitive check
	private void logIn () {
		String name;
		String password;
		boolean done = false;
		while(!done) {
			System.out.println("Log in to Your Account for LandIt!\nUsername: ");
			name = scanner.next();
			System.out.println("Password: ");
			password = scanner.next();
			int count = 0;
			for (User user: data.getUsers()) {
				count++;
				if (name.toLowerCase().equals(user.getName()) && password.equals(user.getPassword())) {
					System.out.println("Successfully logged in!");
					done = true;
					break;
				} else if (name.toLowerCase().equals(user.getName())) {
					// gets here b/c password incorrect
					System.out.println("Incorrect password");
				}
			}
			if (count == data.getUsers().size() && !done) {
				System.out.println("user does not exit, please check your account or create a new user");
			}
		}

	}
	
	// function to sign up:
	// create a new user, add it to list, and write back to data set
	private void signUp () {
		String name;
		String password;
		try {
			while(true) {
				System.out.println("Sign Up Your Account for LandIt!\nUsername: ");
				name = scanner.next();
				System.out.println("Password: ");
				password = scanner.next();

				if (name.length() > 20) {
					System.out.println("name can't be more than 20 chars");
				} else if (password.length() > 20) {
					System.out.println("password can't be more than 20 chars");
				} else if (name.isEmpty() || name == null) {
					System.out.println("name can't be empty");
				} else if (password.isEmpty() || password == null) {
					System.out.println("password can't be empty");
				} else {
					boolean taken = false;
					for (User user: data.getUsers()) {
						if (name.toLowerCase().equals(user.getName())) {
							System.out.println("Username Taken");
							taken = true;
							break;
						}
					}
					if (!taken) {
						User user = new User(name, password);
						data.getUsers().add(user);
						String jsonString = gson.toJson(data);
						FileWriter fileWriter = new FileWriter(file_path);
						fileWriter.write(jsonString);
						fileWriter.close();
						System.out.println("Sign up successful!");
						break;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// function to load data from data base
	private void loadData() {
		try {
			FileReader fr = new FileReader(file_path);
			data = gson.fromJson(fr, Data.class);
		} catch (FileNotFoundException fnfe) {
			System.out.println("That file could not be found. ");
		} catch (JsonParseException e) {
			System.out.println("That file is not a well-formed JSON file. ");
		} 
		catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
	}
	
	// function to initialize data base
	private void initializeData() {
		try {
			String jsonString = gson.toJson(data);
			FileWriter fileWriter = new FileWriter(file_path);
			fileWriter.write(jsonString);
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// function to clear data base, just for testing
	private void clearData() {
		while(!data.getUsers().isEmpty()) {
			data.getUsers().remove(0);
		}
		try {
			String jsonString = gson.toJson(data);
			FileWriter fileWriter = new FileWriter(file_path);
			fileWriter.write(jsonString);
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
