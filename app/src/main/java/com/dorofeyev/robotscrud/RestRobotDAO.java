package com.dorofeyev.robotscrud;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;


import java.io.IOException;
import java.util.List;

/**
 * Created by xor on 7/20/15.
 */
public class RestRobotDAO implements RobotDAO {
    @Override
    public void create(Robot robot)  {

    }

    @Override
    public List<Robot> read(final Callback callback)  {


        RobotRestClient.get("", null, new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                AsyncHttpClient.log.w("JsonHttpRH", "onSuccess(int, Header[], JSONObject) was not overriden, but callback was received");
            }

            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                ObjectMapper mapper = new ObjectMapper();

                try {
                    List<Robot> robots = null;
                    robots = mapper.readValue(response.toString(), List.class);
                    callback.execute(robots);
                } catch (IOException e) {
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

            int x = 5+5;

        });






        return null;
    }

    @Override
    public void update(Robot robot) {

    }

    @Override
    public void delete(Robot robot) {

    }

    @Override
    public Robot fetchById(int id) {
        return null;
    }
}
