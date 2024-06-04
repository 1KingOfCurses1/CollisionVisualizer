package collisionvisualizer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class DrawingSurface extends JPanel implements Runnable {

    private Thread animator;
    private final int DELAY = 25;

    private double mass1, mass2, velocity1, velocity2, elasticity;
    private Square redSquare, blueSquare;

    public DrawingSurface() {
        this.setFocusable(true);
        this.requestFocus();
        // Initialize squares with default values
        redSquare = new Square(1);  // Default mass, will be updated
        blueSquare = new Square(1);  // Default mass, will be updated
    }
    public void updateParameters(double m1, double v1, double m2, double v2, double e) {
        this.mass1 = m1;
        this.mass2 = m2;
        this.velocity1 = v1;
        this.velocity2 = v2;
        this.elasticity = e;

        // Update square sizes based on masses
        redSquare = new Square(mass1);
        blueSquare = new Square(mass2);

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

        // Draw velocities as arrows
        g2d.setColor(Color.BLACK);
        g2d.drawLine(redSquare.getXPos() + (int)redSquare.getLength() / 2, redSquare.getYPos() + (int)redSquare.getWidth() / 2,
                redSquare.getXPos() + (int)redSquare.getLength() / 2 + (int) (velocity1 * 10), redSquare.getYPos() + (int)redSquare.getWidth() / 2);
        g2d.drawLine(blueSquare.getXPos() + (int)blueSquare.getLength() / 2, blueSquare.getYPos() + (int)blueSquare.getWidth() / 2,
                blueSquare.getXPos() + (int)blueSquare.getLength() / 2 + (int) (velocity2 * 10), blueSquare.getYPos() + (int)blueSquare.getWidth() / 2);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    public void moveObject() {
        // Implement your object movement logic here
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
            moveObject();
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
