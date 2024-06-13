/**
 * Shan Truong, Aryan Verma, Jerry Wu
 * June 11, 2024
 * Simulation page that displays collision to user and all collision functions
 * IE selection for masses, velocities, and type of collision (perfectly elastic, partially elastic or completely inelastic) and viewing collision
 */
package collisionvisualizer;

//imports
import java.awt.Color;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * SimulationPage class provides a GUI window for running and visualizing
 * collision simulations. It includes controls for setting the masses and
 * velocities of two objects and for running the simulation.
 */
public class SimulationPage extends javax.swing.JFrame {

    //Final velocity of the first object
    private double vf1;

    //Final velocity of the second object
    private double vf2;

    //Initial kinetic energy of the first object
    private double Eki1;

    //Initial kinetic energy of the second object
    private double Eki2;

    //Final kinetic energy of the first object
    private double Ekf1;

    //Final kinetic energy of the second object
    private double Ekf2;

    //Array List used for logging data
    private ArrayList<String> logs = new ArrayList<>();

    //Reference to the main window
    MainPage mainWindow;

    //Counter used to count the number of runs
    private int run;

    /**
     * Creates new form SimulationPage
     *
     * @param m - MainPage instance to return to when 'Back' button is pressed.
     */
    public SimulationPage(MainPage m) {

        //Display GUI
        initComponents();

        //changing background colour to cream
        getContentPane().setBackground(new Color(255, 253, 208));

        //assigning value to reference
        mainWindow = m;

        //setting run counter to default value of zero 
        run = 0;

        //Initialize first object initial energy value text area with default value
        txtAreaEki1.setText("0J");

        //Initialize second object initial energy value text area with default value
        txtAreaEki2.setText("0J");

        //Initialize first object final energy value text area with default value
        txtAreaEkf1.setText("0J");

        //Initialize second object final energy value text area with default value
        txtAreaEkf2.setText("0J");

        //Initialize first object final velocity text area with default value
        txtAreaVf1.setText("0m/s");

        //Initialize second object final velocity text area with default value
        txtAreaVf2.setText("0m/s");

        //Initialize first object mass text area with default value
        txtAreaMass1.setText("10 kg");

        //Initialize second object mass text area with default value
        txtAreaMass2.setText("10 kg");

        //Initialize first object initial velocity text area with default value
        txtAreaVelocity1.setText("10m/s");

        //Initialize second object initial velocity text area with default value
        txtAreaVelocity2.setText("10m/s");

        //Initialize type of collision between objects text area with default value
        txtAreaElas.setText("0.5");
    }

    /**
     * Writes the current run log data to a text file and asks user where to
     * save the file.
     */
    public void writeCurrentLogToFile() {

// Create a file chooser instance
        JFileChooser fileChooser = new JFileChooser();

        // Set the title of the file chooser dialog
        fileChooser.setDialogTitle("Specify a file to save");

        // Set a file filter to only show text files
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));

        // Show the save dialog and store the user's selection
        int userSelection = fileChooser.showSaveDialog(null);

        // Check if the user approved the file selection
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            // Get the file chosen by the user
            File fileToSave = fileChooser.getSelectedFile();

            // Ensure the file has a .txt extension
            if (!fileToSave.getAbsolutePath().endsWith(".txt")) {
                // Append .txt extension if not present
                fileToSave = new File(fileToSave.getAbsolutePath() + ".txt");
            }

            // Try-with-resources to auto-close FileWriter
            try (FileWriter writer = new FileWriter(fileToSave, true)) {
                // Loop through the logs and write each one to the file
                for (int i = 0; i < logs.size(); i++) {
                    // Write the current log entry followed by a newline
                    writer.write(logs.get(i) + "\n");
                }
            } catch (IOException e) {
                // Handle any IO exceptions that occur
                // Display error message to the user
                JOptionPane.showMessageDialog(null,"An error occurred while writing to the log file: " + e.getMessage());
            }
        }
    }

    /**
     * Calculates the final velocities of two objects after a collision based on
     * their masses, initial velocities, and the coefficient of restitution.
     *
     * @param m1 - Mass of the first object
     * @param m2 - Mass of the second object
     * @param vi1 - Initial velocity of the first object
     * @param vi2 - Initial velocity of the second object
     * @param e - Coefficient of restitution
     */
    public void collision(double m1, double m2, double vi1, double vi2, double e) {

        //Calculating final velocity of mass 1
        vf1 = ((m1 * vi1) + (m2 * vi2) + (m2 * (e / 100) * (vi2 - vi1))) / (m1 + m2);

        //Calculating final velocity of mass 2
        vf2 = ((m1 * vi1) + (m2 * vi2) + (m1 * (e / 100) * (vi1 - vi2))) / (m1 + m2);

        //Calculating initial kinetic energy of mass 1
        Eki1 = 0.5 * m1 * (Math.pow(vi1, 2));

        //Calculating initial kinetic energy of mass 2
        Eki2 = 0.5 * m2 * (Math.pow(vi2, 2));

        //Calculating final kinetic energy of mass 1
        Ekf1 = 0.5 * m1 * (Math.pow(vf1, 2));

        //Calculating final kinetic energy of mass 2
        Ekf2 = 0.5 * m2 * (Math.pow(vf2, 2));
    }

    /**
     * Accessor that gets the final velocity of shape 1
     *
     * @return - final velocity of shape 1
     */
    public double getVf1() {

        //return final velocity of shape 1
        return vf1;
    }

    /**
     * Accessor that gets the final velocity of shape 2
     *
     * @return - final velocity of shape 2
     */
    public double getVf2() {

        //return final velocity of shape 2
        return vf2;
    }

    /**
     * Accessor that gets the initial energy of shape 1
     *
     * @return - inital energy value of shape 1
     */
    public double getEKi1() {

        //return inital energy value of shape 1
        return Eki1;
    }

    /**
     * Accessor that gets the initial energy of shape 2
     *
     * @return - inital energy value of shape 2
     */
    public double getEki2() {

        //return inital energy value of shape 2
        return Eki2;
    }

    /**
     * Accessor that gets the final energy of shape 1
     *
     * @return - final energy value of shape 1
     */
    public double getEkf1() {

        //return final energy value of shape 1
        return Ekf1;
    }

    /**
     * Accessor that gets the final energy of shape 2
     *
     * @return - final energy value of shape 2
     */
    public double getEkf2() {

        //return final energy value of shape 2
        return Ekf2;
    }

    /**
     * Mutator that sets the final velocity of shape 1
     *
     * @param vf1 - inputed value of the final velocity of shape 1
     */
    public void setVf1(double vf1) {

        //setting final velocity of shape 1 to inputed value 
        this.vf1 = vf1;
    }

    /**
     * Mutator that sets the final velocity of shape 2
     *
     * @param vf2 - inputed value of the final velocity of shape 2
     */
    public void setVf2(double vf2) {

        //setting final velocity of shape 2 to inputed value 
        this.vf1 = vf2;
    }

    /**
     * Mutator that sets the initial energy of shape 1
     *
     * @param Eki1 - inputed value for initial energy of shape 1
     */
    public void setEki1(double Eki1) {

        //setting initial energy of shape 1 to inputed value
        this.Eki1 = Eki1;
    }

    /**
     * Mutator that sets the initial energy of shape 2
     *
     * @param Eki2 - inputed value for initial energy of shape 2
     */
    public void setEki2(double Eki2) {

        //setting initial energy of shape 1 to inputed value
        this.Eki2 = Eki2;
    }

    /**
     * Mutator that sets the final energy of shape 1
     *
     * @param Ekf1 - inputed value for final energy of shape 1
     */
    public void setEkf1(double Ekf1) {

        //setting final energy of shape 1 to inputed value
        this.Ekf1 = Ekf1;
    }

    /**
     * Mutator that sets the final energy of shape 2
     *
     * @param Ekf2 - inputed value for final energy of shape 2
     */
    public void setEkf2(double Ekf2) {

        //setting final energy of shape 1 to inputed value
        this.Eki2 = Ekf2;
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
        lblEki1 = new javax.swing.JLabel();
        lblEki2 = new javax.swing.JLabel();
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
        txtAreaEki1 = new javax.swing.JTextArea();
        jScrollPane7 = new javax.swing.JScrollPane();
        txtAreaEki2 = new javax.swing.JTextArea();
        jScrollPane8 = new javax.swing.JScrollPane();
        txtAreaVf2 = new javax.swing.JTextArea();
        jScrollPane9 = new javax.swing.JScrollPane();
        txtAreaVf1 = new javax.swing.JTextArea();
        jScrollPane10 = new javax.swing.JScrollPane();
        txtAreaEkf2 = new javax.swing.JTextArea();
        lblEkf3 = new javax.swing.JLabel();
        lblEkf4 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        txtAreaEkf1 = new javax.swing.JTextArea();

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
            .addGap(0, 700, Short.MAX_VALUE)
        );
        drawDisplayLayout.setVerticalGroup(
            drawDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 199, Short.MAX_VALUE)
        );

        lblEki1.setForeground(new java.awt.Color(255, 0, 0));
        lblEki1.setText("EKI1");

        lblEki2.setForeground(new java.awt.Color(0, 0, 255));
        lblEki2.setText("EKI2");

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

        txtAreaEki1.setEditable(false);
        txtAreaEki1.setColumns(20);
        txtAreaEki1.setRows(5);
        jScrollPane6.setViewportView(txtAreaEki1);

        jScrollPane7.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane7.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        txtAreaEki2.setEditable(false);
        txtAreaEki2.setColumns(20);
        txtAreaEki2.setRows(5);
        jScrollPane7.setViewportView(txtAreaEki2);

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

        jScrollPane10.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane10.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        txtAreaEkf2.setEditable(false);
        txtAreaEkf2.setColumns(20);
        txtAreaEkf2.setRows(5);
        jScrollPane10.setViewportView(txtAreaEkf2);

        lblEkf3.setForeground(new java.awt.Color(255, 0, 0));
        lblEkf3.setText("EKF1");

        lblEkf4.setForeground(new java.awt.Color(0, 0, 255));
        lblEkf4.setText("EKF2");

        jScrollPane11.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane11.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        txtAreaEkf1.setEditable(false);
        txtAreaEkf1.setColumns(20);
        txtAreaEkf1.setRows(5);
        jScrollPane11.setViewportView(txtAreaEkf1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBack)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnRun)
                            .addComponent(btnReset))
                        .addGap(108, 108, 108)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblEki1, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                            .addComponent(lblVf1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblEkf3, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(199, 199, 199)
                                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblEkf4, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblEki2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(89, 89, 89)
                                        .addComponent(lblVf2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
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
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblDisplayElas))
            .addGroup(layout.createSequentialGroup()
                .addGap(219, 219, 219)
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(elasticitySlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(drawDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEki1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblEki2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(22, 22, 22)
                .addComponent(lblDisplayElas)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnRun)
                                    .addComponent(lblEkf3))
                                .addGap(24, 24, 24))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblEkf4))
                                .addGap(51, 51, 51)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(lblVf2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblVf1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(drawDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnReset)
                                .addGap(251, 251, 251)))
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
                        .addGap(138, 138, 138))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Handles the action of the back button, returning to the main window.
     *
     * @param evt the action event
     */
    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed

        //invoking resetObjects method 
        resetObjects();
        // Write the current log entry to the file
        writeCurrentLogToFile();
        run = 0;
        //display main page
        mainWindow.setVisible(true);

        //removing page visibility 
        this.setVisible(false);
    }//GEN-LAST:event_btnBackActionPerformed
    /**
     * Handles the action of the run button, updating the display and running
     * the collision simulation.
     *
     * @param evt the action event
     */
    private void btnRunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRunActionPerformed

        //invoking displayObjects method
        displayObjects();

        //creating decimal format for numbers
        DecimalFormat num = new DecimalFormat("###,###.##");

        //invoking collision method and passing user selected values 
        collision(massSlider1.getValue(), massSlider2.getValue(),
                velocitySlider1.getValue(), velocitySlider2.getValue(),
                elasticitySlider.getValue());

        //invoking drawCollision method
        ((DrawingSurface) drawDisplay).drawCollision(vf1, vf2, elasticitySlider.getValue());

        //Updating initial energy of object 1 text area with calculated value
        txtAreaEki1.setText(num.format(Eki1) + "J");

        //Updating initial energy of object 2 text area with calculated value
        txtAreaEki2.setText(num.format(Eki2) + "J");

        //Updating final energy of object 1 text area with calculated value
        txtAreaEkf1.setText(num.format(Ekf1) + "J");

        //Updating final energy of object 2 text area with calculated value
        txtAreaEkf2.setText(num.format(Ekf2) + "J");

        //Updating final velocity of object 1 text area with calculated value
        txtAreaVf1.setText(num.format(vf1) + "m/s");

        //Updating final velocity of object 2 text area with calculated value
        txtAreaVf2.setText(num.format(vf2) + "m/s");

        //adding to run counter
        run++;

        // Create the log entry for the current run
        String logEntry = "Run: " + run + "\nCoefficient Of Restitution: " + elasticitySlider.getValue()
                + "\nRed Initial Velocity: " + velocitySlider1.getValue() + "m/s Blue Initial Velocity: "
                + velocitySlider2.getValue() + "m/s\nRed Final Velocity: " + vf1 + "m/s Blue Final Velocity: "
                + vf2 + "m/s\nRed Mass: " + massSlider1.getValue() + "kg Blue Mass: " + massSlider2.getValue() + "kg";

        //adding log entry 
        logs.add(logEntry);
    }//GEN-LAST:event_btnRunActionPerformed

    /**
     * Handles the action of the reset button, resetting the objects to their
     * initial states.
     *
     * @param evt the action event
     */
    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed

        //invoking resetObject method 
        resetObjects();
    }//GEN-LAST:event_btnResetActionPerformed
    /**
     * Handles the action of dragging the mass slider for the first object,
     * updating its mass value.
     *
     * @param evt the mouse event
     */
    private void massSlider1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_massSlider1MouseDragged

        //setting mass 1 text area to mass 1 slider value
        txtAreaMass1.setText("" + (massSlider1.getValue()) + " kg");

        //updating parameter values
        ((DrawingSurface) drawDisplay).updateParameters(massSlider1.getValue(), 0,
                massSlider2.getValue(), 0, elasticitySlider.getValue());
    }//GEN-LAST:event_massSlider1MouseDragged
    /**
     * Handles the action of dragging the mass slider for the second object,
     * updating its mass value.
     *
     * @param evt the mouse event
     */
    private void massSlider2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_massSlider2MouseDragged

        //setting mass 2 text area to mass 2 slider value
        txtAreaMass2.setText("" + (massSlider2.getValue()) + " kg");

        //updating parameter values
        ((DrawingSurface) drawDisplay).updateParameters(massSlider1.getValue(), 0,
                massSlider2.getValue(), 0, elasticitySlider.getValue());
    }//GEN-LAST:event_massSlider2MouseDragged
    /**
     * Handles the action of dragging the velocity slider for the first object,
     * updating its velocity value.
     *
     * @param evt the mouse event
     */
    private void velocitySlider1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_velocitySlider1MouseDragged

        //setting velocity 1 text area to velocity 1 slider value
        txtAreaVelocity1.setText("" + (velocitySlider1.getValue()) + "m/s");

    }//GEN-LAST:event_velocitySlider1MouseDragged

    /**
     * Handles the action of dragging the velocity slider for the second object,
     * updating its velocity value.
     *
     * @param evt the mouse event
     */
    private void velocitySlider2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_velocitySlider2MouseDragged

        //setting velocity 2 text area to velocity 2 slider value
        txtAreaVelocity2.setText("" + (velocitySlider2.getValue()) + "m/s");

    }//GEN-LAST:event_velocitySlider2MouseDragged

    /**
     * Handles the action of dragging the elasticity slider, updating its value.
     *
     * @param evt the mouse event
     */
    private void elasticitySliderMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_elasticitySliderMouseDragged

        //setting elasticity text area to elasticity slider value
        txtAreaElas.setText("" + ((elasticitySlider.getValue() / 100.0)));

        //updating parameter values
        ((DrawingSurface) drawDisplay).updateParameters(massSlider1.getValue(),
                0, massSlider2.getValue(), 0, elasticitySlider.getValue());
    }//GEN-LAST:event_elasticitySliderMouseDragged
    /**
     * Updates the display objects with the current parameters from the sliders.
     */
    private void displayObjects() {

        //updating parameter values
        ((DrawingSurface) drawDisplay).updateParameters(massSlider1.getValue(),
                velocitySlider1.getValue(), massSlider2.getValue(), velocitySlider2.getValue(),
                (elasticitySlider.getValue() / 100.0));
    }

    /**
     * Resets the objects to their initial states.
     */
    private void resetObjects() {

        //reseting mass 1 slider to max value (default value) 
        massSlider1.setValue(massSlider1.getMaximum());

        //reseting mass 2 slider to max value (default value) 
        massSlider2.setValue(massSlider2.getMaximum());

        //reseting velocity 1 slider to max value (default value) 
        velocitySlider1.setValue(velocitySlider1.getMaximum());

        //reseting velocity 2 slider to max value (default value) 
        velocitySlider2.setValue(velocitySlider2.getMaximum());

        //reseting elasticity slider to 0.5 (default value) 
        elasticitySlider.setValue(50);

        //reseting mass 1 text area to max value (default value) 
        txtAreaMass1.setText("10 kg");

        //reseting mass 2 text area to max value (default value)
        txtAreaMass2.setText("10 kg");

        //reseting velocity 1 text area to max value (default value)
        txtAreaVelocity1.setText("10m/s");

        //reseting velocity 2 text area to max value (default value)
        txtAreaVelocity2.setText("10m/s");

        //reseting elasticity text area to 0.5 (default value) 
        txtAreaElas.setText("0.5");

        //reseting initial energy of object 1 text area to 0 (default value) 
        txtAreaEki1.setText("0J");

        //reseting initial energy of object 2 text area to 0 (default value) 
        txtAreaEki2.setText("0J");

        //reseting final energy of object 1 text area to 0 (default value) 
        txtAreaEkf1.setText("0J");

        //reseting final energy of object 2 text area to 0 (default value) 
        txtAreaEkf2.setText("0J");

        //reseting final velocity of object 1 text area to 0 (default value) 
        txtAreaVf1.setText("0m/s");

        //reseting final velocity of object 2 text area to 0 (default value) 
        txtAreaVf2.setText("0m/s");

        //updating parameter values
        ((DrawingSurface) drawDisplay).updateParameters(massSlider1.getMaximum(), 0, massSlider2.getMaximum(), 0, 0.5);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnRun;
    private javax.swing.JPanel drawDisplay;
    private javax.swing.JSlider elasticitySlider;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel lblDisplayElas;
    private javax.swing.JLabel lblEkf3;
    private javax.swing.JLabel lblEkf4;
    private javax.swing.JLabel lblEki1;
    private javax.swing.JLabel lblEki2;
    private javax.swing.JLabel lblMass1;
    private javax.swing.JLabel lblMass2;
    private javax.swing.JLabel lblVelocity1;
    private javax.swing.JLabel lblVelocity2;
    private javax.swing.JLabel lblVf1;
    private javax.swing.JLabel lblVf2;
    private javax.swing.JSlider massSlider1;
    private javax.swing.JSlider massSlider2;
    private javax.swing.JLabel title;
    private javax.swing.JTextArea txtAreaEkf1;
    private javax.swing.JTextArea txtAreaEkf2;
    private javax.swing.JTextArea txtAreaEki1;
    private javax.swing.JTextArea txtAreaEki2;
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
