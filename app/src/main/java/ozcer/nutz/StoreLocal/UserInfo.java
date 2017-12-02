package ozcer.nutz.StoreLocal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ozcer.nutz.Structs.Course;

public class UserInfo implements Serializable {

  private static final long serialVersionUID = 8086L;

  private List<Course> takenCourses = new ArrayList<Course>();

  protected UserInfo() {
    super();
  }

  public boolean addCourse(Course course) {
    boolean success = this.takenCourses.add(course);
    return success;
  }

  public boolean removeCourse(Course course) {
    return this.takenCourses.remove(course);
  }
}
