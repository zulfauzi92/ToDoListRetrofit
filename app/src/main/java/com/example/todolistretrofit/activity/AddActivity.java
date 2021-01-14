package com.example.todolistretrofit.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.todolistretrofit.model.ObjectJSON;
import com.example.todolistretrofit.R;
import com.example.todolistretrofit.api.ApiAccess;
import com.example.todolistretrofit.model.Task;
import com.google.android.material.button.MaterialButton;

public class AddActivity extends AppCompatActivity {

    private EditText et_title;
    private EditText et_description;
    private MaterialButton addButton;
    private TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        initialize();

        addButton.setOnClickListener(this::addData);

    }

    private void initialize() {
        tv_title = findViewById(R.id.tv_titleEdit);
        et_title = findViewById(R.id.et_title);
        et_description = findViewById(R.id.et_description);
        addButton = findViewById(R.id.button_add);

        tv_title.setText("Add Task");
    }


    private void addData(View view) {
        String title =et_title.getText().toString();
        String description =et_description.getText().toString();

        if (title.trim().isEmpty()){
            et_title.setError("title empty !");
        } else if (description.trim().isEmpty()) {
            et_description.setError("description empty !");
        } else {

            Task task = new Task(title, description, 0);
            ObjectJSON objectJSON = new ObjectJSON();
            ApiAccess apiAccess = new ApiAccess();

            apiAccess.addData(objectJSON.getApiJsonMap(task), AddActivity.this);
            Intent intent = new Intent(AddActivity.this, MainActivity.class);
            startActivity(intent);

        }
    }

}