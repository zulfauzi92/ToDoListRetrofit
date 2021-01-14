package com.example.todolistretrofit.edittask;

import android.content.Context;

import com.example.todolistretrofit.api.APIRequestData;
import com.example.todolistretrofit.api.ApiAccess;
import com.example.todolistretrofit.api.RetroServer;
import com.example.todolistretrofit.model.ObjectJSON;
import com.example.todolistretrofit.model.Task;
import com.example.todolistretrofit.response_model.ResponseAdd;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditTaskPresenter implements EditTaskContract.Presenter{

    private final EditTaskContract.View view;
    private Task editedTask;

    public EditTaskPresenter(EditTaskContract.View view) {
        this.view = view;
    }

    @Override
    public void saveData(String title, String description, Context context) {

        Task task = new Task(title, description, editedTask.getCheked());

        ObjectJSON objectJSON = new ObjectJSON();
        ApiAccess apiAccess = new ApiAccess();

        apiAccess.editData(objectJSON.getApiJsonMap(task), String.valueOf(editedTask.getId()), context);

        view.redirectToDashboard();
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
    public void start() {

    }
}
