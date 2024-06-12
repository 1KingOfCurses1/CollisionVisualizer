/**
 * Shan Truong, Aryan Verma, Jerry Wu
 * June 11, 2024
 * Credit page that displays credits to group members
 */
package collisionvisualizer;

//imports
import java.awt.Color;

/**
 *credit page class that extands from a j frame
 * @author arver0606
 */
public class CreditsPage extends javax.swing.JFrame {
    
    //main window reference
    MainPage mainWindow;
    
    
    /**
     * Creates new form CreditsPage
     * @param m - window input
     */
    public CreditsPage(MainPage m) {
        
        //displaying GUI
        initComponents();
        
        //changing background colour to cream
        getContentPane().setBackground(new Color(255, 253, 208));
        
        //assigning value to window reference
        mainWindow = m;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        shanRoleLbl = new javax.swing.JLabel();
        jerryNameLbl = new javax.swing.JLabel();
        jerryRoleLbl = new javax.swing.JLabel();
        shanNameLbl = new javax.swing.JLabel();
        aryanRoleLbl = new javax.swing.JLabel();
        aryanNameLbl = new javax.swing.JLabel();
        backBtn = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Heyam", 0, 36)); // NOI18N
        jLabel1.setText("Instructions");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Heyam", 0, 36)); // NOI18N
        jLabel2.setText("Credits");

        shanRoleLbl.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        shanRoleLbl.setText("Project Manager and Lead Programmer");

        jerryNameLbl.setFont(new java.awt.Font("Palace Script MT", 1, 36)); // NOI18N
        jerryNameLbl.setForeground(new java.awt.Color(255, 102, 255));
        jerryNameLbl.setText("Jerry Wu");

        jerryRoleLbl.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jerryRoleLbl.setText("System Analyst and Tester");

        shanNameLbl.setFont(new java.awt.Font("Palace Script MT", 1, 36)); // NOI18N
        shanNameLbl.setForeground(new java.awt.Color(124, 215, 255));
        shanNameLbl.setText("Shan Truong");

        aryanRoleLbl.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        aryanRoleLbl.setText("Senior Software Engineer and Technical Writer ");

        aryanNameLbl.setFont(new java.awt.Font("Palace Script MT", 1, 36)); // NOI18N
        aryanNameLbl.setForeground(new java.awt.Color(102, 0, 255));
        aryanNameLbl.setText("Aryan Verma");

        backBtn.setText("Back");
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(backBtn)
                        .addGap(123, 123, 123)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(147, 147, 147)
                        .addComponent(shanRoleLbl))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(193, 193, 193)
                        .addComponent(jerryRoleLbl))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(214, 214, 214)
                        .addComponent(aryanNameLbl))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(229, 229, 229)
                        .addComponent(jerryNameLbl)))
                .addContainerGap(173, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(217, 217, 217)
                    .addComponent(shanNameLbl)
                    .addContainerGap(235, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(143, Short.MAX_VALUE)
                    .addComponent(aryanRoleLbl)
                    .addGap(130, 130, 130)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backBtn))
                .addGap(37, 37, 37)
                .addComponent(shanRoleLbl)
                .addGap(110, 110, 110)
                .addComponent(aryanNameLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jerryRoleLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jerryNameLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(147, 147, 147)
                    .addComponent(shanNameLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(211, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(206, Short.MAX_VALUE)
                    .addComponent(aryanRoleLbl)
                    .addGap(171, 171, 171)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        
        //display main page
        mainWindow.setVisible(true);
        
        //removing page visibility 
        this.setVisible(false);
    }//GEN-LAST:event_backBtnActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel aryanNameLbl;
    private javax.swing.JLabel aryanRoleLbl;
    private javax.swing.JButton backBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jerryNameLbl;
    private javax.swing.JLabel jerryRoleLbl;
    private javax.swing.JLabel shanNameLbl;
    private javax.swing.JLabel shanRoleLbl;
    // End of variables declaration//GEN-END:variables
}
