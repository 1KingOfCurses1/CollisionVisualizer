package collisionvisualizer;

import java.awt.*;

/**
 * A class representing a square in a 1D space with specific attributes and behaviors.
 */
public class Square extends AbstractShape {

    // Dimensions of the square
    private int length;
    private int width;

    /**
     * Default constructor initializing the square to default values.
     */
    public Square() {
        super();
        this.length = 0;
        this.width = 0;
    }

    /**
     * Constructor initializing the square with a specific mass and deriving its dimensions.
     *
     * @param mass - mass of the square
     */
    public Square(double mass) {
        this();
        this.mass = mass;
        this.length =  (int) mass * 10; // Size derived from mass for visualization
        this.width = (int) mass * 10;  // Size derived from mass for visualization
    }

    // Accessor and mutator methods for length and width
    public double getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public void update() {
        this.xPos += this.velocity; // Update position in 1D space
    }

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        if (!super.equals(obj)) return false;

        Square square = (Square) obj;
        return Double.compare(square.length, length) == 0 &&
                Double.compare(square.width, width) == 0;
    }

    @Override
    public String toString() {
        return super.toString() + "\nLength: " + length + "\nWidth: " + width;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.fillRect(xPos, yPos, width, length); 
    }


    @Override
    public Color getColour() {
        return null;
    }


    @Override
    public void setColour(Color colour) {
        
    }
}
