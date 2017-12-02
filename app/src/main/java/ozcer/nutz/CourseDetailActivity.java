package ozcer.nutz;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
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
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;

import ozcer.nutz.Structs.Course;

public class CourseDetailActivity extends AppCompatActivity {
    List<Integer> availableTermsList = new ArrayList<>();
    String apiKey = "key=aCmmLsCQbeovDkMfOtcUbzkLxcYvChMm";
    String apiBase = "https://cobalt.qas.im/api/1.0/courses/";
    String apiBase2 = "https://cobalt.qas.im/api/1.0/courses/filter?q=code:\"";
    String courseCode="";
    String actualCourseCode="";
    String urlFindByCourseId="";
    String urlFindByCourseCode="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);
        String courseCode = getIntent().getStringExtra("COURSE_ID");
        String actualCourseCode = courseCode.substring(0, 8);
        urlFindByCourseId = apiBase + courseCode +"/?" + apiKey;
        urlFindByCourseCode = apiBase2 + actualCourseCode + "&" + apiKey;
        TextView title = (TextView) findViewById(R.id.courseDetailCode);
        title.setText(courseCode);
        HttpsURLConnection connection = null;
        BufferedReader reader = null;

        try {
            new SearchByCourseCodeTask().execute(urlFindByCourseId);

        } catch (Exception e) {
            Log.i("Exception", e.toString());
        }
    }
    public class SearchByCourseCodeTask extends AsyncTask<String, Void, JSONObject> {

        @Override
        protected JSONObject doInBackground(String... params) {
            HttpsURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(
                        String.format(urlFindByCourseId));
                Log.i("url", url.toString());
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
                JSONObject jsonObject= new JSONObject(finalJson);

                return jsonObject;

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
        protected void onPostExecute(JSONObject JsonCourse) {
            super.onPostExecute(JsonCourse);
                try {
                    Log.i("Kappa","llll");
                    String courseName = JsonCourse.getString("name");
                    String courseTerm = JsonCourse.getString("term");
                    String coursePrereq = JsonCourse.getString("prerequisites");
                    TextView nameView = findViewById(R.id.courseDetailName);
                    nameView.setText(courseName);
                    TextView termView = findViewById(R.id.courseDetailTerm);
                    termView.setText(courseTerm);
                    TextView prereqView = findViewById(R.id.courseDetailPrereq);
                    Pattern p = Pattern.compile("[A-Z]{3}[A-Z_0-9][0-9]{2}");
                    Matcher matcher = p.matcher(coursePrereq);
                    List prereqList = new ArrayList();
                    while(matcher.find()){
                        prereqList.add(matcher.group().toString());
                    }
                    prereqView.setText(prereqList.toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }

        }
    }
    public class SearchByActualCourseCodeTask extends AsyncTask<String, Void, JSONArray> {

        @Override
        protected JSONArray doInBackground(String... params) {
            HttpsURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(
                        String.format(urlFindByCourseId));
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


            for(int i=0;i<jsonArray.length();i++) {
                try {
                    JSONObject course = jsonArray.getJSONObject(i);
                    String courseId = course.getString("id");
                    String name = course.getString("name");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
