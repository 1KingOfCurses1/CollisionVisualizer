/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package collisionvisualizer;

import java.io.InputStream;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author skrs1
 */
public class DefinitionPage extends javax.swing.JFrame {

    MainPage mainWindow;

    public static Term sortedList[];

    /**
     * Creates new form DefinitionForm
     */
    public DefinitionPage(MainPage m) {
        initComponents();
        mainWindow = m;

        String name = "";
        String definition = "";
        Term list[] = new Term[15];
        try {
            InputStream in = DefinitionPage.class.getResourceAsStream("definitions.txt");
            Scanner s = new Scanner(in);

            for (int i = 0; i < list.length; i++) {
                name = s.nextLine();
                definition = s.nextLine();

                list[i] = new Term(name, definition);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: File not found.");
        }

        sortedList = quickSort(list, 0, list.length - 1);
        String displayTerms = "";
        for (int i = 0; i < sortedList.length; i++) {
            displayTerms += sortedList[i].getName() + "\n";
        }

        definitionTxtArea.setText(displayTerms);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backBtn = new javax.swing.JButton();
        searchTxtField = new javax.swing.JTextField();
        listLbl = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        definitionTxtArea = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        searchBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        backBtn.setText("Back");
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });

        listLbl.setText("List of terms:");

        definitionTxtArea.setEditable(false);
        definitionTxtArea.setColumns(20);
        definitionTxtArea.setRows(5);
        jScrollPane1.setViewportView(definitionTxtArea);

        jLabel1.setFont(new java.awt.Font("Heyam", 0, 36)); // NOI18N
        jLabel1.setText("Definitions:");

        searchBtn.setText("Search");
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 39, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(backBtn)
                        .addGap(127, 127, 127)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(listLbl))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(searchTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(searchBtn)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(backBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchBtn))
                .addGap(33, 33, 33)
                .addComponent(listLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        mainWindow.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_backBtnActionPerformed

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        String target = searchTxtField.getText();
        
        int location = linearSearch(sortedList, target);
        
        

    }//GEN-LAST:event_searchBtnActionPerformed

    public int linearSearch(Term[] sortedTerms, String target) {
        // Examine each element in the array
        for (int i = 0; i < sortedTerms.length; i++) {
            // Is this the one we're looking for?
            if (sortedTerms[i].getName().equals(target)) {
                // Return the index where the target is found
                return i;
            }
            // Have we gone past where the target should be?
            if (sortedTerms[i].getName().compareTo(target) > 0) {
                // Since the array is sorted, if the current term name is greater than the target,
                // the target is not in the array
                return -1;
            }
        }
        // The entire array has been searched without finding the target
        return -1;
    }

    /**
     * Method sorts unsorted array in ascending order using quick sort. The
     * method is recursive.
     *
     * @param unsorted - unsorted array
     * @param start - starting index
     * @param end - ending index
     * @return - sorted list
     */
    private static Term[] quickSort(Term[] unsorted, int start, int end) {
        // If the start index is greater than or equal to the end index, the array is already sorted or contains only one element
        if (start >= end) {
            // Return the sorted array
            return unsorted;
        }

        // The variable 'i' is set to start
        int i = start;
        // The variable 'j' is set to end
        int j = end;
        // Pivot value is set to the middle index of the array
        Term pivotValue = unsorted[(start + end) / 2];

        // While loop will run as long as 'j' is greater than 'i'
        while (i <= j) {
            // Move the 'i' index to the right until we find an element greater than or equal to the pivot
            while (unsorted[i].getName().compareTo(pivotValue.getName()) < 0) {
                // Increase 'i' (shift to right)
                i++;
            }
            // Move the 'j' index to the left until we find an element less than or equal to the pivot
            while (unsorted[j].getName().compareTo(pivotValue.getName()) > 0) {
                // Decrease 'j' (shift to left)
                j--;
            }
            // If 'i' is less than or equal to 'j'
            if (i <= j) {
                // Swap the elements at 'i' and 'j'
                Term temp = unsorted[i];
                unsorted[i] = unsorted[j];
                unsorted[j] = temp;
                // Increase 'i'
                i++;
                // Decrease 'j'
                j--;
            }
        }

        // Recursive call on the left partition
        if (start < j) {
            quickSort(unsorted, start, j);
        }

        // Recursive call on the right partition
        if (i < end) {
            quickSort(unsorted, i, end);
        }

        // Return the sorted array
        return unsorted;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backBtn;
    private javax.swing.JTextArea definitionTxtArea;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel listLbl;
    private javax.swing.JButton searchBtn;
    private javax.swing.JTextField searchTxtField;
    // End of variables declaration//GEN-END:variables
}
