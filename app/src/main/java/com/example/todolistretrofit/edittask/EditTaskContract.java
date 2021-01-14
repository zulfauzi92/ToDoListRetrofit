package com.example.todolistretrofit.edittask;

import android.content.Context;

import com.example.todolistretrofit.base.BasePresenter;
import com.example.todolistretrofit.base.BaseView;
import com.example.todolistretrofit.model.Task;

public class EditTaskContract {
    interface View extends BaseView<EditTaskContract.Presenter> {
        void redirectToDashboard();
        void showData(Task task);
    }

    interface Presenter extends BasePresenter {
        void saveData(String title, String description, Context context);
        void loadData(String id);
    }
}
