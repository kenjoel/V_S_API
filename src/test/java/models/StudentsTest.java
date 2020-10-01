package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StudentsTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void studentInstantiatesCorrectly() {
        Students testStudents = new Students("George", "gokumu122.com", "java");
        assertEquals(true, testStudents instanceof Students);
    }
}