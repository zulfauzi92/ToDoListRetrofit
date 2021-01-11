package com.example.todolistretrofit.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todolistretrofit.ObjectJSON;
import com.example.todolistretrofit.R;
import com.example.todolistretrofit.api.APIRequestData;
import com.example.todolistretrofit.api.ApiAccess;
import com.example.todolistretrofit.api.RetroServer;
import com.example.todolistretrofit.base_model.Task;
import com.example.todolistretrofit.response_model.ResponseAdd;
import com.google.android.material.button.MaterialButton;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddActivity extends AppCompatActivity {

    private EditText et_title;
    private EditText et_description;
    private MaterialButton addButton;
    private TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        tv_title = findViewById(R.id.tv_titleEdit);
        et_title = findViewById(R.id.et_title);
        et_description = findViewById(R.id.et_description);
        addButton = findViewById(R.id.button_add);

        tv_title.setText("Add Task");

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        });

    }

}