package com.dorofeyev.robotscrud;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.IOException;
import java.util.List;

/**
 * Created by xor on 7/20/15.
 */
public class RobotDAO implements IRobotDAO {
    @Override
    public void create(Robot robot) throws Exception {

    }

    @Override
    public List<Robot> read() throws Exception {

        RobotRestClient.get("", null, new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                AsyncHttpClient.log.w("JsonHttpRH", "onSuccess(int, Header[], JSONObject) was not overriden, but callback was received");
            }

            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                String str = "";
                Robot robot = null;
                ObjectMapper mapper = new ObjectMapper();

                try {
                    str = response.getString(0);
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }

                try {
                    robot = mapper.readValue(str, Robot.class);
                    int y = 7+7;
                } catch (IOException e) {
                    e.printStackTrace();
                }

                int robotid = robot.getId();
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






        return null;
    }

    @Override
    public void update(Robot robot) throws Exception {

    }

    @Override
    public void delete(Robot robot) throws Exception {

    }

    @Override
    public Robot fetchById(int id) throws Exception {
        return null;
    }
}
