package wenzinrestfulwebservicemodel;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.support.discovery.SelectorResolver;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import wenzinrestfulwebservicemodel.beans.Student;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class ApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void registerStudent() {
        String body = "{\"id\":\"456\",\"name\":\"Birk\",\"description\":\"redhead\",\"courses\":[\"2222\",\"6666\"]}";
        createStudent(body);

        RestAssured.given().contentType("application/json")
                .get("http://localhost:8083/student/456")
                .then()
                .log().all()
                .statusCode(200)
                .body(Matchers.equalTo(body));
    }

    @Test
    public void getAllStudents() {
        String body = "{\"id\":\"456\",\"name\":\"Birk\",\"description\":\"redhead\",\"courses\":[\"2222\",\"6666\"]}";
        createStudent(body);
        String body2 = "{\"id\":\"123\",\"name\":\"Ronja\",\"description\":\"brave\",\"courses\":[\"2222\",\"6666\"]}";
        createStudent(body2);

        String joinedBody = "[{\"id\":\"123\",\"name\":\"Ronja\",\"description\":\"brave\",\"courses\":[\"2222\",\"6666\"]}," +
                "{\"id\":\"456\",\"name\":\"Birk\",\"description\":\"redhead\",\"courses\":[\"2222\",\"6666\"]}]";

        RestAssured.given().contentType("application/json")
                .get("http://localhost:8083/student/allstudents")
                .then()
                .log().all()
                .statusCode(200)
                .body(Matchers.equalTo(joinedBody));
    }

    @Test
    public void deleteStudent() {
        String body = "{\"id\":\"456\",\"name\":\"Birk\",\"description\":\"redhead\",\"courses\":[\"2222\",\"6666\"]}";
        createStudent(body);

        RestAssured.given().contentType("application/json")
                .delete("http://localhost:8083/delete/student/456")
                .then()
                .log().all()
                .statusCode(200)
                .body(Matchers.equalTo("Delete successful"));
    }

    private void createStudent(String body) {
        RestAssured.given().contentType("application/json")
                .body(body)
                .post("http://localhost:8083/register/student")
                .then()
                .statusCode(200);
    }
}