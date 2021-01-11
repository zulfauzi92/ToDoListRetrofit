package com.example.todolistretrofit.fragment;

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
import com.example.todolistretrofit.activity.AddActivity;
import com.example.todolistretrofit.adapter.AdapterTask;
import com.example.todolistretrofit.api.APIRequestData;
import com.example.todolistretrofit.api.RetroServer;
import com.example.todolistretrofit.base_model.Task;
import com.example.todolistretrofit.response_model.ResponseData;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaskFragment extends Fragment {

    private View view;
    private RecyclerView recyclerViewData;
    private RecyclerView.Adapter adapterData;
    private RecyclerView.LayoutManager layoutManagerData;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ProgressBar progressBar;
    private FloatingActionButton floatingActionButton;
    private int status=0;

    public TaskFragment(int status){
        this.status = status;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_unchecked, container, false);

        swipeRefreshLayout = view.findViewById(R.id.swl_dashboard);
        progressBar = view.findViewById(R.id.pb_dashboard);
        recyclerViewData = view.findViewById(R.id.rv_dashboard);
        floatingActionButton = view.findViewById(R.id.add_task);
        layoutManagerData = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerViewData.setLayoutManager(layoutManagerData);

        if (status == 1) {
            floatingActionButton.setVisibility(View.GONE);
        }

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                showData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), AddActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        showData();
    }


    private void showData() {

        progressBar.setVisibility(View.VISIBLE);


        APIRequestData apiRequestData = RetroServer.koneksiRetrofit().create(APIRequestData.class);
        Call<ResponseData> responseDataCall = apiRequestData.ardTask();

        responseDataCall.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {

                List<Task> taskList =  new ArrayList<>();
                Boolean checked = false;

                taskList = notChecked(response.body().getData(), status);

                if (status == 1) {
                    checked = true;
                }

                adapterData = new AdapterTask(view.getContext(), taskList, checked);
                recyclerViewData.setAdapter(adapterData);
                adapterData.notifyDataSetChanged();

                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                Toast.makeText(view.getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

                progressBar.setVisibility(View.INVISIBLE);
            }
        });

    }

    private List<Task> notChecked(List<Task> tasks, int status) {
        List<Task> temp = new ArrayList<>();

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getCheked() == status) {
                temp.add(tasks.get(i));
            }
        }

        return temp;

    }

}
