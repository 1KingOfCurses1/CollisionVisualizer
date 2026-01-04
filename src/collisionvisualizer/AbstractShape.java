package collisionvisualizer;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Abstract shape class that holds all attributes and functions of a shape.
 */
abstract public class AbstractShape implements Shape {
    
    // Position properties
    protected double xPos;
    protected double yPos;

    // Physics properties
    protected double mass;
    protected double vx;
    protected double vy;

    // Aesthetic properties
    protected Color colour;

    public AbstractShape() {
        xPos = 0;          
        yPos = 0;        
        mass = 1.0;     
        vx = 0.0;
        vy = 0.0;    
        colour = Color.BLACK; 
    }

    public AbstractShape(int xPos, int yPos, double mass, double vx, double vy, Color colour) {
        this.xPos = xPos;     
        this.yPos = yPos;    
        this.mass = mass;   
        this.vx = vx; 
        this.vy = vy;
        this.colour = colour;     
    }

    @Override
    abstract public void draw(Graphics2D g2d);

    @Override
    public double getXPos() { return xPos; }
    
    @Override
    public void setXPos(double xPos) { this.xPos = xPos; }
    
    @Override
    public double getYPos() { return yPos; }
    
    @Override
    public void setYPos(double yPos) { this.yPos = yPos; }

    @Override
    public double getMass() { return mass; }
    
    @Override
    public void setMass(double mass) { this.mass = mass; }
    
    @Override
    public double getVX() { return vx; }
    
    @Override
    public void setVX(double vx) { this.vx = vx; }

    @Override
    public double getVY() { return vy; }
    
    @Override
    public void setVY(double vy) { this.vy = vy; }
    
    @Override
    public Color getColour() { return colour; }
    
    @Override
    public void setColour(Color colour) { this.colour = colour; }
    
    @Override
    public void update() {
        xPos += vx;
        yPos += vy;
    }
    
    public boolean equals(AbstractShape s) {
        return xPos == s.xPos && 
               yPos == s.yPos && 
               mass == s.mass && 
               vx == s.vx && 
               vy == s.vy &&
               colour.equals(s.colour);
    }
    
    @Override
    public String toString() {
        return String.format("Pos: (%.2f, %.2f) | Mass: %.2f | Vel: (%.2f, %.2f) | Color: %s", 
            xPos, yPos, mass, vx, vy, colour.toString());
    }
}
