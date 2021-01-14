package com.example.todolistretrofit.addtask;

import android.content.Context;

import com.example.todolistretrofit.api.ApiAccess;
import com.example.todolistretrofit.model.ObjectJSON;
import com.example.todolistretrofit.model.Task;

public class AddTaskPresenter implements AddTaskContract.Presenter{

    private final AddTaskContract.View view;

    public AddTaskPresenter(AddTaskContract.View view) {
        this.view = view;
    }

    @Override
    public void addData(String title, String description, Context context) {

        Task task = new Task(title, description, 0);
        ObjectJSON objectJSON = new ObjectJSON();
        ApiAccess apiAccess = new ApiAccess();

        apiAccess.addData(objectJSON.getApiJsonMap(task), context);
        view.redirectToDashboard();
    }

    @Override
    public void start() {

    }
}
