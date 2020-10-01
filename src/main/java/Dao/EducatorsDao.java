package Dao;

import models.Educators;
import models.Schools;

import java.util.List;

public interface EducatorsDao {
    //create
    void add(Educators educators);
    void addEducatorsToSchool(Educators ed, Schools schools);

    //    //read
    Educators getEducatorsById(int id);
    //
//    //Delete
    void DeleteAllEducatorsFromJoin(int id);
}
