package collisionvisualizer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class DrawingSurface extends JPanel implements Runnable {

    private Thread animator;
    private final int DELAY = 25;

    private Square redSquare, blueSquare;

    public DrawingSurface() {
        this.setFocusable(true);
        this.requestFocus();
        // Initialize squares with default values and initial positions
        redSquare = new Square(10);  // Default mass
        blueSquare = new Square(10);  // Default mass

        // Set initial positions
        redSquare.setXPos(200);  // Example starting position
        blueSquare.setXPos(400);  // Example starting position
        
         // Set initial positions to separate the squares
        redSquare.setYPos(75);
        blueSquare.setYPos(75);
    }

    public void updateParameters(double m1, double v1, double m2, double v2, double e) {
        // Update squares with new masses and velocities
        redSquare = new Square(m1);
        redSquare.setVelocity((int) v1);  // Update velocity based on input
        blueSquare = new Square(m2);
        blueSquare.setVelocity((int) v2);  // Update velocity based on input

        // Set initial positions to separate the squares
        redSquare.setXPos(200);
        blueSquare.setXPos(400);
        
        
         // Set initial positions to separate the squares
        redSquare.setYPos(75);
        blueSquare.setYPos(75);

        repaint();
    }

    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        // Clear the panel
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Draw the squares at their updated positions
        g2d.setColor(Color.RED);
        redSquare.draw(g2d);
        g2d.setColor(Color.BLUE);
        blueSquare.draw(g2d);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    public void moveObject() {
        redSquare.update();
        blueSquare.update();

        // Add logic to handle collisions or boundaries if needed
    }

    @Override
    public void addNotify() {
        super.addNotify();
        animator = new Thread(this);
        animator.start();
    }

    @Override
    public void run() {
        long beforeTime, timeDiff, sleep;
        beforeTime = System.currentTimeMillis();

        while (true) {
            // Update the squares' positions
            moveObject();
            // Redraw the screen
            repaint();

            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDiff;

            if (sleep < 0) {
                sleep = 2;
            }

            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                JOptionPane.showMessageDialog(this, "Thread interrupted: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }

            beforeTime = System.currentTimeMillis();
        }
    }
}
