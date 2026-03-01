package ui;

import dao.EventDAO;
import models.Event;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TeacherEventsPanel extends JPanel {

    private JTable table;
    private DefaultTableModel model;
    private int teacherId;

    public TeacherEventsPanel(int teacherId) {

        this.teacherId = teacherId;

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(15,15,15,15));

        String[] columns = {"ID", "Title", "Description", "Date", "Max Participants"};

        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);

        add(new JScrollPane(table), BorderLayout.CENTER);

        // ===== Buttons =====
        JPanel buttonPanel = new JPanel();

        JButton updateBtn = new JButton("Update Event");
        JButton deleteBtn = new JButton("Delete Event");

        updateBtn.setBackground(new Color(52, 152, 219));
        updateBtn.setForeground(Color.WHITE);

        deleteBtn.setBackground(new Color(231, 76, 60));
        deleteBtn.setForeground(Color.WHITE);

        buttonPanel.add(updateBtn);
        buttonPanel.add(deleteBtn);

        add(buttonPanel, BorderLayout.SOUTH);

        loadEvents();

        // ===== Delete Action =====
        deleteBtn.addActionListener(e -> deleteEvent());

        // ===== Update Action =====
        updateBtn.addActionListener(e -> updateEvent());
    }

    private void loadEvents() {

        model.setRowCount(0);

        EventDAO dao = new EventDAO();
        List<Event> list = dao.getEventsByTeacher(teacherId);

        for (Event e : list) {
            model.addRow(new Object[]{
                    e.getId(),
                    e.getTitle(),
                    e.getDescription(),
                    e.getEventDate(),
                    e.getMaxParticipants()
            });
        }
    }

    private void deleteEvent() {

        int row = table.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select an event first!");
            return;
        }

        int eventId = (int) model.getValueAt(row, 0);

        EventDAO dao = new EventDAO();
        boolean success = dao.deleteEvent(eventId);

        if (success) {
            JOptionPane.showMessageDialog(this, "Event Deleted!");
            loadEvents();
        } else {
            JOptionPane.showMessageDialog(this, "Error deleting event!");
        }
    }

    private void updateEvent() {

        int row = table.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select an event first!");
            return;
        }

        int eventId = (int) model.getValueAt(row, 0);

        String newTitle = JOptionPane.showInputDialog(this, "Enter New Title:");

        if (newTitle == null || newTitle.isEmpty()) {
            return;
        }

        EventDAO dao = new EventDAO();
        boolean success = dao.updateEventTitle(eventId, newTitle);

        if (success) {
            JOptionPane.showMessageDialog(this, "Event Updated!");
            loadEvents();
        } else {
            JOptionPane.showMessageDialog(this, "Error updating event!");
        }
    }
}