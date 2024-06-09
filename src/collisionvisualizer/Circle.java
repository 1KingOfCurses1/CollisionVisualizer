package collisionvisualizer;

// Imports
import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Circle class representing a circular shape in a 2D space with specific attributes and behaviors.
 */
public class Circle extends AbstractShape {

    // Encapsulation of attributes of a circle
    //  Radius of circle
    private int radius;
    // mass of circle
    private double mass;

    /**
     * Primary constructor initializing the circle to default values.
     */
    public Circle() {
        radius = 0; // Radius is set to 0
        mass = 0; // mass is initialized to 0
    }

    /**
     * Secondary constructor initializing the circle with a specific radius.
     *
     * @param radius - radius of the circle
     */
    public Circle(double mass) {
        this(); // Chains to the primary constructor
        this.radius = (int)mass * 10; // Sets the radius based off of mass
    }

    /**
     * Accessor method to get the radius of the circle.
     *
     * @return the radius of the circle
     */
    public int getRadius() {
        return radius; // return radius of the circle
    }

    /**
     * Mutator method to set the radius of the circle.
     *
     * @param radius - new radius
     */
    public void setRadius(int radius) {
        this.radius = radius;
    }

    /**
     * Clone method to create a copy of a circle.
     *
     * @return a clone of the circle
     */
    @Override
    public Circle clone() {
        Circle c = new Circle(radius); // Create a new circle with the same radius
        c.setXPos(this.xPos); // Set the x position
        c.setYPos(this.yPos); // Set the y position
        c.setMass(this.mass); // Set the mass
        c.setVelocity(this.velocity); // Set the velocity
        c.setColour(this.color); // Set the color
        return c;
    }

    /**
     * Equals method to check if two circles are equal.
     *
     * @param c - circle to compare to
     * @return true if the circles are equal, false otherwise
     */
    public boolean equals(Circle c) {
        return super.equals(c) && c.radius == radius; // Check if the superclass attributes and radius are equal
    }

    /**
     * Method to create a string representation of a circle.
     *
     * @return a string representation of the circle
     */
    @Override
    public String toString() {
        return super.toString() + "\nRadius: " + radius; // Include radius in the string representation
    }

    /**
     * Draw method to draw the circle.
     *
     * @param g2d - graphics object used for drawing the shape
     */
    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(color); // Set the color for drawing
        g2d.fillOval(xPos, yPos, radius * 2, radius * 2); // Draw a filled oval representing the circle
    }

    /**
     * Update method to update the circle's position based on its velocity.
     */
    @Override
    public void update() {
        xPos += velocity; // Update the x position based on the velocity
    }

    /**
     * Gets the y position of the shape.
     *
     * @return the y position of the shape
     */
    @Override
    public int getYPos() {
        return yPos; // Return the y position
    }

    /**
     * Gets the color of the shape.
     *
     * @return the color of the shape
     */
    @Override
    public Color getColour() {
        return color; // Return the color of the circle
    }

    /**
     * Sets the y position of the shape.
     *
     * @param yPos the y position to set
     */
    @Override
    public void setYPos(int yPos) {
        this.yPos = yPos; // Set the y position
    }

    /**
     * Sets the color of the shape.
     *
     * @param colour the color to set
     */
    @Override
    public void setColour(Color colour) {
        this.color = colour; // Set the color of the circle
    }
}
