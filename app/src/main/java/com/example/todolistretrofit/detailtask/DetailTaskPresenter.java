package com.example.todolistretrofit.detailtask;

import android.content.Intent;

import com.example.todolistretrofit.api.APIRequestData;
import com.example.todolistretrofit.api.RetroServer;
import com.example.todolistretrofit.model.Task;
import com.example.todolistretrofit.response_model.ResponseAdd;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailTaskPresenter implements DetailTaskContract.Presenter{

    private Task editedTask;
    private final DetailTaskContract.View view;

    public DetailTaskPresenter(DetailTaskContract.View view) {
        this.view = view;
    }

    @Override
    public void loadData(String id) {
        APIRequestData apiRequestData = RetroServer.koneksiRetrofit().create(APIRequestData.class);
        Call<ResponseAdd> responseDataCall = apiRequestData.ardDetail(
                id
        );

        responseDataCall.enqueue(new Callback<ResponseAdd>() {
            @Override
            public void onResponse(Call<ResponseAdd> call, Response<ResponseAdd> response) {
                editedTask = response.body().getData();
                view.showData(editedTask);
            }

            @Override
            public void onFailure(Call<ResponseAdd> call, Throwable t) {

            }
        });
    }

    @Override
    public void setShareTask() {

        String status = "Not Finished";

        if (editedTask.getCheked() == 1){
            status = "Finished";
        }

        Intent mSharingIntent = new Intent(Intent.ACTION_SEND);
        mSharingIntent.setType("text/plain");
        mSharingIntent.putExtra(Intent.EXTRA_SUBJECT, "My Task");
        mSharingIntent.putExtra(Intent.EXTRA_TEXT,
                "Title : " + editedTask.getTitle() +"\n" +
                        "Description : " + editedTask.getDescription() + "\n" +
                        "Status : " + status
        );

        view.onShare(mSharingIntent);

    }

    @Override
    public void start() {

    }
}
