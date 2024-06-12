/**
 * Shan Truong, Aryan Verma, Jerry Wu
 * June 11, 2024
 * Shape interface that sets all methods abstract shape should implement
 */
package collisionvisualizer;

//imports
import java.awt.Color;
import java.awt.Graphics2D;

public interface Shape {
    
    //Abstract method to be implemented by subclasses for drawing the shape
    public void draw(Graphics2D g);
    
    //Accessor that gets the x position of the shape
    public double getXPos();
    
    //Accessor that gets the y position of the shape
    public double getYPos();
    
    //Accessor that gets the mass of the shape
    public double getMass();
    
    //Accessor that gets the velocity of the shape
    public double getVelocity();
    
    //Accessor that gets the colour of the shape
    public Color getColour();
    
    //Mutator that sets the x position of the shape
    public void setXPos(double xPos);
    
    
    //Mutator that sets the y position of the shape
    public void setYPos(double yPos);
    
    //Mutator that sets the mass of the shape
    public void setMass(double mass);
    
    
    //Mutator that sets the velocity of the shape
    public void setVelocity(double velocity);
    
    //Mutator that sets the colour of the shape
    public void setColour(Color colour);
    
    //to string method to display all attribtues of class 
    public String toString();
}
