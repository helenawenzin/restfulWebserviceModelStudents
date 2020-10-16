package wenzinrestfulwebservicemodel.beans;

import java.util.List;

public class CourseRegistrationReply {

    String name;
    String description;
    String idCourse;
    List<String> stepsToFinish;
    String registrationStatus;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(String idCourse) {
        this.idCourse = idCourse;
    }

    public List<String> getStepsToFinish() {
        return stepsToFinish;
    }

    public void setStepsToFinish(List<String> stepsToFinish) {
        this.stepsToFinish = stepsToFinish;
    }

    public String getRegistrationStatus() {
        return registrationStatus;
    }

    public void setRegistrationStatus(String registrationStatus) {
        this.registrationStatus = registrationStatus;
    }
}
