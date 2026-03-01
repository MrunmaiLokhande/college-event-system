package ui;

import dao.EventDAO;
import models.Event;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;

public class EventForm extends JPanel {

    public EventForm(int teacherId) {

        setLayout(new GridLayout(6,2,10,10));
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        JTextField titleField = new JTextField();
        JTextField descField = new JTextField();
        JTextField dateField = new JTextField();
        JTextField maxField = new JTextField();

        JButton createBtn = new JButton("Create Event");

        add(new JLabel("Title:"));
        add(titleField);

        add(new JLabel("Description:"));
        add(descField);

        add(new JLabel("Event Date (yyyy-mm-dd):"));
        add(dateField);

        add(new JLabel("Max Participants:"));
        add(maxField);

        add(new JLabel());
        add(createBtn);

        createBtn.addActionListener(e -> {

            try {
                if (titleField.getText().isEmpty() ||
                    dateField.getText().isEmpty() ||
                    maxField.getText().isEmpty()) {

                    JOptionPane.showMessageDialog(this, "Please fill all required fields!");
                    return;
                }

                Event event = new Event();
                event.setTitle(titleField.getText());
                event.setDescription(descField.getText());
                event.setEventDate(Date.valueOf(dateField.getText()));
                event.setCreatedBy(teacherId);
                event.setMaxParticipants(Integer.parseInt(maxField.getText()));

                new EventDAO().createEvent(event);

                JOptionPane.showMessageDialog(this, "Event Created Successfully!");

                titleField.setText("");
                descField.setText("");
                dateField.setText("");
                maxField.setText("");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
                ex.printStackTrace();
            }
        });
    }
}