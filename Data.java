import java.util.*;

public class Data {
	private ArrayList<User> users = new ArrayList<User>();

	public Data(ArrayList<User> users) {
		this.users = users;
	}
	
	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}
	
	public ArrayList<User> getUsers() {
		return this.users;
	}
	
}
