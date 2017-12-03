package ozcer.nutz.Structs;

import java.io.Serializable;
import java.util.List;

public class Course implements Serializable {

  private static final long serialVersionUID = 8087L;

  private String courseCode;
  private String courseName;
  private String courseId;
  private List<Course> prereqs;
  private int term;

  public List<Course> getPrereqs() {
    return prereqs;
  }

  public void setCourseId(String courseId) {
    this.courseId = courseId;
  }

  public String getCourseId() {
    return this.courseId;
  }

  public int getTerm() {
    return this.term;
  }

  public void setTerm(Integer term) {
    this.term = term;
  }

  public void setPrereqs(List<Course> prereqs) {
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
