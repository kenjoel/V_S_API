package Dao;

import models.Educators;
import models.Schools;
import models.Students;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class Sql2oSchoolDao implements SchoolDao {


    private final Sql2o sql2o;

    public Sql2oSchoolDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Schools schools) {
        String sql = "INSERT INTO schools (schoolname, schooladdress, schoolwebsite,  schoolemail, schoolphone) VALUES (:schoolname, :schooladdress, :schoolwebsite, :schoolemail, :schoolphone)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .addParameter("schoolname", schools.getSchoolName())
                    .addParameter("schooladdress", schools.getSchoolAddress())
                    .addParameter("schoolwebsite", schools.getSchoolWebsite())
                    .addParameter("schoolemail", schools.getSchoolEmail())
                    .addParameter("schoolphone",schools.getSchoolPhoneNumber())
                    .executeUpdate()
                    .getKey();
            schools.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Schools> getAll() {
        try (Connection con = sql2o.open()) {
            System.out.println(con.createQuery("SELECT * FROM schools")
                    .executeAndFetch(Schools.class));
            return con.createQuery("SELECT * FROM schools")
                    .executeAndFetch(Schools.class);
        }
    }

    @Override
    public List<Educators> getAllEducatorsBySchool(int id) {
        List<Educators> educators = new ArrayList<>();
        String joinQuery = "SELECT educatorid FROM school_educator WHERE schoolid = :schoolid";
        try (Connection con = sql2o.open()) {
            List<Integer> allIds = con.createQuery(joinQuery)
                    .addParameter("schoolid", id)
                    .executeAndFetch(Integer.class);
            System.out.println("got the ids");
            for (Integer bam : allIds){
                String educatorsid = "SELECT * FROM educators WHERE id = :id";
                educators.add(
                        con.createQuery(educatorsid)
                                .addParameter("id", bam)
                                .executeAndFetchFirst   (Educators.class));
            }
        } catch (Sql2oException xe){
            System.out.println(xe);
        }
        return educators;
    }


    @Override
    public Schools findSchoolById(int schoolid) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM schools WHERE id = :id")
                    .addParameter("id", schoolid)
                    .executeAndFetchFirst(Schools.class);
        }
    }

//    @Override
//    public List<Students> getAllStudentsBySchool(int id) {
//        String sql = "SELECT * FROM school_student WHERE studentid = :studentid";
//        try (Connection con = sql2o.open()) {
//            con.createQuery(sql)
//                    .addParameter("studentid", id)
//                    .executeAndFetchFirst(Students.class);
//        } catch (Sql2oException ex) {
//            System.out.println(ex);
//        }
//
//    }

    @Override
    public List<Students> getAllStudentsBySchool(int schoolid) {
        ArrayList<Students> students = new ArrayList<>();

        String joinQuery = "SELECT studentid FROM school_student WHERE schoolid = :schoolid";

        try (Connection con = sql2o.open()) {
            List<Integer> allusersIds = con.createQuery(joinQuery)
                    .addParameter("schoolid", schoolid)
                    .executeAndFetch(Integer.class);
            for (Integer studentid : allusersIds) {
                String userQuery = "SELECT * FROM students WHERE id = :studentid";
                students.add(
                        con.createQuery(userQuery)
                                .addParameter("studentid", studentid)
                                .executeAndFetchFirst(Students.class));
            }
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
        return students;
    }

//    @Override
//    public void update(int id, String name, String about, String website, String email) {
//        String sql = "UPDATE departments SET (id,name, about, website, email)=(:id, :name, :about, :website, :email) WHERE id=:id";
//        try (Connection con = sql2o.open()) {
//            con.createQuery(sql)
//                    .addParameter("name", name)
//                    .addParameter("about", about)
//                    .addParameter("website", website)
//                    .addParameter("email", email)
//                    .executeUpdate();
//        }
//    }


    @Override
    public void deleteById(int id) {
        String sql = "DELETE from schools WHERE id=:id";
        String deleteJoin = "DELETE from school_student WHERE schoolid = :schoolid";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
            con.createQuery(deleteJoin)
                    .addParameter("schoolid", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void clearAll() {
        String sql = "DELETE from schools";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql).executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
}
