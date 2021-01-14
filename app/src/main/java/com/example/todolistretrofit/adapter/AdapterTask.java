package com.example.todolistretrofit.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolistretrofit.model.ObjectJSON;
import com.example.todolistretrofit.detailtask.DetailActivity;
import com.example.todolistretrofit.edittask.EditActivity;
import com.example.todolistretrofit.dashboard.DashboardActivity;
import com.example.todolistretrofit.R;
import com.example.todolistretrofit.api.ApiAccess;
import com.example.todolistretrofit.model.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.checkbox.MaterialCheckBox;

import java.util.List;

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
        private TextView tv_title;
        private TextView tv_id;
        private TextView tv_description;
        private MaterialCheckBox checkBox;
        private MaterialButton edit_but;
        private MaterialButton del_but;
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

            checkBox.setOnClickListener(this::onChecked);

            edit_but.setOnClickListener(this::onEdit);

            del_but.setOnClickListener(this::onDelete);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            id = (String) tv_id.getText();

            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("id", id);
            context.startActivity(intent);

        }

        private void onDelete(View view) {
            id = (String) tv_id.getText();
            ApiAccess apiAccess = new ApiAccess();

            apiAccess.deleteData(id, context);
            Intent intent = new Intent(context, DashboardActivity.class);
            context.startActivity(intent);
        }

        private void onEdit(View view) {

            id = (String) tv_id.getText();

            Intent intent = new Intent(context, EditActivity.class);
            intent.putExtra("id", id);
            context.startActivity(intent);
        }

        private void onChecked(View view) {
            id = (String) tv_id.getText();

            if (!checked){
                check = 1;
            }

            Task task = new Task(tv_title.getText().toString(), tv_description.getText().toString(), check);
            ObjectJSON objectJSON = new ObjectJSON();
            ApiAccess apiAccess = new ApiAccess();

            apiAccess.editData(objectJSON.getApiJsonMap(task), id, context);
            Intent intent = new Intent(context, DashboardActivity.class);
            context.startActivity(intent);
        }

    }

}
