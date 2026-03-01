package ui;

import dao.EventDAO;
import dao.RegistrationDAO;
import models.Event;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ViewEventsPanel extends JPanel {

    private JTable table;
    private DefaultTableModel model;
    private int studentId;

    public ViewEventsPanel(int studentId) {

        this.studentId = studentId;

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(15,15,15,15));

        String[] columns = {"ID", "Title", "Description", "Date", "Max Participants"};

        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);

        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton registerBtn = new JButton("Register");
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(registerBtn);
        add(bottomPanel, BorderLayout.SOUTH);

        registerBtn.addActionListener(e -> registerEvent());

        loadEvents();
    }

    private void loadEvents() {

        model.setRowCount(0);

        EventDAO dao = new EventDAO();
        List<Event> list = dao.getAllEvents();

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

    private void registerEvent() {

        int row = table.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select an event first!");
            return;
        }

        int eventId = (int) model.getValueAt(row, 0);

        RegistrationDAO dao = new RegistrationDAO();
        boolean success = dao.registerEvent(studentId, eventId);

        if (success) {
            JOptionPane.showMessageDialog(this, "Registered Successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Already Registered!");
        }
    }
}