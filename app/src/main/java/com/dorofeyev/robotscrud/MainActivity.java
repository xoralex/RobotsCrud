package com.dorofeyev.robotscrud;

import android.content.Context;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;


import com.dorofeyev.robotscrud.model.*;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    ListView robotsListView;
    RobotsListViewAdapter robotsListViewAdapter;
    Context context = MainActivity.this;
    RobotDAO robotDAO = new RestRobotDAO(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // заполняем список данными
        robotsListView = (ListView)findViewById(R.id.listViewRobots);
        robotsListViewAdapter = new RobotsListViewAdapter(context, new ArrayList<Robot>());
        robotsListView.setAdapter(robotsListViewAdapter);

        updateRobots();
    }

    /**
     * Получаем список роботов с сервера и обновляем список на экране.
     */
    private void updateRobots() {
        robotDAO.read(new Callback() {
            @Override
            public void execute(Object result) {
                robotsListViewAdapter.robots = (List<Robot>) result;
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

        if (id == R.id.action_add) {
            menuAddClicked();
        }
        else if (id == R.id.action_refresh) {
            updateRobots();
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * При нажатии на кнопку Delete удаляем выбранного робота.
     * @param view
     */
    public void buttonDeleteClicked(View view) {
        // берем выбранного робота
        int position = robotsListView.getPositionForView((View)view.getParent());
        Robot robot = (Robot)robotsListView.getItemAtPosition(position);

        // обновляем список на экране
        robotsListViewAdapter.robots.remove(robot);
        robotsListViewAdapter.notifyDataSetChanged();

        // посылаем запрос на сервер об удалении
        robotDAO.delete(robot);
    }

    /**
     * При выборе Add в меню добавляем нового робота
     */
    public void menuAddClicked() {
        // вызываем диалоговое окно добавления робота
        RobotDialogFragment addRobotDialogFragment = new RobotDialogFragment();
        addRobotDialogFragment.setDialogType(RobotDialogFragment.DialogType.ADD_ROBOT);
        addRobotDialogFragment.show(getFragmentManager(), "");
    }

    /**
     * При нажатии на кнопку Confirm диалогового окна добавления робота, добавляем робота.
     * @param view
     */
    public void dialogAddRobotConfirmed(View view) {
        // получаем данные из диалогового окна
        String name = ((EditText)view.findViewById(R.id.editTextName)).getText().toString();
        String type = ((Spinner)view.findViewById(R.id.spinnerType)).getSelectedItem().toString();
        String year = ((EditText)view.findViewById(R.id.editTextYear)).getText().toString();
        Robot newRobot = new Robot(name, type, Integer.parseInt(year));

        // добавляем робота в список на экране
        robotsListViewAdapter.robots.add(newRobot);
        robotsListViewAdapter.notifyDataSetChanged();

        // посылаем запрос на сервер о добавлении робота
        robotDAO.create(newRobot);
    }

    /**
     * При нажатии на кнопку Edit редактируем данные выбранного робота.
     * @param view
     */
    public void buttonEditClicked(View view) {
        // берем выбранного робота
        int position = robotsListView.getPositionForView((View) view.getParent());
        Robot robot = (Robot)robotsListView.getItemAtPosition(position);

        // показываем диалог редактирования робота
        RobotDialogFragment editRobotDialogFragment = new RobotDialogFragment();
        editRobotDialogFragment.setDialogType(RobotDialogFragment.DialogType.EDIT_ROBOT);
        editRobotDialogFragment.setRobot(robot);
        editRobotDialogFragment.show(getFragmentManager(), "");
    }

    /**
     * При нажатии на кнопку Confirm диалогового окна редактирования робота, редактируем робота.
     * @param view
     */
    public void dialogEditRobotConfirmed(View view) {
        // получаем данные из диалогового окна
        int id = Integer.parseInt(((EditText) view.findViewById(R.id.editTextId)).getText().toString());
        String name = ((EditText)view.findViewById(R.id.editTextName)).getText().toString();
        String type = ((Spinner)view.findViewById(R.id.spinnerType)).getSelectedItem().toString();
        int year = Integer.parseInt(((EditText) view.findViewById(R.id.editTextYear)).getText().toString());

        // изменяем робота на экране
        for (Robot robot : robotsListViewAdapter.robots) {
            if (robot.getId() == id) {
                robot.setName(name);
                robot.setType(type);
                robot.setYear(year);
                break;
            }
        }
        robotsListViewAdapter.notifyDataSetChanged();

        // посылаем запрос на сервер об изменении робота
        robotDAO.update(new Robot(id, name, type, year));
    }
}
