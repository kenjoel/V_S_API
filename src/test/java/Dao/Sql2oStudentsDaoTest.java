package Dao;

import models.Schools;
import models.Students;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oStudentsDaoTest {
    private Connection conn;
    private StudentsDao studentsDao;


    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/newsportal_test"; //connect to postgres test database
        Sql2o sql2o = new Sql2o(connectionString,"moringa",":Georgedatabase1");
        studentsDao = new Sql2oStudentsDao(sql2o);
        conn = sql2o.open();
        studentsDao.clearAll();
    }

    @After
    public void tearDown() throws Exception {
        studentsDao.clearAll();
        conn.close();
    }

    @Test
    public void addingStudentSetsId() throws Exception {
        Students testStudents = setupNewStudents();
        int originalStudentsId = testStudents.getId();
        studentsDao.add(testStudents);
        assertNotEquals(originalStudentsId, testStudents.getId());
    }
    @Test
    public void addingToSchoolAddsCorrectly() {
        Schools testSchools = setupSchool();

        schoolsDao.add(testSchools);

        Students testStudents = setupNewStudents();

        studentsDao.add(testStudents);

        studentsDao.addStudentsToSchool(testStudents, testSchools);

        assertEquals(2, studentsDao.getAllSchoolsForAStudent(testStudents.getId()).size());
    }



    @Test
    public void clearAll() throws Exception {
        Students testStudents = setupNewStudents();
        Students otherStudents = setupNewStudents();
        studentsDao.clearAll();
        assertEquals(0, studentsDao.getAll().size());
    }

    //helper

    public Schools setupSchool() {
        Schools schools = new Schools("Kianda", "10010", "www.kianda.com", "DailyNation@nation.com", "07574537821");
        return schools;
    }


    public Students setupNewStudents(){
        Students students = new Students("Joel", "joel12@gmail.com", "Python");
        studentsDao.add(students);
        return students;
    }
}