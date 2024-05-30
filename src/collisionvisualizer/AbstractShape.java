/*
 *
 *
 */
package collisionvisualizer;

import java.awt.Graphics2D;

abstract public class AbstractShape implements Shape {

    int xPos;
    int yPos;
    double mass;
    double velocity;

    public AbstractShape() {
        xPos = 0;
        yPos = 0;
        mass = 0;
        velocity = 0;
    }

    public AbstractShape(int xPos, int yPos, double mass, double velocity) {
        this();
        this.xPos = xPos;
        this.yPos = yPos;
        this.mass = mass;
        this.velocity = velocity;
    }

    abstract public void draw(Graphics2D g);
    
    public int getXPos() {
        return xPos;
    }

    public int getYPos() {
        return yPos;
    }

    public void setXPos(int xPos) {
        this.xPos = xPos;
    }

    public void setYPos(int yPos) {
        this.yPos = yPos;
    }

    public double getMass() {
        return mass;
    }

    public double getVelocity() {
        return velocity;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public boolean equals(AbstractShape s) {
        return s.xPos == xPos && s.yPos == yPos && s.mass == mass && s.velocity == velocity;
    }

    public String toString() {
        return "X-position: " + xPos + "\nY-position: " + yPos + "\nMass: " + mass + "\nVelocity: " + velocity;
    }

}
