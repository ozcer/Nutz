package ozcer.nutz.Structs;

import java.io.Serializable;
import java.util.List;

public class CourseBuilder {
  private String courseCode;
  private String courseId;
  private String courseName;
  private int term;
  private List<Course> prereqs;

  public CourseBuilder setCourseCode(String courseCode) {
    this.courseCode = courseCode;
    return this;
  }

  public CourseBuilder setCourseName(String name) {
    this.courseName = name;
    return this;
  }

  public CourseBuilder setPrereqs(List<Course> prereqs){
    this.prereqs = prereqs;
    return this;
  }

  public CourseBuilder setCourseId(String courseId) {
    this.courseId = courseId;
    return this;
  }

  public CourseBuilder setTerm(int term) {
    this.term = term;
    return this;
  }

  public Course build() {
    Course course = new Course();
    course.setCourseCode(this.courseCode);
    course.setCourseName(this.courseName);
    course.setPrereqs(this.prereqs);
    course.setCourseId(this.courseId);
    return course;
  }

}
