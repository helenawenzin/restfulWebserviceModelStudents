package wenzinrestfulwebservicemodel.beans;

import java.util.List;

public class Course {

    String id;
    String name;
    String description;
    List<String> stepsToFinishCourse;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public List<String> getStepsToFinishCourse() {
        return stepsToFinishCourse;
    }

    public void setStepsToFinishCourse(List<String> stepsToFinishCourse) {
        this.stepsToFinishCourse = stepsToFinishCourse;
    }
}