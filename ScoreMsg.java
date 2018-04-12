import java.io.Serializable;

public class ScoreMsg implements Serializable {
	public static final long serialVersionUID = 1;
	private String username;
	private int score;
	
	public ScoreMsg(String username, int score) {
		this.username = username;
		this.score = score;
	}
	public String getUsername() {
		return username;
	}
	public int getScore() {
		return this.score;
	}
}