import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SnakeParts extends Coord {
//SnakeParts are Coord objects displayed on screen using a picture
public static final int SIZE = 10;

public static final String IMG_FILE = "snakedot.png";

private static BufferedImage img;

public SnakeParts (int xPos, int yPos) {
	super(xPos, yPos, SIZE, SIZE);

	try {
        if (img == null) {
            img = ImageIO.read(new File(IMG_FILE));
        }
    } catch (IOException e) {
        System.out.println("Internal Error:" + e.getMessage());
    }
}

@Override
public void draw(Graphics g) {
	g.drawImage(img, this.getXpos(), this.getYpos(), this.getWidth(), this.getHeight(), null);
}

}
