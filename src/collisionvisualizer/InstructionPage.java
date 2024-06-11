/**
 * Shan Truong, Aryan Verma, Jerry Wu
 * June 11, 2024
 * Instruction page that displays instructions to user
 */
package collisionvisualizer;

import java.awt.Color;

/**
 * InstructionPage class provides a GUI window to display instructions to the user.
 */
public class InstructionPage extends javax.swing.JFrame {
    
    //reference to main window
    MainPage mainWindow;

    /**
     * Creates new form InstructionPage
     * @param m MainPage instance to return to when 'Back' button is pressed.
     */
    public InstructionPage(MainPage m) {
        
        //displaying GUI
        initComponents();
        
        //changing background colour to cream
        getContentPane().setBackground(new Color(255, 253, 208));
        
        //assigning value to reference
        mainWindow = m;
        
        //putting text in the text area
        txtArea.setText("Instructions could include on how to use the program "
                + "and what each button does or means. Can include pictures of "
                + "the buttons to make it easier for the user to know which "
                + "buttons we are talking about.\n\nEx:\n\nMoving slider for object "
                + "one’s velocity, increases its velocity which increases it’s "
                + "momentum. Making its velocity positive will make it move right "
                + "and negative will make it move left.");
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
        jScrollPane1 = new javax.swing.JScrollPane();
        txtArea = new javax.swing.JTextArea();
        backBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Heyam", 0, 36)); // NOI18N
        jLabel1.setText("Instructions");

        txtArea.setEditable(false);
        txtArea.setColumns(20);
        txtArea.setLineWrap(true);
        txtArea.setRows(5);
        txtArea.setWrapStyleWord(true);
        jScrollPane1.setViewportView(txtArea);

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
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(backBtn)
                        .addGap(160, 160, 160)
                        .addComponent(jLabel1))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 593, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backBtn))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Sets up the text area with instructions.
     */
    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        
        //display main page
        mainWindow.setVisible(true);
        
        //removing menu page visibility 
        this.setVisible(false);
    }//GEN-LAST:event_backBtnActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtArea;
    // End of variables declaration//GEN-END:variables
}
