package collisionvisualizer;

import java.awt.*;
import javax.swing.*;

/**
 * Modernized Credits Page.
 */
public class CreditsPage extends javax.swing.JFrame {
    
    private MainPage mainWindow;

    public CreditsPage(MainPage m) {
        this.mainWindow = m;
        initComponentsModern();
    }

    private void initComponentsModern() {
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                UIUtils.drawModernBackground(g, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new GridBagLayout());
        setContentPane(mainPanel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Header
        JLabel title = new JLabel("LAB PERSONNEL");
        title.setFont(UIUtils.FONT_TITLE);
        title.setForeground(UIUtils.ACCENT_CYAN);
        gbc.gridy = 0;
        mainPanel.add(title, gbc);

        // Cards for members
        addMemberCard(mainPanel, "SHAN TRUONG", "Project Lead // Senior Developer", gbc, 1);
        addMemberCard(mainPanel, "ARYAN VERMA", "Physics Architect // Technical Lead", gbc, 2);
        addMemberCard(mainPanel, "JERRY WU", "System Analyst // QA Operations", gbc, 3);

        // Back button
        JButton backBtn = new JButton("RETURN TO TERMINAL");
        UIUtils.styleButton(backBtn);
        backBtn.addActionListener(e -> {
            mainWindow.setVisible(true);
            this.setVisible(false);
        });
        gbc.gridy = 4;
        gbc.insets = new Insets(40, 10, 10, 10);
        mainPanel.add(backBtn, gbc);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
    }

    private void addMemberCard(JPanel p, String name, String role, GridBagConstraints gbc, int y) {
        JPanel card = new JPanel();
        card.setOpaque(false);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        
        JLabel nameLbl = new JLabel(name);
        nameLbl.setFont(new Font("Inter", Font.BOLD, 24));
        nameLbl.setForeground(UIUtils.TEXT_WHITE);
        nameLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel roleLbl = new JLabel(role);
        roleLbl.setFont(UIUtils.FONT_NORMAL);
        roleLbl.setForeground(UIUtils.TEXT_GRAY);
        roleLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        card.add(nameLbl);
        card.add(Box.createRigidArea(new Dimension(0, 5)));
        card.add(roleLbl);
        
        gbc.gridy = y;
        p.add(card, gbc);
    }
}
