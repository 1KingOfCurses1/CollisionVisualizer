/*
 * 
 * 
 */
package collisionvisualizer;

public class Circle extends AbstractShape {

    double radius;

    public Circle() {
        radius = 0;
    }

    public Circle(double radius) {
        this();
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
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



}
