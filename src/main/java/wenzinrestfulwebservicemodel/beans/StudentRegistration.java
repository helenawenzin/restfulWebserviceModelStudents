package wenzinrestfulwebservicemodel.beans;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StudentRegistration {

    private Map<String, Student> studentRecords;

    private static StudentRegistration stdreg = null;

    private StudentRegistration() {
        studentRecords = new HashMap<>();
    }

    public static StudentRegistration getInstance() {

        if (stdreg == null) {
            stdreg = new StudentRegistration();
            return stdreg;
        } else {
            return stdreg;
        }
    }

    public void add(Student student) {
        studentRecords.put(student.getId(), student);
    }

    public String updateStudent(Student student) {
        studentRecords.put(student.getId(), student);
        return "Update successful";
    }

    public String deleteStudent(String studentId) {
        studentRecords.remove(studentId);
        return "Delete successful";
    }

    public List<Student> getStudentRecords() {
        return new ArrayList<>(studentRecords.values());
    }

    public Student getStudent(String studentId) {
        return studentRecords.get(studentId);
    }

    public List<Course> getAllCoursesForStudent(String studentId) {

        Student student = studentRecords.get(studentId);

        Map<String, Course> allCourses = CourseRegistration.getInstance().getCourseRecords();

        List<Course> coursesForStudent = allCourses.values()
                .stream()
                .filter(course -> student.getCourses().contains(course.getId()))
                .collect(Collectors.toList());

        return coursesForStudent;
    }

    public Course getSpecificCourseForStudent(String studentId, String courseId) {

        Student student = studentRecords.get(studentId);

        if (student.getCourses().contains(courseId)) {
            return CourseRegistration.getInstance().getCourseById(courseId);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Course not found");
        }
    }

    public String addCourseToStudent(String studentId, String courseId) {
        Course course = CourseRegistration.getInstance().getCourseRecords().get(courseId);

        if (course == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Course not found");
        }
        Student student = studentRecords.get(studentId);
        student.getCourses().add(courseId);
        return "Update successful";
    }

    public String deleteCourseToStudent(String studentId, String courseId) {
        Student student = studentRecords.get(studentId);

        if (student.getCourses().contains(courseId)) {
            student.getCourses().remove(courseId);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Course not found");
        }
        return "Update successful";
    }
}
