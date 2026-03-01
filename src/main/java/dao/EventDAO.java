package dao;

import db.DBConnection;
import models.Event;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventDAO {

    public List<Event> getAllEvents() {

        List<Event> list = new ArrayList<>();

        try {

            Connection con = DBConnection.getConnection();
            String query = "SELECT * FROM events";
            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Event event = new Event(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getDate("event_date"),     // ✅ Correct column
                        rs.getInt("created_by"),
                        rs.getInt("max_participants")
                );

                list.add(event);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    
    public void createEvent(Event event) {

        try {
            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO events (title, description, event_date, created_by, max_participants) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, event.getTitle());
            ps.setString(2, event.getDescription());
            ps.setDate(3, event.getEventDate());
            ps.setInt(4, event.getCreatedBy());
            ps.setInt(5, event.getMaxParticipants());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<Event> getEventsByTeacher(int teacherId) {

        List<Event> list = new ArrayList<>();

        try (Connection con = DBConnection.getConnection()) {

            String sql = "SELECT * FROM events WHERE created_by = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, teacherId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Event e = new Event();
                e.setId(rs.getInt("id"));
                e.setTitle(rs.getString("title"));
                e.setDescription(rs.getString("description"));
                e.setEventDate(rs.getDate("event_date"));
                e.setMaxParticipants(rs.getInt("max_participants"));

                list.add(e);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    
    public boolean deleteEvent(int eventId) {

        boolean result = false;

        try (Connection con = DBConnection.getConnection()) {

            String sql = "DELETE FROM events WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, eventId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                result = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
    
    public boolean updateEventTitle(int eventId, String title) {

        boolean result = false;

        try (Connection con = DBConnection.getConnection()) {

            String sql = "UPDATE events SET title=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, title);
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
}