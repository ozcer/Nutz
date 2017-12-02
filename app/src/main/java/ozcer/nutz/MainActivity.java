package ozcer.nutz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import ozcer.nutz.StoreLocal.UserInfoSingleton;

public class MainActivity extends AppCompatActivity {

  ArrayList<String> searchResult = new ArrayList<>();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    try {
      UserInfoSingleton.load_user_data(null);
    } catch (IOException e) {
      Toast.makeText(this, "Failed to load user info", Toast.LENGTH_LONG).show();
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