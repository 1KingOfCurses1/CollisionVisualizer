package collisionvisualizer;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 * Utility class for modern UI components and styling.
 */
public class UIUtils {

    // Modern Color Palette
    public static final Color BG_DARK = new Color(15, 15, 15);
    public static final Color SURFACE_DARK = new Color(30, 30, 30);
    public static final Color ACCENT_CYAN = new Color(0, 210, 211);
    public static final Color ACCENT_RED = new Color(255, 71, 87);
    public static final Color TEXT_WHITE = new Color(241, 242, 246);
    public static final Color TEXT_GRAY = new Color(164, 176, 190);

    // Fonts
    public static final Font FONT_TITLE = new Font("Inter", Font.BOLD, 32);
    public static final Font FONT_BOLD = new Font("Inter", Font.BOLD, 14);
    public static final Font FONT_NORMAL = new Font("Inter", Font.PLAIN, 14);

    public static void styleButton(JButton btn) {
        btn.setBackground(SURFACE_DARK);
        btn.setForeground(TEXT_WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(new LineBorder(new Color(60, 60, 60), 1));
        btn.setFont(FONT_BOLD);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(200, 45));
        
        // Hover effect
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(45, 45, 45));
                btn.setBorder(new LineBorder(ACCENT_CYAN, 1));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(SURFACE_DARK);
                btn.setBorder(new LineBorder(new Color(60, 60, 60), 1));
            }
        });
    }

    public static void styleSecondaryButton(JButton btn) {
        styleButton(btn);
        btn.setBackground(BG_DARK);
        btn.setFont(FONT_NORMAL);
    }

    public static void styleFrame(JFrame frame) {
        frame.getContentPane().setBackground(BG_DARK);
    }

    public static void drawModernBackground(Graphics g, int width, int height) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Gradient
        GradientPaint gp = new GradientPaint(0, 0, new Color(20, 20, 20), 0, height, BG_DARK);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, width, height);
        
        // Grid
        g2d.setColor(new Color(255, 255, 255, 10));
        for (int i = 0; i < width; i += 40) g2d.drawLine(i, 0, i, height);
        for (int j = 0; j < height; j += 40) g2d.drawLine(0, j, width, j);
    }
}
