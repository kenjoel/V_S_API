package Dao;

import models.Educators;
import models.Schools;
import models.Students;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

import static org.junit.Assert.*;

public class Sql2oSchoolDaoTest {

    private static Sql2oSchoolDao schoolsDao;
    private static Sql2oStudentsDao studentsDao;
    private  static Sql2oEducatorsDao educatorsDao;
    private static Connection conn;

    @Before
    public  void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/education_test"; //connect to postgres test database
        Sql2o sql2o = new Sql2o(connectionString, "moringa", "Georgedatabase1");

//        String connectionString = "jdbc:postgresql://ec2-54-90-68-208.compute-1.amazonaws.com:5432/d3prpp5l3ugu23 "; //!
//        Sql2o sql2o = new Sql2o(connectionString, "fzcpvwmacnmgzn", "f8b6da8439fce09b61063f1c4856d2b191217799d97e4c506bddf5f7fc97f401");
        schoolsDao = new Sql2oSchoolDao(sql2o);
        studentsDao = new Sql2oStudentsDao(sql2o);
        educatorsDao = new Sql2oEducatorsDao(sql2o);

        conn = sql2o.open();
        schoolsDao.clearAll();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("clearing databse");
        schoolsDao.clearAll();
        conn.close();
    }

    @Test
     public void testSaveMethod(){
        Schools schools = new Schools("Agha Khan","1001","agahaan.website.com","jssk@gmail.com","07219277282");
        schoolsDao.add(schools);
        assertEquals(1, schools.getId());
    }

    @Test
    public void savingSetsId(){
        Schools schools = new Schools("Agha Khan","1001","agahaan.website.com","jssk@gmail.com","07219277282");
        schoolsDao.add(schools);
        Schools aid = schoolsDao.findSchoolById(schools.getId());

        assertEquals( aid.getId(), schools.getId());
    }

//    @Test
//    public void addEducatorToSchool(){
//        Schools schools = new Schools("Agha Khan","1001","agahaan.website.com","jssk@gmail.com","07219277282");
//        schoolsDao.add(schools);
//        Educators educators = new Educators("bahali yake","0872776242","bahaliyake@gmail.com","beauty");
//        educatorsDao.add(educators);
//
//        educatorsDao.addEducatorsToSchool(schools, educators);
//
//        List<Educators> ed = schoolsDao.getAllEducatorsBySchool(schools.getId());
//       // assertTrue(schools.getId() == schoolsDao.getAllEducatorsBySchool(schools.getId()).size());
//    }

    @Test
    public void takeStudenToSchool(){
        Schools schools = new Schools("Agha Khan","1001","agahaan.website.com","jssk@gmail.com","07219277282");
        schoolsDao.add(schools);

        Students students = new Students("Geroge Okumu", "gokumu@gmail.com","Softwaredev");
        studentsDao.add(students);
        studentsDao.addStudentsToSchool(students, schools);

        List<Students> skull = schoolsDao.getAllStudentsBySchool(schools.getId());

        assertTrue(skull.contains(students));
    }

    @Test
    public void getAllMethodWorks(){
        Schools schools = new Schools("Agha Khan","1001","agahaan.website.com","jssk@gmail.com","07219277282");
        schoolsDao.add(schools);

        Schools scho = new Schools(" Khan","1001","agaha.com","jssk@yahoo.com","07219277282");
        schoolsDao.add(scho);

        assertEquals(2, schoolsDao.getAll().size());

    }

    @Test
    public void findByIdWorks(){
        Schools schools = new Schools("Agha Khan","1001","agahaan.website.com","jssk@gmail.com","07219277282");
        schoolsDao.add(schools);
        Students students = new Students("Andiwo","20019","andiwo.com");
        studentsDao.add(students);

        studentsDao.addStudentsToSchool(students, schools);

        assertTrue(schoolsDao.getAllStudentsBySchool(schools.getId()).contains(students));
    }
}