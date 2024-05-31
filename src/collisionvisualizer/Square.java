/*
Shan Truong
May 29, 2024
Square class with all square functions 
 */
package collisionvisualizer;

import java.awt.Graphics2D;

public class Square extends AbstractShape{
    /**
     * Encapsulation of attributes
     */
    private int length;
    
    private int width;

    /**
     * Primary Constructor that initialize the attributes of a square
     */
    public Square(){
        
        length = 0;
        
        width = 0;

    }

    /**
     * Secondary constructor that chains the primary constructor.
     * The secondary constructor takes in values and assign them to the attributes of a square
     * @param length - length of the square
     * @param width - width of the square
     */
    
    public Square(int length, int width, Color colour){
        
        this();
        
        this.length = length;
        
        this.width = width;
        

    }

    /**
     * Accessor that gets the length of a square
     * @return - the length of a square
     */
    public double getLength(){
        return length;
    }

    /**
     * Accessor that gets the width of the square
     * @return - the width of the square
     */
    public double getWidth(){
        return width;
    }

    /**
     * mutator that sets the length of the square
     * @param length - length of the square
     */
    public void setLength(int length){
        this.length = length;
    }

    /**
     * mutator that sets the width of the square
     * @param width - width of the square
     */
    public void setWidth(int width){
        this.width = width;
    }

    /**
     * a clone method to create an exact copy of the square
     * @return - a copy of the square
     */
    public Square clone(){
        
        Square s = new Square(length, width, colour);
        
        return s;
    }

    /**
     * equals method that check if the two square are the same, if the square is the same output true else false
     * @param s - square object
     * @return - boolean
     */
    public boolean equals(Square s){
        return super.equals(s) && s.length == length && s.width == width;
    }

    /**
     * String representation of the square
     * @return - string representation of the square
     */
    public String toString(){
        
        return super.toString() + "\nLength: " + length + "\nWidth: " + width;
    }

    /**
     * draw method
     * @param g2d - Graphics2D object
     *
     */
    public void draw(Graphics2D g2d) {

        g2d.fillRect(xPos,yPos,length,width);
        
        
    }

}