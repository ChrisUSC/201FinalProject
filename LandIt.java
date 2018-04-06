import processing.core.*;
import java.io.*;
import java.util.*;
import com.google.gson.*;

import controlP5.ControlP5;
import java.awt.*;

public class LandIt extends PApplet {
	private static Data data = new Data(new ArrayList<User>()); // global static variable, all function get access to it
	private static Gson gson = new Gson();
	private String file_path = "data.json"; // static file path
	Scanner scanner = new Scanner(System.in);

	ControlP5 cp5;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int Width = (int) screenSize.getWidth();
	int Height = (int) screenSize.getHeight();
	
	boolean firstTimePlay = true; // prevent incorrect submit at first time
	boolean firstTimeLog = true;
	boolean firstTimeSign = true;
	
	public void settings(){
		fullScreen();
	}
	
	public void draw() {
		background(0);
	}
	
	public void setup() {
		//noLoop();
		//initializeData();

		
	  PFont font = createFont("arial",40);
	  cp5 = new ControlP5(this);

	  cp5.addButton("Play")
	   //Set the position of the button : (X,Y)
	   .setPosition(Width/4,1*Height/3)
	   //Set the size of the button : (X,Y)
	   .setSize(Width/2,50)
	   //Set the pre-defined Value of the button : (int)
	   .setValue(0)
	   //set the way it is activated : RELEASE the mouseboutton or PRESS it
	   .activateBy(ControlP5.PRESSED);
		
	  cp5.addButton("LogIn")
	   //Set the position of the button : (X,Y)
	   .setPosition(Width/4,2*Height/3)
	   //Set the size of the button : (X,Y)
	   .setSize(Width/5,50)
	   //Set the pre-defined Value of the button : (int)
	   .setValue(0)
	   //set the way it is activated : RELEASE the mouseboutton or PRESS it
	   .activateBy(ControlP5.PRESSED);
	   
	  cp5.addButton("SignUp")
	   //Set the position of the button : (X,Y)
	   .setPosition(2*Width/4,2*Height/3)
	   //Set the size of the button : (X,Y)
	   .setSize(Width/5,50)
	   //Set the pre-defined Value of the button : (int)
	   .setValue(0)
	   //set the way it is activated : RELEASE the mouseboutton or PRESS it
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
		//LandIt landit = new LandIt();
		//PApplet.main("Main");
		//PApplet.main("Login");
		PApplet.main("LandIt");
    }
	
	public void Play(int value) {
		if (firstTimePlay) {
			firstTimePlay = false;
			return;
		}
		PApplet.main("Main");
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
