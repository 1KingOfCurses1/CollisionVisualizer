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
        super();
        this.length = 20;
        this.width = 20;
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

    public Square(double mass){
        this();
        this.mass = mass;
        this.length = (int) (mass * 10);
        this.width = (int) (mass * 10);
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
    // Inherited update() handles 2D movement now
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
     * Checks if the current square is equal to another square.
     * Compares both inherited attributes and square-specific dimensions.
     * @param s - square to compare with
     * @return true if the squares are equal, false otherwise
     */
    public boolean equals(Square s) {
        
        // Compare inherited attributes and square-specific dimensions
        return super.equals(s) && s.length == length && s.width == width;
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
        
        // Set the color for the square
        g2d.setColor(colour);
        
        // Draw the filled rectangle at the current position with the specified dimensions
        g2d.fillRect((int) Math.round(xPos), (int) Math.round(yPos), width, length);
    }
}