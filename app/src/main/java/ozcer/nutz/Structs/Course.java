package ozcer.nutz.Structs;

import java.util.ArrayList;

public class Course {
  private String courseCode;
  private String name;
  private ArrayList<String> prereqs; // just a course code

  public Course(String courseCode, String name, String term, ArrayList<String> prereqs){
    this.courseCode = courseCode;
    this.name = name;
    this.prereqs = prereqs;
  }

  public ArrayList<String> getPrereqs() {
    return prereqs;
  }

  public void setPrereqs(ArrayList<String> prereqs) {
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
