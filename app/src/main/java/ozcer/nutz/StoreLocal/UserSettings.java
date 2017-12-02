package ozcer.nutz.StoreLocal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import ozcer.nutz.Structs.Course;

public class UserSettings implements Serializable {

  /**
   * This is the serialVersionUID for the class.
   */
  private static final long serialVersionUID = 8086L;

  public List<Course> takenCourses;

  public UserSettings() {
    this.takenCourses = new ArrayList<>();
  }
}
