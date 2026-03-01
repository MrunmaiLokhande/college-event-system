package ui;

import dao.RegistrationDAO;
import models.Registration;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ApprovePanel extends JPanel {

    private JTable table;
    private DefaultTableModel model;
    private RegistrationDAO dao;
    private int teacherId;

    public ApprovePanel(int teacherId) {

        this.teacherId = teacherId;
        this.dao = new RegistrationDAO();

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(15,15,15,15));

        // ===== Table Setup =====
        String[] columns = {"Reg ID", "Student ID", "Event ID", "Status"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);

        add(new JScrollPane(table), BorderLayout.CENTER);

        // ===== Buttons =====
        JPanel buttonPanel = new JPanel();

        JButton approveBtn = new JButton("Approve");
        approveBtn.setBackground(new Color(46, 204, 113));
        approveBtn.setForeground(Color.WHITE);

        JButton rejectBtn = new JButton("Reject");
        rejectBtn.setBackground(new Color(231, 76, 60));
        rejectBtn.setForeground(Color.WHITE);

        buttonPanel.add(approveBtn);
        buttonPanel.add(rejectBtn);

        add(buttonPanel, BorderLayout.SOUTH);

        // Load data
        loadRegistrations();

        // ===== Approve Action =====
        approveBtn.addActionListener(e -> {
            int row = table.getSelectedRow();

            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Please select a registration!");
                return;
            }

            int regId = (int) model.getValueAt(row, 0);

            boolean success = dao.updateStatus(regId, "Approved");

            if (success) {
                JOptionPane.showMessageDialog(this, "Registration Approved!");
                loadRegistrations();
            } else {
                JOptionPane.showMessageDialog(this, "Error updating status!");
            }
        });

        // ===== Reject Action =====
        rejectBtn.addActionListener(e -> {
            int row = table.getSelectedRow();

            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Please select a registration!");
                return;
            }

            int regId = (int) model.getValueAt(row, 0);

            boolean success = dao.updateStatus(regId, "Rejected");

            if (success) {
                JOptionPane.showMessageDialog(this, "Registration Rejected!");
                loadRegistrations();
            } else {
                JOptionPane.showMessageDialog(this, "Error updating status!");
            }
        });
    }

    // 🔄 Load Registrations from DB
    private void loadRegistrations() {

        model.setRowCount(0); // clear table

        List<Registration> list = dao.getRegistrationsByTeacher(teacherId);

        for (Registration r : list) {
            model.addRow(new Object[]{
                    r.getId(),
                    r.getStudentId(),
                    r.getEventId(),
                    r.getStatus()
            });
        }
    }
}