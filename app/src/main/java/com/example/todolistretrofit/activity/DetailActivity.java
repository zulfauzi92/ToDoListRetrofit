package com.example.todolistretrofit.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.todolistretrofit.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

public class DetailActivity extends AppCompatActivity {

    private String title;
    private String description;
    private String status;
    private MaterialTextView tv_title;
    private MaterialTextView tv_description;
    private MaterialTextView tv_status;
    private MaterialButton share_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        tv_title = findViewById(R.id.title_detail);
        tv_description = findViewById(R.id.description_detail);
        share_btn = findViewById(R.id.share_btn);
        tv_status = findViewById(R.id.tv_status);

        Intent intent = getIntent();

        title = intent.getStringExtra("title");
        description = intent.getStringExtra("description");
        status = intent.getStringExtra("status");

        tv_title.setText(title);
        tv_description.setText(description);
        tv_status.setText("Status : " + status);


    }


}