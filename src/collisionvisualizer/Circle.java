/*
 * 
 * 
 */
package collisionvisualizer;

import java.awt.Graphics2D;

public class Circle extends AbstractShape {

    int radius;

    public Circle() {
        radius = 0;
    }

    public Circle(int radius) {
        this();
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public Circle clone() {
        Circle c = new Circle(radius);
        return c;
    }

    public boolean equals(Circle c){
        return super.equals(c) && c.radius == radius;
    }

    public String toString(){
        return super.toString() + "\nRadius: " + radius;
    }

    
    public void draw(Graphics2D g) {
        g.fillOval(xPos, yPos, radius, radius);
    }



}
