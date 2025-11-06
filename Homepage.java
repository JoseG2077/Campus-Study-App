import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Map;

public class Homepage extends JFrame {
    private DefaultListModel<String> listModel = new DefaultListModel<>();

    public Homepage() {
        setTitle("Campus Study Spaces");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Background map
        ImageIcon mapIcon = new ImageIcon("StanStateMap.jpg");
        Image mapImage = mapIcon.getImage().getScaledInstance(800, 600, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(mapImage);

        JLabel backgroundLabel = new JLabel(scaledIcon);
        backgroundLabel.setLayout(new BorderLayout());

        // Side panel for the study list
        JPanel sidePanel = new JPanel(new BorderLayout());
        sidePanel.setPreferredSize(new Dimension(220, 0)); // width of side panel
        sidePanel.setOpaque(false);

        // Study space list
        JList<String> studyList = new JList<>(listModel);
        studyList.setFont(new Font("Arial", Font.BOLD, 14));
        studyList.setFixedCellHeight(25);

        JScrollPane scrollPane = new JScrollPane(studyList);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        sidePanel.add(scrollPane, BorderLayout.CENTER);

        // Add side panel to the right side
        backgroundLabel.add(sidePanel, BorderLayout.EAST);

        // Search bar
        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.setOpaque(false);
        JTextField searchField = new JTextField();
        JButton searchButton = new JButton("Search");
        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(searchButton, BorderLayout.EAST);
        backgroundLabel.add(searchPanel, BorderLayout.NORTH);

        setContentPane(backgroundLabel);

        // Load study spaces in background
        new Thread(() -> {
            List<String> spaces = Database.getStudySpaces();
            SwingUtilities.invokeLater(() -> {
                for (String space : spaces) listModel.addElement(space);
            });
        }).start();

        // Search button functionality
        searchButton.addActionListener(e -> {
            String searchName = searchField.getText().trim();
            if (searchName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a study space name.");
            } else {
                openStudySpace(searchName);
            }
        });

        // Double-click list item to open
        studyList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    String selectedSpace = studyList.getSelectedValue();
                    openStudySpace(selectedSpace);
                }
            }
        });

        setVisible(true);
    }

    // Open Study Space 
    private void openStudySpace(String spaceName) {
        new Thread(() -> {
            Map<String, String> details = Database.getStudySpaceDetails(spaceName);
            SwingUtilities.invokeLater(() -> {
                if (details.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Study space not found in database.");
                } else {
                    dispose(); // close current Homepage
                    new StudySpace(
                            details.get("spaceName"),
                            details.get("location"),
                            details.get("description"),
                            details.get("photoPath"),
                            400,
                            300,
                            details.get("indoorsOrOutdoors"),
                            details.get("noiseLevel")
                    );
                }
            });
        }).start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Homepage());
    }
}
