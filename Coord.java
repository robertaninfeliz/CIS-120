import java.awt.Graphics;

//Coord objects populate the 2D array to keep track of what's where
//contains internal structure about where each object is and when
//allows for storing of snake head
public abstract class Coord {
private int xPos;
private int yPos;
/* Size of object, in pixels. */
private int width;
private int height;

public Coord (int xPos, int yPos, int width, int height) {
	this.xPos = xPos;
	this.yPos = yPos;
	this.height = height;
	this.width = width;
}

public abstract void draw (Graphics g);

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + xPos;
	result = prime * result + yPos;
	return result;
}
@Override
public boolean equals(Object h) {
	if (this == h)
		return true;
	if (h == null)
		return false;
	Coord hello = (Coord) h;
	if(xPos == hello.getXpos() && yPos == hello.getYpos()) {
		return true;
	}
	return false;
}
public boolean collides(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	Coord other = (Coord) obj;
	boolean collideX = (xPos + width >= other.getXpos()) && (other.getXpos() + other.getWidth() >= 
			xPos);
	boolean collideY = (yPos + height >= other.getYpos()) && (other.getYpos() + other.getHeight() >= 
			yPos);
	return collideX && collideY;

}

/*** GETTERS **********************************************************************************/
public int getXpos() {
	return this.xPos;
}

public int getYpos() {
	return this.yPos;
}
public int getWidth () {
	return this.width;
}
public int getHeight () {
	return this.height;
}
/*** SETTERS **********************************************************************************/
public void clip () {
	if(xPos > 400) {
		xPos = 400;
	}
	if(xPos < 0) {
		xPos = 0;
	}
	if(yPos > 400) {
		yPos = 400;
	}
	if(yPos < 0) {
		yPos = 0;
	}
}
public void setXpos(int px) {
    this.xPos = px;
    clip();
}

public void setYpos(int py){
    this.yPos = py;
    clip();
}

public void setHeight(int h) {
    this.height = h;
}

public void setWidth(int w) {
    this.width = w;
}
}

