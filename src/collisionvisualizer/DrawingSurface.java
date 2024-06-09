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

    private double vf1;
    private double vf2;
    
    private Thread animator;
    private final int DELAY = 25;

    private Square redSquare, blueSquare;
    /**
     * Constructor initializes the drawing surface and the squares.
     */
    public DrawingSurface() {
        this.setFocusable(true);
        this.requestFocus();
        // Initialize squares with default values and initial positions
        redSquare = new Square(10);  // Default mass
        blueSquare = new Square(10);  // Default mass

        centerSquare(redSquare, 250, 150);
        centerSquare(blueSquare, 450, 150);

    }
    /**
     * Updates the parameters of the squares with new masses and velocities.
     *
     * @param m1  mass of the red square
     * @param v1  velocity of the red square
     * @param m2  mass of the blue square
     * @param v2  velocity of the blue square
     * @param e   coefficient of restitution (unused in this implementation)
     * @param vf1 final velocity of the red square (unused in this implementation)
     * @param vf2 final velocity of the blue square (unused in this implementation)
     */
    public void updateParameters(double m1, double v1, double m2, double v2, double e) {
        // Update squares with new masses and velocities
        redSquare = new Square(m1);
        redSquare.setVelocity((int) v1);  // Update velocity based on input
        blueSquare = new Square(m2);
        blueSquare.setVelocity((int) v2);  // Update velocity based on input
        
        // Set initial positions to separate the squares
        centerSquare(redSquare, 250, 150);
        centerSquare(blueSquare, 450, 150);

        repaint();
    }
    
    public void drawCollision(double vf1, double vf2) {

        this.vf1 = vf1;
        
        this.vf2 = vf2;
    }
    
    /**
     * Centers the square at the specified coordinates.
     *
     * @param square  the square to be centered
     * @param centerX the x-coordinate of the center position
     * @param centerY the y-coordinate of the center position
     */
    private void centerSquare(Square square, int centerX, int centerY) {
        int newXPos = centerX - (int) (square.getWidth() / 2);
        int newYPos = centerY - (int) (square.getLength() / 2);
        square.setXPos(newXPos);
        square.setYPos(newYPos);
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
        redSquare.update();
        blueSquare.update();

        // Add logic to handle collisions or boundaries if needed
    }
    /**
     * Overrides the addNotify method to start the animation thread.
     */
    @Override
    public void addNotify() {
        super.addNotify();
        animator = new Thread(this);
        animator.start();
    }
    /**
     * The run method for the animation thread.
     * This method updates the squares' positions and repaints the screen at regular intervals.
     */
    @Override
    public void run() {
        long beforeTime, timeDiff, sleep;
        beforeTime = System.currentTimeMillis();

        while (true) {
            // Update the squares' positions
            moveObject();
            
            System.out.println("red: " + (redSquare.getXPos() + redSquare.getLength()) + " BLue: " + (blueSquare.getXPos() + 1));
            if ((redSquare.getXPos() + redSquare.getLength()) <=  (blueSquare.getXPos())){
                
                redSquare.setVelocity(vf1);

                blueSquare.setVelocity(vf2);
            }
            
            // Redraw the screen
            repaint();

            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDiff;

            if (sleep < 0) {
                sleep = 2;
            }

            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                JOptionPane.showMessageDialog(this, "Thread interrupted: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }

            beforeTime = System.currentTimeMillis();
        }
    }
}
