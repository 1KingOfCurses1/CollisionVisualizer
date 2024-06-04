/*
Shan Truong
May 29, 2024
Square class with all square functions
 */
package collisionvisualizer;

//imports
import java.awt.Graphics2D;

public class Square extends AbstractShape{
    /**
     * Encapsulation of attributes
     */
    private double length; // length of the square

    private double width; // width of the square
    private double mass;
    /**
     * Primary Constructor that initialize the attributes of a square
     */
    public Square() {

        //assigning value to length attribute
        length = 0;

        //assigning value to width attribute
        width = 0;

    }

    /**
     *
     * @param mass
     */
    public Square(double mass) {

        //invoking primary constructor
        this();

        //assigning value to length attribute
        this.length = mass * 10;

        //assigning value to width attribute
        this.width = mass * 10;

        this.mass = mass;

    }

    /**
     * Accessor that gets the length of a square
     * @return - the length of a square
     */
    public double getLength() {

        //return length attribute
        return length;
    }

    /**
     * Accessor that gets the width of the square
     * @return - the width of the square
     */
    public double getWidth() {

        //return width attribute
        return width;
    }

    /**
     * mutator that sets the length of the square
     * @param length - length of the square
     */
    public void setLength(int length) {

        //assigning value to length attribute
        this.length = length;
    }

    /**
     * mutator that sets the width of the square
     *
     * @param width - width of the square
     */
    public void setWidth(int width) {

        //assigning value to width attribute
        this.width = width;
    }

    /**
     * a clone method to create an exact copy of the square
     * @return - a copy of the square
     */
    public Square clone() {

        //cloning Square object with square object attributes
        Square s = new Square(mass);

        //returning cloned Square object
        return s;
    }

    /**
     * equals method that check if the two square are the same, if the square is
     * the same output true else false
     * @param s - square object
     * @return - boolean
     */
    public boolean equals(Square s) {

        //returning true or false if objects are equal or not (same attribute values)
        return super.equals(s) && s.length == length && s.width == width;
    }

    /**
     * String representation of the square
     * @return - string representation of the square
     */
    public String toString() {

        //returning string with all shape and square attributes
        return super.toString() + "\nLength: " + length + "\nWidth: " + width;
    }

    /**
     * draw method
     * @param g2d - Graphics2D object
     *
     */
    public void draw(Graphics2D g2d) {

        //drawing square
        g2d.fillRect(xPos,yPos, (int) width, (int) length);
    }

}