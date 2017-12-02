package ozcer.nutz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

  ArrayList<String> searchResult = new ArrayList<>();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Button userBtn = (Button) findViewById(R.id.userBtn);
    userBtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
      Intent intent = new Intent(MainActivity.this, UserInfo.class);
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
