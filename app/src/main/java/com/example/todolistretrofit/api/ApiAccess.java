package com.example.todolistretrofit.api;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.todolistretrofit.activity.AddActivity;
import com.example.todolistretrofit.response_model.ResponseAdd;
import com.example.todolistretrofit.response_model.ResponseDelete;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiAccess  {

    public ApiAccess() {
    }

    public void editData (JsonObject task, String id, Context context) {

        APIRequestData apiRequestData = RetroServer.koneksiRetrofit().create(APIRequestData.class);
        Call<ResponseAdd> responseEditCall = apiRequestData.ardEdit(
                id,
                task
        );

        responseEditCall.enqueue(new Callback<ResponseAdd>() {
            @Override
            public void onResponse(Call<ResponseAdd> call, Response<ResponseAdd> response) {
                Toast.makeText(context, response.body().getStatus(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseAdd> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



    }

    public void deleteData (String id, Context context) {

        APIRequestData apiRequestData = RetroServer.koneksiRetrofit().create(APIRequestData.class);
        Call<ResponseDelete> responseDataCall = apiRequestData.ardDelete(
                id
        );


        responseDataCall.enqueue(new Callback<ResponseDelete>() {
            @Override
            public void onResponse(Call<ResponseDelete> call, Response<ResponseDelete> response) {
                Toast.makeText(context, response.body().getStatus(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseDelete> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void addData (JsonObject task, Context context) {

        APIRequestData apiRequestData = RetroServer.koneksiRetrofit().create(APIRequestData.class);
        Call<ResponseAdd> responseDataCall = apiRequestData.ardAdd(
                task
        );

        responseDataCall.enqueue(new Callback<ResponseAdd>() {
            @Override
            public void onResponse(Call<ResponseAdd> call, Response<ResponseAdd> response) {
                Toast.makeText(context, response.body().getStatus(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseAdd> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}
