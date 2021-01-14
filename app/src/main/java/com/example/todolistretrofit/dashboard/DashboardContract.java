package com.example.todolistretrofit.dashboard;

import android.content.Intent;

import com.example.todolistretrofit.base.BasePresenter;
import com.example.todolistretrofit.base.BaseView;
import com.example.todolistretrofit.model.Task;

import java.util.List;

public class DashboardContract {
    interface View extends BaseView<DashboardContract.Presenter> {
        void showList(List<Task> task);
    }

    interface Presenter extends BasePresenter {
        void loadList();
    }
}
