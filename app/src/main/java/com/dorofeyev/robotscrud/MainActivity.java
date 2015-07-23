package com.dorofeyev.robotscrud;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    ListView robotsListView;
    RobotsListViewAdapter robotsListViewAdapter;
    Context context = MainActivity.this;
    RobotDAO robotDAO = new RestRobotDAO();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        updateRobots();

        robotsListView = (ListView)findViewById(R.id.listViewRobots);
        robotsListViewAdapter = new RobotsListViewAdapter(context, new ArrayList<Robot>());
        robotsListView.setAdapter(robotsListViewAdapter);
    }

    private void updateRobots() {
        robotDAO.read(new Callback() {
            @Override
            public void execute(Object result) {
                robotsListViewAdapter.robots = (List<Robot>)result;
                robotsListViewAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void buttonEditClicked(View view) {

    }

    public void buttonDeleteClicked(View view) {
        int position = robotsListView.getPositionForView((View)view.getParent());
        Robot robot = (Robot)robotsListView.getItemAtPosition(position);

        robotsListViewAdapter.robots.remove(robot);
        robotsListViewAdapter.notifyDataSetChanged();

        robotDAO.delete(robot);
        updateRobots();
    }



}
