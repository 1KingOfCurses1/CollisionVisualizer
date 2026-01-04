package collisionvisualizer;

import java.awt.*;
import javax.swing.*;

/**
 * Modernized Instruction Page.
 */
public class InstructionPage extends javax.swing.JFrame {
    
    private MainPage mainWindow;
    private JTextArea txtArea;
    private JLabel titleLbl;
    private JButton backBtn;

    public InstructionPage(MainPage m) {
        this.mainWindow = m;
        initComponentsModern();
        setupStyles();
    }

    private void initComponentsModern() {
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                UIUtils.drawModernBackground(g, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new BorderLayout(20, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 40, 40));
        setContentPane(mainPanel);

        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        
        titleLbl = new JLabel("OPERATING MANUAL");
        titleLbl.setFont(UIUtils.FONT_TITLE);
        titleLbl.setForeground(UIUtils.ACCENT_CYAN);
        
        backBtn = new JButton("← BACK");
        UIUtils.styleSecondaryButton(backBtn);
        backBtn.setPreferredSize(new Dimension(100, 35));
        backBtn.addActionListener(e -> {
            mainWindow.setVisible(true);
            this.setVisible(false);
        });

        header.add(backBtn, BorderLayout.WEST);
        header.add(titleLbl, BorderLayout.CENTER);
        titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
        
        // Content
        txtArea = new JTextArea();
        txtArea.setEditable(false);
        txtArea.setLineWrap(true);
        txtArea.setWrapStyleWord(true);
        txtArea.setBackground(UIUtils.SURFACE_DARK);
        txtArea.setForeground(UIUtils.TEXT_WHITE);
        txtArea.setFont(new Font("Consolas", Font.PLAIN, 15));
        txtArea.setMargin(new Insets(20, 20, 20, 20));
        txtArea.setBorder(BorderFactory.createLineBorder(new Color(60, 60, 60)));

        JScrollPane scrollPane = new JScrollPane(txtArea);
        scrollPane.setBorder(null);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        mainPanel.add(header, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        txtArea.setText("SYSTEM ACCESS: SIMULATION MODULE\n" +
                "----------------------------------\n" +
                "> VELOCITY SLIDERS: Adjust the initial X-momentum of bodies.\n" +
                "> MASS SLIDERS: Define physical mass. Larger mass increases collision dominance.\n" +
                "> COEFFICIENT OF RESTITUTION (ELASTICITY): \n" +
                "  - 1.00: Perfect Elastic Collision (No energy loss)\n" +
                "  - 0.50: Partial Elastic Collision (Physics typical)\n" +
                "  - 0.00: Inelastic Collision (Objects stick/absorb impact)\n\n" +
                "SYSTEM ACCESS: DICTIONARY MODULE\n" +
                "----------------------------------\n" +
                "> Use the search interface to query specific physics terminology.\n" +
                "> Data is sorted and indexed for high-performance retrieval.");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
    }

    private void setupStyles() {
        // Additional styling if needed
    }
}
