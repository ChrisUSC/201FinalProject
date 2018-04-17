import processing.core.*;

public class LandingPlatform {
	private float xpos;
	private float ypos;
	private float width;
	private float height;
	private PApplet parent;
	
	public LandingPlatform(PApplet p, int xpos, int ypos, int width, int height){
		parent = p;
		this.xpos = xpos;
		this.ypos = ypos;
		this.width = width;
		this.height = height;
	}
	public void display() {
		parent.fill(200, 200, 200);
		parent.rect(xpos, ypos, width, height);
	}
	

}
