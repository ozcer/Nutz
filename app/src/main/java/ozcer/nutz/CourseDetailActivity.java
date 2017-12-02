package ozcer.nutz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class CourseDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);
        String courseCode = getIntent().getStringExtra("courseCode");
        try {
            URL url = new URL("p");

            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();

        } catch(Exception e){
            Log.i("exception", e.toString());
        }
    }
}
