package ozcer.nutz.Structs;

import java.util.List;

public class CourseBuilder {
  private Course course;

  public CourseBuilder setCourseCode(String courseCode) {
    this.course.setCourseCode(courseCode);
    return this;
  }

  public CourseBuilder setName(String name) {
    this.course.setName(name);
    return this;
  }

  public CourseBuilder setPrereqs(List<String> prereqs){
    this.course.setPrereqs(prereqs);
    return this;
  }

  public Course build() {
    return this.course;
  }

}
