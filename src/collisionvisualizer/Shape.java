/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package collisionvisualizer;

/**
 *
 * @author shtru6553
 */
public interface Shape {
    
    
    public int getXPos();
    
    public int getYPos();
    
    public double getMass();
    
    public double getVelocity();
    
    public void setXPos(int xPos);
    
    public void setYPos(int yPos);
    
    public void setMass(double mass);
    
    public void setVelocity(double velocity);
    
    public Shape clone();
    
    public boolean equals(Shape s);
    
    public String toString();
}
