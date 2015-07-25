package com.dorofeyev.robotscrud;

import android.content.Context;

import com.loopj.android.http.*;

import org.apache.http.entity.StringEntity;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * Created by xor on 7/20/15.
 * Класс облегчающий взаимодействие с android-async-http.
 * Позволяет отправлять разные виды http запросов.
 */
public class RobotRestClient {

    private static final String BASE_URL = "http://frontend.test.pleaple.com/api/robots/";
    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }

    private static AsyncHttpClient client = new AsyncHttpClient();


    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, String robotJson, Context context, AsyncHttpResponseHandler responseHandler) {

        StringEntity entity = null;
        try {
            entity = new StringEntity(robotJson);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        client.post(context, getAbsoluteUrl(url), entity, "application/json", responseHandler);
    }

    public static void put(String url, String robotJson, Context context, AsyncHttpResponseHandler responseHandler) {
        StringEntity entity = null;
        try {
            entity = new StringEntity(robotJson);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        client.put(context, getAbsoluteUrl(url), entity, "application/json", responseHandler);
    }

    public static void delete(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.delete(getAbsoluteUrl(url), params, responseHandler);
    }

}
