package ozcer.nutz.StoreLocal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ozcer.nutz.Structs.Course;

public class UserInfo implements Serializable {

  private static final long serialVersionUID = 8086L;

  public List<Course> takenCourses = new ArrayList<Course>();

  protected UserInfo() {
    super();
  }
}
