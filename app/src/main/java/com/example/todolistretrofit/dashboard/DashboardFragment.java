package com.example.todolistretrofit.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.todolistretrofit.R;
import com.example.todolistretrofit.addtask.AddActivity;
import com.example.todolistretrofit.adapter.AdapterTask;
import com.example.todolistretrofit.api.APIRequestData;
import com.example.todolistretrofit.api.RetroServer;
import com.example.todolistretrofit.detailtask.DetailTaskContract;
import com.example.todolistretrofit.model.Task;
import com.example.todolistretrofit.response_model.ResponseData;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardFragment extends Fragment implements DashboardContract.View {

    private View view;
    private RecyclerView recyclerViewData;
    private RecyclerView.Adapter adapterData;
    private RecyclerView.LayoutManager layoutManagerData;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ProgressBar progressBar;
    private FloatingActionButton floatingActionButton;
    private int status=0;
    private DashboardContract.Presenter dashboardPresenter;

    public DashboardFragment(int status){
        this.status = status;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_unchecked, container, false);

        initialize();
        dashboardPresenter = new DashboardPresenter(this);
        dashboardPresenter.start();

        swipeRefreshLayout.setOnRefreshListener(this::onRefresh);
        floatingActionButton.setOnClickListener(this::onClick);

        return view;
    }

    private void onClick(View view) {
        Intent intent = new Intent(view.getContext(), AddActivity.class);
        startActivity(intent);
    }

    private void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        showData();
        swipeRefreshLayout.setRefreshing(false);
    }

    private void initialize() {
        swipeRefreshLayout = view.findViewById(R.id.swl_dashboard);
        progressBar = view.findViewById(R.id.pb_dashboard);
        recyclerViewData = view.findViewById(R.id.rv_dashboard);
        floatingActionButton = view.findViewById(R.id.add_task);
        layoutManagerData = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerViewData.setLayoutManager(layoutManagerData);
        if (status == 1) {
            floatingActionButton.setVisibility(View.GONE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        showData();
    }


    private void showData() {

        progressBar.setVisibility(View.VISIBLE);

        dashboardPresenter.loadList();
        progressBar.setVisibility(View.INVISIBLE);

    }

    private List<Task> checkTask(List<Task> tasks, int status) {
        List<Task> temp = new ArrayList<>();

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getCheked() == status) {
                temp.add(tasks.get(i));
            }
        }

        return temp;

    }

    @Override
    public void showList(List<Task> task) {

        List<Task> taskList =  new ArrayList<>();
        Boolean checked = false;

        taskList = checkTask(task, status);

        if (status == 1) {
            checked = true;
        }
        adapterData = new AdapterTask(view.getContext(), taskList, checked);
        recyclerViewData.setAdapter(adapterData);
        adapterData.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(DashboardContract.Presenter presenter) {
        dashboardPresenter = presenter;
    }
}
