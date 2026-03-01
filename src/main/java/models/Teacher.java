package models;

public class Teacher {

    private int id;
    private int userId;
    private String name;
    private String email;
    private String department;

    public Teacher() {}

    public Teacher(int id, int userId, String name,
                   String email, String department) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.department = department;
    }

    public int getId() { return id; }
    public int getUserId() { return userId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getDepartment() { return department; }
}