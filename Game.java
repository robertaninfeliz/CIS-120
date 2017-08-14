/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.1, Apr 2017
 */

// imports necessary libraries for Java swing
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Game Main class that specifies the frame and widgets of the GUI
 */
public class Game implements Runnable {
    public void run() {
        // Top-level frame in which game components live
    	final JFrame frame = new JFrame("SNAKE: Re-Imagined");
        frame.setLocation(300, 300);
        
        //text displaying game instructions
        String instructions = "Welcome to Snake!! \nInstructions: \nThe instructions of the game are" 
        + " simple.\nUse the arrow keys to move up, down, left or right.\nDon't run into your own body" 
    	+ "  or you'll die. \nIf you touch the boundaries of the screen, \nyou also die," 
        + " so make sure to stay in bounds.\nIf this sounds good to you, press okay to start the game!";
    	JOptionPane.showMessageDialog(frame, instructions, "Snake:Re-imagined", JOptionPane.OK_OPTION);
    	

        // Status panel
        final JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.SOUTH);
        final JLabel status = new JLabel("Running...");
        status_panel.add(status);

        // Main playing area
        final GameBoard court = new GameBoard(status);
        frame.add(court, BorderLayout.CENTER);

        // Reset button
        final JPanel control_panel = new JPanel();
        frame.add(control_panel, BorderLayout.NORTH);

        final JButton reset = new JButton("Reset");
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                court.reset();
            }
        });
        control_panel.add(reset);
        
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        court.reset();
    }

    /**
     * Main method run to start and run the game. Initializes the GUI elements specified in Game and
     * runs it. IMPORTANT: Do NOT delete! You MUST include this in your final submission.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Game());
    }
}