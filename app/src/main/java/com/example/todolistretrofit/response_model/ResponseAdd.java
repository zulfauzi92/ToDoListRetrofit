package com.example.todolistretrofit.response_model;

import com.example.todolistretrofit.base_model.Task;

public class ResponseAdd {

    private String status;
    private Task task;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
