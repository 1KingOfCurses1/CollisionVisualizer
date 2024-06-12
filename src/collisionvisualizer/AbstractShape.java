/**
 * Shan Truong, Aryan Verma, Jerry Wu
 * June 11, 2024
 * Abstract shape class that holds all attributes and functions of a shape
 */
package collisionvisualizer;

//imports
import java.awt.Color;
import java.awt.Graphics2D;

abstract public class AbstractShape implements Shape {
    
    // Encapsulation of attributes
    // Position in 2D space (x-axis)
    protected double xPos;

    // Position in 2D space (y-axis)
    protected double yPos;

    // Mass attribute for physics calculations
    protected double mass;

    // Velocity attribute for physics calculations
    protected double velocity;

    // Color attribute for drawing the shape
    protected Color colour;

    /**
     * Primary constructor setting attributes to default values.
     */
    public AbstractShape() {
        
        //Initialize x position to 0
        xPos = 0;          
        
        //Initialize y position to 0
        yPos = 0;        
        
        //Initialize mass to 0.0
        mass = 0.0;     
        
        //Initialize velocity to 0.0
        velocity = 0.0;    
        
        // Initialize color to black
        colour = Color.BLACK; 
    }

    /**
     * Secondary constructor to assign values to attributes.
     * @param xPos - x position variable
     * @param yPos - y position of the variable
     * @param mass - mass variable
     * @param velocity - velocity variable
     * @param color - color variable
     */
    public AbstractShape(int xPos, int yPos, double mass, double velocity, Color colour) {
        
        //Call the primary constructor to initialize default values
        this();       
        
        //Set the x position
        this.xPos = xPos;     
        
        //Set the y position
        this.yPos = yPos;    
        
        //Set the mass
        this.mass = mass;   
        
        //Set the velocity
        this.velocity = velocity; 
        
        //Set the color
        this.colour = colour;     
    }

    /**
     * Abstract method to be implemented by subclasses for drawing the shape.
     * @param g2d the Graphics2D object used for drawing
     */
    abstract public void draw(Graphics2D g2d);

    /**
     * Gets the x position of the shape.
     * @return the x position of the shape
     */
    public double getXPos() {
        
        //Return the x position
        return xPos;
    }
    
    /**
     * Sets the x position of the shape.
     * @param xPos the x position to set
     */
    public void setXPos(double xPos) {
        
        //Set the x position
        this.xPos = xPos; 
    }
    
    /**
     * Gets the y position of the shape.
     * @return the y position of the shape
     */

    public double getYPos() {
        
        //Return the y position
        return yPos;
    }
    
    /**
     * Sets the y position of the shape.
     * @param yPos the y position to set
     */
    public void setYPos(double yPos) {
        
        //Set the y position
        this.yPos = yPos;
    }

    /**
     * Gets the mass of the shape.
     * @return the mass of the shape
     */
    public double getMass() {
        
        //Return the mass
        return mass;
    }
    
    /**
     * Sets the mass of the shape.
     * @param mass the mass to set
     */
    public void setMass(double mass) {
        
        //Set the mass
        this.mass = mass; 
    }
    
    /**
     * Gets the velocity of the shape.
     * @return the velocity of the shape
     */
    public double getVelocity() {
        
        //Return the velocity
        return velocity; 
    }
    
    /**
     * Sets the velocity of the shape.
     * @param velocity the velocity to set
     */
    public void setVelocity(double velocity) {
        
        //Set the velocity
        this.velocity = velocity;
    }
    
    /**
     * Gets the color of the shape.
     * @return the color of the shape
     */
    public Color getColour() {
        
        //Return the color
        return colour;
    }
    
    /**
     * Sets the color of the shape.
     * @param color the color to set
     */
    public void setColour(Color colour) {
        
        //Set the color
        this.colour = colour; 
    }
    
    /**
     * Abstract method to be implemented by subclasses for updating the shape's position.
     */
    public abstract void update();
    
    /**
     * Checks if the current shape is equal to another object.
     * @param obj the object to compare with
     * @return true if the objects are equal, false otherwise
     */
    public boolean equals(AbstractShape s) {
        
        return xPos == s.xPos && //Compare x positions
                yPos == s.yPos && //Compare y positions
                mass == s.mass && //Comparing mass
                velocity == s.velocity && //Comparing velocity
                colour.equals(s.colour); //Compare colors
    }
    
    /**
     * Provides a string representation of the shape.
     * @return a string describing the shape
     */
    @Override
    public String toString() {
        
        //returning all attributes in abstract shape
        return "X-position: " + xPos +           // Include x position in the string
                "\nY-position: " + yPos +         // Include y position in the string
                "\nMass: " + mass +               // Include mass in the string
                "\nVelocity: " + velocity +       // Include velocity in the string
                "\nColor: " + colour.toString();   // Include color in the string
    }
}
