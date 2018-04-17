import processing.core.PApplet;

public class Star {
	private PApplet parent;
	float x, y, radius1, radius2, npoints;
	
	Star(PApplet parent, float x, float y, float radius1, float radius2, int npoints) {
		this.parent = parent;
		this.x = x;
		this.y = y;
		this.radius1 = radius1;
		this.radius2 = radius2;
		this.npoints = npoints;
	}
	
	public void setXposition(int x){
		this.x = x;
	}
	
	public void setYposition(int y){
		this.y = y;
	}
	
	public float getXposition(){
		return this.x;
	}
	public float getYposition(){
		return this.y;
	}
	
	public void display(){
		parent.fill(255, 255, 0);
		float angle = parent.TWO_PI / npoints;
		float halfAngle = (float) (angle / 2.0);
		parent.beginShape();
		for (float a = 0; a < parent.TWO_PI; a += angle) {
			float sx = x + parent.cos(a) * radius2;
			float sy = y + parent.sin(a) * radius2;
			parent.vertex(sx, sy);
			sx = x + parent.cos(a + halfAngle) * radius1;
			sy = y + parent.sin(a + halfAngle) * radius1;
			parent.vertex(sx, sy);
		}
		parent.endShape(parent.CLOSE);
	}
}
