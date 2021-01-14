package com.example.todolistretrofit.dashboard;

import android.view.View;
import android.widget.Toast;

import com.example.todolistretrofit.adapter.AdapterTask;
import com.example.todolistretrofit.api.APIRequestData;
import com.example.todolistretrofit.api.RetroServer;
import com.example.todolistretrofit.model.Task;
import com.example.todolistretrofit.response_model.ResponseData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardPresenter implements DashboardContract.Presenter{

    private List<Task> tasks;
    private final DashboardContract.View view;

    public DashboardPresenter(DashboardContract.View view) {
        this.view = view;
        tasks = new ArrayList<>();
    }

    @Override
    public void loadList() {
        APIRequestData apiRequestData = RetroServer.koneksiRetrofit().create(APIRequestData.class);
        Call<ResponseData> responseDataCall = apiRequestData.ardTask();

        responseDataCall.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {

                tasks = response.body().getData();
                view.showList(tasks);
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {

            }
        });
    }

    @Override
    public void start() {

    }
}
