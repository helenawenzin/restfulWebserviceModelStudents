package wenzinrestfulwebservicemodel.beans;

import java.util.ArrayList;
import java.util.List;

public class CourseRegistration {

    private List<Course> courseRecords;

    private static CourseRegistration coursereg = null;

    private CourseRegistration() {
        courseRecords = new ArrayList<Course>();
    }

    public static CourseRegistration getInstance() {

        if (coursereg == null) {
            coursereg = new CourseRegistration();
            return coursereg;
        } else {
            return coursereg;
        }
    }

    public List<Course> getCourseRecords() {
        return courseRecords;
    }

    public void  add(Course course) {courseRecords.add(course);}

    public String updateCourse(Course course) {
        for (int i = 0; i < courseRecords.size(); i++) {
            Course course1 = courseRecords.get(i);
            if (course1.getId().equals(course.getId())) {
                courseRecords.set(i, course);
                return "Update Successful";
            }
        }
        return "Update un-succesful";
    }

    public String deleteCourse(String courseId) {

        for (int i = 0; i < courseRecords.size(); i++) {
            Course course = courseRecords.get(i);
            if (course.getId().equals(courseId)) {
                courseRecords.remove(i);
                return "Delete successful";
            }
        }
        return "Update un-successful";
    }
}
