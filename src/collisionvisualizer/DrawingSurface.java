package collisionvisualizer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * A class representing the drawing surface for visualizing collisions.
 * This class extends JPanel and implements Runnable to allow for animation.
 */
public class DrawingSurface extends JPanel implements Runnable {

    //Final velocity of the first square
    private double vf1; 
    
    //Final velocity of the second square
    private double vf2; 
    
    //Elasticity (determines type of elasticity) 
    private double e;
    
    private Thread animator; // Thread for handling animation
    private final int DELAY = 10; // Delay between animation frames in milliseconds

    private Square redSquare, blueSquare; // Two squares representing the objects in the simulation

    /**
     * Constructor initializes the drawing surface and the squares.
     */
    public DrawingSurface() {
        this.setFocusable(true); // Make the panel focusable
        this.requestFocus(); // Request focus for the panel
        // Initialize squares with default values and initial positions
        redSquare = new Square(10); // Default mass for red square
        blueSquare = new Square(10); // Default mass for blue square

        centerSquare(redSquare, 250, 150); // Center red square at (250, 150)
        centerSquare(blueSquare, 450, 150); // Center blue square at (450, 150)
    }

    /**
     * Updates the parameters of the squares with new masses and velocities.
     *
     * @param m1 mass of the red square
     * @param v1 velocity of the red square
     * @param m2 mass of the blue square
     * @param v2 velocity of the blue square
     * @param e coefficient of restitution (unused in this implementation)
     */
    public void updateParameters(double m1, double v1, double m2, double v2, double e) {
        // Update squares with new masses and velocities
        redSquare = new Square(m1);
        redSquare.setVelocity((int) v1); // Update velocity based on input
        blueSquare = new Square(m2);
        blueSquare.setVelocity((int) v2); // Update velocity based on input

        // Set initial positions to separate the squares
        centerSquare(redSquare, 250, 150);
        centerSquare(blueSquare, 450, 150);

        repaint(); // Repaint the panel to reflect changes
    }

    /**
     * Sets the final velocities of the squares after collision.
     *
     * @param vf1 final velocity of the red square
     * @param vf2 final velocity of the blue square
     */
    public void drawCollision(double vf1, double vf2,double e) {
        this.vf1 = vf1; // Set final velocity for red square
        this.vf2 = vf2; // Set final velocity for blue square
        this.e = e;
    }

    /**
     * Centers the square at the specified coordinates.
     *
     * @param square the square to be centered
     * @param centerX the x-coordinate of the center position
     * @param centerY the y-coordinate of the center position
     */
    private void centerSquare(Square square, int centerX, int centerY) {
        int newXPos = centerX - (int) (square.getWidth() / 2); // Calculate new x position
        int newYPos = centerY - (int) (square.getLength() / 2); // Calculate new y position
        square.setXPos(newXPos); // Set new x position
        square.setYPos(newYPos); // Set new y position
    }

    /**
     * Performs the drawing operations for the squares.
     *
     * @param g the Graphics object used for drawing
     */
    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        // Clear the panel
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Draw the squares at their updated positions
        g2d.setColor(Color.RED);
        redSquare.draw(g2d);
        g2d.setColor(Color.BLUE);
        blueSquare.draw(g2d);
    }

    /**
     * Overrides the paintComponent method to perform custom painting.
     *
     * @param g the Graphics object used for painting
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    /**
     * Updates the positions of the squares.
     */
    public void moveObject() {
        redSquare.update(); // Update red square's position
        blueSquare.update(); // Update blue square's position

        // Add logic to handle collisions or boundaries if needed
    }

    /**
     * Overrides the addNotify method to start the animation thread.
     */
    @Override
    public void addNotify() {
        super.addNotify();
        animator = new Thread(this); // Create a new thread for animation
        animator.start(); // Start the animation thread
    }

    /**
     * The run method for the animation thread.
     * This method updates the squares' positions and repaints the screen at regular intervals.
     */
    @Override
    public void run() {
        long beforeTime, timeDiff, sleep;
        beforeTime = System.currentTimeMillis(); // Record the start time

        //loop while true
        while (true) {
            // Update the squares' positions
            moveObject();

            // Check squares are within a +-20 range of each other 
            if ((redSquare.getXPos() + redSquare.getLength()) <=  (blueSquare.getXPos() + 20)
                    && (redSquare.getXPos() + redSquare.getLength()) >=  (blueSquare.getXPos() - 20)){
                
                //if the square values are not equal and the collision is completely inelastic 
                if((redSquare.getXPos() + redSquare.getLength()) !=  blueSquare.getXPos() && e == 0){
                    
                    //set red square to blue square posision (right side of red square to left side of blue square) 
                    redSquare.setXPos(blueSquare.getXPos() - (int)(redSquare.getLength()));
                }
                
                //setting red square velocity to its finial velocity 
                redSquare.setVelocity(vf1);
                
                //setting blue square velocity to its finial velocity 
                blueSquare.setVelocity(vf2);
            }

            // Redraw the screen
            repaint();

            timeDiff = System.currentTimeMillis() - beforeTime; // Calculate time difference
            sleep = DELAY - timeDiff; // Calculate sleep time

            if (sleep < 0) {
                sleep = 2; // Set minimum sleep time
            }

            try {
                Thread.sleep(sleep); // Sleep to control frame rate
            } catch (InterruptedException e) {
                JOptionPane.showMessageDialog(this, "Thread interrupted: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }

            beforeTime = System.currentTimeMillis(); // Record the start time of the next frame
        }
    }
}
