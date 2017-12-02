package ozcer.nutz.StoreLocal;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import ozcer.nutz.Structs.Course;

public class UserSettings {

  private List<Course> takenCourses = new ArrayList<Course>();
  public boolean addCourse(Course course) {
    boolean success = this.takenCourses.add(course);
    return success;
  }

  public void save_user_data() {
    JSONObject jsonObject = new JSONObject();
    jsonObject.
  }
}
