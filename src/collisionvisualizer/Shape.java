package collisionvisualizer;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * The Shape interface defines the contract for all physical objects in the simulation.
 */
public interface Shape {
    /** Draws the shape on the provided graphics context. */
    void draw(Graphics2D g);
    
    /** Physics update loop for position based on velocity. */
    void update();
    
    // Getters and Setters
    double getXPos();
    double getYPos();
    double getMass();
    double getVX();
    double getVY();
    Color getColour();
    
    void setXPos(double xPos);
    void setYPos(double yPos);
    void setMass(double mass);
    void setVX(double vx);
    void setVY(double vy);
    void setColour(Color colour);
}
