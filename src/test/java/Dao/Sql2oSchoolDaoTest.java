package Dao;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oSchoolDaoTest {

    private static Sql2oSchoolDao schoolsDao;
    private static Sql2oEducatorsDao educatorsDao;
    private static Connection conn;

    @BeforeClass
    public static void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/education_test"; //connect to postgres test database
        Sql2o sql2o = new Sql2o(connectionString, "moringa", "://postgress");

//        String connectionString = "jdbc:postgresql://ec2-54-90-68-208.compute-1.amazonaws.com:5432/d3prpp5l3ugu23 "; //!
//        Sql2o sql2o = new Sql2o(connectionString, "fzcpvwmacnmgzn", "f8b6da8439fce09b61063f1c4856d2b191217799d97e4c506bddf5f7fc97f401");
        schoolsDao = new Sql2oSchoolDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("clearing databse");
        schoolsDao.clearAll();
//        conn.close();
    }
}