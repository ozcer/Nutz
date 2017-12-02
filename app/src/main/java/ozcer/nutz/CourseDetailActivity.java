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
            String apiKey = getResources().getString(R.string.apiKey);
            String apiUrl = "https://cobalt.qas.im/api/1.0/courses/";
            URL url = new URL(apiUrl + "filter?q=code:\"" + courseCode);

            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();

        } catch(Exception e){
            Log.i("exception", e.toString());
        }
    }
}
