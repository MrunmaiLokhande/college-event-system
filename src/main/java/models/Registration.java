package models;

public class Registration {

    private int id;
    private int studentId;
    private int eventId;
    private String status;   // NEW FIELD

    public Registration() {}

    public Registration(int studentId, int eventId, String status) {
        this.studentId = studentId;
        this.eventId = eventId;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getStatus() {       // NEW GETTER
        return status;
    }

    public void setStatus(String status) {   // NEW SETTER
        this.status = status;
    }
}