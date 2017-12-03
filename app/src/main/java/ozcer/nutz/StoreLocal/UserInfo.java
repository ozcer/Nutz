package ozcer.nutz.StoreLocal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ozcer.nutz.Structs.Course;

public class UserInfo implements Serializable {

  private static final long serialVersionUID = 8086L;

  public List<Course> takenCourses = new ArrayList<Course>();

  public void addCourse(Course course) {
    this.takenCourses.add(course);
  }

  public boolean hasTaken(Course course) {
    String longName = course.getCourseName();
    // truncate  to len6 to remove useless info
    String courseName = longName.substring(0, Math.min(longName.length(), 6));
    for (Course takenCourse: takenCourses) {
      if(takenCourse.getCourseName().contains(courseName)) {
        return true;
      }
    }
    return false;
  }

  protected UserInfo() {
    super();
  }
}
