package collisionvisualizer;

import javax.swing.JFrame;

/**
 *
 * @author arver0606
 */
public class SimulationPage extends javax.swing.JFrame {

    MainPage mainWindow;
    private DrawingSurface drawDisplay; // Change type to DrawingSurface

    /**
     * Creates new form SimulationPage
     *
     * @param m
     */
    public SimulationPage(MainPage m) {
        initComponents();
        mainWindow = m;
    }

    public static void collision(double m1, double m2, double vi1, double vi2, double e) {
        double vf1;
        double vf2;
        vf1 = (((m1 - (e * m2)) / (m1 + m2)) * vi1) + ((((1 + e) * m2) / (m1 + m2)) * vi2);
        vf2 = (((m2 - (e * m1)) / (m1 + m2)) * vi2) + ((((1 + e) * m1) / (m1 + m2)) * vi1);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        btnBack = new javax.swing.JButton();
        btnRun = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lblMass1 = new javax.swing.JLabel();
        lblVelocity1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblVelocity2 = new javax.swing.JLabel();
        massSlider1 = new javax.swing.JSlider();
        massSlider2 = new javax.swing.JSlider();
        velocitySlider1 = new javax.swing.JSlider();
        velocitySlider2 = new javax.swing.JSlider();
        elasticitySlider = new javax.swing.JSlider();
        javax.swing.JLabel lblMassDisplay1 = new javax.swing.JLabel();
        lblMassDisplay2 = new javax.swing.JLabel();
        lblVelocityDisplay1 = new javax.swing.JLabel();
        lblVelocityDisplay2 = new javax.swing.JLabel();
        lblDisplayElas = new javax.swing.JLabel();
        drawDisplay = new DrawingSurface(); // Initialize as DrawingSurface
        javax.swing.JLabel lblMassDisplay3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btnRun.setText("Run");
        btnRun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRunActionPerformed(evt);
            }
        });

        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Amount of Elasticity: 0 to 1");

        lblMass1.setText("Enter Mass 1:");

        lblVelocity1.setText("Enter Velocity 1:");

        jLabel4.setText("Enter Mass 2:");

        lblVelocity2.setText("Enter Velocity 2:");

        massSlider1.setMaximum(10);
        massSlider1.setMinimum(1);

        massSlider2.setMaximum(10);
        massSlider2.setMinimum(1);

        velocitySlider1.setMaximum(10);
        velocitySlider1.setMinimum(-10);

        velocitySlider2.setMaximum(10);
        velocitySlider2.setMinimum(-10);

        elasticitySlider.setSnapToTicks(true);

        drawDisplay.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout drawDisplayLayout = new javax.swing.GroupLayout(drawDisplay);
        drawDisplay.setLayout(drawDisplayLayout);
        drawDisplayLayout.setHorizontalGroup(
                drawDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
        drawDisplayLayout.setVerticalGroup(
                drawDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 281, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(57, 57, 57)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(lblMass1)
                                                                .addGap(15, 15, 15))
                                                        .addComponent(lblVelocity1)
                                                        .addComponent(btnRun, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(btnReset, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(btnBack, javax.swing.GroupLayout.Alignment.LEADING))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(massSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(velocitySlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(jLabel4)
                                                                                .addGap(33, 33, 33)
                                                                                .addComponent(massSlider2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(lblVelocity2)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(velocitySlider2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                        .addComponent(drawDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(141, 141, 141)
                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(elasticitySlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lblDisplayElas)))
                                .addContainerGap(60, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(204, 204, 204)
                                                .addComponent(lblVelocityDisplay1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(lblVelocityDisplay2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(137, 137, 137)
                                                .addComponent(lblMassDisplay1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(lblMassDisplay2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(107, 107, 107))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(147, 147, 147)
                                        .addComponent(lblMassDisplay3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(537, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(btnBack)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblDisplayElas)
                                        .addComponent(elasticitySlider, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(lblVelocityDisplay1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(31, 31, 31)
                                                .addComponent(lblMassDisplay1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(lblVelocityDisplay2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(31, 31, 31)
                                                .addComponent(lblMassDisplay2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblMass1)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel4)
                                                .addComponent(massSlider2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(massSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(23, 23, 23)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblVelocity1)
                                        .addComponent(lblVelocity2)
                                        .addComponent(velocitySlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(velocitySlider2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(drawDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnRun)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnReset)
                                .addContainerGap(35, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(293, 293, 293)
                                        .addComponent(lblMassDisplay3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(367, Short.MAX_VALUE)))
        );

        pack();
    }

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
        mainWindow.setVisible(true);
    }

    private void btnRunActionPerformed(java.awt.event.ActionEvent evt) {
        int mass1 = massSlider1.getValue();
        int mass2 = massSlider2.getValue();
        int velocity1 = velocitySlider1.getValue();
        int velocity2 = velocitySlider2.getValue();
        double elasticity = elasticitySlider.getValue() / 100.0;

        drawDisplay.updateParameters(mass1, velocity1, mass2, velocity2, elasticity);
    }

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {
        massSlider1.setValue(1);
        massSlider2.setValue(1);
        velocitySlider1.setValue(0);
        velocitySlider2.setValue(0);
        elasticitySlider.setValue(100);

        drawDisplay.updateParameters(1, 0, 1, 0, 1.0);
    }

    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnRun;
    private javax.swing.JSlider elasticitySlider;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lblDisplayElas;
    private javax.swing.JLabel lblMass1;
    private javax.swing.JLabel lblMassDisplay2;
    private javax.swing.JLabel lblVelocity1;
    private javax.swing.JLabel lblVelocity2;
    private javax.swing.JLabel lblVelocityDisplay1;
    private javax.swing.JLabel lblVelocityDisplay2;
    private javax.swing.JSlider massSlider1;
    private javax.swing.JSlider massSlider2;
    private javax.swing.JSlider velocitySlider1;
    private javax.swing.JSlider velocitySlider2;
    // End of variables declaration
}
