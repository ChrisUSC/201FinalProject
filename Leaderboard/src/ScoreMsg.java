import java.io.Serializable;
import java.util.Comparator;

public class ScoreMsg implements Serializable {
	public static final long serialVersionUID = 1;
	private String username;
	private int score;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public static Comparator<ScoreMsg> ScoreComp = new Comparator<ScoreMsg>() {

		@Override
		public int compare(ScoreMsg s1, ScoreMsg s2) {
			return (int) (s1.getScore() - s2.getScore());
		}
	};
}