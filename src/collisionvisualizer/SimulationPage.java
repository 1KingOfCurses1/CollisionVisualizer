package collisionvisualizer;

import java.awt.*;
import java.text.DecimalFormat;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 * Modernized Simulation Page.
 */
public class SimulationPage extends javax.swing.JFrame {

    private double Eki1, Eki2;
    private StringBuilder sessionLog = new StringBuilder();
    private MainPage mainWindow;
    private DecimalFormat df = new DecimalFormat("0.00");

    public SimulationPage(MainPage m) {
        this.mainWindow = m;
        initComponentsModern();
        resetObjects();
    }

    private void initComponentsModern() {
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                UIUtils.drawModernBackground(g, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new BorderLayout(15, 15));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setContentPane(mainPanel);

        // --- TOP BAR ---
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setOpaque(false);
        
        JButton backBtn = new JButton("← MENU");
        UIUtils.styleSecondaryButton(backBtn);
        backBtn.addActionListener(e -> {
            mainWindow.setVisible(true);
            this.setVisible(false);
        });
        
        JLabel headerTitle = new JLabel("PHYSICS SIMULATION CORE");
        headerTitle.setFont(UIUtils.FONT_BOLD);
        headerTitle.setForeground(UIUtils.ACCENT_CYAN);
        headerTitle.setHorizontalAlignment(SwingConstants.CENTER);
        
        topBar.add(backBtn, BorderLayout.WEST);
        topBar.add(headerTitle, BorderLayout.CENTER);
        mainPanel.add(topBar, BorderLayout.NORTH);

        // --- CENTER: SIMULATION DISPLAY ---
        drawDisplay = new DrawingSurface();
        drawDisplay.setBorder(new LineBorder(new Color(60, 60, 60), 1));
        mainPanel.add(drawDisplay, BorderLayout.CENTER);

        // --- BOTTOM: CONTROLS ---
        JPanel controls = new JPanel(new GridLayout(1, 3, 20, 0));
        controls.setOpaque(false);
        controls.setPreferredSize(new Dimension(800, 220));

        // Panel 1: Red Object
        JPanel redCtrl = createControlPanel("RED ENTITY [C1]", UIUtils.ACCENT_RED);
        massSlider1 = createSlider(1, 20, 10);
        velocitySlider1 = createSlider(-20, 20, 10);
        txtAreaMass1 = createDataField();
        txtAreaVelocity1 = createDataField();
        
        addControl(redCtrl, "MASS (kg)", massSlider1, txtAreaMass1);
        addControl(redCtrl, "INITIAL VEL (m/s)", velocitySlider1, txtAreaVelocity1);
        
        // Panel 2: Global Params & Actions
        JPanel midCtrl = createControlPanel("SYSTEM PARAMETERS", UIUtils.ACCENT_CYAN);
        elasticitySlider = createSlider(0, 100, 50);
        txtAreaElas = createDataField();
        addControl(midCtrl, "ELASTICITY %", elasticitySlider, txtAreaElas);
        
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnPanel.setOpaque(false);
        btnRun = new JButton("EXECUTE RUN");
        btnReset = new JButton("SYSTEM RESET");
        UIUtils.styleButton(btnRun);
        UIUtils.styleSecondaryButton(btnReset);
        btnRun.addActionListener(e -> runSimulation());
        btnReset.addActionListener(e -> resetObjects());
        btnPanel.add(btnRun);
        btnPanel.add(btnReset);
        midCtrl.add(btnPanel);

        // Panel 3: Blue Object
        JPanel blueCtrl = createControlPanel("BLUE ENTITY [C2]", UIUtils.ACCENT_CYAN);
        massSlider2 = createSlider(1, 20, 10);
        velocitySlider2 = createSlider(-20, 20, -10);
        txtAreaMass2 = createDataField();
        txtAreaVelocity2 = createDataField();
        
        addControl(blueCtrl, "MASS (kg)", massSlider2, txtAreaMass2);
        addControl(blueCtrl, "INITIAL VEL (m/s)", velocitySlider2, txtAreaVelocity2);

        controls.add(redCtrl);
        controls.add(midCtrl);
        controls.add(blueCtrl);
        mainPanel.add(controls, BorderLayout.SOUTH);

        // Add Listeners
        massSlider1.addChangeListener(e -> {
            txtAreaMass1.setText(massSlider1.getValue() + "kg");
            updatePreview();
        });
        massSlider2.addChangeListener(e -> {
            txtAreaMass2.setText(massSlider2.getValue() + "kg");
            updatePreview();
        });
        velocitySlider1.addChangeListener(e -> txtAreaVelocity1.setText(velocitySlider1.getValue() + "m/s"));
        velocitySlider2.addChangeListener(e -> txtAreaVelocity2.setText(velocitySlider2.getValue() + "m/s"));
        elasticitySlider.addChangeListener(e -> txtAreaElas.setText((elasticitySlider.getValue()/100.0) + ""));

        // Sidebar Results
        JPanel results = new JPanel(new GridLayout(6, 1, 5, 5));
        results.setOpaque(false);
        results.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        results.setPreferredSize(new Dimension(120, 0));
        
        txtAreaEki1 = createResultLabel("Eki 1", UIUtils.ACCENT_RED);
        txtAreaEki2 = createResultLabel("Eki 2", UIUtils.ACCENT_CYAN);
        txtAreaVf1 = createResultLabel("Vf 1", UIUtils.ACCENT_RED);
        txtAreaVf2 = createResultLabel("Vf 2", UIUtils.ACCENT_CYAN);
        
        results.add(txtAreaEki1);
        results.add(txtAreaEki2);
        results.add(txtAreaVf1);
        results.add(txtAreaVf2);
        mainPanel.add(results, BorderLayout.EAST);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 750);
        setLocationRelativeTo(null);
    }

    private JPanel createControlPanel(String title, Color accent) {
        JPanel p = new JPanel();
        p.setOpaque(false);
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        TitledBorder b = BorderFactory.createTitledBorder(new LineBorder(new Color(60, 60, 60)), title);
        b.setTitleColor(accent);
        b.setTitleFont(UIUtils.FONT_BOLD);
        p.setBorder(b);
        return p;
    }

    private JSlider createSlider(int min, int max, int val) {
        JSlider s = new JSlider(min, max, val);
        s.setOpaque(false);
        s.setForeground(UIUtils.TEXT_GRAY);
        return s;
    }

    private JLabel createDataField() {
        JLabel l = new JLabel("0");
        l.setForeground(UIUtils.TEXT_WHITE);
        l.setFont(UIUtils.FONT_BOLD);
        return l;
    }

    private JLabel createResultLabel(String prefix, Color col) {
        JLabel l = new JLabel(prefix + ": 0J");
        l.setForeground(col);
        l.setFont(new Font("Inter", Font.BOLD, 12));
        return l;
    }

    private void addControl(JPanel p, String label, JSlider s, JLabel val) {
        JPanel row = new JPanel(new BorderLayout());
        row.setOpaque(false);
        JLabel l = new JLabel(label);
        l.setForeground(UIUtils.TEXT_GRAY);
        l.setFont(new Font("Inter", Font.PLAIN, 10));
        row.add(l, BorderLayout.WEST);
        row.add(val, BorderLayout.EAST);
        p.add(row);
        p.add(s);
        p.add(Box.createRigidArea(new Dimension(0, 10)));
    }

    private void updatePreview() {
        ((DrawingSurface) drawDisplay).preview(massSlider1.getValue(), massSlider2.getValue());
    }

    private void runSimulation() {
        double m1 = massSlider1.getValue();
        double v1 = velocitySlider1.getValue();
        double m2 = massSlider2.getValue();
        double v2 = velocitySlider2.getValue();
        double e = elasticitySlider.getValue();

        Eki1 = 0.5 * m1 * v1 * v1;
        Eki2 = 0.5 * m2 * v2 * v2;

        txtAreaEki1.setText("Eki 1: " + df.format(Eki1) + "J");
        txtAreaEki2.setText("Eki 2: " + df.format(Eki2) + "J");

        ((DrawingSurface) drawDisplay).updateParameters(m1, v1, m2, v2, e);
        sessionLog.append(String.format("Run: m1=%f, v1=%f, m2=%f, v2=%f, e=%f\n", m1, v1, m2, v2, e/100.0));
    }

    private void resetObjects() {
        massSlider1.setValue(10);
        massSlider2.setValue(10);
        velocitySlider1.setValue(10);
        velocitySlider2.setValue(-10);
        elasticitySlider.setValue(50);
        txtAreaMass1.setText("10kg");
        txtAreaMass2.setText("10kg");
        txtAreaVelocity1.setText("10m/s");
        txtAreaVelocity2.setText("-10m/s");
        txtAreaElas.setText("0.5");
        updatePreview();
    }

    // Swing Components
    private JPanel drawDisplay;
    private JSlider massSlider1, massSlider2, velocitySlider1, velocitySlider2, elasticitySlider;
    private JLabel txtAreaMass1, txtAreaMass2, txtAreaVelocity1, txtAreaVelocity2, txtAreaElas;
    private JLabel txtAreaEki1, txtAreaEki2, txtAreaVf1, txtAreaVf2;
    private JButton btnRun, btnReset;
}
