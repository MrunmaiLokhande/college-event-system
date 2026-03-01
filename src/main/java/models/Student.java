package models;

public class Student {

    private int id;
    private int userId;
    private String name;
    private String email;
    private String branch;
    private int year;

    public Student() {}

    public Student(int id, int userId, String name, String email,
                   String branch, int year) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.branch = branch;
        this.year = year;
    }

    public int getId() { return id; }
    public int getUserId() { return userId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getBranch() { return branch; }
    public int getYear() { return year; }
}