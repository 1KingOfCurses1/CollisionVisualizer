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
    
    public int getXPos();
    
    public int getYPos();
    
    public double getMass();
    
    public double getVelocity();
    
    public Color getColour();
    
    public void setXPos(int xPos);
    
    public void setYPos(int yPos);
    
    public void setMass(double mass);
    
    public void setVelocity(double velocity);
    
    public void setColour(Color colour);
    
    public String toString();
}
