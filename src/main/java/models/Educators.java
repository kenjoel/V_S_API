package models;

public class Educators {
    private int id;
    private String educatorsname;
    private String educatorsphone;
    private String educatorsemail;
    private String educatorscourse;


    public Educators(String educatorsname, String educatorsphone, String educatorsemail, String educatorscourse){
        this.educatorsname = educatorsname;
        this.educatorsphone = educatorsphone;
        this.educatorsemail = educatorsemail;
        this.educatorscourse = educatorscourse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return educatorsname;
    }

    public String getPhone() {
        return educatorsphone;
    }

    public String getEmail() {
        return educatorsemail;
    }

    public String getCourse() {
        return educatorscourse;
    }

    public void setName(String name) {
        this.educatorsname = name;
    }

    public void setPhone(String phone) {
        this.educatorsphone = phone;
    }

    public void setEmail(String email) {
        this.educatorsemail = email;
    }

    public void setCourse(String course) {
        this.educatorscourse = course;
    }

}
