package dao;

import db.DBConnection;
import models.Student;

import java.sql.*;

public class StudentDAO {

    public Student getStudentByUserId(int userId) {

        String sql = "SELECT * FROM students WHERE user_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Student(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("branch"),
                        rs.getInt("year")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}