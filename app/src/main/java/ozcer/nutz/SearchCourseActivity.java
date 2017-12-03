package ozcer.nutz;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

import javax.net.ssl.HttpsURLConnection;

public class SearchCourseActivity extends AppCompatActivity {

    String apiKey = "&key=aCmmLsCQbeovDkMfOtcUbzkLxcYvChMm";
    String apiBase = "https://cobalt.qas.im/api/1.0/courses/filter?q=code:\"";
    ArrayList<String> searchResult;
    ArrayList<String> searchResultCourseId;
    ArrayAdapter<String> myAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_course);
        searchResult = new ArrayList<>();

        searchResultCourseId = new ArrayList<>();

        EditText searchFieldEdt = (EditText) findViewById(R.id.searchFieldEdt);

        searchFieldEdt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView theTv, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String searchTerm = theTv.getText().toString();
                    new SearchByCourseCodeTask().execute(searchTerm);
                }
                return false;
            }
        });


        ListView searchResultLv = (ListView)findViewById(R.id.searchResultLv);
        myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, searchResult);
        searchResultLv.setAdapter(myAdapter);
        searchResultLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
                Intent i = new Intent(SearchCourseActivity.this, CourseDetailActivity.class);
                String courseCode = searchResultCourseId.get(index);
                i.putExtra("COURSE_ID", courseCode);
                startActivity(i);
            }
        });

    }

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

            searchResult.clear();
            searchResultCourseId.clear();
            for(int i=0;i<jsonArray.length();i++) {
                try {
                    JSONObject course = jsonArray.getJSONObject(i);
                    String courseId = course.getString("id");
                    String name = course.getString("name");
                    searchResult.add(courseId+"\n\t"+name);
                    searchResultCourseId.add(courseId);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            ((BaseAdapter) myAdapter).notifyDataSetChanged();
        }
    }
}
