package collisionvisualizer;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Abstract class representing a 1D shape with basic attributes and behaviors.
 */
abstract public class AbstractShape implements Shape {
    // Encapsulation of attributes
    // Position in 2D space (x-axis)
    protected int xPos;
    
    // Position in 2D space (x-axis)
    protected int yPos;

    // Mass and velocity attributes for physics calculations
    protected double mass;
    protected double velocity;

    // Color for drawing the shape
    protected Color color;

    /**
     * Primary constructor setting attributes to default values.
     */
    public AbstractShape() {
        this.xPos = 0;
        this.yPos = 0;
        this.mass = 0.0;
        this.velocity = 0.0;
        this.color = Color.BLACK;
    }

    /**
     * Secondary constructor to assign values to attributes.
     *
     * @param xPos - x position variable
     * @param yPos - y position of the variable
     * @param mass - mass variable
     * @param velocity - velocity variable
     * @param color - color variable
     */
    public AbstractShape(int xPos, int yPos, double mass, double velocity, Color color) {
        this();
        this.xPos = xPos;
        this.yPos = yPos;
        this.mass = mass;
        this.velocity = velocity;
        this.color = color;
    }

    /**
     * Abstract method to be implemented by subclasses for drawing the shape.
     *
     * @param g2d the Graphics2D object used for drawing
     */
    abstract public void draw(Graphics2D g2d);

    /**
     * Gets the x position of the shape.
     *
     * @return the x position of the shape
     */
    public int getXPos() {
        return xPos;
    }
    /**
     * Sets the x position of the shape.
     *
     * @param xPos the x position to set
     */
    public void setXPos(int xPos) {
        this.xPos = xPos;
    }
    /**
     * Gets the y position of the shape.
     *
     * @return the y position of the shape
     */

    public int getYPos() {
        return yPos;
    }
    /**
     * Sets the y position of the shape.
     *
     * @param yPos the y position to set
     */
    public void setYPos(int yPos) {
        this.yPos = yPos;
    }

    /**
     * Gets the mass of the shape.
     *
     * @return the mass of the shape
     */
    public double getMass() {
        return mass;
    }
    /**
     * Sets the mass of the shape.
     *
     * @param mass the mass to set
     */
    public void setMass(double mass) {
        this.mass = mass;
    }
    /**
     * Gets the velocity of the shape.
     *
     * @return the velocity of the shape
     */
    public double getVelocity() {
        return velocity;
    }
    /**
     * Sets the velocity of the shape.
     *
     * @param velocity the velocity to set
     */
    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }
    /**
     * Gets the color of the shape.
     *
     * @return the color of the shape
     */
    public Color getColor() {
        return color;
    }
    /**
     * Sets the color of the shape.
     *
     * @param color the color to set
     */
    public void setColor(Color color) {
        this.color = color;
    }
    /**
     * Abstract method to be implemented by subclasses for updating the shape's position.
     */
    public abstract void update();
    /**
     * Checks if the current shape is equal to another object.
     *
     * @param obj the object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        AbstractShape that = (AbstractShape) obj;

        return xPos == that.xPos &&
                Double.compare(that.mass, mass) == 0 &&
                Double.compare(that.velocity, velocity) == 0 &&
                color.equals(that.color);
    }
    /**
     * Provides a string representation of the shape.
     *
     * @return a string describing the shape
     */
    @Override
    public String toString() {
        return "X-position: " + xPos +
                "\nMass: " + mass +
                "\nVelocity: " + velocity +
                "\nColor: " + color.toString();
    }
}
