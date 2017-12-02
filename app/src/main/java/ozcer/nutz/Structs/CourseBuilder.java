package ozcer.nutz.Structs;

import java.util.ArrayList;

public class CourseBuilder {
  private String courseCode;
  private String name;
  private ArrayList<String> prereqs; // just a course code

  public CourseBuilder() {
  }

  public CourseBuilder setCourseCode(String courseCode){
    this.courseCode = courseCode;
    return this;
  }

  public CourseBuilder setName(String name){
    this.name = name;
    return this;
  }

  public CourseBuilder setPrereqs(ArrayList<String> prereqs){
    this.prereqs = prereqs;
    return this;
  }

  public Course createCourse() {
    return new Course(courseCode, name, prereqs);
  }

}
