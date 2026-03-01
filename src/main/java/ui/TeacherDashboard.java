package ui;

import javax.swing.*;
import java.awt.*;

public class TeacherDashboard extends JFrame {

    private JPanel contentPanel;
    private int teacherId;

    public TeacherDashboard(int teacherId) {

        this.teacherId = teacherId;

        setTitle("Teacher Dashboard");
        setSize(1100, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        Color sidebarColor = new Color(33, 47, 60);
        Color bgColor = new Color(245, 247, 250);

        JPanel sidebar = new JPanel();
        sidebar.setBackground(sidebarColor);
        sidebar.setPreferredSize(new Dimension(220, 650));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Teacher Panel");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));

        JButton createEventBtn = createMenuButton("Create Event");
        JButton viewEventsBtn = createMenuButton("View Events");
        JButton approveBtn = createMenuButton("Approve Registrations");
        JButton logoutBtn = createMenuButton("Logout");

        sidebar.add(title);
        sidebar.add(createEventBtn);
        sidebar.add(viewEventsBtn);
        sidebar.add(approveBtn);
        sidebar.add(Box.createVerticalGlue());
        sidebar.add(logoutBtn);

        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(bgColor);

        contentPanel.add(new EventForm(teacherId), BorderLayout.CENTER);

        // ✅ Correct Panel Switching
        createEventBtn.addActionListener(e ->
                switchPanel(new EventForm(teacherId)));

        viewEventsBtn.addActionListener(e ->
        switchPanel(new TeacherEventsPanel(teacherId)));

        approveBtn.addActionListener(e ->
                switchPanel(new ApprovePanel(teacherId)));

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
        button.setMaximumSize(new Dimension(200, 45));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setFocusPainted(false);
        button.setBackground(new Color(41, 128, 185));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }
}