package wenzinrestfulwebservicemodel.beans;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

public class CourseRegistration {

    private Map<String, Course> courseRecords;

    private static CourseRegistration courseReg = null;

    private CourseRegistration() {
        courseRecords = new HashMap<>();
    }

    public static CourseRegistration getInstance() {

        if (courseReg == null) {
            courseReg = new CourseRegistration();
        }
        return courseReg;
    }

    public Map<String, Course> getCourseRecords() {
        return courseRecords;
    }

    public Course getCourseById(String courseId) {
        Course course = courseRecords.get(courseId);
        if (course == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Course not found");
        }
        return course;
    }

    public void add(Course course) {
        courseRecords.put(course.getId(), course);
    }

    public String updateCourse(Course course) {
        add(course);
        return "Update succesful";
    }

    public String deleteCourse(String courseId) {
        courseRecords.remove(courseId);
        return "Delete successful";
    }
}
