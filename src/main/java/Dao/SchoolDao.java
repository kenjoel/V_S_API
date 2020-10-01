package Dao;

import models.Educators;
import models.Schools;
import models.Students;

import java.util.List;

public interface SchoolDao {

    //create
    void add(Schools schools);


    //read
    Schools findSchoolById(int schoolId);
    List<Schools> getAll();
    List<Students> getAllStudentsBySchool(int id);

   // List<Educators> getAllEducatorsBySchool(int id);

    //update
    //void update(int id, String schoolName, String schoolAddress, String schoolWebsite, String schoolEmail, String phoneNumber);


    //delete
    void deleteById(int id);
    void clearAll();

}
