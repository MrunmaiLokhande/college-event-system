package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import models.Registration;

public class RegistrationDAO {

    // 🔹 Check if already registered
    public boolean isAlreadyRegistered(int studentId, int eventId) {
        boolean exists = false;

        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT * FROM registration WHERE student_id=? AND event_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, studentId);
            ps.setInt(2, eventId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                exists = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return exists;
    }

    public boolean registerEvent(int studentId, int eventId) {

        boolean result = false;

        try (Connection con = DBConnection.getConnection()) {

            // check if already registered
            String checkSql = "SELECT * FROM registrations WHERE student_id=? AND event_id=?";
            PreparedStatement checkPs = con.prepareStatement(checkSql);
            checkPs.setInt(1, studentId);
            checkPs.setInt(2, eventId);

            ResultSet rs = checkPs.executeQuery();

            if (rs.next()) {
                return false; // already registered
            }

            String sql = "INSERT INTO registrations(student_id, event_id, status) VALUES (?, ?, 'Pending')";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, studentId);
            ps.setInt(2, eventId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                result = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public boolean updateStatus(int regId, String status) {

        boolean result = false;

        try (Connection con = DBConnection.getConnection()) {

            String sql = "UPDATE registrations SET status=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, status);
            ps.setInt(2, regId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                result = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
    
    public List<Registration> getRegistrationsByTeacher(int teacherId) {

        List<Registration> list = new ArrayList<>();

        try (Connection con = DBConnection.getConnection()) {

            String sql =
                    "SELECT r.id, r.student_id, r.event_id, r.status " +
                    "FROM registrations r " +
                    "JOIN events e ON r.event_id = e.id " +
                    "WHERE e.created_by = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, teacherId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Registration r = new Registration();
                r.setId(rs.getInt("id"));
                r.setStudentId(rs.getInt("student_id"));
                r.setEventId(rs.getInt("event_id"));
                r.setStatus(rs.getString("status"));

                list.add(r);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    
    public List<Registration> getRegistrationsByStudent(int studentId) {

        List<Registration> list = new ArrayList<>();

        try (Connection con = DBConnection.getConnection()) {

            String sql =
                    "SELECT * FROM registrations WHERE student_id = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, studentId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Registration r = new Registration();
                r.setId(rs.getInt("id"));
                r.setStudentId(rs.getInt("student_id"));
                r.setEventId(rs.getInt("event_id"));
                r.setStatus(rs.getString("status"));

                list.add(r);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}