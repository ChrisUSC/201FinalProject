import processing.core.PApplet;

public class BlackHole {
	private PApplet parent;

	BlackHole(PApplet parent) {
		this.parent = parent;
	}

	void display(float x, float y) {
		parent.fill(0);
		parent.ellipse(x, y, 60, 60);
	}

}