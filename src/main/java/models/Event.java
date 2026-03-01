package models;

import java.sql.Date;

public class Event {

    private int id;
    private String title;
    private String description;
    private Date eventDate;
    private int createdBy;
    private int maxParticipants;

    public Event() {}

    public Event(int id, String title, String description, Date eventDate,
                 int createdBy, int maxParticipants) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.eventDate = eventDate;
        this.createdBy = createdBy;
        this.maxParticipants = maxParticipants;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }
}