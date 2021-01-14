package com.example.todolistretrofit.detailtask;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.todolistretrofit.R;
import com.example.todolistretrofit.edittask.EditTaskContract;
import com.example.todolistretrofit.model.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

public class DetailActivity extends AppCompatActivity implements DetailTaskContract.View{

    private String id;
    private MaterialTextView tv_title;
    private MaterialTextView tv_description;
    private MaterialTextView tv_status;
    private MaterialButton share_btn;
    private DetailTaskContract.Presenter detailTaskPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        detailTaskPresenter = new DetailTaskPresenter(this);
        detailTaskPresenter.start();

        initialize();
        dataFromIntent();

        detailTaskPresenter.loadData(id);

        share_btn.setOnClickListener(this::onShare);

    }

    private void onShare(View view) {
        detailTaskPresenter.setShareTask();
    }

    private void dataFromIntent() {
        Intent intent = getIntent();

        id = intent.getStringExtra("id");

    }

    private void initialize() {
        tv_title = findViewById(R.id.title_detail);
        tv_description = findViewById(R.id.description_detail);
        share_btn = findViewById(R.id.share_btn);
        tv_status = findViewById(R.id.tv_status);
    }


    @Override
    public void showData(Task task) {
        String status = "Finished";

        tv_title.setText(task.getTitle());
        tv_description.setText(task.getDescription());;
        if (task.getCheked() == 0){
            status = "Not Finished";
        }
        tv_status.setText("Status : " + status);

    }

    @Override
    public void onShare(Intent intent) {
        startActivity(Intent.createChooser(intent, "Share Task via"));
    }

    @Override
    public void setPresenter(DetailTaskContract.Presenter presenter) {
        detailTaskPresenter = presenter;
    }
}