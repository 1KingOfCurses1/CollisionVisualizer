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
        // Initialize squares with default values
        redSquare = new Square(10);  // Default mass, will be updated
        blueSquare = new Square(10);  // Default mass, will be updated
    }
    public void updateParameters(double m1, double v1, double m2, double v2, double e) {
        redSquare.setVelocity(10);
        // Update square sizes based on masses
        redSquare = new Square(m1);
        System.out.println(redSquare.getVelocity());
        blueSquare = new Square(m2);

        repaint();
    }

    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        // Clear the panel
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Set positions for squares
        redSquare.setXPos(getWidth() / 4 - (int)redSquare.getLength() / 2);
        redSquare.setYPos(getHeight() / 2 - (int)redSquare.getWidth() / 2);
        blueSquare.setXPos(3 * getWidth() / 4 - (int)blueSquare.getLength() / 2);
        blueSquare.setYPos(getHeight() / 2 - (int)blueSquare.getWidth() / 2);

        // Draw the squares
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
            //update the balls position
            moveObject();
            //redraws the screen (calling the paint component method)
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
