package ozcer.nutz.Structs;

import java.util.List;

public class Course {
  private String courseCode;
  private String courseName;
  private List<String> prereqs; // just a course code

  public List<String> getPrereqs() {
    return prereqs;
  }

  public void setPrereqs(List<String> prereqs) {
    this.prereqs = prereqs;
  }

  public String getCourseName() {
    return courseName;
  }

  public void setCourseName(String courseName) {
    this.courseName = courseName;
  }

  public String getCourseCode() {
    return courseCode;
  }

  public void setCourseCode(String courseCode) {
    this.courseCode = courseCode;
  }

  public String toString() {
    return this.courseCode + " " + this.courseName;
  }
}
