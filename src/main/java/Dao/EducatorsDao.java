package Dao;

import models.Educators;
import models.Schools;

import java.util.List;

public interface EducatorsDao {
    //create
    void add(Educators educators);
    void addEducatorsToSchool(Schools schools, Educators ed);

    //    //read
    List<Educators> getAllEducatorsBySchool(int id);
    Educators getEducatorsById(int id);
    //
//    //Delete
    void DeleteAllEducatorsFromJoin(int id);
}
