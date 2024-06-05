/*
 *
 *
 */
package collisionvisualizer;

//imports 
import java.awt.Color;
import java.awt.Graphics2D;

/**
 * making an abstract class that implements shape interface
 *
 * @author shtru6553
 */
abstract public class AbstractShape implements Shape {

    //creating protecting integer attribute for x position 
    protected int xPos;

    //creating protecting integer attribute for y position 
    protected int yPos;

    //creating protecting double attribute for mass
    protected double mass;

    //creating protecting double attribute for velocity
    protected double velocity;

    //creating protecting colour for colour attribute 
    protected Color colour;

    /**
     * Primary constructor setting attributes to default values
     */
    public AbstractShape() {

        //setting x position attribute to zero 
        xPos = 0;

        //setting y position attribute to zero 
        yPos = 0;

        //setting mass attribute to zero 
        mass = 0;

        //setting velocity attribute to zero 
        velocity = 0;

        //setting colour attribute to black
        colour = new Color(0, 0, 0);
    }

    /**
     * Secondary constructor to assign values to attributes
     *
     * @param xPos - x position variable
     * @param yPos - y position variable
     * @param mass - mass variable
     * @param velocity - velocity variable
     * @param colour - colour variable
     */
    public AbstractShape(int xPos, int yPos, double mass, double velocity, Color colour) {

        //invoking primary constructor 
        this();

        //assigning value to x position attribute 
        this.xPos = xPos;

        //assigning value to x position attribute
        this.yPos = yPos;

        //assigning value to mass attribute
        this.mass = mass;

        //assigning value to velocity attribute
        this.velocity = velocity;

        //assigning value to colour attribute
        this.colour = colour;
    }

    //abstract class that draws shapes
    abstract public void draw(Graphics2D g2d);

    /**
     * Accessor method to get x position
     *
     * @return - x position attribute
     */
    public int getXPos() {

        //return x position attribute
        return xPos;
    }

    /**
     * Accessor method to get y position
     *
     * @return - y position attribute
     */
    public int getYPos() {

        //return y position attribute
        return yPos;
    }

    /**
     * Mutator method to change x position attribute
     *
     * @param xPos - x position variable
     */
    public void setXPos(int xPos) {

        //assigning value to x position attribute 
        this.xPos = xPos;
    }

    /**
     * Mutator method to change y position attribute
     *
     * @param yPos - y position variable
     */
    public void setYPos(int yPos) {

        //assigning value to y position attribute 
        this.yPos = yPos;
    }

    /**
     * Accessor method to get mass
     *
     * @return - mass attribute
     */
    public double getMass() {

        //return mass attribute
        return mass;
    }

    /**
     * Accessor method to get velocity
     *
     * @return - velocity attribute
     */
    public double getVelocity() {

        //return velocity attribute
        return velocity;
    }

    /**
     * Mutator method to change mass attribute
     *
     * @param mass - mass variable
     */
    public void setMass(double mass) {

        //assigning value to mass attribute 
        this.mass = mass;
    }

    /**
     * Mutator method to change velocity attribute
     *
     * @param velocity - velocity variable
     */
    public void setVelocity(double velocity) {

        //assigning value to velocity attribute 
        this.velocity = velocity;
    }

    /**
     * Accessor method to get colour
     *
     * @return - colour attribute
     */
    public Color getColour() {

        //return colour attribute
        return colour;
    }

    /**
     * Mutator method to change colour attribute
     *
     * @param colour - colour variable
     */
    public void setColour(Color colour) {

        //assigning value to colour attribute 
        this.colour = colour;
    }


    /**
     * Equals method to check if shape is equal to another shape
     *
     * @param s - shape object
     * @return - true or false if objects are equal or not (same attribute
     * values)
     */
    public boolean equals(AbstractShape s) {

        //returning true or false if objects are equal or not (same attribute values) 
        return s.xPos == xPos && s.yPos == yPos && s.mass == mass && s.velocity == velocity && s.colour == colour;
    }

    /**
     * To String method to display shape attribute values
     *
     * @return - String with all shape attributes
     */
    public String toString() {

        //returning string with all shape attributes 
        return "X-position: " + xPos + "\nY-position: " + yPos + "\nMass: " + mass + "\nVelocity: " + velocity;
    }

}
