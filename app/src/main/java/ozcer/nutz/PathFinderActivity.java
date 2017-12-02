package ozcer.nutz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import ozcer.nutz.Structs.Course;

public class PathFinderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path_finder);
    }

    protected ArrayList<Course> getLeafCourses(Course rootCourse) {
        ArrayList<Course> leaves = new ArrayList<>();
        leaves.add(rootCourse);
        int i = 0;
        while (!leaves.isEmpty() && i < leaves.size()) {
            Course course = leaves.get(i);
            List<Course> prereqCourses = course.getPrereqs();
            if (prereqCourses != null && !prereqCourses.isEmpty()) {
                leaves.remove(course);
                for (Course ccourse : prereqCourses) {
                    if (!prereqCourses.contains(ccourse)) {
                        prereqCourses.add(ccourse);
                    }
                }
            } else {
                i++;
            }
        }
        return leaves;
    }
}
