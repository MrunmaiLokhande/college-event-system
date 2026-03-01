package ui;

import dao.RegistrationDAO;
import models.Registration;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MyRegistrationsPanel extends JPanel {

    private JTable table;
    private DefaultTableModel model;

    public MyRegistrationsPanel(int studentId) {

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(15,15,15,15));

        String[] columns = {"Reg ID", "Event ID", "Status"};
        model = new DefaultTableModel(columns, 0);

        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        loadRegistrations(studentId);
    }

    private void loadRegistrations(int studentId) {

        model.setRowCount(0);

        RegistrationDAO dao = new RegistrationDAO();
        List<Registration> list = dao.getRegistrationsByStudent(studentId);

        for (Registration r : list) {
            model.addRow(new Object[]{
                    r.getId(),
                    r.getEventId(),
                    r.getStatus()
            });
        }
    }
}