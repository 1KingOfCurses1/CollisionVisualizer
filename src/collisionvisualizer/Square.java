/*
Shan Truong
May 29, 2024
Square class with all square functions 
 */
package collisionvisualizer;

import java.awt.Color;
import java.awt.Graphics2D;

public class Square extends AbstractShape{
    
    private int length;
    
    private int width;
    
    private Color colour;
    
    public Square(){
        
        length = 0;
        
        width = 0;
        
        colour = new Color(0,0,0);
    }
    
    public Square(int length, int width, Color colour){
        
        this();
        
        this.length = length;
        
        this.width = width;
        
        this.colour = colour;
    }
    
    public double getLength(){
        return length;
    }
    
    public double getWidth(){
        return width;
    }
    
    public void setLength(int length){
        this.length = length;
    }
    
    public void setWidth(int width){
        this.width = width;
    }
    
    public Color getColour() {
        return colour;
    }

    public void setColour(Color colour) {
        this.colour = colour;
    }
    
    public Square clone(){
        
        Square s = new Square(length, width, colour);
        
        return s;
    }

    public boolean equals(Square s){
        return super.equals(s) && s.length == length && s.width == width;
    }

    public String toString(){
        
        return super.toString() + "\nLength: " + length + "\nWidth: " + width;
    }
    
    public void draw(Graphics2D g) {
        
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.setPaint(colour);
        
        g2d.fillRect(xPos,yPos,length,width);
        
        
    }

}
