import static org.junit.Assert.*;
import org.junit.Test;

public class SnakeTest {
	SnakeParts head = new SnakeParts (200, 200);
	public Snake snake = new Snake (head);

	@Test
	public void testHead ()  {
		assertEquals(head, snake.returnHead());
	}

	@Test
	public void testDirectionGetsSet () {
		snake.setDirection(3);
		assertEquals(snake.getDirection(), 3);
	}

	@Test
	public void gettersWork () {
		assertEquals(200, snake.returnHead().getXpos());
		assertEquals(200, snake.returnHead().getYpos());
	}
	
	@Test
	public void settersWork () {
		snake.returnHead().setXpos(300);
		assertEquals(300, snake.returnHead().getXpos());
	}
	
	@Test
	public void getLengthWorks () {
		assertEquals(2, snake.getLength());
	}
	
	@Test
	public void increaseSnakeLength () {
		snake.getLonger();
		assertEquals(3, snake.getLength());
	} 
	
	@Test
	public void moveInCorrectDirection () {
		snake.setDirection(0);
		snake.move();
		assertEquals(190, snake.returnHead().getXpos());
	}
	
	@Test
	public void moveTwiceComeBack () {
		snake.setDirection(0);
		snake.move();
		snake.setDirection(1);
		snake.move();
		assertEquals(snake.returnHead().getXpos(), 200);
		assertEquals(snake.returnHead().getYpos(), 200);
	}
	
	@Test
	public void moveOutOfBoundsPositiveX() {
		snake.returnHead().setXpos(500);
		assertEquals(400, head.getXpos());
	}
	
	@Test
	public void moveOutOfBoundsNegativeX () {
		snake.returnHead().setXpos(-200);
		assertEquals(0, head.getXpos());
	}
	
	@Test
	public void moveOutOfBoundsPositiveY () {
		snake.returnHead().setYpos(600);
		assertEquals(400, head.getYpos());
	}
	
	@Test
	public void moveOutOfBoundsNegativeY () {
		snake.returnHead().setYpos(-2);
		assertEquals(0, head.getYpos());
	}
	
	@Test
	public void moveDoesntIncreaseLength () {
		snake.move();
		assertEquals(2, snake.getLength());
	}
	@Test
	public void testInternalStructure () {
		snake.setDirection(1);
		snake.move();
		assertFalse("old head should change", snake.returnHead().getXpos() == 200);
	}

}
