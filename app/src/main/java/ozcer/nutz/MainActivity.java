package ozcer.nutz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import ozcer.nutz.StoreLocal.UserInfo;
import ozcer.nutz.StoreLocal.UserInfoSingleton;

public class MainActivity extends AppCompatActivity {

  ArrayList<String> searchResult = new ArrayList<>();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    UserInfoSingleton.getInstance();
    try {
      File file = getApplication().getFileStreamPath("user.ser");
      UserInfoSingleton.load_user_data(getApplicationContext(), file);
    } catch (IOException e) {
      String name = e.getMessage();
      Toast.makeText(this, name, Toast.LENGTH_LONG).show();
    } catch (ClassNotFoundException e) {
      Toast.makeText(this, "ClassNotFoundException", Toast.LENGTH_SHORT).show();
    }

    Button userBtn = (Button) findViewById(R.id.userBtn);

    userBtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
      Intent intent = new Intent(MainActivity.this, UserActivity.class);
      startActivity(intent);
    }
    });
    


    Button searchBtn = (Button) findViewById(R.id.searchCourseBtn);

    searchBtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
      Intent intent = new Intent(MainActivity.this, SearchCourseActivity.class);
      startActivity(intent);
    }
    });
  }
}