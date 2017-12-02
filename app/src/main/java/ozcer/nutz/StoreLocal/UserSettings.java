package ozcer.nutz.StoreLocal;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import ozcer.nutz.Structs.Course;

public class UserSettings {

  final private String userSettingsDefaultPath = "user.json";
  final private String jsonTakenCourses = "takenCourses";

  private List<Course> takenCourses = new ArrayList<Course>();
  private JSONObject userJson;

  public UserSettings() {
    userJson = new JSONObject();
  }

  public boolean addCourse(Course course) {
    boolean success = this.takenCourses.add(course);
    return success;
  }

  public void load_user_data(String filePath) throws FileNotFoundException, IOException, JSONException {
    if (filePath == null) {
      filePath = this.userSettingsDefaultPath;
    }
    String fileAsString;

    InputStream is = new FileInputStream(filePath);
    BufferedReader buf = new BufferedReader(new InputStreamReader(is));
    String line = buf.readLine();
    StringBuilder sb = new StringBuilder();

    while (line != null) {
      sb.append(line).append("\n");
      line = buf.readLine();
    }
    fileAsString = sb.toString();
    userJson = new JSONObject(fileAsString);
    userJson.getJSONArray(jsonTakenCourses);
  }

  public void save_user_data(String filePath) throws FileNotFoundException, IOException {
    if (filePath == null) {
      filePath = this.userSettingsDefaultPath;
    }
    FileOutputStream file;
    file = new FileOutputStream(filePath);
    byte[] strToBytes = userJson.toString().getBytes();
    file.write(strToBytes);
  }
}
