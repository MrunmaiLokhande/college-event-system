package ui;

import javax.swing.*;
import java.awt.*;

public class StudentDashboard extends JFrame {

    private JPanel contentPanel;
    private int studentId;

    public StudentDashboard(int studentId) {

        this.studentId = studentId;

        setTitle("Student Dashboard");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        Color sidebarColor = new Color(44, 62, 80);
        Color bgColor = new Color(245, 247, 250);

        // ===== Sidebar =====
        JPanel sidebar = new JPanel();
        sidebar.setBackground(sidebarColor);
        sidebar.setPreferredSize(new Dimension(200, 600));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Student Panel");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JButton viewEventsBtn = createMenuButton("View Events");
        JButton myEventsBtn = createMenuButton("My Registrations");
        JButton logoutBtn = createMenuButton("Logout");

        sidebar.add(title);
        sidebar.add(viewEventsBtn);
        sidebar.add(myEventsBtn);
        sidebar.add(Box.createVerticalGlue());
        sidebar.add(logoutBtn);

        // ===== Content Panel =====
        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(bgColor);

        // ✅ Default Panel
        contentPanel.add(new ViewEventsPanel(studentId), BorderLayout.CENTER);

        // ===== Button Actions =====

        // View All Events
        viewEventsBtn.addActionListener(e -> switchPanel(new ViewEventsPanel(studentId)));

        // My Registrations
        myEventsBtn.addActionListener(e -> switchPanel(new MyRegistrationsPanel(studentId)));

        // Logout
        logoutBtn.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });

        add(sidebar, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void switchPanel(JPanel panel) {
        contentPanel.removeAll();
        contentPanel.add(panel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private JButton createMenuButton(String text) {
        JButton button = new JButton(text);
        button.setMaximumSize(new Dimension(180, 45));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setFocusPainted(false);
        button.setBackground(new Color(52, 152, 219));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }
}