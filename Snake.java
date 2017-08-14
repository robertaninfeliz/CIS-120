import java.awt.Graphics;
import java.util.LinkedList;

public class Snake {
//Snake is represented by a LinkedList of SnakeParts
	
LinkedList <SnakeParts> snakeObj = new LinkedList <> ();
private int direction; 
public final int DIRECTION_LEFT = 0;
public final int DIRECTION_RIGHT = 1;
public final int DIRECTION_UP = 2;
public final int DIRECTION_DOWN = 3;

//Constructor makes snake, which initially consists of two dots
public Snake (SnakeParts initialPosition) {
	SnakeParts tail = new SnakeParts(initialPosition.getXpos(), initialPosition.getYpos() + 20);
	snakeObj.addLast(initialPosition);
	snakeObj.addLast(tail);
}

public void getLonger() {
	SnakeParts tail = snakeObj.peekLast();
	SnakeParts newTail = new SnakeParts(tail.getXpos(), tail.getYpos());
	snakeObj.addLast(newTail);
}

public int getLength () {
	return snakeObj.size();
}
//for testing purposes only
public LinkedList<SnakeParts> getSnakeList () {
	return snakeObj;
}

public boolean checkCollision (Coord next) {
	for(SnakeParts p: snakeObj) {
	if(p != next && p.collides(next)) {
		return true;
	}
	}
	return false;
	}

public int getDirection () {
	return this.direction;
}
public boolean checkSnakeCollision () {
	java.util.Iterator<SnakeParts> i = snakeObj.iterator();
	SnakeParts hello = i.next();
	while(i.hasNext())
	{
		if(i.next().equals(hello)) {
			return true;
		}
	}	
	return false;
	}

public SnakeParts returnHead () {
	return snakeObj.peekFirst();
}

public void setDirection (int direction) {
	this.direction = direction;
}

public void move () {
	SnakeParts head = snakeObj.peekFirst();
	SnakeParts newhead = null;
	if(this.direction == 0) {
		newhead = new SnakeParts(head.getXpos()-10, head.getYpos());
	} else if (this.direction == 1) {
		newhead = new SnakeParts(head.getXpos()+10, head.getYpos());
	} else if (this.direction == 2) {
		newhead = new SnakeParts(head.getXpos(), head.getYpos()+10);
	} else if (this.direction == 3) {
		newhead = new SnakeParts(head.getXpos(), head.getYpos()-10);
	}
	snakeObj.addFirst(newhead);
	snakeObj.removeLast();
}

public void drawSnake (Graphics g) {
	for(SnakeParts p: snakeObj) {
		p.draw(g);
	}
}
}


