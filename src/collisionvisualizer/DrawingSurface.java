/**
 * Shan Truong, Aryan Verma, Jerry Wu
 * June 11, 2024
 * Drawing  surface that draws and animates collision
 */
package collisionvisualizer;

//imports
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
    
    //Thread for handling animation
    private Thread animator; 
    
    //Delay between animation frames in milliseconds
    private final int DELAY = 3; 

    //Two squares representing the objects in the simulation
    private Square redSquare, blueSquare; 
    
    //Logs for the two x position of two square
    private String logXPos; 
    
    /**
     * Constructor initializes the drawing surface and the squares.
     */
    public DrawingSurface() {
        
        //Make the panel focusable
        this.setFocusable(true); 
        
        //Request focus for the panel
        this.requestFocus();
        
        //Initialize squares with default values and initial positions (for lines 56 - 62)
        
        //Default mass for red square
        redSquare = new Square(10); 
        
        //Default mass for blue square
        blueSquare = new Square(10);

        //Center red square at (250, 150)
        centerSquare(redSquare, 250, 150); 
        
        //Center blue square at (450, 150)
        centerSquare(blueSquare, 450, 150); 
    }

    /**
     * Accessor that gets the current x location of the red square
     * @return Current x position of the red square
     */
    public double getRedXPos(){
        
        //returning red square x posision
        return redSquare.getXPos();
    }

    /**
     * Accessor that gets the current y location of the blue square
     * @return Current x position of the blue square
     */
    public double getBlueXPos(){
        
        //returning blue square x posision
        return blueSquare.getXPos();
    }
    /**
     * Accessor that returns the log of x positions
     * @return - string presentation of x position of the squares
     */
    public String getLogXPos(){
        
        //returning log x posision
        return logXPos;
    }
    
    /**
     * Mutator for red X Position 
     * @param redXPos - inputed red x position
     */
    public void setRedXPos(double redXPos){
        
        //setting red square x posision
        redSquare.setXPos(redXPos);
    }
    
    /**
     * Mutator for blue X Position
     * @param blueXPos - inputed blue x position
     */
    public void setBlueXPos(double blueXPos){
        
        //setting blue square x posision
        blueSquare.setXPos(blueXPos);
    }
    
    /**
     * Mutator for log X Position
     * @param logXPos - inputed log x position
     */
    public void setLogXPos(String logXPos){
        
        //setting log x posision
        this.logXPos = logXPos;
    }
    
    /**
     * Accessor that gets the final velocity of shape 1
     * @return - final velocity of shape 1
     */
    public double getVf1(){
        
        //return final velocity of shape 1
        return vf1;
    }
    
    /**
     * Accessor that gets the final velocity of shape 2
     * @return - final velocity of shape 2
     */
    public double getVf2(){
        
        //return final velocity of shape 2
        return vf2;
    }
    
    /**
     * Accessor that gets the type of elasticity (type of collision) 
     * @return - type of elasticity
     */
    public double getE(){
        
        //return type of elasticity
        return e;
    }
    
    /**
     * Mutator that sets the final velocity of shape 1
     * @param vf1 - inputed value of the final velocity of shape 1
     */
    public void setVf1(double vf1){
        
        //setting final velocity of shape 1 to inputed value 
        this.vf1 = vf1;
    }
    
    /**
     * Mutator that sets the final velocity of shape 2
     * @param vf2 - inputed value of the final velocity of shape 2
     */
    public void setVf2(double vf2){
        
        //setting final velocity of shape 2 to inputed value 
        this.vf1= vf2;
    }
    
    /**
     * Mutator that sets the type of elasticity (type of collision) 
     * @param e - inputed value of the type of elasticity 
     */
    public void setE(double e){
        
        //setting elasticity to inputed value
        this.e = e;
    }
    
    /**
     * Updates the parameters of the squares with new masses and velocities.
     * @param m1 mass of the red square
     * @param v1 velocity of the red square
     * @param m2 mass of the blue square
     * @param v2 velocity of the blue square
     * @param e coefficient of restitution (unused in this implementation)
     */
    public void updateParameters(double m1, double v1, double m2, double v2, double e) {
        
        //Update squares with new masses and velocities
        
        //updating mass of red square
        redSquare = new Square(m1);
        
        //Update velocity based on input
        redSquare.setVelocity(v1 / 5); 
        
        //updating mass of blue square
        blueSquare = new Square(m2);
        
        //Update velocity based on input
        blueSquare.setVelocity(v2 / 5); 

        //Set initial positions to separate the squares
        
        //Set initial position of red square 
        centerSquare(redSquare, 250, 150);
        
        //Set initial position of blue square 
        centerSquare(blueSquare, 450, 150);

        //Repaint the panel to reflect changes
        repaint(); 
    }

    /**
     * Sets the final velocities of the squares after collision.
     * @param vf1 final velocity of the red square
     * @param vf2 final velocity of the blue square
     */
    public void drawCollision(double vf1, double vf2,double e) {
        
        //Set final velocity for red square
        this.vf1 = vf1; 
        
        //Set final velocity for blue square
        this.vf2 = vf2; 
        
        //set elasticity type 
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
        
        //Calculate new x position
        int newXPos = centerX - (int) (square.getWidth() / 2); 
        
        //Calculate new y position
        int newYPos = centerY - (int) (square.getLength() / 2); 
        
        //Set new x position
        square.setXPos(newXPos);
        
        //Set new y position
        square.setYPos(newYPos); 
    }

    /**
     * Performs the drawing operations for the squares.
     * @param g the Graphics object used for drawing
     */
    private void doDrawing(Graphics g) {
        
        //casting graphics to graphics2d and assigning to g2s
        Graphics2D g2d = (Graphics2D) g;

        //Clear the panel
        g2d.setColor(Color.WHITE);
        
        //creating rectangle
        g2d.fillRect(0, 0, getWidth(), getHeight());

        //Draw the squares at their updated positions
        
        //setting colour to red 
        g2d.setColor(Color.RED);
        
        //drawing red square
        redSquare.draw(g2d);
        
        //setting colour to blue
        g2d.setColor(Color.BLUE);
        
        //drawing blue square 
        blueSquare.draw(g2d);
    }

    /**
     * Overrides the paintComponent method to perform custom painting.
     * @param g the Graphics object used for painting
     */
    @Override
    protected void paintComponent(Graphics g) {
        
        //
        super.paintComponent(g);
        doDrawing(g);
    }

    /**
     * Updates the positions of the squares.
     */
    public void moveObject() {
        
        //Update red square's position
        redSquare.update(); 
        
        //Update blue square's position
        blueSquare.update();
    }

    /**
     * Overrides the addNotify method to start the animation thread.
     */
    @Override
    public void addNotify() {
        super.addNotify();
        
        //Create a new thread for animation
        animator = new Thread(this); 
        
        //Start the animation thread
        animator.start();
    }

    /**
     * The run method for the animation thread.
     * This method updates the squares' positions and repaints the screen at regular intervals.
     */
    @Override
    public void run() {
        long beforeTime, timeDiff, sleep;
        
        //Record the start time
        beforeTime = System.currentTimeMillis(); 
        
        //loop while true
        while (true) {
            
            // Update the squares' positions
            moveObject();
            
            //keeping a log of the positions not implemented
            logXPos += "Red: " + redSquare.getXPos () + " Blue: " + redSquare.getXPos () + "\n";

            // Check squares are within a +-5 range of each other 
            if ((redSquare.getXPos() + redSquare.getLength()) <=  (blueSquare.getXPos() + 5)
                    && (redSquare.getXPos() + redSquare.getLength()) >=  (blueSquare.getXPos() - 5)){
                
                //if the square values are not equal and the collision is completely inelastic 
                if((redSquare.getXPos() + redSquare.getLength()) !=  blueSquare.getXPos() && e == 0){
                    
                    //set red square to blue square posision (right side of red square to left side of blue square) 
                    redSquare.setXPos(blueSquare.getXPos() - (int)(redSquare.getLength()));
                }
                
                //setting red square velocity to its finial velocity 
                redSquare.setVelocity(vf1 / 5);
                
                //setting blue square velocity to its finial velocity 
                blueSquare.setVelocity(vf2 / 5);
            }

            // Redraw the screen
            repaint();

            //Calculate time difference
            timeDiff = System.currentTimeMillis() - beforeTime;
            
            //Calculate sleep time
            sleep = DELAY - timeDiff; 

            //if sleep time is less than zero 
            if (sleep < 0) {
                
                //Set minimum sleep time
                sleep = 2; 
            }

            //trying to control frame rate
            try {
                
                //Sleep to control frame rate
                Thread.sleep(sleep); 
            } 
            
            //if an error occurs 
            catch (InterruptedException e) {
                
                //display error message
                JOptionPane.showMessageDialog(this, "Thread interrupted: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }

            //Record the start time of the next frame
            beforeTime = System.currentTimeMillis(); 
        }
    }
}
