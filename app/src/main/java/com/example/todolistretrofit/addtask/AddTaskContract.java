package com.example.todolistretrofit.addtask;

import android.content.Context;

import com.example.todolistretrofit.base.BasePresenter;
import com.example.todolistretrofit.base.BaseView;

public class AddTaskContract {
    interface View extends BaseView<Presenter> {
        void redirectToDashboard();
    }

    interface Presenter extends BasePresenter {
        void addData(String title, String description, Context context);
    }
}
