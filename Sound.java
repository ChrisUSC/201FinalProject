
public class Sound extends Thread {
	private String effect;
	public Sound (String effect) {
		this.effect = effect;
	}
	
	public void run() {
		
		LandIt.playAudio(getFileName(effect));
	}
	
	private String getFileName(String effect) {
		if (effect == "background") {
			return "src/2 Hey Pocky A-Way.m4a";
		} else if (effect == "launch") {
			return "src/Launch.mp3";
		} else if (effect == "land") {
			return "src/bingo.mp3";
		} else if (effect == "crash") {
			return "src/Houston.m4a";
		} else if (effect == "blackhole") {
			return "src/blackhole.m4a";
		} else if (effect == "star") {
			return "src/star.m4a";
		} else if (effect == "boom") {
			return "src/Crash.mp3";
		}
		else {
			return "";
		}
	}
}
