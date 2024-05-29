/*
Shan Truong
May 29, 2024
Square class with all square functions 
 */
package collisionvisualizer;

public class Square extends AbstractShape{
    
    private double length;
    
    private double width;
    
    public Square(){
        
        length = 0;
        
        width = 0;
    }
    
    public Square(double length, double width){
        
        this();
        
        this.length = length;
        
        this.width = width;
    }
    
    public double getLength(){
        return length;
    }
    
    public double getWidth(){
        return width;
    }
    
    public void setLength(double length){
        this.length = length;
    }
    
    public void setWidth(double width){
        this.width = width;
    }
    
    public Square clone(){
        
        Square s = new Square(length, width);
        
        return s;
    }

    public boolean equals(Square s){
        return super.equals(s) && s.length == length && s.width == width;
    }

    public String toString(){
        
        return super.toString() + "\nLength: " + length + "\nWidth: " + width;
    }

}
