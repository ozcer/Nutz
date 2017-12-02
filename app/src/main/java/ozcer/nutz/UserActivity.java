package ozcer.nutz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ozcer.nutz.StoreLocal.UserInfo;
import ozcer.nutz.StoreLocal.UserInfoSingleton;
import ozcer.nutz.Structs.Course;
import ozcer.nutz.Structs.CourseBuilder;

public class UserActivity extends AppCompatActivity {

  private UserInfo userInfo = UserInfoSingleton.getInstance();
  private ArrayAdapter<Course> myAdapter;
  private final List<Course> takenCourses = userInfo.takenCourses;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_user);

    UserInfoSingleton.getInstance();
    try {
      UserInfoSingleton.save_user_data(getApplicationContext(), null);
    } catch (IOException e) {
      String name = e.getMessage();
      Toast.makeText(this, name, Toast.LENGTH_LONG).show();
    }

    ListView takenCourseView = (ListView)findViewById(R.id.taken_courses);
    myAdapter = new ArrayAdapter<Course>(this, android.R.layout.simple_list_item_1, this.takenCourses);
    takenCourseView.setAdapter(myAdapter);
    takenCourseView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
        Intent i = new Intent(UserActivity.this, CourseDetailActivity.class);
        String courseId= takenCourses.get(index).getCourseId();
        i.putExtra("COURSE_ID", courseId);
        startActivity(i);
      }
    });
  }
  @Override
  public void onBackPressed(){
    super.onBackPressed();
    finish();
  }
}
