package com.dorofeyev.robotscrud;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.AsyncHttpClient;
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
 */
public class RestRobotDAO implements RobotDAO {
    @Override
    public void create(Robot robot)  {

    }

    @Override
    public void read(final Callback callback)  {

        RobotRestClient.get("", null, new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

            }

            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                ObjectMapper mapper = new ObjectMapper();

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

            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {

            }

            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {

            }

            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            public void onSuccess(int statusCode, Header[] headers, String responseString) {

            }

        });

    }

    @Override
    public void update(Robot robot) {

    }

    @Override
    public void delete(Robot robot) {
        String id = Integer.toString(robot.getId());
        RobotRestClient.delete(id, null, new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

            }

            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {

            }

            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {

            }

            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {

            }

            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            public void onSuccess(int statusCode, Header[] headers, String responseString) {

            }

        });
    }

    @Override
    public Robot fetchById(int id) {
        return null;
    }
}
