import Dao.Sql2oEducatorsDao;
import Dao.Sql2oSchoolDao;
import Dao.Sql2oStudentsDao;
import com.google.gson.Gson;
import exceptions.ApiExceptions;
import models.Educators;
import models.Schools;
import models.Students;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;


public class App {
    public static void main(String[] args) {
        Connection conn;
        Gson gson = new Gson();
        Sql2oStudentsDao studentsDao;
        Sql2oSchoolDao schoolDao;
        Sql2oEducatorsDao educatorsDao;

        String connectionString = "jdbc:postgresql://localhost:5432/education";
        Sql2o sql2o = new Sql2o(connectionString, "moringa", "Georgedatabase1");

        studentsDao = new Sql2oStudentsDao(sql2o);
        schoolDao = new Sql2oSchoolDao(sql2o);
        educatorsDao = new Sql2oEducatorsDao(sql2o);
        conn = sql2o.open();

        get("/schools", "application/json", (request, response) -> {
            return gson.toJson(schoolDao.getAll());
        });


        get("/students", "application/json", (request, response) -> {
            return gson.toJson(studentsDao.getAll());
        });


        get("/educators/:id", "application/json", (request, response) -> { //accept a request in format JSON from an app
            int educatorsId = Integer.parseInt(request.params("id"));

            if (educatorsDao.getEducatorsById(educatorsId) == null){
                throw new ApiExceptions(404, String.format("No Educators found: \"%s\" exists", request.params("id")));
            }
            return gson.toJson(educatorsDao.getEducatorsById(educatorsId));
        });

        get("/schools/:id", "application/json", (request, response) -> { //accept a request in format JSON from an app
            int schoolsId = Integer.parseInt(request.params("id"));

            if (schoolDao.findSchoolById(schoolsId) == null){
                throw new ApiExceptions(404, String.format("No Schools found: \"%s\" exists", request.params("id")));
            }
            return gson.toJson(schoolDao.findSchoolById(schoolsId));
        });

        get("/students/:id", "application/json", (request, response) -> { //accept a request in format JSON from an app
            int studentsId = Integer.parseInt(request.params("id"));

            if (studentsDao.findById(studentsId) == null){
                throw new ApiExceptions(404, String.format("No Student with the id: \"%s\" exists", request.params("id")));
            }
            return gson.toJson(studentsDao.findById(studentsId));
        });

        get("/schools/:id/students", "application/json", (request, response) -> {
            int schoolsId = Integer.parseInt(request.params("id"));
            Schools schoolToFind = schoolDao.findSchoolById(schoolsId);
            if (schoolToFind == null){
                throw new ApiExceptions(404, String.format("No school with the id: \"%s\" exists", request.params("id")));
            }
            else if (schoolDao.getAllStudentsBySchool(schoolsId).size()==0){
                return "{\"message\":\"I'm sorry, but no students are listed for this school.\"}";
            }
            else {
                return gson.toJson(schoolDao.getAllStudentsBySchool(schoolsId));
            }
        });



        //Start of post methods

        post("/schools/:schoolsId/students/:studentsId", "application/json", (request, response) -> {

            int schoolsId = Integer.parseInt(request.params("schoolsId"));
            int studentsId = Integer.parseInt(request.params("studentsId"));
            Schools schools = schoolDao.findSchoolById(schoolsId);
            Students students = studentsDao.findById(studentsId);


            if (schools != null && students != null){
                //both exist and can be associated
                studentsDao.addStudentsToSchool(students, schools);
                response.status(201);
                return gson.toJson(String.format("School '%s' and Student '%s' are related",schools.getSchoolName(), students.getStudentname()));
            }
            else {
                throw new ApiExceptions(404, String.format("School or Student does not exist"));
            }
        });

        post("/schools/new", "application/json", (request, response)->{
            Schools schools = gson.fromJson(request.body(), Schools.class);
            schoolDao.add(schools);
            response.status(201);
            return gson.toJson(schools);
        });

        post("/students/new", "application/json", (request, response) -> {
            Students students = gson.fromJson(request.body(), Students.class);
            studentsDao.add(students);
            response.status(201);
            return gson.toJson(students);
        });

        post("/educators/new", "application/json", (request, response) -> {
            Educators educators = gson.fromJson(request.body(), Educators.class);
            educatorsDao.add(educators);
            response.status(201);
            return gson.toJson(educators);
        });







//Filters

        exception(ApiExceptions.class, (exc, req, res) -> {
            ApiExceptions err = exc;
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("status", err.getStatusCode());
            jsonMap.put("errorMessage", err.getMessage());
            res.type("application/json"); //after does not run in case of an exception.
            res.status(err.getStatusCode()); //set the status
            res.body(gson.toJson(jsonMap));  //set the output.
        });
        after((request, response) ->{
            response.type("application/json");
        });


    }
}
