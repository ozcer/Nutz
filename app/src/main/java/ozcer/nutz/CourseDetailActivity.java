package ozcer.nutz;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import ozcer.nutz.Structs.Course;

public class CourseDetailActivity extends AppCompatActivity {
    String apiKey = "&key=aCmmLsCQbeovDkMfOtcUbzkLxcYvChMm";
    String apiBase = "https://cobalt.qas.im/api/1.0/courses/filter?q=code:\"";
    String courseCode="";
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
            new SearchByCourseCodeTask().execute(courseCode);

        } catch (Exception e) {
            Log.i("Exception", e.toString());
        }
    }
    String apiUrl = "https://cobalt.qas.im/api/1.0/courses/filter?q=code:\"" + courseCode + "\"&key=" + apiKey;
    public class SearchByCourseCodeTask extends AsyncTask<String, Void, JSONArray> {

        @Override
        protected JSONArray doInBackground(String... params) {
            HttpsURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(
                        String.format(apiBase+params[0]+"\""+apiKey));
                connection  = (HttpsURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();

                String line = "";
                while((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                String finalJson = buffer.toString();
                Log.i("oscar", finalJson);
                JSONArray jsonArray = new JSONArray(finalJson);

                return jsonArray;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if(connection != null) {
                    connection.disconnect();
                }
                try {
                    if(reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(JSONArray jsonArray) {
            super.onPostExecute(jsonArray);
                try {
                     JSONObject JsonCourse = jsonArray.getJSONObject(0);
                    String courseName = JsonCourse.getString("name");
                    String courseTerm = JsonCourse.getString("term");
                    String coursePrereq = JsonCourse.getString("prerequisites");
                    TextView nameView = findViewById(R.id.courseDetailName);
                    nameView.setText(courseName);
                    TextView termView = findViewById(R.id.courseDetailTerm);
                    termView.setText(courseTerm);
                    TextView prereqView = findViewById(R.id.courseDetailPrereq);
                    prereqView.setText(coursePrereq);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

        }
    }
}
