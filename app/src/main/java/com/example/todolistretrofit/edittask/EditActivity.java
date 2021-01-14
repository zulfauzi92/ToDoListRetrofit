package com.example.todolistretrofit.edittask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.todolistretrofit.dashboard.DashboardActivity;
import com.example.todolistretrofit.R;
import com.example.todolistretrofit.model.Task;
import com.google.android.material.button.MaterialButton;

public class EditActivity extends AppCompatActivity implements EditTaskContract.View{

    private EditText et_title;
    private EditText et_description;
    private MaterialButton editButton;
    private TextView tv_title;
    private String id;
    private EditTaskContract.Presenter editTaskPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        editTaskPresenter = new EditTaskPresenter(this);
        editTaskPresenter.start();

        initialize();
        dataFromIntent();

        editTaskPresenter.loadData(id);

        editButton.setOnClickListener(this::onEdit);

    }

    private void dataFromIntent() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
    }

    private void initialize() {
        tv_title = findViewById(R.id.tv_titleEdit);
        et_title = findViewById(R.id.et_title);
        et_description = findViewById(R.id.et_description);
        editButton = findViewById(R.id.button_add);

        tv_title.setText("Edit Task");
    }

    private void onEdit(View view) {
        String title =et_title.getText().toString();
        String description =et_description.getText().toString();

        if (title.trim().isEmpty()){
            et_title.setError("title empty !");
        } else if (description.trim().isEmpty()) {
            et_description.setError("description empty !");
        } else {
            editTaskPresenter.saveData(title, description, this);
        }
    }

    @Override
    public void redirectToDashboard() {
        Intent intent = new Intent(EditActivity.this, DashboardActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showData(Task task) {
        this.et_title.setText(task.getTitle());
        this.et_description.setText(task.getDescription());
    }


    @Override
    public void setPresenter(EditTaskContract.Presenter presenter) {
        editTaskPresenter = presenter;
    }
}