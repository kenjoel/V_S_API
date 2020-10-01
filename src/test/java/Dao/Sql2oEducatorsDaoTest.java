package Dao;

import org.junit.After;
import org.junit.Before;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oEducatorsDaoTest {

    private Connection conn;
    private StudentsDao studentsDao;
    private SchoolDao schoolDao;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/education_test"; //connect to postgres test database
        Sql2o sql2o = new Sql2o(connectionString, "moringa", "://postgres");
        studentsDao = new Sql2oStudentsDao(sql2o);
        schoolDao = new Sql2oSchoolDao(sql2o);
        conn = sql2o.open();
        studentsDao.clearAll();
    }

    @After
    public void tearDown() throws Exception {
        studentsDao.clearAll();
        conn.close();
    }
}