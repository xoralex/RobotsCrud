package com.dorofeyev.robotscrud;

import android.content.Context;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xor on 7/20/15.
 * Класс Data Access Object для взаимодействия с данными.
 * Данные хранятся на сервере. Взаимодействие осуществляется через REST API.
 */
public class RestRobotDAO implements RobotDAO {

    private ObjectMapper mapper = new ObjectMapper();

    private Context context;

    public RestRobotDAO(Context context) {
        this.context = context;
    }

    /**
     * Запрашиваем список роботов с сервера
     * @param callback ответ отправляем в качестве параметра методу callback.execute(Object o)
     */
    @Override
    public void read(final Callback callback)  {

        RobotRestClient.get("", null, new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {}

            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                try {
                    List<Robot> robots = new ArrayList<Robot>();
                    for (int i=0; i<response.length(); i++) {
                        robots.add(mapper.readValue(response.get(i).toString(), Robot.class));
                    }
                    callback.execute(robots);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {}
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {}
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {}
            public void onSuccess(int statusCode, Header[] headers, String responseString) {}
        });

    }

    /**
     * Создаем нового робота на сервере
     * @param robot робот которого нужно создать
     */
    @Override
    public void create(Robot robot)  {

        // Переводим робота в формат JSON
        String robotJson = null;
        try {
            robotJson = mapper.writeValueAsString(robot);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        // отправляем запрос на сервер
        RobotRestClient.post("", robotJson, context, new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {}
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {}
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {}
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {}
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {}
            public void onSuccess(int statusCode, Header[] headers, String responseString) {}
        });
    }

    /**
     * Обновляем робота на сервере
     * @param robot робот данные которого нужно обновить
     */
    @Override
    public void update(Robot robot) {
        // Переводим робота в формат JSON
        String robotJson = null;
        try {
            robotJson = mapper.writeValueAsString(robot);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        String id = Integer.toString(robot.getId());

        RobotRestClient.put(id, robotJson, context, new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {}
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {}
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {}
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {}
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {}
            public void onSuccess(int statusCode, Header[] headers, String responseString) {}
        });
    }

    /**
     * Удаляем робота с сервера
     * @param robot робот которого нужно удалить
     */
    @Override
    public void delete(Robot robot) {
        String id = Integer.toString(robot.getId());
        RobotRestClient.delete(id, null, new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {}
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {}
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {}
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {}
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {}
            public void onSuccess(int statusCode, Header[] headers, String responseString) {}
        });
    }

    /**
     * Получаем робота по айди. Не реализовано.
     * @param id айди робота
     * @return возвращаем робота соответсвующего айди
     */
    @Override
    public Robot fetchById(int id) {
        return null;
    }
}
