package wenzinrestfulwebservicemodel.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class StudentRegistration {

    private List<Student> studentRecords;

    private static StudentRegistration stdreg = null;

    private StudentRegistration() {
        studentRecords = new ArrayList<Student>();
    }

    public static StudentRegistration getInstance() {

        if (stdreg == null) {
            stdreg = new StudentRegistration();
            return stdreg;
        } else {
            return stdreg;
        }
    }

    public void  add(Student student) {studentRecords.add(student);}

    public String updateStudent(Student student) {
        for (int i = 0; i < studentRecords.size(); i++) {
            Student student1 = studentRecords.get(i);
            if (student1.getId().equals(student.getId())) {
                studentRecords.set(i, student);
                return "Update Successful";
            }
        }
        return "Update un-succesful";
    }

    public String deleteStudent(String studentId) {

        for (int i = 0; i < studentRecords.size(); i++) {
            Student student = studentRecords.get(i);
            if (student.getId().equals(studentId)) {
                studentRecords.remove(i);
                return "Delete successful";
            }
        }
        return "Update un-successful";
    }

    public List<Student> getStudentRecords() {
        return studentRecords;
    }

    public Student getStudent(String studentId) {

        Student student = new Student();

        for (int i = 0; i < studentRecords.size(); i++) {
            Student st = studentRecords.get(i);
            if (st.getId().equals(studentId)) {

                student.setName(st.getName());
                student.setId(st.getId());
                student.setDescription(st.getDescription());
                student.setCourses(st.getCourses());
            }
        } return student;
    }

    public List<Course> getAllCoursesForStudent(String studentId) {

        Student student = new Student();

        for (int i = 0; i < studentRecords.size(); i++) {
            Student st = studentRecords.get(i);
            if (st.getId().equals(studentId)) {
                student.setCourses(st.getCourses());
            }
        }

        List<Course> allCourses = CourseRegistration.getInstance().getCourseRecords();

        List<Course> coursesForStudent = allCourses.stream()
                .filter(course -> student.getCourses().contains(course.getId()))
                .collect(Collectors.toList());

        return coursesForStudent;
    }

    public Course getSpecificCourseForStudent(String studentId, String courseId) {

        Student student = new Student();

        for (int i = 0; i < studentRecords.size(); i++) {
            Student st = studentRecords.get(i);
            if (st.getId().equals(studentId)) {
                student.setCourses(st.getCourses());
            }
        }

        if (student.getCourses().contains(courseId)) {
            return CourseRegistration.getInstance().getCourseById(courseId);
        } else {
            throw new IllegalArgumentException("Invalid courseId provided");
        }
    }

    public String addCourseToStudent(String studentId, String courseId) {

        for (int i = 0; i < studentRecords.size(); i++) {
            Student student = studentRecords.get(i);
            if (student.getId().equals(studentId)) {

                Set<String> courses = student.getCourses();
                courses.add(courseId);
                student.setCourses(courses);
                studentRecords.set(i, student);
                return "Update successful";
            }
        }
        return "Update not succesful";
    }

    public String deleteCourseToStudent(String studentId, String courseId) {

        for (int i = 0; i < studentRecords.size(); i++) {
            Student student = studentRecords.get(i);
            if (student.getId().equals(studentId)) {

                Set<String> courses = student.getCourses();
                courses.remove(courseId);
                student.setCourses(courses);
                studentRecords.set(i, student);
                return "Update successful";
            }
        }
        return "Update not succesful";
    }
}
