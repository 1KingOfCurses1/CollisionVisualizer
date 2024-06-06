package collisionvisualizer;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Abstract class representing a 1D shape with basic attributes and behaviors.
 */
abstract public class AbstractShape implements Shape {

    // Position in 1D space (x-axis)
    protected int xPos;

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
        this.mass = 0.0;
        this.velocity = 0.0;
        this.color = Color.BLACK;
    }

    /**
     * Secondary constructor to assign values to attributes.
     *
     * @param xPos - x position variable
     * @param mass - mass variable
     * @param velocity - velocity variable
     * @param color - color variable
     */
    public AbstractShape(int xPos, double mass, double velocity, Color color) {
        this();
        this.xPos = xPos;
        this.mass = mass;
        this.velocity = velocity;
        this.color = color;
    }

    // Abstract method to be implemented by subclasses for drawing the shape
    abstract public void draw(Graphics2D g2d);

    // Accessor and mutator methods
    public int getXPos() {
        return xPos;
    }

    public void setXPos(int xPos) {
        this.xPos = xPos;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public abstract void update();

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

    @Override
    public String toString() {
        return "X-position: " + xPos +
                "\nMass: " + mass +
                "\nVelocity: " + velocity +
                "\nColor: " + color.toString();
    }
}
