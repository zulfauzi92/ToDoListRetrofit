package com.example.todolistretrofit;

import android.util.Log;

import com.example.todolistretrofit.base_model.Task;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

public class ObjectJSON {

    private JSONObject jsonObject;

    public ObjectJSON() {
    }

    public JsonObject getApiJsonMap(Task task) {

        JsonObject gsonObject = new JsonObject();
        try {
            jsonObject = new JSONObject();
            jsonObject.put("title", task.getTitle());
            jsonObject.put("description", task.getDescription());
            jsonObject.put("cheked", task.getCheked());

            JsonParser jsonParser = new JsonParser();
            gsonObject = (JsonObject) jsonParser.parse(jsonObject.toString());

            //print parameter
            Log.e("MY gson.JSON:  ", "AS PARAMETER  " + gsonObject);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObject gsonObject2 = new JsonObject();
        try {
            JSONObject task2 = new JSONObject();
            task2.put("task", this.jsonObject);

            JsonParser jsonParser = new JsonParser();
            gsonObject2 = (JsonObject) jsonParser.parse(task2.toString());

            //print parameter
            Log.e("MY gson.JSON:  ", "AS PARAMETER  " + gsonObject2);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return gsonObject2;
    }

}
