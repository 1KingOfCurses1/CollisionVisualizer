/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package collisionvisualizer;

import javax.swing.JFrame;

/**
 *
 * @author arver0606
 */
public class SimulationPage extends javax.swing.JFrame {

    MainPage mainWindow;

    /**
     * Creates new form SimulationPage
     *
     * @param m
     */
    public SimulationPage(MainPage m) {
        initComponents();
        mainWindow = m;

        txtAreaMass1.setText("10 kg");
        txtAreaMass2.setText("10 kg");
        txtAreaVelocity1.setText("10m/s");
        txtAreaVelocity2.setText("10m/s");
        txtAreaElas.setText("0.5");
    }

    public static void collision(double m1, double m2, double vi1, double vi2, double e, double vf1, double vf2) {

        vf1 = (((m1 - (e * m2)) / (m1 + m2)) * vi1) + ((((1 + e) * m2) / (m1 + m2)) * vi2);

        vf2 = (((m2 - (e * m1)) / (m1 + m2)) * vi2) + ((((1 + e) * m1) / (m1 + m2)) * vi1);
        
         System.out.println("v1: " + vf1 + " v2: " + vf2);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnBack = new javax.swing.JButton();
        btnRun = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        title = new javax.swing.JLabel();
        lblMass1 = new javax.swing.JLabel();
        lblVelocity1 = new javax.swing.JLabel();
        lblMass2 = new javax.swing.JLabel();
        lblVelocity2 = new javax.swing.JLabel();
        massSlider1 = new javax.swing.JSlider();
        massSlider2 = new javax.swing.JSlider();
        velocitySlider1 = new javax.swing.JSlider();
        velocitySlider2 = new javax.swing.JSlider();
        elasticitySlider = new javax.swing.JSlider();
        lblDisplayElas = new javax.swing.JLabel();
        drawDisplay = new DrawingSurface();
        lblEK1 = new javax.swing.JLabel();
        lblEK2 = new javax.swing.JLabel();
        lblVf1 = new javax.swing.JLabel();
        lblVf2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaMass1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAreaVelocity1 = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtAreaVelocity2 = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtAreaMass2 = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtAreaElas = new javax.swing.JTextArea();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtAreaEk1 = new javax.swing.JTextArea();
        jScrollPane7 = new javax.swing.JScrollPane();
        txtAreaEk2 = new javax.swing.JTextArea();
        jScrollPane8 = new javax.swing.JScrollPane();
        txtAreaVf2 = new javax.swing.JTextArea();
        jScrollPane9 = new javax.swing.JScrollPane();
        txtAreaVf1 = new javax.swing.JTextArea();

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

        title.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        title.setText("Amount of Elasticity: 0 to 1");

        lblMass1.setForeground(new java.awt.Color(255, 0, 0));
        lblMass1.setText("Enter Mass 1:");

        lblVelocity1.setForeground(new java.awt.Color(255, 0, 0));
        lblVelocity1.setText("Enter Velocity 1:");

        lblMass2.setForeground(new java.awt.Color(0, 0, 255));
        lblMass2.setText("Enter Mass 2:");

        lblVelocity2.setForeground(new java.awt.Color(0, 51, 255));
        lblVelocity2.setText("Enter Velocity 2:");

        massSlider1.setMaximum(10);
        massSlider1.setMinimum(1);
        massSlider1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                massSlider1MouseDragged(evt);
            }
        });

        massSlider2.setMaximum(10);
        massSlider2.setMinimum(1);
        massSlider2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                massSlider2MouseDragged(evt);
            }
        });

        velocitySlider1.setMaximum(10);
        velocitySlider1.setMinimum(-10);
        velocitySlider1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                velocitySlider1MouseDragged(evt);
            }
        });

        velocitySlider2.setMaximum(10);
        velocitySlider2.setMinimum(-10);
        velocitySlider2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                velocitySlider2MouseDragged(evt);
            }
        });

        elasticitySlider.setSnapToTicks(true);
        elasticitySlider.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                elasticitySliderMouseDragged(evt);
            }
        });

        drawDisplay.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout drawDisplayLayout = new javax.swing.GroupLayout(drawDisplay);
        drawDisplay.setLayout(drawDisplayLayout);
        drawDisplayLayout.setHorizontalGroup(
            drawDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 689, Short.MAX_VALUE)
        );
        drawDisplayLayout.setVerticalGroup(
            drawDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 293, Short.MAX_VALUE)
        );

        lblEK1.setForeground(new java.awt.Color(255, 0, 0));
        lblEK1.setText("EK1");

        lblEK2.setForeground(new java.awt.Color(0, 0, 255));
        lblEK2.setText("EK2");

        lblVf1.setForeground(new java.awt.Color(255, 0, 0));
        lblVf1.setText("VF1");

        lblVf2.setForeground(new java.awt.Color(0, 0, 255));
        lblVf2.setText("VF2");

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        txtAreaMass1.setEditable(false);
        txtAreaMass1.setColumns(20);
        txtAreaMass1.setRows(5);
        jScrollPane1.setViewportView(txtAreaMass1);

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        txtAreaVelocity1.setEditable(false);
        txtAreaVelocity1.setColumns(20);
        txtAreaVelocity1.setRows(5);
        jScrollPane2.setViewportView(txtAreaVelocity1);

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        txtAreaVelocity2.setEditable(false);
        txtAreaVelocity2.setColumns(20);
        txtAreaVelocity2.setRows(5);
        jScrollPane3.setViewportView(txtAreaVelocity2);

        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane4.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        txtAreaMass2.setEditable(false);
        txtAreaMass2.setColumns(20);
        txtAreaMass2.setRows(5);
        jScrollPane4.setViewportView(txtAreaMass2);

        jScrollPane5.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane5.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        txtAreaElas.setEditable(false);
        txtAreaElas.setColumns(20);
        txtAreaElas.setRows(5);
        jScrollPane5.setViewportView(txtAreaElas);

        jScrollPane6.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane6.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        txtAreaEk1.setEditable(false);
        txtAreaEk1.setColumns(20);
        txtAreaEk1.setRows(5);
        jScrollPane6.setViewportView(txtAreaEk1);

        jScrollPane7.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane7.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        txtAreaEk2.setEditable(false);
        txtAreaEk2.setColumns(20);
        txtAreaEk2.setRows(5);
        jScrollPane7.setViewportView(txtAreaEk2);

        jScrollPane8.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane8.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        txtAreaVf2.setEditable(false);
        txtAreaVf2.setColumns(20);
        txtAreaVf2.setRows(5);
        jScrollPane8.setViewportView(txtAreaVf2);

        jScrollPane9.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane9.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        txtAreaVf1.setEditable(false);
        txtAreaVf1.setColumns(20);
        txtAreaVf1.setRows(5);
        jScrollPane9.setViewportView(txtAreaVf1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(431, 431, 431)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblVf2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblEK2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE))
                .addGap(393, 393, 393)
                .addComponent(lblDisplayElas))
            .addGroup(layout.createSequentialGroup()
                .addGap(219, 219, 219)
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(elasticitySlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(drawDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                    .addGap(57, 57, 57)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnBack)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblMass1)
                                .addComponent(lblVelocity1))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(velocitySlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(massSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(32, 32, 32)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lblMass2)
                                    .addGap(33, 33, 33)
                                    .addComponent(massSlider2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lblVelocity2)
                                    .addGap(18, 18, 18)
                                    .addComponent(velocitySlider2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnRun)
                                .addComponent(btnReset))
                            .addGap(108, 108, 108)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblEK1, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                                .addComponent(lblVf1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(141, 141, 141)
                                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(141, 141, 141)
                                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(btnBack)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(elasticitySlider, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(61, 61, 61)
                .addComponent(lblDisplayElas)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnRun, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblEK1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblEK2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(lblVf1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jScrollPane9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(lblVf2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(drawDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnReset)
                                .addGap(322, 322, 322)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(lblMass1)
                                            .addComponent(lblMass2)
                                            .addComponent(massSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(massSlider2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(33, 33, 33))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(32, 32, 32)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblVelocity1)
                                        .addComponent(velocitySlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblVelocity2)
                                        .addComponent(velocitySlider2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(27, 27, 27))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        resetObjects();
        mainWindow.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnRunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRunActionPerformed
        displayObjects();

        double vf1 = 0;
        
        double vf2 = 0;

        collision(massSlider1.getValue(), massSlider2.getValue(), 
                velocitySlider1.getValue(), velocitySlider2.getValue(), 
                elasticitySlider.getValue(), vf1, vf2);
        
        
    }//GEN-LAST:event_btnRunActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        resetObjects();
    }//GEN-LAST:event_btnResetActionPerformed

    private void massSlider1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_massSlider1MouseDragged
        txtAreaMass1.setText("" + (massSlider1.getValue()) + " kg");
    }//GEN-LAST:event_massSlider1MouseDragged

    private void massSlider2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_massSlider2MouseDragged
        txtAreaMass2.setText("" + (massSlider2.getValue()) + " kg");
    }//GEN-LAST:event_massSlider2MouseDragged

    private void velocitySlider1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_velocitySlider1MouseDragged
        txtAreaVelocity1.setText("" + (velocitySlider1.getValue()) + "m/s");
    }//GEN-LAST:event_velocitySlider1MouseDragged

    private void velocitySlider2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_velocitySlider2MouseDragged
        txtAreaVelocity2.setText("" + (velocitySlider2.getValue()) + "m/s");
    }//GEN-LAST:event_velocitySlider2MouseDragged

    private void elasticitySliderMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_elasticitySliderMouseDragged
        txtAreaElas.setText("" + ((elasticitySlider.getValue() / 100.0)));
    }//GEN-LAST:event_elasticitySliderMouseDragged

    private void displayObjects() {
        int mass1 = massSlider1.getValue();
        int mass2 = massSlider2.getValue();
        int velocity1 = velocitySlider1.getValue();
        int velocity2 = velocitySlider2.getValue();
        double elasticity = elasticitySlider.getValue() / 100.0;

        ((DrawingSurface) drawDisplay).updateParameters(mass1, velocity1, mass2, velocity2, elasticity);
    }

    private void resetObjects() {

        massSlider1.setValue(massSlider1.getMaximum());
        massSlider2.setValue(massSlider2.getMaximum());
        velocitySlider1.setValue(velocitySlider1.getMaximum());
        velocitySlider2.setValue(velocitySlider2.getMaximum());
        elasticitySlider.setValue(50);

        txtAreaMass1.setText("10 kg");
        txtAreaMass2.setText("10 kg");
        txtAreaVelocity1.setText("10m/s");
        txtAreaVelocity2.setText("10m/s");
        txtAreaElas.setText("0.5");

        ((DrawingSurface) drawDisplay).updateParameters(massSlider1.getMaximum(), velocitySlider1.getMaximum(), massSlider2.getMaximum(), velocitySlider2.getMaximum(), 0.5);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnRun;
    private javax.swing.JPanel drawDisplay;
    private javax.swing.JSlider elasticitySlider;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel lblDisplayElas;
    private javax.swing.JLabel lblEK1;
    private javax.swing.JLabel lblEK2;
    private javax.swing.JLabel lblMass1;
    private javax.swing.JLabel lblMass2;
    private javax.swing.JLabel lblVelocity1;
    private javax.swing.JLabel lblVelocity2;
    private javax.swing.JLabel lblVf1;
    private javax.swing.JLabel lblVf2;
    private javax.swing.JSlider massSlider1;
    private javax.swing.JSlider massSlider2;
    private javax.swing.JLabel title;
    private javax.swing.JTextArea txtAreaEk1;
    private javax.swing.JTextArea txtAreaEk2;
    private javax.swing.JTextArea txtAreaElas;
    private javax.swing.JTextArea txtAreaMass1;
    private javax.swing.JTextArea txtAreaMass2;
    private javax.swing.JTextArea txtAreaVelocity1;
    private javax.swing.JTextArea txtAreaVelocity2;
    private javax.swing.JTextArea txtAreaVf1;
    private javax.swing.JTextArea txtAreaVf2;
    private javax.swing.JSlider velocitySlider1;
    private javax.swing.JSlider velocitySlider2;
    // End of variables declaration//GEN-END:variables
}
