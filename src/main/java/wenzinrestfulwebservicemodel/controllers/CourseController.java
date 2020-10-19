package wenzinrestfulwebservicemodel.controllers;

import org.springframework.web.bind.annotation.*;
import wenzinrestfulwebservicemodel.beans.Course;
import wenzinrestfulwebservicemodel.beans.CourseRegistration;
import wenzinrestfulwebservicemodel.beans.CourseRegistrationReply;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CourseController {

    @RequestMapping(method = RequestMethod.GET, value = "/course/allcourses")
    public List<Course> getAllCourses() {
        return new ArrayList<>(CourseRegistration.getInstance().getCourseRecords().values());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/course/{courseId}")
    public Course getCoursebyId(@PathVariable("courseId") String courseId) {
        return CourseRegistration.getInstance().getCourseById(courseId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/register/course")
    public CourseRegistrationReply registerCourse(@RequestBody Course course) {

        CourseRegistrationReply courseregReply = new CourseRegistrationReply();

        CourseRegistration.getInstance().add(course);

        courseregReply.setName(course.getName());
        courseregReply.setDescription(course.getDescription());
        courseregReply.setIdCourse(course.getId());
        courseregReply.setStepsToFinish(course.getStepsToFinishCourse());
        courseregReply.setRegistrationStatus("Successful");

        return courseregReply;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/register/course")
    public String updateCourseRecord(@RequestBody Course course) {
        return CourseRegistration.getInstance().updateCourse(course);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/course/{courseId}")
    public String deleteCourseRecord(@PathVariable("courseId") String courseId) {
        return CourseRegistration.getInstance().deleteCourse(courseId);
    }
}