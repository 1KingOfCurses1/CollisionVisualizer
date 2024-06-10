/*
Shan Truong
May 29, 2024
Interface for all Shapes
*/
package collisionvisualizer;

import java.awt.Color;
import java.awt.Graphics2D;

public interface Shape {
    
    public void draw(Graphics2D g);
    
    public double getXPos();
    
    public double getYPos();
    
    public double getMass();
    
    public double getVelocity();
    
    public Color getColour();
    
    public void setXPos(double xPos);
    
    public void setYPos(double yPos);
    
    public void setMass(double mass);
    
    public void setVelocity(double velocity);
    
    public void setColour(Color colour);
    
    public String toString();
}
