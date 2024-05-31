/*
 * Aryan Verma
 * 5/31/2024
 * Circle Class
 */
package collisionvisualizer;

// Imports
import java.awt.Graphics2D;

public class Circle extends AbstractShape {
    
    // Attributes
    // Circle has a radius
    private int radius;
    
    // Primary constructor, if nothing is provided
    public Circle() {
        radius = 0; // Radius is set to 0
    }
    /**
     * Secondary constructor, if radius and color is provided
     * @param radius - radius of the circle
     */
    public Circle(int radius) {
        // Chains to the primary constructor
        this();
        // Sets the radius
        this.radius = radius;
    }
    /**
     * Accessor, to get the radius of the circle
     * @return - radius of the circle
     */
    public int getRadius() {
        return radius;
    }
    /**
     * Mutator, sets the radius
     * @param radius - new radius
     */
    public void setRadius(int radius) {
        this.radius = radius;
    }
    /**
     * Clone method, to create a copy of a circle
     * @return - circle clone
     */
    public Circle clone() {
        Circle c = new Circle(radius);
        return c;
    }
    /**
     * Equal method, checks if two circles are equal
     * @param c - circle to compare to
     * @return - true or false
     */
    public boolean equals(Circle c){
        return super.equals(c) && c.radius == radius;
    }
    /**
     * Method that creates a string representation of a circle
     * @return - String representation
     */
    public String toString(){
        return super.toString() + "\nRadius: " + radius;
    }
    /**
     * Draw method to draw the circle
     * @param g2d - graphics to create the shape
     */
    public void draw(Graphics2D g2d) {
        g2d.fillOval(xPos, yPos, radius, radius);
    }



}
