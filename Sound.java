
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
			return "files/2 Hey Pocky A-Way.m4a";
		} else if (effect == "launch") {
			return "files/Launch.mp3";
		} else if (effect == "land") {
			return "files/bingo.mp3";
		} 
		else {
			return "";
		}
	}
}
