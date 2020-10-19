package wenzinrestfulwebservicemodel.controllers;

import wenzinrestfulwebservicemodel.beans.Course;
import wenzinrestfulwebservicemodel.beans.Student;
import wenzinrestfulwebservicemodel.beans.StudentRegistration;
import wenzinrestfulwebservicemodel.beans.StudentRegistrationReply;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @RequestMapping(method = RequestMethod.GET, value = "/student/allstudents")
    public List<Student> getStudents() {
        return StudentRegistration.getInstance().getStudentRecords();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/student/{studentId}")
    public Student getStudentById(@PathVariable("studentId") String studentId) {
        return StudentRegistration.getInstance().getStudent(studentId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/courses/student/{studentId}")
    public List<Course> getAllCoursesForStudentById(@PathVariable("studentId") String studentId) {
        return StudentRegistration.getInstance().getAllCoursesForStudent(studentId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/course/{courseId}/student/{studentId}")
    public Course getSpecificCourseForStudentById(@PathVariable("studentId") String studentId, @PathVariable("courseId") String courseId) {
        return StudentRegistration.getInstance().getSpecificCourseForStudent(studentId, courseId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/register/student")
    public StudentRegistrationReply registerStudent(@RequestBody Student student) {

        StudentRegistrationReply stdregReply = new StudentRegistrationReply();

        StudentRegistration.getInstance().add(student);

        stdregReply.setName(student.getName());
        stdregReply.setDescription(student.getDescription());
        stdregReply.setIdStudent(student.getId());
        stdregReply.setCourses(student.getCourses());
        stdregReply.setRegistrationStatus("Successful");

        return stdregReply;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/register/course/{courseId}/student/{studentId}")
    public String addCourseToStudent(@PathVariable("studentId") String studentId,
                                                       @PathVariable("courseId") String courseId) {

        //StudentRegistrationReply stdregReply = new StudentRegistrationReply();

        return StudentRegistration.getInstance().addCourseToStudent(studentId, courseId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/register/student")
    public String updateStudentRecord(@RequestBody Student student) {
        return StudentRegistration.getInstance().updateStudent(student);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/student/{regdNum}")
    public String deleteStudentRecord(@PathVariable("regdNum") String regdNum) {
        return StudentRegistration.getInstance().deleteStudent(regdNum);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/course/{courseId}/student/{studentId}")
    public String deleteCourseToStudent(@PathVariable("studentId") String studentId,
                                     @PathVariable("courseId") String courseId) {
        return StudentRegistration.getInstance().deleteCourseToStudent(studentId, courseId);
    }
}