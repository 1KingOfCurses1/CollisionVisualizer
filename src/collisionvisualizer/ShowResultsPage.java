package collisionvisualizer;

import java.awt.*;
import javax.swing.*;

/**
 * Modernized Show Results Page.
 */
public class ShowResultsPage extends javax.swing.JFrame {

    private DefinitionPage mainWindow;
    private JLabel titleLbl;
    private JTextArea definitionTxtArea;

    public ShowResultsPage(DefinitionPage d, String name, String definition) {
        this.mainWindow = d;
        initComponentsModern();
        titleLbl.setText(name.toUpperCase());
        definitionTxtArea.setText(definition);
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
        
        titleLbl = new JLabel("");
        titleLbl.setFont(UIUtils.FONT_TITLE);
        titleLbl.setForeground(UIUtils.ACCENT_CYAN);
        header.add(titleLbl, BorderLayout.CENTER);
        titleLbl.setHorizontalAlignment(SwingConstants.CENTER);

        JButton backBtn = new JButton("← BACK TO INDEX");
        UIUtils.styleSecondaryButton(backBtn);
        backBtn.addActionListener(e -> {
            mainWindow.setVisible(true);
            this.setVisible(false);
        });
        header.add(backBtn, BorderLayout.WEST);
        mainPanel.add(header, BorderLayout.NORTH);

        // Content
        definitionTxtArea = new JTextArea();
        definitionTxtArea.setEditable(false);
        definitionTxtArea.setLineWrap(true);
        definitionTxtArea.setWrapStyleWord(true);
        definitionTxtArea.setBackground(UIUtils.SURFACE_DARK);
        definitionTxtArea.setForeground(UIUtils.TEXT_WHITE);
        definitionTxtArea.setFont(UIUtils.FONT_NORMAL);
        definitionTxtArea.setMargin(new Insets(20, 20, 20, 20));
        definitionTxtArea.setBorder(BorderFactory.createLineBorder(new Color(60, 60, 60)));

        JScrollPane scrollPane = new JScrollPane(definitionTxtArea);
        scrollPane.setBorder(null);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);
    }
}
