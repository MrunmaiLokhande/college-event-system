package dao;

import db.DBConnection;
import models.Teacher;

import java.sql.*;

public class TeacherDAO {

    public Teacher getTeacherByUserId(int userId) {

        String sql = "SELECT * FROM teachers WHERE user_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Teacher(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("department")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}