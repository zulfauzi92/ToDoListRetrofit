package com.example.todolistretrofit.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.todolistretrofit.ObjectJSON;
import com.example.todolistretrofit.R;
import com.example.todolistretrofit.api.ApiAccess;
import com.example.todolistretrofit.model.Task;
import com.google.android.material.button.MaterialButton;

import org.json.JSONObject;

public class EditActivity extends AppCompatActivity {

    private EditText et_title;
    private EditText et_description;
    private MaterialButton editButton;
    private JSONObject task;
    private TextView tv_title;
    private String id;
    private int check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        initialize();
        dataFromIntent();

        editButton.setOnClickListener(this::onEdit);

    }

    private void onEdit(View view) {
        String title =et_title.getText().toString();
        String description =et_description.getText().toString();

        if (title.trim().isEmpty()){
            et_title.setError("title empty !");
        } else if (description.trim().isEmpty()) {
            et_description.setError("description empty !");
        } else {

            Task task = new Task(title, description, check);
            ObjectJSON objectJSON = new ObjectJSON();
            ApiAccess apiAccess = new ApiAccess();

            apiAccess.editData(objectJSON.getApiJsonMap(task), id, EditActivity.this);
            Intent intent = new Intent(EditActivity.this, MainActivity.class);
            startActivity(intent);

        }
    }

    private void dataFromIntent() {
        Intent intent = getIntent();
        check = intent.getIntExtra("check", 0);

        id = intent.getStringExtra("id");
        et_title.setText(intent.getStringExtra("title"));
        et_description.setText(intent.getStringExtra("description"));
    }

    private void initialize() {
        tv_title = findViewById(R.id.tv_titleEdit);
        et_title = findViewById(R.id.et_title);
        et_description = findViewById(R.id.et_description);
        editButton = findViewById(R.id.button_add);

        tv_title.setText("Edit Task");
    }

}