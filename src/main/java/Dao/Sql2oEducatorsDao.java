package Dao;

import models.Educators;
import models.Schools;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class Sql2oEducatorsDao implements EducatorsDao {

    private final Sql2o sql2o;

    public Sql2oEducatorsDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Educators educators){
        String sql = "INSERT INTO educators (educatorsname, educatorsphone, educatorsemail, educatorscourse) VALUES (:educatorsname, :educatorsphone, :educatorsemail,:educatorscourse)";
        try(Connection connection = sql2o.open()){
            int id = (int) connection.createQuery(sql, true)
                    .addParameter("educatorsname", educators.getName())
                    .addParameter("educatorsphone", educators.getPhone())
                    .addParameter("educatorsemail", educators.getEmail())
                    .addParameter("educatorsemail", educators.getEmail())
                    .addParameter("educatorscourse", educators.getCourse())
                    .executeUpdate()
                    .getKey();
            educators.setId(id);
        }
    }

    @Override
    public void addEducatorsToSchool(Schools schools, Educators ed){
        String sql = "INSERT INTO school_educator(educatorid, schoolid) VALUES (:educatorid, :schoolid);";
        try(Connection connection = sql2o.open()){
            connection.createQuery(sql)
                    .addParameter("educatorid", ed.getId())
                    .addParameter("schoolid", schools.getId())
                    .executeUpdate();
        }
    }



    @Override
    public Educators getEducatorsById(int id){
        String sql = "SELECT * FROM educators WHERE id=:id";
        try(Connection connection = sql2o.open()){
            return connection.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Educators.class);
        }
    }

    @Override
    public void DeleteAllEducatorsFromJoin(int id){
        String sql = "DELETE FROM educators WHERE id=:id";
        String sql1 = "DELETE FROM school_educator WHERE id=:id";
        try(Connection connection = sql2o.open()){
            connection.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
            connection.createQuery(sql1)
                    .addParameter("id", id)
                    .executeUpdate();
        }catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

}
