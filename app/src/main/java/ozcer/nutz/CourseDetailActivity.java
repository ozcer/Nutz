package ozcer.nutz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import ozcer.nutz.Structs.Course;

public class CourseDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);
        String courseCode = getIntent().getStringExtra("COURSE_CODE");
        TextView title = (TextView) findViewById(R.id.courseDetailCode);
        title.setText(courseCode);
        HttpsURLConnection connection = null;
        BufferedReader reader = null;

        try {
            String apiKey = getResources().getString(R.string.apiKey);
            String apiUrl = "https://cobalt.qas.im/api/1.0/courses/";
            URL url = new URL(apiUrl + "filter?q=code:\"" + courseCode + "\"&key=\"" + apiKey + "\"");
            connection = (HttpsURLConnection) url.openConnection();
            connection.connect();

            InputStream stream = connection.getInputStream();

            reader = new BufferedReader(new InputStreamReader(stream));

            StringBuffer buffer = new StringBuffer();

            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

            String finalJson = buffer.toString();
            JSONArray jsonArray = new JSONArray(finalJson);
            JSONObject JsonCourse = jsonArray.getJSONObject(0);
        } catch (Exception e) {
            Log.i("Exception", e.toString());
        }
    }
}
