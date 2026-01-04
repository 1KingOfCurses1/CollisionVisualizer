package collisionvisualizer;

import java.awt.*;
import java.io.InputStream;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 * Modernized Definition/Dictionary Page.
 */
public class DefinitionPage extends javax.swing.JFrame {

    private MainPage mainWindow;
    private ShowResultsPage resultsWindow;
    public static Term[] sortedList;

    // UI Components
    private JTextField searchTxtField;
    private JTextArea definitionTxtArea;
    private JLabel errorLbl;
    private JButton backBtn, searchBtn;

    public DefinitionPage(MainPage m) {
        this.mainWindow = m;
        initComponentsModern();
        loadData();
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
        
        JLabel title = new JLabel("PHYSICS INDEX");
        title.setFont(UIUtils.FONT_TITLE);
        title.setForeground(UIUtils.ACCENT_CYAN);
        header.add(title, BorderLayout.CENTER);
        title.setHorizontalAlignment(SwingConstants.CENTER);

        backBtn = new JButton("← BACK");
        UIUtils.styleSecondaryButton(backBtn);
        backBtn.addActionListener(e -> {
            mainWindow.setVisible(true);
            this.setVisible(false);
        });
        header.add(backBtn, BorderLayout.WEST);
        mainPanel.add(header, BorderLayout.NORTH);

        // Center Content
        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.setOpaque(false);

        // Search Bar
        JPanel searchBar = new JPanel(new BorderLayout(10, 0));
        searchBar.setOpaque(false);
        
        searchTxtField = new JTextField();
        searchTxtField.setBackground(UIUtils.SURFACE_DARK);
        searchTxtField.setForeground(UIUtils.TEXT_WHITE);
        searchTxtField.setCaretColor(UIUtils.ACCENT_CYAN);
        searchTxtField.setBorder(new LineBorder(new Color(60, 60, 60), 1));
        searchTxtField.setFont(UIUtils.FONT_NORMAL);
        searchTxtField.setPreferredSize(new Dimension(0, 40));

        searchBtn = new JButton("SEARCH DATABASE");
        UIUtils.styleButton(searchBtn);
        searchBtn.setPreferredSize(new Dimension(180, 40));
        searchBtn.addActionListener(e -> performSearch());

        searchBar.add(searchTxtField, BorderLayout.CENTER);
        searchBar.add(searchBtn, BorderLayout.EAST);

        errorLbl = new JLabel("");
        errorLbl.setForeground(UIUtils.ACCENT_RED);
        errorLbl.setFont(new Font("Inter", Font.ITALIC, 12));
        
        JPanel searchSection = new JPanel(new BorderLayout());
        searchSection.setOpaque(false);
        searchSection.add(searchBar, BorderLayout.NORTH);
        searchSection.add(errorLbl, BorderLayout.SOUTH);

        // Term List
        definitionTxtArea = new JTextArea();
        definitionTxtArea.setEditable(false);
        definitionTxtArea.setBackground(UIUtils.SURFACE_DARK);
        definitionTxtArea.setForeground(UIUtils.TEXT_GRAY);
        definitionTxtArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        definitionTxtArea.setMargin(new Insets(10, 10, 10, 10));
        
        JScrollPane scrollPane = new JScrollPane(definitionTxtArea);
        scrollPane.setBorder(new LineBorder(new Color(60, 60, 60), 1));

        centerPanel.add(searchSection, BorderLayout.NORTH);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
    }

    private void loadData() {
        Term list[] = new Term[15];
        try {
            InputStream in = DefinitionPage.class.getResourceAsStream("definitions.txt");
            Scanner s = new Scanner(in);
            for (int i = 0; i < list.length; i++) {
                String name = s.nextLine();
                String definition = s.nextLine();
                list[i] = new Term(name, definition);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Critical Error: Definitions library missing.");
        }
        
        sortedList = quickSort(list, 0, list.length - 1);
        StringBuilder sb = new StringBuilder("AVAILABLE INDEX TERMS:\n\n");
        for (Term t : sortedList) {
            sb.append(" > ").append(t.getName()).append("\n");
        }
        definitionTxtArea.setText(sb.toString());
    }

    private void performSearch() {
        String target = searchTxtField.getText().trim();
        int location = linearSearch(sortedList, target);
        if (location != -1) {
            errorLbl.setText("");
            resultsWindow = new ShowResultsPage(this, sortedList[location].getName(), sortedList[location].getDefinition());
            resultsWindow.setVisible(true);
            this.setVisible(false);
        } else {
            errorLbl.setText("⚠ TERM NOT FOUND IN DATABASE");
        }
    }

    public int linearSearch(Term[] sortedTerms, String target) {
        for (int i = 0; i < sortedTerms.length; i++) {
            if (sortedTerms[i].getName().equalsIgnoreCase(target)) return i;
        }
        return -1;
    }

    private static Term[] quickSort(Term[] unsorted, int start, int end) {
        if (start >= end) return unsorted;
        int i = start, j = end;
        Term pivotValue = unsorted[(start + end) / 2];
        while (i <= j) {
            while (unsorted[i].getName().compareTo(pivotValue.getName()) < 0) i++;
            while (unsorted[j].getName().compareTo(pivotValue.getName()) > 0) j--;
            if (i <= j) {
                Term temp = unsorted[i];
                unsorted[i] = unsorted[j];
                unsorted[j] = temp;
                i++; j--;
            }
        }
        if (start < j) quickSort(unsorted, start, j);
        if (i < end) quickSort(unsorted, i, end);
        return unsorted;
    }
}
