/*
 * 
 * 
 */
package collisionvisualizer;

import java.awt.Color;
import java.awt.Graphics2D;

public class Circle extends AbstractShape {

    private int radius;

    private Color colour;
    
    public Circle() {
        radius = 0;
        
        colour = new Color(0,0,0);
    }

    public Circle(int radius, Color colour) {
        this();
        this.radius = radius;
        
        this.colour = colour;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public Color getColour(){
        return colour;
    }
    
    public void setColour(Color colour){
        this.colour = colour;
    }
    
    public Circle clone() {
        Circle c = new Circle(radius, colour);
        return c;
    }

    public boolean equals(Circle c){
        return super.equals(c) && c.radius == radius;
    }

    public String toString(){
        return super.toString() + "\nRadius: " + radius;
    }

    public void draw(Graphics2D g) {
        
        Graphics2D g2d = (Graphics2D) g;

        g2d.setPaint(colour);
        
        g.fillOval(xPos, yPos, radius, radius);
    }



}
