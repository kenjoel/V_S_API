package Dao;

import models.Schools;
import models.Students;

import java.util.List;

public interface StudentsDao {
    void add(Students students);
    void addStudentsToSchool(Students students, Schools schools);

    List<Students> getAll();
    List<Schools> getAllSchoolsForAStudent(int id);

    Students findByName(String studentsname);
    void clearAll();

}
