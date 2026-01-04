/**
 * Shan Truong, Aryan Verma, Jerry Wu
 * June 11, 2024
 * Circle class that hold all circle attributes and functions
 */
package collisionvisualizer;

// Imports
import java.awt.Color;
import java.awt.Graphics2D;


public class Circle extends AbstractShape {

    // Encapsulation of attributes of a circle
    //  Radius of circle
    private int radius;

    /**
     * Primary constructor initializing the circle to default values.
     */
    public Circle() {
        super();
        radius = 10; 
    }

    /**
     * Secondary constructor to set a specific integer radius.
     * @param radius - the radius of the circle
     */
    public Circle(int radius) {
        
        // Chain to the primary constructor to initialize inherited attributes
        this();
        
        // Set the radius to the inputted value
        this.radius = radius;
    }

    public Circle(double mass) {
        this();
        this.mass = mass;
        this.radius = (int) (mass * 5); // Balanced scaling
    }

    /**
     * Accessor method to get the radius of the circle.
     * @return the radius of the circle
     */
    public int getRadius() {
        return radius; // return radius of the circle
    }

    /**
     * Mutator method to set the radius of the circle.
     * @param radius - new radius
     */
    public void setRadius(int radius) {
        
        //setting value to radius attribute to inputed value
        this.radius = radius;
    }

    /**
     * Clone method to create a copy of a circle.
     * @return a clone of the circle
     */
    @Override
    public Circle clone() {
        Circle c = new Circle(radius); // Create a new circle with the same radius
       
        //returning clone
        return c;
    }

    /**
     * Equals method to check if two circles are equal.
     * @param c - circle to compare to
     * @return true if the circles are equal, false otherwise
     */
    public boolean equals(Circle c) {
        return super.equals(c) && c.radius == radius; // Check if the superclass attributes and radius are equal
    }

    /**
     * Method to create a string representation of a circle.
     * @return a string representation of the circle
     */
    @Override
    public String toString() {
        return super.toString() + "\nRadius: " + radius; // Include radius in the string representation
    }

    /**
     * Draw method to draw the circle.
     * @param g2d - graphics object used for drawing the shape
     */
    public void draw(Graphics2D g2d) {
        g2d.setColor(colour); // Set the color for drawing
        g2d.fillOval(((int)(Math.round(xPos))), ((int)(Math.round(yPos))), radius * 2, radius * 2); // Draw a filled oval representing the circle
    }

    // inherited update() from AbstractShape is sufficient now for 2D movement
    
    /**
     * Helper to draw glowing shapes
     */
    protected void drawGlow(Graphics2D g2d, int x, int y, int size, Color c) {
        float[] fractions = {0.0f, 1.0f};
        Color[] colors = {new Color(c.getRed(), c.getGreen(), c.getBlue(), 100), new Color(c.getRed(), c.getGreen(), c.getBlue(), 0)};
        java.awt.RadialGradientPaint paint = new java.awt.RadialGradientPaint(x + size/2, y + size/2, size, fractions, colors);
        g2d.setPaint(paint);
        g2d.fillOval(x - size/2, y - size/2, size * 2, size * 2);
        g2d.setColor(c);
        g2d.fillRect(x, y, size, size);
    }

}
