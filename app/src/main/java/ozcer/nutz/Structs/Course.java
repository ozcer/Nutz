package ozcer.nutz.Structs;

import java.util.ArrayList;
import java.util.List;

public class Course {
  private String courseCode;
  private String name;
  private List<String> prereqs; // just a course code

  public List<String> getPrereqs() {
    return prereqs;
  }

  public void setPrereqs(List<String> prereqs) {
    this.prereqs = prereqs;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCourseCode() {
    return courseCode;
  }

  public void setCourseCode(String courseCode) {
    this.courseCode = courseCode;
  }
}
