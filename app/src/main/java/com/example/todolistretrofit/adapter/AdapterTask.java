package com.example.todolistretrofit.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolistretrofit.ObjectJSON;
import com.example.todolistretrofit.activity.DetailActivity;
import com.example.todolistretrofit.activity.EditActivity;
import com.example.todolistretrofit.activity.MainActivity;
import com.example.todolistretrofit.R;
import com.example.todolistretrofit.api.APIRequestData;
import com.example.todolistretrofit.api.ApiAccess;
import com.example.todolistretrofit.api.RetroServer;
import com.example.todolistretrofit.base_model.Task;
import com.example.todolistretrofit.response_model.ResponseAdd;
import com.example.todolistretrofit.response_model.ResponseDelete;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterTask extends RecyclerView.Adapter<AdapterTask.HolderData> {
    private Context context;
    private List<Task> taskList;
    private Boolean checked;

    public AdapterTask(Context context, List<Task> taskList, Boolean checked) {
        this.context = context;
        this.checked = checked;
        this.taskList = taskList;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        HolderData holderData = new HolderData(layout);
        return holderData;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        Task task = taskList.get(position);
        holder.tv_id.setText(String.valueOf(task.getId()));
        holder.tv_title.setText(task.getTitle());
        holder.tv_description.setText(task.getDescription());

    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class HolderData extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_title;
        TextView tv_id;
        TextView tv_description;
        MaterialCheckBox checkBox;
        MaterialButton edit_but;
        MaterialButton del_but;
        private String id;
        private int check = 0;

        public HolderData(@NonNull View itemView) {
            super(itemView);
            tv_id = itemView.findViewById(R.id.tv_id);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_description = itemView.findViewById(R.id.tv_description);
            checkBox = itemView.findViewById(R.id.check_item);
            del_but = itemView.findViewById(R.id.button_delete);
            edit_but = itemView.findViewById(R.id.button_edit);

            checkBox.setChecked(checked);

            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onChecked();
                }
            });

            edit_but.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onEdit();
                }
            });

            del_but.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onDelete();
                }
            });

            itemView.setOnClickListener(this);

        }

        private void onDelete() {
            id = (String) tv_id.getText();
            ApiAccess apiAccess = new ApiAccess();

            apiAccess.deleteData(id, context);
            Intent intent = new Intent(context, MainActivity.class);
            context.startActivity(intent);
        }

        private void onEdit() {
            String title = tv_title.getText().toString();
            String description = tv_description.getText().toString();
            id = (String) tv_id.getText();

            Intent intent = new Intent(context, EditActivity.class);
            intent.putExtra("id", id);
            intent.putExtra("title", title);
            intent.putExtra("description", description);
            if (checked){
                check = 1;
            }
            intent.putExtra("check", check);
            context.startActivity(intent);
        }

        private void onChecked() {
            id = (String) tv_id.getText();

            if (!checked){
                check = 1;
            }

            Task task = new Task(tv_title.getText().toString(), tv_description.getText().toString(), check);
            ObjectJSON objectJSON = new ObjectJSON();
            ApiAccess apiAccess = new ApiAccess();

            apiAccess.editData(objectJSON.getApiJsonMap(task), id, context);
            Intent intent = new Intent(context, MainActivity.class);
            context.startActivity(intent);
        }

        @Override
        public void onClick(View v) {

            String title = tv_title.getText().toString();
            String description = tv_description.getText().toString();
            String status = "Finished";

            if (!checked) {
                status = "Not Finished";
            }


            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("title", title);
            intent.putExtra("description", description);
            intent.putExtra("status", status);
            context.startActivity(intent);

        }


    }

}
