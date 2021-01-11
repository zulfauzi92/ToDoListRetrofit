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

        tv_title = findViewById(R.id.tv_titleEdit);
        et_title = findViewById(R.id.et_title);
        et_description = findViewById(R.id.et_description);
        editButton = findViewById(R.id.button_add);


        tv_title.setText("Edit Task");
        Intent intent = getIntent();
        check = intent.getIntExtra("check", 0);

        id = intent.getStringExtra("id");
        et_title.setText(intent.getStringExtra("title"));
        et_description.setText(intent.getStringExtra("description"));


        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        });

    }

}