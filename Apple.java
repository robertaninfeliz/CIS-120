import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
//draws and places the apple on the screen

public class Apple extends Coord {
public static final int SIZE = 20;
public static final String IMG_FILE = "red.png";

private static BufferedImage img;

public Apple (int xPos, int yPos) {
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
