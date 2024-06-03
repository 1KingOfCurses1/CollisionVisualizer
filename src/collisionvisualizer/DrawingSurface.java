/*
*
 */
package collisionvisualizer;


import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class DrawingSurface extends JPanel implements Runnable {

    private Thread animator;
    private final int DELAY = 25;
    private Square object1;
    private Square object2;

    public DrawingSurface() { //constructor for the panel
        this.setFocusable(true);
        this.requestFocus();
    }

    //does the actual drawing
    private void doDrawing(Graphics g) {
        //the Graphics2D class is the class that handles all the drawing
        //must be casted from older Graphics class in order to have access to some newer methods
        Graphics2D g2d = (Graphics2D) g;

    }

    //overrides paintComponent in JPanel class
    //performs custom painting
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);//does the necessary work to prepare the panel for drawing
        doDrawing(g);
    }

    //update the position of the ball and make it bounce
    //(we could do more complex game updates here)
    public void moveObject() {
        
    }

    //this method is called after the JPanel is added to the JFrame
    //we can perform start up tasks here
    @Override
    public void addNotify() {
        super.addNotify();
        animator = new Thread(this);
        animator.start();
    }

    //this method is called only once, when the Thread starts
    @Override
    public void run() {

        long beforeTime, timeDiff, sleep;
        //get the current time
        beforeTime = System.currentTimeMillis();

        while (true) { //this loop runs once ever 25 ms (the DELAY)

            //update the balls position
            moveObject();
            //redraws the screen (calling the paint component method)
            repaint();

            //calculate how much time has passed since the last call
            //this allows smooth updates and our ball will move at a constant speed (as opposed to being dependent on processor availability)
            timeDiff = System.currentTimeMillis() - beforeTime;

            //calculate how much time to wait before the next call
            sleep = DELAY - timeDiff;

            //always wait at least 2 ms
            if (sleep < 0) {
                sleep = 2;
            }

            //try to actually wait
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                //threads can be interrupted from other threads
                JOptionPane.showMessageDialog(this, "Thread interrupted: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            //get the new current time
            beforeTime = System.currentTimeMillis();
        }
    }

}
