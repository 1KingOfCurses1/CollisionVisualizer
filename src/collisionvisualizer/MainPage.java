package collisionvisualizer;

import java.awt.*;
import javax.swing.*;

/**
 * The main page of the Collision Visualizer application.
 */
public class MainPage extends javax.swing.JFrame {

    private InstructionPage instructionsWindow;
    private SimulationPage simulationWindow;
    private DefinitionPage definitionWindow;
    private CreditsPage creditWindow;

    public MainPage() {
        initComponents();
        setupModernUI();
    }

    private void setupModernUI() {
        // Replace the main panel with a custom styled one
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                UIUtils.drawModernBackground(g, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new GridBagLayout());
        setContentPane(mainPanel);

        // UI Components Container
        JPanel container = new JPanel();
        container.setOpaque(false);
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        // Title
        JLabel title = new JLabel("COLLISION LAB");
        title.setFont(new Font("Inter", Font.BOLD, 48));
        title.setForeground(UIUtils.ACCENT_CYAN);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel subtitle = new JLabel("PHYSICS VISUALIZATION SUITE");
        subtitle.setFont(new Font("Inter", Font.PLAIN, 14));
        subtitle.setForeground(UIUtils.TEXT_GRAY);
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        container.add(title);
        container.add(Box.createRigidArea(new Dimension(0, 5)));
        container.add(subtitle);
        container.add(Box.createRigidArea(new Dimension(0, 50)));

        // Buttons
        JButton[] buttons = {simulationBtn, instructionBtn, defBtn, creditBtn, exitBtn};
        for (JButton btn : buttons) {
            UIUtils.styleButton(btn);
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            container.add(btn);
            container.add(Box.createRigidArea(new Dimension(0, 15)));
        }
        
        // Special style for Exit
        exitBtn.setBorder(BorderFactory.createLineBorder(UIUtils.ACCENT_RED, 1));
        
        mainPanel.add(container);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Collision Lab v2.0");
        setSize(800, 600);
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        simulationBtn = new JButton("START SIMULATION");
        instructionBtn = new JButton("INSTRUCTIONS");
        defBtn = new JButton("PHYSICS DICTIONARY");
        creditBtn = new JButton("PROJECT CREDITS");
        exitBtn = new JButton("EXIT SYSTEM");

        simulationBtn.addActionListener(e -> {
            if (simulationWindow == null) simulationWindow = new SimulationPage(this);
            simulationWindow.setVisible(true);
            this.setVisible(false);
        });

        instructionBtn.addActionListener(e -> {
            if (instructionsWindow == null) instructionsWindow = new InstructionPage(this);
            instructionsWindow.setVisible(true);
            this.setVisible(false);
        });

        defBtn.addActionListener(e -> {
            if (definitionWindow == null) definitionWindow = new DefinitionPage(this);
            definitionWindow.setVisible(true);
            this.setVisible(false);
        });

        creditBtn.addActionListener(e -> {
            if (creditWindow == null) creditWindow = new CreditsPage(this);
            creditWindow.setVisible(true);
            this.setVisible(false);
        });

        exitBtn.addActionListener(e -> System.exit(0));
    }

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        java.awt.EventQueue.invokeLater(() -> new MainPage().setVisible(true));
    }

    private JButton creditBtn;
    private JButton defBtn;
    private JButton exitBtn;
    private JButton instructionBtn;
    private JButton simulationBtn;
}
