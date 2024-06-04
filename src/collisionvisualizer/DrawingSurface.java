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

    public DrawingSurface() {
        this.setFocusable(true);
        this.requestFocus();
    }

    public void updateParameters(double m1, double v1, double m2, double v2, double e) {
        this.mass1 = m1;
        this.mass2 = m2;
        this.velocity1 = v1;
        this.velocity2 = v2;
        this.elasticity = e;
        repaint();
    }

    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        
        // Clear the panel
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Example: Draw two circles representing the particles
        g2d.setColor(Color.RED);
        int radius1 = (int) (mass1 * 10); // Example scale factor for visualization
        int radius2 = (int) (mass2 * 10);

        int x1 = getWidth() / 4 - radius1 / 2;
        int y1 = getHeight() / 2 - radius1 / 2;

        int x2 = 3 * getWidth() / 4 - radius2 / 2;
        int y2 = getHeight() / 2 - radius2 / 2;

        g2d.fillOval(x1, y1, radius1, radius1);
        g2d.setColor(Color.BLUE);
        g2d.fillOval(x2, y2, radius2, radius2);

        // Draw velocities as arrows
        g2d.setColor(Color.BLACK);
        g2d.drawLine(x1 + radius1 / 2, y1 + radius1 / 2, x1 + radius1 / 2 + (int)(velocity1 * 10), y1 + radius1 / 2);
        g2d.drawLine(x2 + radius2 / 2, y2 + radius2 / 2, x2 + radius2 / 2 + (int)(velocity2 * 10), y2 + radius2 / 2);
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
