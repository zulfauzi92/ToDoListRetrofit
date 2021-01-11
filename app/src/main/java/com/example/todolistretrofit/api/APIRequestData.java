package com.example.todolistretrofit.api;

import com.example.todolistretrofit.response_model.ResponseAdd;
import com.example.todolistretrofit.response_model.ResponseData;
import com.example.todolistretrofit.response_model.ResponseDelete;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface APIRequestData {


    @GET("task")
    Call<ResponseData> ardTask();

    @DELETE("task/{id}")
    Call<ResponseDelete> ardDelete(
            @Path("id") String id
    );


    @POST("task")
    Call<ResponseAdd> ardAdd(
      @Body JsonObject task
    );

    @PUT("task/{id}")
    Call<ResponseAdd> ardEdit(
       @Path("id") String id,
       @Body JsonObject task
    );


}
