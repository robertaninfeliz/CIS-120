import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;


@SuppressWarnings("serial")
public class GameBoard extends JPanel {
	
	//Game constants
	public static final int COURT_WIDTH = 400;
	public static final int COURT_LENGTH = 400;
    public static final int INTERVAL = 160;
    private Apple apple = new Apple (100, 255);
    private SnakeParts initial = new SnakeParts(344,200);
    private Snake snake = new Snake(new SnakeParts (344, 200));
    public static boolean playing = false;  
    private JLabel status;
    
    public GameBoard (JLabel status) {
    	setBorder(BorderFactory.createLineBorder(Color.BLACK));

    	 Timer timer = new Timer(INTERVAL, new ActionListener() {
             public void actionPerformed(ActionEvent e) {
            	 try {
					tick();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
                
             }
         });
         timer.start();
         setFocusable(true);
         
         
         addKeyListener(new KeyAdapter() {
             public void keyPressed(KeyEvent e) {
                 if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                     snake.setDirection(0);
                 } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                     snake.setDirection(1);
                 } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                     snake.setDirection(2);
                 } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                     snake.setDirection(3);
                 }
             
             }
         });

         this.status = status;
     }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        apple.draw(g);
        snake.drawSnake(g);
    }
    
    public void tick () throws IOException {
    	if(playing) {
    		snake.move();
    		if(snake.checkCollision(apple)){
    			spawnFood();
    			snake.getLonger();
    			status.setText("Score: " + snake.getLength());
    		} 
    		SnakeParts head = snake.returnHead();
    		if(head.getXpos() >= 400 || head.getXpos() < 0 || head.getYpos() >=400 
    				||head.getYpos() < 0 || snake.checkSnakeCollision()) {
    			playing = false;
    			status.setText("You lose! Your score was " + snake.getLength());
    			readScores();
    			writeScores();
    		}
    		repaint();
    	}
    }
   //method reading in all scores from txt file
    public void readScores () throws IOException{
    	JFrame frame = new JFrame("Friendly Competition");
    	frame.setLocation(300, 300);
    	JPanel score_panel = new JPanel();
        frame.add(score_panel, BorderLayout.SOUTH);
        
    	Reader r = new FileReader("high_scores.txt");
    	BufferedReader s = new BufferedReader(r);
    	String curr = "";
    	while((curr = s.readLine()) != null) {
    		
    	status = new JLabel("Even though this game isn't curved. I thought I'd let you know how " +
    	    	"the person before you did. They had a score of " + curr);
    	}
    	s.close();
    	
        score_panel.add(status);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
    }
    
    //method writing all the scores into the text file
    public void writeScores () throws IOException{
    	Writer r = new FileWriter("high_scores.txt", true);
    	BufferedWriter s = new BufferedWriter(r);
    	s.write("\n" + snake.getLength());
    	s.close();
    }
    
   //method to create food on the screen 
   public void spawnFood () {
	   int ranx = 10 + (int) (Math.random() * 355);
	   int rany = 10 + (int) (Math.random() * 355);
	   apple.setXpos(ranx);
	   apple.setYpos(rany);
   }
   
	public void reset() {
		snake = new Snake(initial);
		playing = true;
		status.setText("Score: " + snake.getLength());
        requestFocusInWindow();
	}
	
	 @Override
	 public Dimension getPreferredSize() {
	     return new Dimension(COURT_WIDTH, COURT_LENGTH);
	    }
	
    }
