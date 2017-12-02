package ozcer.nutz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import ozcer.nutz.Structs.Course;

import java.util.ArrayList;

import ozcer.nutz.StoreLocal.UserInfo;
import ozcer.nutz.StoreLocal.UserInfoSingleton;
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
    /*
    public void pathFinding(Course goal, Integer startTerm) {
        int[] terms = {1,5,9};
        ArrayList<ArrayList> semesters = new ArrayList<>();
        Integer thisTerm = startTerm;

        UserInfo user = UserInfoSingleton.getInstance();
        ArrayList<Course> futureTaken = new ArrayList<>(user.takenCourses);

        String goalName = goal.getCourseCode();

        // all the courses
        ArrayList<Course> leafs = new ArrayList<>();

        // loop until goal is the only leaf left and it's available this term
        while (!(leafs.size() != 1 && leafs.get(0).getCourseCode().equals(goalName) && goal.availableTerms.contains(thisTerm))) {
            ArrayList<Course> termLoad = new ArrayList<>(); // new semester
            // for every course
            for(Course leaf: leafs) {
                // if available this term
                if(leaf.availableTerms.contains(thisTerm)) {
                    // add to current termLoad
                    termLoad.add(leaf);
                    futureTaken.add(leaf);
                    // if all parent prereqs are in future taken, pop self add parent
                    if()
                }
                // add semester planning to semesters
                // go to next term
            }
        }
        // if all reqs are filled
        ArrayList<Course> theTermYouReachGoal = new ArrayList<>();
        theTermYouReachGoal.add(goal);
        semesters.add(theTermYouReachGoal);


    }
    */
}
