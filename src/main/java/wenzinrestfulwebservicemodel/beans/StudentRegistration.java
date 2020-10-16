package wenzinrestfulwebservicemodel.beans;

import java.util.ArrayList;
import java.util.List;

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

    public String deleteStudent(String registrationNumber) {

        for (int i = 0; i < studentRecords.size(); i++) {
            Student student = studentRecords.get(i);
            if (student.getId().equals(registrationNumber)) {
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

}
