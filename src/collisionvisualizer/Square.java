package collisionvisualizer;

import java.awt.*;

/**
 * A class representing a square in a 1D space with specific attributes and behaviors.
 */
public class Square extends AbstractShape {

    // Encapsulation of attributes of a square
    private int length;
    private int width;
    private double mass;

    /**
     * Default constructor initializing the square to default values.
     */
    public Square() {
        super();
        this.length = 0;
        this.width = 0;
        this.mass = 0;
    }

    /**
     * Constructor initializing the square with a specific mass and deriving its dimensions.
     * Chains the primary constructor
     * @param mass - the mass of the square
     */
    public Square(double mass) {
        this();
        this.mass = mass;
        this.length =  (int) mass * 10; // Size derived from mass for visualization
        this.width = (int) mass * 10;  // Size derived from mass for visualization
    }

    /**
     * Gets the length of the square.
     *
     * @return the length of the square
     */
    public double getLength() {
        return length;
    }

    /**
     * Sets the length of the square.
     *
     * @param length the length to set
     */
    public void setLength(int length) {
        this.length = length;
    }

    /**
     * Gets the width of the square.
     *
     * @return the width of the square
     */
    public double getWidth() {
        return width;
    }
    /**
     * Sets the width of the square.
     *
     * @param width the width to set
     */
    public void setWidth(int width) {
        this.width = width;
    }
    /**
     * Updates the position of the square in 1D space based on its velocity.
     */
    @Override
    public void update() {
        this.xPos += this.velocity; // Update position in 1D space
    }
    /**
     * Creates a clone of the current square.
     *
     * @return a new Square object with the same attributes as the current one
     */
    @Override
    public Square clone() {
        Square clone = new Square(this.mass);
        clone.setXPos(this.xPos);
        clone.setLength(this.length);
        clone.setWidth(this.width);
        clone.setVelocity(this.velocity);
        clone.setColor(this.color);
        return clone;
    }
    /**
     * Checks if the current square is equal to another object.
     *
     * @param obj the object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        if (!super.equals(obj)) return false;

        Square square = (Square) obj;
        return Double.compare(square.length, length) == 0 &&
                Double.compare(square.width, width) == 0;
    }
    /**
     * Provides a string representation of the square.
     *
     * @return a string describing the square
     */
    @Override
    public String toString() {
        return super.toString() + "\nLength: " + length + "\nWidth: " + width;
    }
    /**
     * Draws the square using the specified Graphics2D object.
     *
     * @param g2d the Graphics2D object used for drawing
     */
    @Override
    public void draw(Graphics2D g2d) {
        g2d.fillRect(((int)(Math.round(xPos))), ((int)(Math.round(yPos))), width, length); 
    }

    /**
     * Gets the color of the square. (Currently returns null as not implemented)
     *
     * @return the color of the square
     */
    @Override
    public Color getColour() {
        return null;
    }

    /**
     * Sets the color of the square. (Currently not implemented)
     *
     * @param colour the color to set
     */
    @Override
    public void setColour(Color colour) {
        // Not implemented
    }
}
