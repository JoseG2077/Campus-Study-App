
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudySpace extends JFrame {

    public StudySpace(String name, String location, String description, String photoPath, int imgWidth, 
    int imgHeight, String indoorsOrOutdoors, String noiseLevel) {
        // Frame setup
        setTitle("Study Space: " + name);
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Top: Study space name
        JLabel nameLabel = new JLabel(name, SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(nameLabel, BorderLayout.NORTH);

        // Center: Image
        JLabel photoLabel = new JLabel();
        ImageIcon icon = new ImageIcon(photoPath);
        Image img = icon.getImage().getScaledInstance(imgWidth, imgHeight, Image.SCALE_SMOOTH);
        photoLabel.setIcon(new ImageIcon(img));
        photoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(photoLabel, BorderLayout.CENTER);

        // Bottom: Details panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel locationLabel = new JLabel("Location: " + location);
        locationLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        bottomPanel.add(locationLabel);

        JLabel descriptionLabel = new JLabel("<html><body style='width:400px'>" + description + "</body></html>");
        descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        descriptionLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        bottomPanel.add(descriptionLabel);

        JLabel indoorsLabel = new JLabel("Indoors/Outdoors: " + indoorsOrOutdoors);
        indoorsLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        indoorsLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        bottomPanel.add(indoorsLabel);

        JLabel noiseLabel = new JLabel("Noise Level: " + noiseLevel);
        noiseLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        noiseLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        bottomPanel.add(noiseLabel);

        JButton returnButton = new JButton("Return to Homepage");
        returnButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        returnButton.addActionListener(e -> {
        dispose();          // close the current StudySpace window
        new Homepage();     // reopen Homepage
        });
        bottomPanel.add(returnButton);

        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);

    }



    public static void main(String[] args) {
        new StudySpace(
                "Vasche Library",
                "Library, 2nd Floor",
                "Perfect for individual and group study. Comfortable seating with outlets.",
                "library.jpg",
                400,
                300,
                "Indoors",
                "Very Quiet"
        );
    }
}
