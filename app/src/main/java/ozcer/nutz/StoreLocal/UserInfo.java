package ozcer.nutz.StoreLocal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ozcer.nutz.Structs.Course;

public class UserInfo implements Serializable {

  private static final long serialVersionUID = 8086L;

  public List<Course> takenCourses = new ArrayList<Course>();

  public void addCourse(Course course) {
    boolean added = false;
    for (Course courseInList : this.takenCourses) {
      if (course.getCourseId().equalsIgnoreCase(courseInList.getCourseId())) {
        added = true;
      }
    }

    if (!added) {
      takenCourses.add(course);
    }
  }

  protected UserInfo() {
    super();
  }
}
