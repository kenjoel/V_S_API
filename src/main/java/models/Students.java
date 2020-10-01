package models;

import java.util.Objects;

public class Students {
    private int id;
    private String studentname;
    private String studentemail;
    private String studentcourse;

    public Students(String studentname, String studentemail, String studentcourse) {
        this.studentname = studentname;
        this.studentemail = studentemail;
        this.studentcourse = studentcourse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public String getStudentemail() {
        return studentemail;
    }

    public void setStudentemail(String studentemail) {
        this.studentemail = studentemail;
    }

    public String getStudentcourse() {
        return studentcourse;
    }

    public void setStudentcourse(String studentcourse) {
        this.studentcourse = studentcourse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Students students = (Students) o;
        return id == students.id &&
                studentname.equals(students.studentname) &&
                studentemail.equals(students.studentemail) &&
                studentcourse.equals(students.studentcourse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, studentname, studentemail, studentcourse);
    }
}
