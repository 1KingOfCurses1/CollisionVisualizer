package collisionvisualizer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.List;

/**
 * Advanced 2D Physics Engine and Drawing Surface.
 * Handles real-time elastic collisions between bodies and boundary constraints.
 */
public class DrawingSurface extends JPanel implements Runnable {

    // Simulation settings
    private final int FPS = 60;
    private final int DELAY = 1000 / FPS;
    
    // Physics Bodies (Using Circles for professional 2D interaction)
    private List<Circle> bodies = new ArrayList<>();
    private Circle redBody, blueBody;
    
    // Simulation state
    private Thread animator;
    private boolean isRunning = false;
    private double elasticity = 0.5;

    public DrawingSurface() {
        this.setFocusable(true);
        initSimulation();
    }

    private void initSimulation() {
        bodies.clear();
        
        // Initialize professional-grade physics bodies
        redBody = new Circle(10.0); // Mass 10
        redBody.setColour(new Color(255, 71, 87));
        redBody.setRadius(25);
        
        blueBody = new Circle(10.0); // Mass 10
        blueBody.setColour(new Color(30, 144, 255));
        blueBody.setRadius(25);
        
        resetPositions();
        
        bodies.add(redBody);
        bodies.add(blueBody);
    }

    public void preview(double m1, double m2) {
        redBody.setMass(m1);
        redBody.setRadius((int)(m1 * 3) + 15);
        blueBody.setMass(m2);
        blueBody.setRadius((int)(m2 * 3) + 15);
        
        isRunning = false; 
        resetPositions();
        repaint();
    }

    private void resetPositions() {
        int h = getHeight() > 0 ? getHeight() : 200;
        int w = getWidth() > 0 ? getWidth() : 700;
        
        redBody.setXPos(100);
        redBody.setYPos(h / 2 - redBody.getRadius());
        
        blueBody.setXPos(w - 100 - blueBody.getRadius()*2);
        // Ensure non-zero Y offset for 2D interaction
        blueBody.setYPos(h / 2 - blueBody.getRadius() + (Math.random() * 40 - 20)); 
    }

    public void updateParameters(double m1, double v1, double m2, double v2, double e) {
        this.elasticity = e / 100.0;
        
        preview(m1, m2); // Sync sizes and positions

        // Set velocities
        redBody.setVX(v1 / 2.0); 
        redBody.setVY((Math.random() - 0.5) * 3.0); 
        
        blueBody.setVX(v2 / 2.0);
        blueBody.setVY((Math.random() - 0.5) * 3.0);

        isRunning = true;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        render((Graphics2D) g);
    }

    private void render(Graphics2D g2d) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Background
        g2d.setColor(UIUtils.BG_DARK);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        
        drawGrid(g2d);

        for (Circle c : bodies) {
            drawNeonCircle(g2d, c);
        }
    }

    private void drawGrid(Graphics2D g2d) {
        g2d.setStroke(new java.awt.BasicStroke(1));
        g2d.setColor(new Color(255, 255, 255, 10)); // Subtle grid matching UIUtils
        for (int i = 0; i < getWidth(); i += 40) {
            g2d.drawLine(i, 0, i, getHeight());
        }
        for (int j = 0; j < getHeight(); j += 40) {
            g2d.drawLine(0, j, getWidth(), j);
        }
    }

    private void drawNeonCircle(Graphics2D g2d, Circle c) {
        int x = (int) c.getXPos();
        int y = (int) c.getYPos();
        int r = c.getRadius();
        Color col = c.getColour();

        // Glow
        for (int i = 1; i <= 8; i++) {
            g2d.setColor(new Color(col.getRed(), col.getGreen(), col.getBlue(), 40 / i));
            g2d.drawOval(x - i, y - i, r*2 + i*2, r*2 + i*2);
        }

        g2d.setColor(col);
        g2d.fillOval(x, y, r*2, r*2);
        
        // Shine
        g2d.setColor(new Color(255, 255, 255, 80));
        g2d.drawArc(x + 5, y + 5, r*2 - 10, r*2 - 10, 100, 50);
    }

    @Override
    public void addNotify() {
        super.addNotify();
        animator = new Thread(this);
        animator.start();
    }

    @Override
    public void run() {
        while (true) {
            if (isRunning) {
                updatePhysics();
            }
            repaint();
            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException ex) {}
        }
    }

    private void updatePhysics() {
        // 1. Boundary Collisions
        for (Circle c : bodies) {
            c.update();
            
            // Wall bounce X
            if (c.getXPos() <= 0 || c.getXPos() + c.getRadius()*2 >= getWidth()) {
                c.setVX(-c.getVX() * 0.95); // Slight energy loss on wall hit
                c.setXPos(c.getXPos() <= 0 ? 0 : getWidth() - c.getRadius()*2);
            }
            // Wall bounce Y
            if (c.getYPos() <= 0 || c.getYPos() + c.getRadius()*2 >= getHeight()) {
                c.setVY(-c.getVY() * 0.95);
                c.setYPos(c.getYPos() <= 0 ? 0 : getHeight() - c.getRadius()*2);
            }
        }

        // 2. Circle-Circle Collision (2D Elastic)
        checkCollision(redBody, blueBody);
    }

    private void checkCollision(Circle c1, Circle c2) {
        double dx = (c2.getXPos() + c2.getRadius()) - (c1.getXPos() + c1.getRadius());
        double dy = (c2.getYPos() + c2.getRadius()) - (c1.getYPos() + c1.getRadius());
        double distance = Math.sqrt(dx*dx + dy*dy);
        double minDistance = c1.getRadius() + c2.getRadius();

        if (distance < minDistance) {
            // Resolve overlap (static resolution)
            double overlap = minDistance - distance;
            double nx = dx / distance; // Normal X
            double ny = dy / distance; // Normal Y
            
            c1.setXPos(c1.getXPos() - nx * overlap / 2);
            c1.setYPos(c1.getYPos() - ny * overlap / 2);
            c2.setXPos(c2.getXPos() + nx * overlap / 2);
            c2.setYPos(c2.getYPos() + ny * overlap / 2);

            // Resolve velocity (dynamic resolution)
            // Normal velocity components
            double v1n = c1.getVX() * nx + c1.getVY() * ny;
            double v2n = c2.getVX() * nx + c2.getVY() * ny;

            // Elastic collision formula for normal components
            double m1 = c1.getMass();
            double m2 = c2.getMass();
            
            double v1nAfter = (v1n * (m1 - m2) + 2 * m2 * v2n) / (m1 + m2);
            double v2nAfter = (v2n * (m2 - m1) + 2 * m1 * v1n) / (m1 + m2);

            // Apply elasticity (coefficient of restitution)
            v1nAfter *= elasticity;
            v2nAfter *= elasticity;

            // Convert back to X,Y components
            c1.setVX(c1.getVX() + (v1nAfter - v1n) * nx);
            c1.setVY(c1.getVY() + (v1nAfter - v1n) * ny);
            c2.setVX(c2.getVX() + (v2nAfter - v2n) * nx);
            c2.setVY(c2.getVY() + (v2nAfter - v2n) * ny);
        }
    }
}
