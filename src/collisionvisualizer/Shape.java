/*
Shan Truong
May 29, 2024
Interface for all Shapes
*/
package collisionvisualizer;

public interface Shape {
    
    public int getXPos();
    
    public int getYPos();
    
    public double getMass();
    
    public double getVelocity();
    
    public void setXPos(int xPos);
    
    public void setYPos(int yPos);
    
    public void setMass(double mass);
    
    public void setVelocity(double velocity);
    
    public String toString();
}
