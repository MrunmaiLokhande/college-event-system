package ui;

import dao.StudentDAO;
import dao.TeacherDAO;
import dao.UserDAO;
import models.Student;
import models.Teacher;
import models.User;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    public LoginFrame() {

        setTitle("College Event System - Login");
        setSize(400,250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(4,2,10,10));

        JTextField userField = new JTextField();
        JPasswordField passField = new JPasswordField();
        JButton loginBtn = new JButton("Login");

        add(new JLabel("Username:"));
        add(userField);

        add(new JLabel("Password:"));
        add(passField);

        add(new JLabel());
        add(loginBtn);

        loginBtn.addActionListener(e -> {

            String username = userField.getText();
            String password = new String(passField.getPassword());

            User user = new UserDAO().login(username, password);

            if(user.getRole().equalsIgnoreCase("teacher")) {

                Teacher teacher = new TeacherDAO().getTeacherByUserId(user.getId());
                new TeacherDashboard(teacher.getId());

            } else {

                Student student = new StudentDAO().getStudentByUserId(user.getId());
                new StudentDashboard(student.getId());
            }
        });

        setVisible(true);
    }
}