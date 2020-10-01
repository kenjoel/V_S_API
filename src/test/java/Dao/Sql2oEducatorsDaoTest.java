package Dao;

import models.Educators;
import models.Schools;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

import static org.junit.Assert.*;

public class Sql2oEducatorsDaoTest {

    private Connection conn;
    private StudentsDao studentsDao;
    private SchoolDao schoolDao;
    private EducatorsDao educatorsDao;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/education_test"; //connect to postgres test database
        Sql2o sql2o = new Sql2o(connectionString, "moringa", "://postgres");
        studentsDao = new Sql2oStudentsDao(sql2o);
        schoolDao = new Sql2oSchoolDao(sql2o);
        educatorsDao = new Sql2oEducatorsDao(sql2o);
        conn = sql2o.open();
        studentsDao.clearAll();
    }

    @After
    public void tearDown() throws Exception {
        studentsDao.clearAll();
        conn.close();
    }


    @Test
    public void SavesSuccessfully(){
        Educators educators = new Educators("Catherine", "071992783","catherina@mail.com", "Mathematics");
        educatorsDao.add(educators);
        assertEquals(1, educators.getId());
    }

    @Test
    public void getByIdWorks(){
        Educators educators = new Educators("Catherine", "071992783","catherina@mail.com", "Mathematics");
        educatorsDao.add(educators);
        Educators educators1 = educatorsDao.getEducatorsById(educators.getId());
        assertEquals(educators1.getId(), educators.getId());
    }


    @Test
    public void gettingEducatorsWorks(){
        Schools schools = new Schools("Agha Khan","1001","agahaan.website.com","jssk@gmail.com","07219277282");
        schoolDao.add(schools);
        Educators educators = new Educators("Catherine", "071992783","catherina@mail.com", "Mathematics");
        educatorsDao.add(educators);

        educatorsDao.addEducatorsToSchool(schools, educators);

        List<Educators> ed = schoolDao.getAllEducatorsBySchool(schools.getId());

        assertEquals(1, ed.size());

    }
}