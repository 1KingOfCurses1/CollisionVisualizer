/**
 * Shan Truong, Aryan Verma, Jerry Wu
 * June 11, 2024
 * Square class that holds all square attributes and functions
 */
package collisionvisualizer;

import java.awt.Graphics2D;


public class Square extends AbstractShape {

    // Encapsulation of attributes of a square
    private int length;
    private int width;

    /**
     * Default constructor initializing the square to default values.
     */
    public Square() {
        
        //invoking primary method of super class
        super();
        
        //assigning default value to length 
        this.length = 0;
        
        //assigning default value to width 
        this.width = 0;
    }

    /**
     * Secondary constructor initializing the square with a specific length and width
     * @param length - inputted length value
     * @param width  - inputted width value
     */
    public Square(int length, int width) {
        
        //Chains to the primary constructor
        this();
        
        //assigning inputed value to length 
        this.length =  length;
        
        //assigning inputed value to width 
        this.width = width;
    }

    /**
     * Secondary constructor initializing the square with a specific mass and deriving its dimensions.
     * @param mass - the mass of the square
     */
    public Square(double mass){
        
        length = (int) mass * 10; // Size derived from mass for visualization
        width = (int) mass * 10;  // Size derived from mass for visualization
    }
    /**
     * Gets the length of the square.
     * @return the length of the square
     */
    public double getLength() {
        
        //returning the length of the square
        return length;
    }

    /**
     * Sets the length of the square.
     * @param length the length to set
     */
    public void setLength(int length) {
        
        //assigning inputed value to length
        this.length = length;
    }

    /**
     * Gets the width of the square.
     * @return the width of the square
     */
    public double getWidth() {
        
        //returning the width of the square
        return width;
    }
    /**
     * Sets the width of the square.
     * @param width the width to set
     */
    public void setWidth(int width) {
        
        //assigning inputed value to width 
        this.width = width;
    }
    /**
     * Updates the position of the square in 1D space based on its velocity.
     */
    public void update() {
        this.xPos += this.velocity; // Update position in 1D space
    }
    /**
     * Creates a clone of the current square.
     * @return a new Square object with the same attributes as the current one
     */
    public Square clone() {
        
        //cloning square with all the same attributes
        Square clone = new Square(length,width);
        
        //returning clone
        return clone;
    }
    /**
     * Checks if the current square is equal to another object.
     * @param s - shape to check equals
     * @return true if the objects are equal, false otherwise
     */
    public boolean equals(Square s) {
        
        //returning if the other square has the same attribute values as this square
        return s.length == length && s.width == width;
    }
    /**
     * Provides a string representation of the square.
     * @return a string describing the square
     */
    public String toString() {
        
        //displaying all square attributes
        return super.toString() + "\nLength: " + length + "\nWidth: " + width;
    }
    
    /**
     * Draws the square using the specified Graphics2D object.
     * @param g2d the Graphics2D object used for drawing
     */
    public void draw(Graphics2D g2d) {
        
        //drawing square with specific position and size
        g2d.fillRect(((int) (Math.round(xPos))), ((int) (Math.round(yPos))), width, length);
    }
}