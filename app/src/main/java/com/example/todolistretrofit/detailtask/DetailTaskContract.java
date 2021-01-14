package com.example.todolistretrofit.detailtask;

import android.content.Context;
import android.content.Intent;

import com.example.todolistretrofit.base.BasePresenter;
import com.example.todolistretrofit.base.BaseView;
import com.example.todolistretrofit.model.Task;

public class DetailTaskContract {

    interface View extends BaseView<DetailTaskContract.Presenter> {
        void showData(Task task);
        void onShare(Intent intent);
    }

    interface Presenter extends BasePresenter {
        void loadData(String id);
        void setShareTask();
    }

}
